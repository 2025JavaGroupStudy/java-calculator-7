package calculator.util;

import java.util.List;

public class NumberProcessUtil {
    public static int stringToInt(String arg){
        if(arg == null || !arg.matches("\\d+")) throw new IllegalArgumentException("Invalid number format");
        return Integer.parseInt(arg);
    }

    public static String intToString(int num){
        return String.valueOf(num);
    }

    public static int addAll(List<Integer> targetList){
        return targetList.stream().mapToInt(Integer::intValue).sum();
    }
}
