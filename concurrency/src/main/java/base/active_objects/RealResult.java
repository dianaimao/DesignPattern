package base.active_objects;

public class RealResult implements Result{
    private final Object resultValue;

    public RealResult(Object resultValue){
        this.resultValue=resultValue;
    }
    @Override
    public Object getResultValue() {
        return this.resultValue;
    }
}
