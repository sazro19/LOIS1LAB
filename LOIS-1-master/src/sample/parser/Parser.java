package sample.parser;

import sample.parser.exception.BracketsNumberException;
import sample.parser.exception.CharNotCorrectException;
import sample.parser.validation.Validator;

public class Parser {

    private static final char OPENED = '(';
    private static final char CLOSED = ')';
    private static final char CLASH = '/';
    private static final char BACKSLASH = '\\';
    private static final char NOT = '!';
    private String expression;
    private boolean isKNF = true;


    public Parser(String expression) {
        this.expression = expression;
    }

    public static void main(String[] args) {
        try {
            System.out.println(new Parser("S").isKNF());
        } catch (BracketsNumberException e) {
            e.printStackTrace();
        } catch (CharNotCorrectException e) {
            e.printStackTrace();
        }
    }

    public boolean isKNF() throws BracketsNumberException, CharNotCorrectException {

        if (!Validator.isBracketsCountCorrect(expression)) {
            isKNF = false;
            throw new BracketsNumberException("не совпадает количество открывающих и закрывающих скобок");
        }
        if (!Validator.isSymbolsCorrect(expression)) {
            isKNF = false;
        }

        if (expression.contains("->") || expression.contains("~")) {
            isKNF = false;
        }

        if (isKNF) {
            replaceInversion();
            if (expression.contains("!") || !replaceDis()) {
                isKNF = false;
            } else if (!expression.contains("\\/")) {
                isKNF = replaceCon();
            } else isKNF = false;
        }
        return isKNF;
    }

    private void replaceInversion() {
        int openedBracket = 0;
        int closedBracket = 0;
        String subInversion;
        for (int i = 0; i < expression.length(); i++) {
            Character character = expression.charAt(i);
            if (character.compareTo(NOT) == 0) {
                openedBracket = i - 1;
                closedBracket = expression.indexOf(CLOSED, i) + 1;
                subInversion = expression.substring(openedBracket, closedBracket);
                if (Validator.isBracketsCountCorrect(subInversion)) {
                    expression = expression.replace(subInversion, "In");
                    i = -1;
                }
            }
        }
    }

    private boolean replaceDis() {
        int openedBracket = 0;
        int closedBracket;
        boolean isCorrectDis = true;

        for (int i = 0; i < expression.length(); i++) {
            Character character = expression.charAt(i);

            if (character.compareTo(OPENED) == 0) {
                openedBracket = i;
            } else if (character.compareTo(BACKSLASH) == 0 && expression.charAt(i + 1) == CLASH &&
                    expression.charAt(i + 2) != OPENED) {

                isCorrectDis = true;
                closedBracket = expression.indexOf(CLOSED, i) + 1;
                String simpleDis = expression.substring(openedBracket, closedBracket);
                if (isBinary(simpleDis, BACKSLASH)) {
                    expression = expression.replace(simpleDis, "Dn");
                    i = -1;
                } else {
                    isCorrectDis = false;
                    break;
                }
            }
        }
        return isCorrectDis;
    }

    private boolean isBinary(String substring, char operat) {
        boolean isBin = true;
        for (int i = 1; i < substring.length() - 1; i++) {
            Character character = substring.charAt(i);
            if (character.compareTo(operat) == 0 && substring.indexOf(operat, i + 1) > -1) {
                isBin = false;
                break;
            }
        }
        return isBin;
    }

    private boolean replaceCon() {

        int openedBracket = 0;
        int closedBracket;
        boolean isKNF = true;

        for (int i = 0; i < expression.length(); i++) {
            Character character = expression.charAt(i);
            if (character.compareTo(OPENED) == 0) {
                openedBracket = i;
            } else if (character.compareTo(CLASH) == 0 && expression.charAt(i + 1) == BACKSLASH && expression.charAt(i + 2) != OPENED) {
                closedBracket = expression.indexOf(CLOSED, i) + 1;
                String simpleDis = expression.substring(openedBracket, closedBracket);
                if (isBinary(simpleDis, CLASH)) {
                    expression = expression.replace(simpleDis, "Co");
                    i = -1;
                } else {
                    isKNF = false;
                    break;
                }

            }
        }
        if (isKNF)
            return expression.contains("Co");
        else return false;
    }

}
