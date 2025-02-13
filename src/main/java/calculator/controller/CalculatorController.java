package calculator.controller;

import calculator.service.SeparatorRecognizeService;
import calculator.view.CalculatorView;


public class CalculatorController {

    private CalculatorView calculatorView;

    private SeparatorRecognizeService separatorRecognizeService;


    public CalculatorController(CalculatorView calculatorView, SeparatorRecognizeService separatorRecognizeService){
        this.calculatorView = calculatorView;
        this.separatorRecognizeService = separatorRecognizeService;
    }

    public void input(){
        String inputLine = calculatorView.read();
        separatorRecognizeService.setInput(inputLine);
        separatorRecognizeService.addObserver(calculatorView);
    }

    public void calculate(){
        separatorRecognizeService.specialDetect();
        separatorRecognizeService.normalDetect();
    }

}
