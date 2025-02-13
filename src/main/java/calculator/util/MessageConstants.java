package calculator.util;

public enum MessageConstants {
    INPUT_GUIDE("덧셈할 문자열을 입력해 주세요."),
    RESULT_GUIDE("결과 : ");

    private final String message;

    MessageConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
