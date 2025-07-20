package compiler.phase.asmgen;


import java.util.Vector;

import compiler.phase.imcgen.IMC;
import compiler.phase.imcgen.IMC.*;
import compiler.phase.memory.MEM;
import compiler.phase.asmgen.ASM.*;
import compiler.common.report.*;
import compiler.phase.imclin.*;


public class AsmGenerator implements IMC.Visitor<Object, AsmChunk> {
    ASM neki = new ASM();
    public AsmGenerator() {
        // Constructor
    }

    public AsmGenerator(Vector<LIN.DataChunk> dc, Vector<LIN.CodeChunk> chunk) {
        vseFunkcije = chunk;
        dataChunk = dc;
    }
	public Object visit(Vector<IMC.Instr> nodes, AsmChunk arg) {
        for (final IMC.Instr node : nodes)   {
            node.accept(this, arg);
		}
		return null;
	}

    public Vector<LIN.DataChunk> dataChunk;
    public Vector<LIN.CodeChunk> vseFunkcije;
    public static int aaaaa = 0;


    @Override
    public Object visit(IMC.CJUMP cjump, AsmChunk arg) {
        IMC.BINOP binop = (IMC.BINOP) cjump.cond;
        IMC.LABEL tLbl = new IMC.LABEL(((IMC.NAME)cjump.posAddr).label);
        IMC.LABEL fLbl = new IMC.LABEL(((IMC.NAME)cjump.negAddr).label);
        String lhs = binop.fstExpr.toString(); 
        String rhs = binop.sndExpr.toString();
        String labelTrue = cjump.posAddr.toString();             // true branch label
        String labelFalse = cjump.negAddr.toString();            // false branch label
        String instr;

        if(binop.fstExpr instanceof IMC.CONST z){
            IMC.TEMP tmp1 = new IMC.TEMP(new MEM.Temp());
            lhs = tmp1.temp.toString();
            Report.warning(lhs);
            arg.addLine(new RegisterAndValue(
                    "li", 
                    new Register(tmp1.temp),
                    z.value
                    ));
        }
        if(binop.sndExpr instanceof IMC.CONST z2){
            IMC.TEMP tmp1 = new IMC.TEMP(new MEM.Temp());
            rhs = tmp1.temp.toString();
            Report.warning(rhs);

            arg.addLine(new RegisterAndValue(
                "li", 
                new Register(tmp1.temp),
                z2.value
            ));
        }

        switch (binop.oper) {
        case AND : 
            IMC.TEMP x = new IMC.TEMP(new MEM.Temp());
            arg.addLine(
                new DoubleRegInstr
                ("and", 
                new Register(x),
                new Register(lhs), 
                new Register(rhs))
            );
            instr = String.format("and %s, %s, %s", x.toString(), lhs, rhs) + "\n";
            arg.addLine(
                new BreakIf
                ("bne", 
                new Register(x),
                ASM.zero,
                new Label(tLbl))
            );
            instr += String.format("bne %s, %s, %s", x.toString(), x.toString(), "x0");
            break;
        case OR:
            IMC.TEMP y = new IMC.TEMP(new MEM.Temp());
            arg.addLine(
                new DoubleRegInstr
                ("or", 
                new Register(y),
                new Register(lhs), 
                new Register(rhs))
            );
            instr = String.format("or %s, %s, %s", y.toString(), lhs, rhs) + "\n";
            arg.addLine(
                new BreakIf
                ("bne", 
                new Register(y),
                ASM.zero,
                new Label(tLbl)
                )
            );
            instr += String.format("bne %s, %s, %s", y.toString(), "x0", labelTrue);
            break;
        case EQU:
            arg.addLine(
                new BreakIf
                ("beq", 
                new Register(lhs),
                new Register(rhs),
                new Label(tLbl)
                )
            );
            instr = String.format("beq %s, %s, %s", lhs, rhs, labelTrue);
            break;
        case NEQ: 
            arg.addLine(
                new BreakIf
                ("bne", 
                new Register(lhs),
                new Register(rhs),
                new Label(tLbl)
                )
            );
            instr = String.format("bne %s, %s, %s", lhs, rhs, labelTrue);
            break;
        case LTH: 
            arg.addLine(
                new BreakIf
                ("blt", 
                new Register(lhs),
                new Register(rhs),
                new Label(tLbl)
                )
            );
            instr = String.format("blt %s, %s, %s", lhs, rhs, labelTrue);
            break;
        case LEQ: 
            arg.addLine(
                new BreakIf
                ("blt", 
                new Register(lhs),
                new Register(rhs),
                new Label(tLbl)
                )
            );
            arg.addLine(
                new BreakIf
                ("beq", 
                new Register(lhs),
                new Register(rhs),
                new Label(tLbl)
                )
            );
            instr = String.format("ble %s, %s, %s", lhs, rhs, labelTrue);
            break;
        case GTH: 
            arg.addLine(
                new BreakIf
                ("bgt", 
                new Register(lhs),
                new Register(rhs),
                new Label(tLbl)
                )
            );
            instr = String.format("bgt %s, %s, %s", lhs, rhs, labelTrue);
            break;
        case GEQ:
            arg.addLine(
                new BreakIf
                ("bgeu", 
                new Register(lhs),
                new Register(rhs),
                new Label(tLbl)
                )
            );
            instr = String.format("bge %s, %s, %s", lhs, rhs, labelTrue);
            break;
        default:

            throw new Report.InternalError("Unsupported binary operator in CJUMP.");
        }

        // Emit false jump after the conditional branch
        arg.addLine(
            new ASM.JumpAndLink
            ("jal", 
            ASM.zero,
            new Label(fLbl)
            )
        );
        String falseJump = String.format("jal x0,%s", labelFalse);

        return instr + "\n" + falseJump;
    }

