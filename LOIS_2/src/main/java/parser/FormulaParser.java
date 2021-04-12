// Лабораторная работа №2 по дисциплине ЛОИС
// Вариант 8: Построить СКНФ для заданной формулы
// Выполнена студентом грруппы 821701 БГУИР Залесский Александр Андреевич

package parser;

import config.Configuration;
import parser.Exception.FormulaException;

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
            if (!Configuration.ALL_LITERALS.contains(EXPRESSION)) {
                throw new FormulaException(6);
            }
        }
        for (int i = 0; i < EXPRESSION.length(); i++) {
            if (!(Configuration.ALL_LITERALS.contains("" + EXPRESSION.charAt(i)) || Configuration.OPERATORS.contains("" + EXPRESSION.charAt(i)))) {
                String op = searchOp(EXPRESSION, i);
                if (!Configuration.OPERATORS.contains(op)) {
                    throw new FormulaException(6);
                } else {
                    if (op.length() == 2) {
                        i++;
                    }
                }
            }
        }
    }

    static String searchOp(String expression, int pointer) {
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
        int openBrackets = 0;
        int closeBrackets = 0;
        for (int i = 0; i < EXPRESSION.length(); i++) {
            if (EXPRESSION.charAt(i) == '(') {
                openBrackets++;
            } else if (EXPRESSION.charAt(i) == ')') {
                closeBrackets++;
            }
        }
        if (openBrackets != closeBrackets) {
            throw new FormulaException(11);
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
