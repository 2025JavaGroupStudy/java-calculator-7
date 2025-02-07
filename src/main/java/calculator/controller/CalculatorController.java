package calculator.controller;

import calculator.Model.DetectResult;
import calculator.service.NumberProcessService;
import calculator.service.SeparatorRecognizeService;
import calculator.view.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static calculator.service.NumberProcessService.addAll;

public class CalculatorController {

    private Console console;

    private SeparatorRecognizeService separatorRecognizeService;

    private static String rawArg;
    private List<Integer> numberList = new ArrayList<>();
    private int calculationResult;
    private DetectResult[] detectResult = new DetectResult[2];
    private char temp;

    public CalculatorController(Console console){
        this.console = console;
        separatorRecognizeService = new SeparatorRecognizeService();
    }

    public void input(){
        console.print("덧셈할 문자열을 입력해 주세요.");
        rawArg = console.read();
        if(Objects.equals(rawArg, ""))calculationResult=0;
    }

    public void process(){
        int leng = rawArg.length();
        for(int i=0; i<leng; i++) {
            temp = rawArg.charAt(i);
            detectResult[0] = separatorRecognizeService.normalDetect(temp, i, leng);
            detectResult[1] = separatorRecognizeService.specialDetect(temp, i, leng);

            if(detectResult[0].getDetected()) {
                int num = detectResult[0].getResultNum();
                numberList.add(num);
            }
            if(detectResult[1].getDetected()) {
                int num = detectResult[1].getResultNum();
                numberList.add(num);
            }
        }
        calculationResult = NumberProcessService.addAll(numberList);
    }

    public void output(){
        String content = "결과 : " + calculationResult;
        console.print(content);
    }
}
