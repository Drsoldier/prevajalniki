package compiler.phase.imcgen;

import compiler.phase.abstr.AST.FunDefn;
import compiler.phase.memory.MEM.*;

public class NekiNovega {
    public FunDefn funDefn;
    public Label l1;
    public Label l2;
    public IMC.Expr lastExpr;
    public NekiNovega(FunDefn funDefn) {
        this.funDefn = funDefn;
    }
    public NekiNovega() {
        this.funDefn = null;
    }
}
