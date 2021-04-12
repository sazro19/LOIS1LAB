// Лабораторная работа №2 по дисциплине ЛОИС
// Вариант 8: Построить СКНФ для заданной формулы
// Выполнена студентом грруппы 821701 БГУИР Залесский Александр Андреевич

package parser;

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
    private GeneratorPCNF generatorPCNF;

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
            generatorPCNF = new GeneratorPCNF(table, LITERALS);
            formulaPCNF = generatorPCNF.getPCNF();
        } catch (FormulaException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void output() {
        LITERALS.add("f");
        // table.output(LITERALS);
        System.out.println(formulaPCNF);
    }

    private void createTruthTable() throws FormulaException {
        table = new Table(LITERALS.size());
        for (int i = 0; i < table.getRows(); i++) {
            table.setValueRow(i, getValue(table.getRow(i), tree));
        }
    }

    private boolean getValue(int[] value, ExpressionNode tree) throws FormulaException {
        switch (tree.getOperation()) {
            case CON: {
                return getValue(value, tree.getLeftNode()) & getValue(value, tree.getRightNode());
            }
            case DIS: {
                return getValue(value, tree.getLeftNode()) | getValue(value, tree.getRightNode());
            }
            case NEG: {
                return !getValue(value, tree.getLeftNode());
            }
            case IMPL: {
                return !getValue(value, tree.getLeftNode()) | getValue(value, tree.getRightNode());
            }
            case EQ: {
                return (!getValue(value, tree.getLeftNode()) & !getValue(value, tree.getRightNode())) |
                        (getValue(value, tree.getLeftNode()) & getValue(value, tree.getRightNode()));
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
