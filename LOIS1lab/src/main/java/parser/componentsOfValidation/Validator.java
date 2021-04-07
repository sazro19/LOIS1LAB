package parser.componentsOfValidation;

import parser.componentsOfExeptions.BracketsException;
import parser.componentsOfExeptions.EmptyExpressionException;

public class Validator {

    public static boolean startValidation(String expression) {
        try {
            if (expression.isEmpty()) {
                throw new EmptyExpressionException("Empty expression!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        try {
            if (!BracketValidator.isBracketsNumberCorrect(expression)) {
                throw new BracketsException("Brackets number is invalid!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        if (!LiteralValidator.isLitCorrect(expression)) {
            return false;
        }
        if (!SymbolValidator.isSymbolsCorrect(expression)) {
            return false;
        }
        if (!OperatorValidator.isOpCorrect(expression)) {
            return false;
        }
        return true;
    }
}
