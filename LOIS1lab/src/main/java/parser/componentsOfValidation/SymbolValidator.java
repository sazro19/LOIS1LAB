package parser.componentsOfValidation;

public class SymbolValidator {

    private final static String OPERATORS = "\\/()!";

    private final static String DIGITS = "0123456789";

    private final static String WORDS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static boolean isSymbolsCorrect(String expression) {
        boolean isCorrect = true;
        for (int i = 0; i < expression.length(); i++) {
            if (!(OPERATORS + WORDS).contains(Character.toString(expression.charAt(i)))) {
                return false;
            }
        }
        if (expression.length() <= 5) {
            if (expression.length() >= 4) {
                if (expression.charAt(3) >= (int) '0' && expression.charAt(3) <= '9') {
                    return true;
                }
            } else {
                return true;
            }
        }
        for (int i = 0; i < expression.length(); i++) {
            if (!WORDS.contains(Character.toString(expression.charAt(i))) &&
                    !OPERATORS.contains(Character.toString(expression.charAt(i)))) {
                if (DIGITS.contains(Character.toString(expression.charAt(i))) &&
                        !WORDS.contains(Character.toString(expression.charAt(i - 1)))) {
                    isCorrect = false;
                    break;
                }
            }
        }
        return isCorrect;
    }
}
