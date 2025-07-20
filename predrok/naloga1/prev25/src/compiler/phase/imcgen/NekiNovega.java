package compiler.phase.imcgen;

import compiler.phase.abstr.AST.FunDefn;
import compiler.phase.memory.MEM;
import compiler.phase.memory.MEM.*;

public class NekiNovega {
    public FunDefn funDefn;
    public Label l1;
    public Label l2;
    public IMC.TEMP staticLink;
    public IMC.Expr lastExpr;
    public NekiNovega(FunDefn funDefn) {
        this.funDefn = funDefn;
        staticLink = null;
    }
    public NekiNovega() {
        this.funDefn = null;
    }
}