    public String visit(IMC.ESTMT estmt, AsmChunk arg){
        if(estmt.expr instanceof IMC.CALL x) {
            if(x.addr instanceof IMC.NAME y) {
                IMC.TEMP x2 = new IMC.TEMP(new MEM.Temp());
                LIN.CodeChunk chunk2 = null;
                Report.warning(y.label.name);
                for(LIN.CodeChunk chunk : vseFunkcije) {
                    if(chunk.frame.label.name.equals(y.label.name)) {
                        chunk2 = chunk;
                        break;
                    }
                }
                MEM.Label lbl = y.label;
                String args = push(x.args, arg);
                StringBuilder sb = new StringBuilder();
                sb.append("###fixing sp###\n");
                arg.addLine(new Comment("fixing sp"));
                for(int i=0; i<x.args.size(); i++) {
                    arg.addLine(
                        new MathOperationWithValue
                        ("addi", 
                        ASM.sp,
                        ASM.zero,
                        -8L
                        )
                    );  

                    sb.append("addi sp, x0, 8\n");
                }
                arg.addLine(new Comment("fixed sp"));

                sb.append("###fixed sp###\n");
                arg.addLine(new JumpAndLink(
                        "jal",
                        ASM.zero,
                        new Label(new IMC.LABEL(lbl))
                    ));
                if(chunk2 == null){
                    arg.addLine(new ASM.Comment("outside function\n"));

                    return "#outside function\njal x0 " + lbl.name + "\n";
                }
                /*arg.addLine(new JumpAndLink(
                    "jal",
                    ASM.zero,
                    new Label(new IMC.LABEL(lbl))
                ));*/
                return 
                    args+
                    "jal " + 
                    lbl.name + 
                    "\n"+
                    "ld " + x2.temp.toString() + ", 0(x1)\n"+
                    sb.toString();
            }
        }
        return "";
    }

    @Override
    public String visit(IMC.JUMP jump, AsmChunk arg) {
        IMC.NAME x = (IMC.NAME)jump.addr;
        MEM.Label lbl = x.label;
        arg.addLine(new JumpAndLink(
            "jal",
            ASM.zero,
            new ASM.Label(new IMC.LABEL(x.label))
        ));

        return "jal x0, " + lbl.name + "\n";
    }

    public String pop(IMC.Expr b){
        return "TODO";
    }

