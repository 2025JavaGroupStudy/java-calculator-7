package calculator.service;

import java.util.List;

public class NumberProcessService {
    public static int stringToInt(String arg){
        if(!arg.matches("\\d+")) throw new IllegalArgumentException("Invalid number format");
        return Integer.parseInt(arg);
    }

    public static String intToString(int num){
        return String.valueOf(num);
    }

    public static int addAll(List<Integer> targetList){
        return targetList.stream().mapToInt(Integer::intValue).sum();
    }
}
