package calculator.Model;

public class DetectResult {
    private int isDetected;
    private int resultNum;

    public DetectResult(){
        this.isDetected = 0;
        this.resultNum = 0;
    }

    public int getResultNum(){
        return resultNum;
    }

    public int getDetected(){
        return isDetected;
    }

    public void setDetected(int num){
        this.isDetected = num;
    }

    public void setResultNum(int resultNum) {
        this.resultNum = resultNum;
    }
}