    public String push(Vector<IMC.Expr> b, AsmChunk arg){
        
        StringBuilder sb = new StringBuilder();
        sb.append("#pushing args\n");
        for(IMC.Expr expr : b) {
            if(expr instanceof IMC.TEMP x) {
                // If argument is IMC.TEMP, push it to the stack
                {
                    arg.addLine(new RegisterAndOffset(
                        "sd", 
                        new Register(x), 
                        ASM.sp, 
                        0L
                    ));
                    sb.append("sd " + x.temp.toString() + ", 0(sp)\n");   
                }
            }else if(expr instanceof IMC.CONST x) {
                
                //If arguemnt is IMC.CONST, push it to the stack

                //Save the temp register value used for the constant
                {
                    sb.append("sd t2, -8(sp)\n");
                    arg.addLine(new RegisterAndOffset(
                        "sd", 
                        ASM.t2, 
                        ASM.sp, 
                        -8L
                    ));
                }

                //Load the constant value into the temp register and save it to the stack
                {
                    arg.addLine(new RegisterAndValue(
                    "li", 
                    ASM.t2,
                    x.value
                    ));


                    arg.addLine(new RegisterAndOffset(
                        "sd", 
                        ASM.t2,
                        ASM.sp,
                        0L
                    ));
                    sb.append("sd t2, 0(sp)\n");
                }

                //Load the old register value from the stack
                {
                    arg.addLine(new RegisterAndOffset(
                        "ld", 
                        ASM.t2, 
                        ASM.sp, 
                        -8L
                    ));
                    sb.append("ld t2, -8(sp)\n");
                }

            }else if(expr instanceof IMC.TEMP x) {
                MEM.Temp lbl = x.temp;

                arg.addLine(new RegisterAndOffset(
                    "sd",
                    new Register(x),
                    ASM.sp,
                    0L
                )
                );
                sb.append("sd " + lbl.toString() + ", 0(sp)\n");
            }


            //Increase the stack pointer 
            arg.addLine(new MathOperationWithValue(
                "addi", 
                ASM.sp,
                ASM.sp,
                -8L
            ));

            sb.append("addi sp, sp, -8\n");
        }
        sb.append("#finished pushing args\n");
        return sb.toString();
    }

    @Override
    public String visit(IMC.LABEL label, AsmChunk arg) {
        MEM.Label lbl = label.label;
        arg.addLine(new ASM.LabelLine(label));
        return lbl.name + "\t#this is in a new line, this should be in line with the documentation for labels in RISC-V  \n";
    }

