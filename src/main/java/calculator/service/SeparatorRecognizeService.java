package calculator.service;

import calculator.Model.DetectResult;

public class SeparatorRecognizeService {

    private String normalClause="";
    private String specialClause="";

    private String specialSeparator="";

    private boolean normalDetected=false;
    private boolean specialDetected=false;
    private int specialState = 0;
    private int specialSeparatorDetected=0;

    public DetectResult normalDetect(char word, int index, int len){
        DetectResult detectResult = new DetectResult();
        if(normalDetected) {
            if(word==':'||word==',') {
                int result = NumberProcessService.stringToInt(normalClause);
                normalDetected = false;
                normalClause = "";

                detectResult.setDetected(true);
                detectResult.setResultNum(result);

            }else{
                normalClause += word;
                detectResult.setDetected(false);
            }

        }else{
            if(word==':'||word==',') {
                normalDetected = true;
            }else if(Character.isDigit(word)){
                normalClause+=word;
                normalDetected=true;
            }
            detectResult.setDetected(false);
        }

        if(len-1==index){
            int result = NumberProcessService.stringToInt(normalClause);
            normalDetected = false;
            normalClause = "";

            detectResult.setDetected(true);
            detectResult.setResultNum(result);
        }

        return detectResult;
    }

    public DetectResult specialDetect(char word, int index, int len){
        DetectResult detectResult = new DetectResult();
        switch (specialState) {
            case 0:
                detectResult.setDetected(false);
                if (specialSeparator!=""&&word == specialSeparator.charAt(specialSeparatorDetected)) {
                    specialSeparatorDetected++;

                    if (specialSeparator.length() > 1 && specialSeparator.length() == specialSeparatorDetected) {
                        specialSeparatorDetected = 0;
                        specialDetected = true;
                    } else if (specialDetected) {
                        int result = NumberProcessService.stringToInt(specialClause);
                        specialDetected = false;
                        specialClause = "";

                        detectResult.setDetected(true);
                        detectResult.setResultNum(result);
                    }

                } else if (specialDetected) {
                    specialClause += word;
                    if(len-1==index){
                        int result = NumberProcessService.stringToInt(specialClause);
                        specialDetected = false;
                        specialClause = "";

                        detectResult.setDetected(true);
                        detectResult.setResultNum(result);
                    }
                } else if (word == '/') specialState++;
                else specialSeparatorDetected = 0;

                return detectResult;
            case 1:
                if (word == '/') specialState++;
                else throw new IllegalArgumentException("Invalid string format");

                detectResult.setDetected(false);
                return detectResult;
            case 2:
                if (word == '\\') specialState++;
                else specialSeparator += word;

                detectResult.setDetected(false);
                return detectResult;
            case 3:
                if (word == 'n') specialState = 0;
                else throw new IllegalArgumentException("Invalid string format");

                detectResult.setDetected(false);
                return detectResult;
        }
        detectResult.setDetected(false);
        return detectResult;
    }
}

