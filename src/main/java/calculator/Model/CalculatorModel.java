package calculator.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorModel {
    private ModelObserver observer;
    private int resultNum;
    private String inputLine;
    private List<String> separatorList;
    private String separatorCheckRegex;
    private final Pattern customCheckPattern = Pattern.compile("//(.*?)\\\\n");

    public CalculatorModel(String inputLine){
        this.resultNum = 0;
        this.inputLine = inputLine;
        this.separatorList = new ArrayList<>(List.of(":", ","));
    }

    public static CalculatorModel setInputLine(String inputLine) {
        return new CalculatorModel(inputLine);
    }

    public void stripInputLine(String regex) {
        inputLine = inputLine.replaceFirst(regex, "");
    }

    public String[] getSplittedInputLine() {
        return inputLine.split(separatorCheckRegex);
    }

    public void setObserver(ModelObserver observer){
        this.observer = observer;
    }

    public Matcher getCustomCheckMatcher(){
        return customCheckPattern.matcher(inputLine);
    }

    public void addSeparator(String separator){
        separatorList.add(separator);
    }

    public void generateSeparatorCheckRegex(){
        separatorCheckRegex = String.join("|", separatorList);
    }

    public void setResultNum(int resultNum) {
        this.resultNum = resultNum;
        observer.onCalculateFinished(resultNum);
    }
}