    @Override
    public String visit(IMC.MOVE move, AsmChunk arg) {

        // Done
        if(move.dst instanceof IMC.TEMP x && move.src instanceof IMC.BINOP y){
            String src1 = y.fstExpr.toString();
            String src2 = y.sndExpr.toString();
            String dst = x.temp.toString();

            // Tx = Ty operacija Tz
            // aka shranjevanje operacije med dvema registroma
            if(y.fstExpr instanceof IMC.TEMP b && y.sndExpr instanceof IMC.TEMP c){ 
                src1 = b.temp.toString();
                src2 = c.temp.toString();
                switch(y.oper) {
                    case ADD: 
                        arg.addLine(new MathOperationWithReg(
                        "add",
                        new Register(x),
                        new Register(b),
                        new Register(c)
                        )) ;
                        return String.format("add %s, %s, %s", dst, src1, src2) + "\n";
                    case SUB:  
                        arg.addLine(new MathOperationWithReg(
                            "sub",
                            new Register(x),
                            new Register(b),
                            new Register(c)
                        ));
                        return String.format("sub %s, %s, %s", dst, src1, src2) + "\n";
                    case MUL:  
                        arg.addLine(new MathOperationWithReg(
                            "mul",
                            new Register(x),
                            new Register(b),
                            new Register(c)
                        )) ;
                        return String.format("mul %s, %s, %s", dst, src1, src2) + "\n";
                    case DIV: 
                        arg.addLine(new MathOperationWithReg(
                            "div",
                            new Register(x),
                            new Register(b),
                            new Register(c)
                        )) ;
                        return String.format("div %s, %s, %s", dst, src1, src2) + "\n";
                    default:
                        throw new Report.InternalError("Unsupported binary operator in MOVE.");
                }
            }
            
            // Move into dst from the register c with offset b2 into dst
            // aka ld dst, b2.value(c)
            if(y.fstExpr instanceof IMC.CONST b2 && y.sndExpr instanceof IMC.TEMP c){
                src2 = c.temp.toString();
                IMC.TEMP b3 = new IMC.TEMP(new MEM.Temp());
                switch(y.oper) {
                    case ADD:
                        arg.addLine(new RegisterAndOffset(
                            "ld",
                            new Register(x),
                            new Register(c),
                            b2.value
                        ));
                        return String.format("ld %s, %s, %s", dst, b2.value, src2) + "\n";
                    case SUB:
                        arg.addLine(new RegisterAndOffset(
                            "ld",
                            new Register(x),
                            new Register(c),
                            -b2.value
                        ));
                        String y12 = "addi " + b3 + ",x0" + ", "+ b2.value+"\n";
                        return y12+String.format("sub %s, %s, %s", dst, b3, src2) + "\n";
                    case MUL:

                        //String y11 = "addi " + b3 + ", x0," + b2.value+"\n";
                        //return y11+String.format("mul %s, %s, %s", dst, b3, src2) + "\n";
                    case DIV:
                        //String y31231 = "addi " + b3 + ",x0" + ", "+ b2.value+"\n";
                        //return y31231+String.format("div %s, %s, %s", dst, b3, src2) + "\n";
                    default:
                        throw new Report.InternalError("After careful consideration, MUL and DIV should be unsupported, so how in the hell did you get here?");
                }
            }else if (y.fstExpr instanceof IMC.TEMP c && y.sndExpr instanceof IMC.CONST b2){
                IMC.TEMP b3 = new IMC.TEMP(new MEM.Temp());
                src1 = c.temp.toString();
                switch(y.oper) {
                    case ADD:      

                        return String.format("addi %s, %s, %s", dst, src1, b2) + "\n";
                    case SUB:   
                        String y12 = "addi " + b3 + ",x0" + ", "+ b2.value+"\n"; 
                        return y12+String.format("sub %s, %s, %s", dst, src1, b3) + "\n";
                    case MUL:
                        //String y11 = "addi " + b3 + ", x0," + b2.value+"\n";
                        //return y11+String.format("mul %s, %s, %s", dst, src1, b3) + "\n";
                    case DIV:    
                        //String y31231 = "addi " + b3 + ",x0" + ", "+ b2.value+"\n";
                        //return y31231+String.format("div %s, %s, %s", dst, src1, b3) + "\n";
                    default:
                        throw new Report.InternalError("After careful consideration, MUL and DIV should be unsupported, so how in the hell did you get here?");
                }
            }else{
                IMC.CONST b1 = (IMC.CONST)y.fstExpr;
                IMC.CONST b2 = (IMC.CONST)y.sndExpr;
                IMC.TEMP tempReg = new IMC.TEMP(new MEM.Temp());
                switch(y.oper) {
                    case ADD:                       
                        arg.addLine(new MathOperationWithValue(
                            "addi",
                            new Register(x),
                            ASM.zero,
                            b1.value
                        ));
                        arg.addLine(new MathOperationWithValue(
                            "addi",
                            new Register(x),
                            ASM.zero,
                            b2.value
                        ));
                        return String.format("addi %s, %s, %s", dst, src1, b2) + "\n";
                    case SUB:   
                        arg.addLine(new MathOperationWithValue(
                            "addi",
                            new Register(x),
                            ASM.zero,
                            b1.value
                        ));
                        arg.addLine(new MathOperationWithValue(
                            "addi",
                            new Register(tempReg),
                            ASM.zero,
                            b2.value
                        ));
                        arg.addLine(new MathOperationWithReg(
                            "sub",
                            new Register(dst),
                            new Register(x),
                            new Register(tempReg)
                        ));
                        String y12 = "addi " + x + ",x0" + ", "+ b2.value+"\n"; 
                        return y12+String.format("sub %s, %s, %s", dst, src1, b1.toString()) + "\n";
                    case MUL:
                        arg.addLine(new MathOperationWithValue(
                            "addi",
                            new Register(x),
                            ASM.zero,
                            b1.value
                        ));
                        arg.addLine(new MathOperationWithValue(
                            "addi",
                            new Register(tempReg),
                            ASM.zero,
                            b2.value
                        ));
                        arg.addLine(new MathOperationWithReg(
                            "mul",
                            new Register(dst),
                            new Register(x),
                            new Register(tempReg)
                        ));
                        String y11 = "addi " + x + ", x0," + b2.value+"\n";
                        return y11+String.format("mul %s, %s, %s", dst, src1, b1.toString()) + "\n";
                    case DIV: 
                        arg.addLine(new MathOperationWithValue(
                            "addi",
                            new Register(x),
                            ASM.zero,
                            b1.value
                        ));
                        arg.addLine(new MathOperationWithValue(
                            "addi",
                            new Register(tempReg),
                            ASM.zero,
                            b2.value
                        ));
                        arg.addLine(new MathOperationWithReg(
                            "div",
                            new Register(dst),
                            new Register(x),
                            new Register(tempReg)
                        ));   
                        String y31231 = "addi " + x + ",x0" + ", "+ b2.value+"\n";
                        return y31231+String.format("div %s, %s, %s", dst, src1, b1.toString()) + "\n";
                    default:
                        throw new Report.InternalError("Unsupported binary operator in MOVE.");
                }
            }
            
            
        }

        // Done
        if(move.dst instanceof IMC.BINOP y && move.src instanceof IMC.TEMP x){
            IMC.BINOP binop = (IMC.BINOP)move.dst;
            IMC.TEMP reg = null;
            Long xxx = 0L;
            if(binop.fstExpr instanceof IMC.TEMP x2){
                reg = x2;
                xxx = ((IMC.CONST)(binop.sndExpr)).value;
            }else{
                xxx = ((IMC.CONST)(binop.fstExpr)).value;
                reg = ((IMC.TEMP)binop.sndExpr);
            }
            String dst1 = binop.fstExpr.toString();
            String dst2 = binop.sndExpr.toString();
            String src = x.temp.toString();
            if(reg == null){
                throw new Report.Error("FUCK");
            }
            arg.addLine(new RegisterAndOffset(
                "ld",
                new Register(x),
                new Register(reg),
                xxx
            ));
            return String.format("add %s, %s, %s", dst1, dst2, src) + "\n";
        }
        
        /*
        * Storamo na naslov k je v dst Reg
        * Not done
        */ 
        if(move.dst instanceof IMC.MEM8 Y) {
            IMC.TEMP tmpReg = new IMC.TEMP(new MEM.Temp());
            IMC.TEMP from = (IMC.TEMP)move.src;
            IMC.TEMP to = ((IMC.TEMP)Y.addr);
            
            arg.addLine(new RegisterAndOffset(
                "sd",
                new Register(to),
                new Register(from),
                0L
            ));
            String dst = ((IMC.TEMP)Y.addr).temp.toString();
            String src = ((IMC.TEMP)move.src).temp.toString();
            return String.format("sd %s, 0(%s)", src, dst) + "\n";
        }else if(move.dst instanceof IMC.MEM1) {
            IMC.TEMP temp = (IMC.TEMP)move.dst;
            arg.addLine(new RegisterAndOffset(
                "sd",
                new Register(temp),
                new Register(((IMC.TEMP)move.src)),
                0L)
            );

            String dst = temp.temp.toString();
            String src = move.src.toString();
            return String.format("sb %s, 0(%s)", dst, src) + "\n";
        }
        
        

        // Done
        if(move.dst instanceof IMC.TEMP dst && move.src instanceof IMC.TEMP src) {

            String dst2 = dst.temp.toString();
            String src2 = src.temp.toString();
            if(arg.frameOfCode!= null){
                Report.info("FUCKFUCKKKKKKK " + new Register(dst)+ " "+ new Register(arg.frameOfCode.RV));
                Register x2 = new Register(new IMC.TEMP(arg.frameOfCode.RV));
                Register y2 = new Register(dst);
                arg.retReg = new Register(arg.frameOfCode.RV);
                if(x2.toString().equals(y2.toString())){
                    arg.addLine(new MathOperationWithReg("add", ASM.a0, ASM.zero, new Register(src)));
                    return String.format("add %s, %s, %s", ASM.a0, ASM.zero, new Register(src));

                }
                //throw new Report.Error("FUCKS SAKE");
            }
            arg.addLine(new MathOperationWithReg(
                "add",
                new Register(dst),
                ASM.zero,
                new Register(src)
            ));

            return String.format("add %s, %s, %s", dst2, "x0", src2) + "\n";
        }
        
        // Done
        if(move.dst instanceof IMC.TEMP dst && move.src instanceof IMC.CONST x) {

            //This is a pseudo command
            {

                arg.addLine(new RegisterAndValue(
                    "li", 
                    new Register(dst),
                    x.value
                ));
            }

            
            return String.format("addi %s, %s, %s", dst, "x0",  x.value) + "\n";
        }
        
        
        // Done
        if(move.dst instanceof IMC.TEMP x && move.src instanceof IMC.MEM1 y){
            String dst = x.temp.toString();
            String src = y.addr.toString();
            if(y.addr instanceof IMC.TEMP b){
                src = b.temp.toString();
            }else if(y.addr instanceof IMC.NAME x2){
                MEM.Label lbl = 
                    x2.label;
                src =
                    lbl.name;    
            }
            if(y.addr instanceof IMC.TEMP b){
                arg.addLine(new RegisterAndOffset(
                    "ld",
                    new Register(x),
                    new Register(b),
                    0L)
                );
            }else if(y.addr instanceof IMC.NAME x2){
                MEM.Label lbl = x2.label;
                src = lbl.name;
                arg.addLine(new LoadData(
                "ld",
                    new Register(x),
                    ASM.zero,
                    new ASM.Label(new IMC.LABEL(x2.label))
                ));
            }
            return String.format("lb %s, 0(%s)", dst, src) + "\n";
    
        }else if(move.dst instanceof IMC.TEMP x && move.src instanceof IMC.MEM8 y){
            String dst = x.temp.toString();
            String src = y.addr.toString();
            if(y.addr instanceof IMC.TEMP b){
                src = b.temp.toString();
                Report.info(move.toString());
                arg.addLine(new RegisterAndOffset(
                "ld",
                    new Register(x),
                    new Register(b),
                    0L)
                );
            }else if(y.addr instanceof IMC.NAME x2){
                MEM.Label lbl = x2.label;
                src = lbl.name;
                arg.addLine(new LoadData(
                "ld",
                    new Register(x),
                    ASM.zero,
                    new ASM.Label(new IMC.LABEL(x2.label))
                ));
            }
            return String.format("ld %s, 0(%s)", dst, src) + "\n";
        }
    
        // Done
        if(move.dst instanceof IMC.TEMP x && move.src instanceof IMC.NAME y){;
            IMC.TEMP nt = new IMC.TEMP(new MEM.Temp());

            arg.addLine(new LoadAddress(
                "la",
                new Register(nt),
                new ASM.Label(new IMC.LABEL(y.label))
            ));
            arg.addLine(new RegisterAndOffset(
                "ld",
                new Register(x),
                new Register(nt),
                0
            ));
            String dst = x.temp.toString();
            return String.format("ld %s, %s(x0)", dst, y.label) + "\n";
        }

        // Done
        if(move.dst instanceof IMC.TEMP x && move.src instanceof CALL y){
            if(y.addr instanceof IMC.NAME z){
                /*LIN.CodeChunk chunk2 = null;
                for(LIN.CodeChunk chunk : vseFunkcije) {
                    if(chunk.frame.label.name.equals(z.label.name)) {
                        chunk2 = chunk;
                        break;
                    }
                }*/
                MEM.Label lbl = z.label;
                String args = push(y.args, arg);
                StringBuilder sb = new StringBuilder();
                sb.append("###fixing sp###\n");
                arg.addLine(new ASM.Comment("fixing sp"));
                arg.addLine(new ASM.Comment("args size is "+ y.args.size()));
                for(int i=0; i<y.args.size(); i++) {
                    arg.addLine(
                        new MathOperationWithValue
                        ("addi", 
                        ASM.sp,
                        ASM.zero,
                        -8L
                        )
                    );  
                    sb.append("addi sp, sp, -8\n");
                }
                arg.addLine(new Comment("fixed sp"));

                sb.append("###fixed sp###\n");
                IMC.TEMP tempArg = new IMC.TEMP(new MEM.Temp());
                arg.addLine(new JumpAndLink(
                    "jal",
                    new Register(tempArg),
                    new ASM.Label(new IMC.LABEL(lbl))
                ));
                
                arg.addLine(new RegisterAndOffset(
                    "ld",
                    new Register(x),
                    new Register(tempArg),
                    0L)
                );
                for(int i=0; i<y.args.size(); i++) {
                    arg.addLine(new MathOperationWithValue(
                        "addi",
                        ASM.sp,
                        ASM.sp,
                        8L
                    ));
                }
                return 
                    args+
                    "jal " + 
                    lbl.name + 
                    "\n"+
                    "ld " + x.temp.toString() + ", 0(x1)\n"+
                    sb.toString();
            }
            
            throw new Report.Error("You should not be here - in move, to help debug");
            /*Report.info("WHAT THE FUCK??????\n"+move.toString());
            StringBuilder sb = new StringBuilder();
            sb.append("###fixing sp###\n");
            for(int i=0; i<y.args.size(); i++) {
                sb.append("addi sp, x0, 8\n");
            }
            sb.append("###fixed sp###\n");
            //arg.addLine(new ASM.SingleRegInstr("jal", ));
            return push(y.args, arg)+"\n" + sb.toString()+"\njal " + ((IMC.TEMP)(y.addr)).temp.toString();*/
        }

        Report.warning(move.toString());
        throw new Report.InternalError("What the sigma");
    }

    @Override
    public String visit(IMC.STMTS stmts, AsmChunk arg) {
        throw new Report.InternalError("How did you manage to visit stmts after the linearization?");
    }
}
