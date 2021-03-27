package parser;

import parser.componentsOfValidation.BracketValidator;
import parser.componentsOfValidation.Validator;

public class Parser {

    private static final char OPENED = '(';
    private static final char CLOSED = ')';
    private static final char CLASH = '/';
    private static final char BACKSLASH = '\\';
    private static final char NOT = '!';
    private String expression;
    private boolean isCNF = true;
    private long inversionCount = 0;
    private long disCount = 0;

    public Parser(String expression) {
        this.expression = expression;
    }

    private boolean isAtom() {
        if (expression.length() <= 2) {
            return expression.charAt(0) >= (int) 'A' && expression.charAt(0) <= (int) 'Z';
        }
        return false;
    }

    public boolean transformFormula() {
        if (!Validator.startValidation(expression)) {
            return false;
        }

        transformInversion();
        if (expression.equals("Inv") && inversionCount == 1) {
            return true;
        }
        if (expression.contains("!") || !transformDis()) {
            isCNF = false;
        } else if (expression.equals("Dis")) {
            return true;
        } else if (!expression.contains("\\/")) {
            isCNF = transformCon();
        } else isCNF = false;
        return isCNF;
    }

    private void transformInversion() {
        int openedBracket;
        int closedBracket;
        String subInversion;
        for (int i = 0; i < expression.length(); i++) {
            Character character = expression.charAt(i);
            if (character.compareTo(NOT) == 0) {
                openedBracket = i - 1;
                closedBracket = expression.indexOf(CLOSED, i) + 1;
                subInversion = expression.substring(openedBracket, closedBracket);
                if (BracketValidator.isBracketsCountCorrect(subInversion)) {
                    expression = expression.replace(subInversion, "Inv");
                    inversionCount++;
                    i = -1;
                }
            }
        }
    }

    private boolean transformDis() {
        int opBracketIndex = 0;
        int clBracketIndex;
        boolean isCorrectDis = true;

        for (int i = 0; i < expression.length(); i++) {
            Character character = expression.charAt(i);

            if (character.compareTo(OPENED) == 0) {
                opBracketIndex = i;
            } else if (character.compareTo(BACKSLASH) == 0 && expression.charAt(i + 1) == CLASH &&
                    expression.charAt(i + 2) != OPENED) {
                isCorrectDis = true;
                clBracketIndex = expression.indexOf(CLOSED, i) + 1;
                String simpleDis = expression.substring(opBracketIndex, clBracketIndex);
                if (isBinary(simpleDis, BACKSLASH)) {
                    expression = expression.replace(simpleDis, "Dis");
                    disCount++;
                    i = -1;
                } else {
                    isCorrectDis = false;
                    break;
                }
            }
        }
        return isCorrectDis;
    }

    private boolean isBinary(String substring, char op) {
        boolean isBin = true;
        for (int i = 1; i < substring.length() - 1; i++) {
            Character character = substring.charAt(i);
            if (character.compareTo(op) == 0 && substring.indexOf(op, i + 1) > -1) {
                isBin = false;
                break;
            }
        }
        return isBin;
    }

    private boolean transformCon() {
        if (isAtom()) {
            return true;
        }

        int openedBracket = 0;
        int closedBracket;
        boolean isCNF = true;

        for (int i = 0; i < expression.length(); i++) {
            Character character = expression.charAt(i);
            if (character.compareTo(OPENED) == 0) {
                openedBracket = i;
            } else if (character.compareTo(CLASH) == 0 && expression.charAt(i + 1) == BACKSLASH && expression.charAt(i + 2) != OPENED) {
                closedBracket = expression.indexOf(CLOSED, i) + 1;
                String simpleCon = expression.substring(openedBracket, closedBracket);
                if (isBinary(simpleCon, CLASH)) {
                    expression = expression.replace(simpleCon, "Con");
                    i = -1;
                } else {
                    isCNF = false;
                    break;
                }

            }
        }
        if (isCNF)
            return expression.contains("Con");
        else return false;
    }

}
