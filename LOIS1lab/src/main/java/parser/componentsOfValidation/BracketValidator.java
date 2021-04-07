package parser.componentsOfValidation;

public class BracketValidator {

    public static boolean isBracketsNumberCorrect(String expression) {
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
}
