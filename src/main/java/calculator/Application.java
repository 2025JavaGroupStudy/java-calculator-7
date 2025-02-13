package calculator;

import calculator.controller.CalculatorController;
import calculator.service.SeparatorRecognizeService;
import calculator.view.CalculatorView;

public class Application {
    public static void main(String[] args) {
        new Application().run();
    }

    public void run(){
        CalculatorView calculatorView = new CalculatorView();
        SeparatorRecognizeService separatorRecognizeService = new SeparatorRecognizeService();
        CalculatorController controller = new CalculatorController(calculatorView, separatorRecognizeService);

        controller.input();
        controller.calculate();
    }
}
