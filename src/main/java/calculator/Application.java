package calculator;

import calculator.controller.CalculatorController;
import calculator.view.Console;

public class Application {
    public static void main(String[] args) {
        try{
            new Application().run();

        } catch (IllegalArgumentException e) {
            System.err.println("ERROR : " + e.getMessage());
            return;
        }
    }

    public void run(){
        Console console = new Console();
        CalculatorController controller = new CalculatorController(console);

        controller.input();
        controller.process();
        controller.output();
    }
}
