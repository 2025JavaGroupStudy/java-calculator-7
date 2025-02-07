package calculator.Model;

public class DetectResult {
    private boolean isDetected;
    private int resultNum;

    public DetectResult(){
        this.isDetected = false;
        this.resultNum = 0;
    }

    public int getResultNum(){
        return resultNum;
    }

    public boolean getDetected(){
        return isDetected;
    }

    public void setDetected(boolean bool){
        this.isDetected = bool;
    }

    public void setResultNum(int resultNum) {
        this.resultNum = resultNum;
    }
}
