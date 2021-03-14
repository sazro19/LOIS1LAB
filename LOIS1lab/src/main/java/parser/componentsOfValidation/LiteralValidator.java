package parser.componentsOfValidation;

public class LiteralValidator {

    public static boolean isLitCorrect(String expression) {
        boolean isCorrect = true;
        if (expression.length() > 2) {
            for (int i = 0; i < expression.length(); i++) {
                if ((expression.charAt(i) >= 65 && expression.charAt(i) <= 90) &&
                        (expression.charAt(i + 1) >= 65 && expression.charAt(i + 1) <= 90)) {
                    isCorrect = false;
                    break;
                }
            }
            return isCorrect;
        } else {
            if (expression.length() == 2) {
                if (expression.charAt(1) < (int) '0' || expression.charAt(1) > (int) '9') {
                    return false;
                }
            }
        }
        return isCorrect;
    }
}
