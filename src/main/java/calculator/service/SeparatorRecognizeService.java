package calculator.service;

import calculator.Model.DetectResult;

public class SeparatorRecognizeService {

    private String normalClause="";
    private String specialClause="";

    private String specialSeparator="";

    private boolean normalDetected=false;
    private int specialDetected=0;
    private int specialState = 0;
    private int specialSeparatorDetected=0;

    public DetectResult normalDetect(char word, int index, int len){
        DetectResult detectResult = new DetectResult();
        if(normalDetected) {
            if(word==':'||word==',') {
                int result = NumberProcessService.stringToInt(normalClause);
                normalDetected = false;
                normalClause = "";

                detectResult.setDetected(1);
                detectResult.setResultNum(result);

            }else{
                normalClause += word;
                detectResult.setDetected(0);
            }

        }else{
            if(word==':'||word==',') {
                normalDetected = true;
            }else if(Character.isDigit(word)){
                normalClause+=word;
                normalDetected=true;
            }else{
                throw new IllegalArgumentException("Invalid string format");
            }
            detectResult.setDetected(0);
        }

        if(len-1==index){
            int result = NumberProcessService.stringToInt(normalClause);
            normalDetected = false;
            normalClause = "";

            detectResult.setDetected(1);
            detectResult.setResultNum(result);
        }

        return detectResult;
    }

    public DetectResult specialDetect(char word, int index, int len){
        DetectResult detectResult = new DetectResult();
        switch (specialState) {
            case 0:
                detectResult.setDetected(0);
                if (specialSeparator!=""&&word == specialSeparator.charAt(specialSeparatorDetected)) {
                    detectResult.setDetected(2);
                    specialSeparatorDetected++;

                    if (specialSeparator.length() > 1 && specialSeparator.length() == specialSeparatorDetected) {
                        specialSeparatorDetected = 0;
                        specialDetected = 1;
                    } else if (specialDetected==1) {
                        int result = NumberProcessService.stringToInt(specialClause);
                        specialDetected = 0;
                        specialClause = "";

                        detectResult.setDetected(1);
                        detectResult.setResultNum(result);
                    }

                } else if (specialDetected==1) {
                    detectResult.setDetected(2);
                    specialClause += word;
                    if(len-1==index){
                        int result = NumberProcessService.stringToInt(specialClause);
                        specialDetected = 0;
                        specialClause = "";

                        detectResult.setDetected(1);
                        detectResult.setResultNum(result);
                    }
                } else if (word == '/') {
                    detectResult.setDetected(2);
                    specialState++;
                }
                else specialSeparatorDetected = 0;

                return detectResult;
            case 1:
                if (word == '/') specialState++;
                else throw new IllegalArgumentException("Invalid string format");

                detectResult.setDetected(2);
                return detectResult;
            case 2:
                if (word == '\\') specialState++;
                else specialSeparator += word;

                detectResult.setDetected(2);
                return detectResult;
            case 3:
                if (word == 'n') specialState = 0;
                else throw new IllegalArgumentException("Invalid string format");

                detectResult.setDetected(2);
                return detectResult;
        }
        detectResult.setDetected(0);
        return detectResult;
    }
}

