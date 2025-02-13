package calculator.view;

import calculator.Model.ModelObserver;
import calculator.util.MessageConstants;

public class CalculatorView implements ModelObserver {

    public String read(){
        System.out.println(MessageConstants.INPUT_GUIDE.getMessage());
        return camp.nextstep.edu.missionutils.Console.readLine();
    }

    @Override
    public void onCalculateFinished(int result){
        System.out.println(MessageConstants.RESULT_GUIDE.getMessage() + result);
    }
}
