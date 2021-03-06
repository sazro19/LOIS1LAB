package parser.componentsOfValidation;

import parser.componentsOfExeptions.EmptyExpressionException;

public class Validator {

    private final static String SYMBOLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ01~\\/()!->";


    public static boolean isSymbolsCorrect(String expression) {
        boolean isCorrect = true;
        if (expression.length() <= 5) {
            if (expression.length() >= 3) {
                if (expression.charAt(3) >= (int) '0' && expression.charAt(3) <= '9') {
                    return true;
                }
            } else {
                return true;
            }
        }
        for (Character character : expression.toCharArray()) {
            if (!SYMBOLS.contains(Character.toString(character))) {
                isCorrect = false;
                break;
            }
        }
        return isCorrect;
    }

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

    public static boolean isBracketsCountCorrect(String expression) {
        try {
            if (expression.isEmpty()) {
                throw new EmptyExpressionException("Invalid!");
            } else {
                if (expression.charAt(0) != '(' && expression.length() > 2) {
                    return false;
                }
                int openedBracket = 0;
                int closedBracket = 0;
                for (char symbol : expression.toCharArray()) {
                    if (symbol == '(') {
                        openedBracket++;
                        continue;
                    }
                    if (symbol == ')') {
                        closedBracket++;
                    }
                }
                return openedBracket == closedBracket;
            }
        } catch (Exception e) {
        }
        return false;
    }

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
