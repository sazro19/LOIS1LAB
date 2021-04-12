// Лабораторная работа №2 по дисциплине ЛОИС
// Вариант 8: Построить СКНФ для заданной формулы
// Выполнена студентом грруппы 821701 БГУИР Залесский Александр Андреевич

package parser;

import parser.Exception.FormulaException;

import java.util.ArrayList;
import java.util.List;

public class FormulaService {
    private String formulaPCNF;
    private ExpressionNode tree;
    private Table table;
    private List<String> LITERALS;
    private FormulaParser parser;
    private boolean result = true;
    private GeneratorPCNF generatorPCNF;

    public FormulaService(String expression) {
        try {
            if ("1".equals(expression) || "0".equals(expression)) {
                result = false;
                throw new FormulaException("1".equals(expression) ? 14 : 13);
            }
            parser = new FormulaParser(expression);
            tree = parser.getTree();
            LITERALS = new ArrayList<>(parser.getLITERALS());
            createTruthTable();
            generatorPCNF = new GeneratorPCNF(table, LITERALS);
            formulaPCNF = generatorPCNF.getPCNF();
        } catch (FormulaException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void out() {
        LITERALS.add("f");
        // table.output(LITERALS);
        System.out.println(formulaPCNF);
    }

    private void createTruthTable() throws FormulaException {
        table = new Table(LITERALS.size());
        TableCalc tableCalc = new TableCalc(LITERALS);
        for (int i = 0; i < table.getRows(); i++) {
            table.setValueRow(i, tableCalc.getValue(table.getRow(i), tree));
        }
    }

    public String getResultParser() {
        return parser.getReport();
    }

    public boolean getResult() {
        return result;
    }

    public String getFormulaPCNF() {
        return formulaPCNF;
    }
}
