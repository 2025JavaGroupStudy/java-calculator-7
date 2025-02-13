package calculator.service;

import calculator.Model.CalculatorModel;
import calculator.Model.ModelObserver;
import calculator.util.NumberProcessUtil;

import java.util.regex.Matcher;

public class SeparatorRecognizeService {

    private CalculatorModel calculatorModel;

    public void setInput(String inputLine){
        calculatorModel = CalculatorModel.setInputLine(inputLine);
    }

    public void addObserver(ModelObserver view){
        calculatorModel.setObserver(view);
    }

    public void specialDetect(){
        Matcher customCheckMatch = calculatorModel.getCustomCheckMatcher();

        //커스텀 구분자를 전부 추출
        while(customCheckMatch.find()){
            String extracted = customCheckMatch.group(1);
            //구분자 목록에 커스텀 구분자 추가
            calculatorModel.addSeparator(extracted);
            //입력받은 문자열에서 해당 부분 제거
            calculatorModel.stripInputLine("//" + extracted + "\\\\n");
        }
    }

    public void normalDetect(){
        int tempNum = 0;
        calculatorModel.generateSeparatorCheckRegex();

        String[] splittedInputLine = calculatorModel.getSplittedInputLine();
        for(String i : splittedInputLine){
            tempNum += NumberProcessUtil.stringToInt(i);
        }

        calculatorModel.setResultNum(tempNum);
    }


}

