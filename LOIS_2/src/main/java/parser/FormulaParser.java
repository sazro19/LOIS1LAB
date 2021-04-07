// Лабораторная работа №2 по дисциплине ЛОИС
// Вариант 8: Построить СКНФ для заданной формулы
// Выполнена студентом грруппы 821701 БГУИР Залесский Александр Андреевич

package parser;

import config.Configuration;

import java.util.*;

public class FormulaParser {
    private final String EXPRESSION;

    private final ExpressionNode tree;
    private String report;

    private final Set<String> LITERALS;

    public FormulaParser(String expression) throws FormulaException {
        this.EXPRESSION = expression;
        LITERALS = new HashSet<>();
        report = "";
        try {
            checkSymbols();
            checkBrackets();
            tree = new ExpressionNode(expression, this);
            report = "Tree has been created.";

        } catch (FormulaException formulaException) {
            throw new FormulaException(formulaException.getNumber());
        }
    }

    private void checkSymbols() throws FormulaException {
        if (EXPRESSION.length() == 1) {
            if (!Configuration.LITERALS.contains(EXPRESSION)) {
                throw new FormulaException(6);
            }
        }
        for (int i = 0; i < EXPRESSION.length(); i++) {
            if (!(Configuration.LITERALS.contains("" + EXPRESSION.charAt(i)) || Configuration.OPERATORS.contains("" + EXPRESSION.charAt(i)))) {
                String sign = searchSign(EXPRESSION, i);
                if (!Configuration.OPERATORS.contains(sign)) {
                    throw new FormulaException(6);
                } else {
                    if (sign.length() == 2) {
                        i++;
                    }
                }
            }
        }
    }

    private String searchSign(String expression, int pointer) {
        if (expression.charAt(pointer) == '!' || expression.charAt(pointer) == '~')
            return expression.charAt(pointer) + "";
        return "" + expression.charAt(pointer) + expression.charAt(pointer + 1);
    }

    private void checkBrackets() throws FormulaException {
        if (EXPRESSION.contains(")(")) {
            throw new FormulaException(3);
        }
        if (EXPRESSION.charAt(0) == ')') {
            throw new FormulaException(3);
        }
        if (EXPRESSION.charAt(0) != '(' && EXPRESSION.length() != 1) {
            throw new FormulaException(3);
        }
        if (EXPRESSION.charAt(0) == '(' && EXPRESSION.charAt(EXPRESSION.length() - 1) != ')') {
            throw new FormulaException(3);
        }
        int checkOpen = 0;
        int checkClose = 0;
        for (int i = 0; i < EXPRESSION.length(); i++) {
            if (EXPRESSION.charAt(i) == '(') {
                checkOpen++;
            } else if (EXPRESSION.charAt(i) == ')') {
                checkClose++;
            }
        }
        if (checkOpen > checkClose) {
            throw new FormulaException(1);
        }
        if (checkClose > checkOpen) {
            throw new FormulaException(2);
        }
    }

    public String getReport() {
        return report;
    }

    public void addElements(String element) {
        LITERALS.add(element);
    }

    public Set<String> getLITERALS() {
        return LITERALS;
    }

    public ExpressionNode getTree() {
        return tree;
    }
}
