// Лабораторная работа №2 по дисциплине ЛОИС
// Вариант 8: Построить СКНФ для заданной формулы
// Выполнена студентом грруппы 821701 БГУИР Залесский Александр Андреевич

package parser;

import config.Configuration;

import java.util.ArrayList;
import java.util.List;

import static config.Configuration.*;

public class FormulaService {
    private String formulaPCNF;
    private ExpressionNode tree;
    private Table table;
    private List<String> LITERALS;
    private FormulaParser grammarParser;
    private boolean result = true;

    public FormulaService(String expression) {
        try {
            if ("1".equals(expression) || "0".equals(expression)) {
                result = false;
                throw new FormulaException("1".equals(expression) ? 14 : 13);
            }
            grammarParser = new FormulaParser(expression);
            tree = grammarParser.getTree();
            LITERALS = new ArrayList<>(grammarParser.getLITERALS());
            createTruthTable();
            formulaPCNF = generateFormula();
        } catch (FormulaException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void output() {
        LITERALS.add("f");
        table.output(LITERALS);
        System.out.println(formulaPCNF);
    }

    private void createTruthTable() throws FormulaException {
        table = new Table(LITERALS.size());
        for (int i = 0; i < table.getRows(); i++) {
            table.setValueRow(i, determineValue(table.getRow(i), tree));
        }
    }

    private boolean determineValue(int[] value, ExpressionNode tree) throws FormulaException {
        switch (tree.getOperation()) {
            case CON: {
                return determineValue(value, tree.getLeftNode()) & determineValue(value, tree.getRightNode());
            }
            case DIS: {
                return determineValue(value, tree.getLeftNode()) | determineValue(value, tree.getRightNode());
            }
            case NEG: {
                return !determineValue(value, tree.getLeftNode());
            }
            case IMPL: {
                return !determineValue(value, tree.getLeftNode()) | determineValue(value, tree.getRightNode());
            }
            case EQ: {
                return (!determineValue(value, tree.getLeftNode()) & !determineValue(value, tree.getRightNode())) |
                        (determineValue(value, tree.getLeftNode()) & determineValue(value, tree.getRightNode()));
            }
            case "": {
                List<String> list = new ArrayList<>(LITERALS);
                if ("1".equals(tree.getExpression()) || "0".equals(tree.getExpression())) {
                    return "1".equals(tree.getExpression());
                }
                return value[list.indexOf(tree.getExpression())] == 1;
            }
            default: {
                throw new FormulaException(13);
            }
        }
    }

    private String generateFormula() {
        StringBuilder builder = new StringBuilder();
        if (table.countDis() == 0) {
            return "0";
        }
        builder.append("(".repeat(Math.max(0, table.countDis() - 1)));
        int count = 0;
        for (int j = 0; j < table.getRows(); j++) {
            if (table.getValueRow(j) == 0) {
                builder.append(atom(LITERALS.size(), table.getTable()[j]));
                if (count != 0) {
                    builder.append(")");
                }
                String MAIN_SIGN = "/\\";
                builder.append(MAIN_SIGN);
                count++;
            }
        }
        builder.setLength(builder.length() - 2);
        return builder.toString();
    }

    private String atom(int countElements, int[] row) {
        StringBuilder atom = new StringBuilder();
        atom.append("(".repeat(Math.max(0, countElements - 1)));
        int count = 0;
        for (int i = 0; i < countElements; i++) {
            atom.append((row[i] == 0) ? Configuration.LITERALS.get(i) : ("(!" + Configuration.LITERALS.get(i) + ")"));
            if (count != 0) {
                atom.append(")");
            }
            String SIGN = "\\/";
            atom.append(SIGN);
            count++;
        }
        atom.setLength(atom.length() - 2);
        return atom.toString();
    }

    public String getResultParser() {
        return grammarParser.getReport();
    }

    public boolean isResult() {
        return result;
    }

    public String getFormulaPCNF() {
        return formulaPCNF;
    }
}
