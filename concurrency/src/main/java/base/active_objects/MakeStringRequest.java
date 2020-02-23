package base.active_objects;

public class MakeStringRequest extends MethodRequest{
    private final int count;
    private final char fillChar;
    public MakeStringRequest(Servant servant, FutureResult futureResult,int count,char fillchar) {
        super(servant, futureResult);
        this.fillChar=fillchar;
        this.count=count;
    }

    @Override
    public void execute() {
        Result result=servant.makeString(count,fillChar);
        futureResult.setResult(result);
    }
}
