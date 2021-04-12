// Лабораторная работа №2 по дисциплине ЛОИС
// Вариант 8: Построить СКНФ для заданной формулы
// Выполнена студентом грруппы 821701 БГУИР Залесский Александр Андреевич

package parser;

import config.Configuration;
import parser.Exception.FormulaException;

public class ExpressionNode {
    private final String expression;
    private final FormulaParser root;
    private String operation;
    private ExpressionNode leftNode;
    private ExpressionNode rightNode;

    public ExpressionNode(String expression, FormulaParser root) throws FormulaException {
        this.expression = expression;
        this.root = root;
        operation = "";
        if (expression.length() == 1) {
            leftNode = rightNode = null;
            if (!"1".equals(expression) && !"0".equals(expression)) {
                root.addElements(expression);
            }
            return;
        }
        try {
            createTree();
        } catch (FormulaException formulaException) {
            throw new FormulaException(formulaException.getNumber());
        }
    }

    private void createTree() throws FormulaException {
        if (expression.charAt(0) == '(' && expression.charAt(expression.length() - 1) == ')') {
            String expression = copy(this.expression, 1, this.expression.length() - 1);
            if (expression.length() == 1) {
                throw new FormulaException(3);
            }
            int pointerOp = OpIndexOut(expression);
            if (pointerOp == 0) {
                if (expression.charAt(pointerOp) != '!') {
                    throw new FormulaException(3);
                }
                rightNode = null;
                leftNode = new ExpressionNode(copy(expression, 1, expression.length()), root);
                operation = FormulaParser.searchOp(expression, pointerOp);
                return;
            }
            if (pointerOp == -1) {
                throw new FormulaException(3);
            }
            operation = FormulaParser.searchOp(expression, pointerOp);
            String leftExpression = copy(expression, 0, pointerOp);
            if (operation.length() == 2) {
                pointerOp += 1;
            }
            String rightExpression = copy(expression, pointerOp + 1, expression.length());
            leftNode = new ExpressionNode(leftExpression, root);
            rightNode = new ExpressionNode(rightExpression, root);
        } else {
            throw new FormulaException(11);
        }
    }

    private String copy(String expression, int start, int end) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = start; i < end; i++) {
            stringBuilder.append(expression.charAt(i));
        }
        return stringBuilder.toString();
    }

    private int OpIndexOut(String expression) {
        int check = 0;
        for (int i = 0; i < expression.length(); i++) {
            if ((expression.charAt(i) != '(' && expression.charAt(i) != ')') && check == 0) {
                String op = FormulaParser.searchOp(expression, i);
                if (Configuration.OPERATORS.contains(op)) {
                    return i;
                }
            }
            if (expression.charAt(i) == '(') {
                check++;
            } else if (expression.charAt(i) == ')') {
                check--;
            }
        }
        return -1;
    }

    public String getExpression() {
        return expression;
    }

    public ExpressionNode getLeftNode() {
        return leftNode;
    }

    public ExpressionNode getRightNode() {
        return rightNode;
    }

    public String getOperation() {
        return operation;
    }
}