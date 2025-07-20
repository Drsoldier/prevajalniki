package compiler.phase.memory;

public class Neki{
    public long depth;
    public long size;
    public long offset;
    public boolean isInFunctionParam;
    public boolean isInFunction;

    public Neki(long depth, long size, long offset) {
        this.depth = depth;
        this.size = size;
        this.offset = offset;
        this.isInFunctionParam = false;
        this.isInFunction = false;
    }
    public Neki(Neki n){
        this.depth = n.depth;
        this.size = n.size;
        this.offset = n.offset;
        this.isInFunctionParam = n.isInFunctionParam;
    }
    public Neki() {
        this.depth = -1;
        this.size = 0;
        this.offset = 0;
        this.isInFunctionParam = false;
    }
    void changeState(){
        isInFunctionParam = !isInFunctionParam;
    }


    void changeIsInFunctionState(){
        isInFunction = !isInFunction;
    }
    @Override
    public String toString(){
        return "depth: " + depth + ", size: " + size + ", offset: " + offset +", isInFunctionParam: " + isInFunctionParam + ", isInFunction:" + isInFunction;
    }
}