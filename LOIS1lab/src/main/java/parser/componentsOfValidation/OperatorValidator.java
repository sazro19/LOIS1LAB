package parser.componentsOfValidation;

public class OperatorValidator {

    public static boolean isOpCorrect(String expression) {
        boolean isCorrect = true;
        for (int i = 0; i < expression.length() - 1; i++) {
            if (expression.charAt(i) == '/' || expression.charAt(i) == '\\' || expression.charAt(i) == '!') {
                if (expression.charAt(i) == expression.charAt(i + 1)) {
                    isCorrect = false;
                }
            }
        }
        return isCorrect;
    }
}
