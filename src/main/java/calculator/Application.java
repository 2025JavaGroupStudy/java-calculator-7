package calculator;

import calculator.controller.CalculatorController;
import calculator.view.Console;

public class Application {
    public static void main(String[] args) {
        new Application().run();
    }

    public void run(){
        Console console = new Console();
        CalculatorController controller = new CalculatorController(console);

        controller.input();
        controller.process();
        controller.output();
    }
}
