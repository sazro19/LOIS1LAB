// Лабораторная работа №2 по дисциплине ЛОИС
// Вариант 8: Построить СКНФ для заданной формулы
// Выполнена студентом грруппы 821701 БГУИР Залесский Александр Андреевич

package parser;

import config.Configuration;

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
            int pointerSign = OpIndexOut(expression);
            if (pointerSign == 0) {
                if (expression.charAt(pointerSign) != '!') {
                    throw new FormulaException(3);
                }
                rightNode = null;
                leftNode = new ExpressionNode(copy(expression, 1, expression.length()), root);
                operation = searchOp(expression, pointerSign);
                return;
            }
            if (pointerSign == -1) {
                throw new FormulaException(3);
            }
            operation = searchOp(expression, pointerSign);
            String leftExpression = copy(expression, 0, pointerSign);
            if (operation.length() == 2) {
                pointerSign += 1;
            }
            String rightExpression = copy(expression, pointerSign + 1, expression.length());
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
                String sign = searchOp(expression, i);
                if (Configuration.OPERATORS.contains(sign)) {
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

    private String searchOp(String expression, int pointer) {
        if (expression.charAt(pointer) == '!' || expression.charAt(pointer) == '~')
            return expression.charAt(pointer) + "";
        return "" + expression.charAt(pointer) + expression.charAt(pointer + 1);
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