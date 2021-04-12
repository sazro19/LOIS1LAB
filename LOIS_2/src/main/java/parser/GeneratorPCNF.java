// Лабораторная работа №2 по дисциплине ЛОИС
// Вариант 8: Построить СКНФ для заданной формулы
// Выполнена студентом грруппы 821701 БГУИР Залесский Александр Андреевич

package parser;

import config.Configuration;

import java.util.List;

public class GeneratorPCNF {

    private final List<String> LITERALS;
    private Table table;

    public GeneratorPCNF(Table table, List<String> LITERALS) {
        this.table = table;
        this.LITERALS = LITERALS;
    }

    public String getPCNF() {
        StringBuilder result = new StringBuilder();
        if (table.count() == 0) {
            return "0";
        }
        result.append("(".repeat(Math.max(0, table.count() - 1)));
        int count = 0;
        for (int j = 0; j < table.getRows(); j++) {
            if (table.getValueRow(j) == 0) {
                result.append(dis(LITERALS.size(), table.getTable()[j]));
                if (count != 0) {
                    result.append(")");
                }
                String MAIN_SIGN = "/\\";
                result.append(MAIN_SIGN);
                count++;
            }
        }
        result.setLength(result.length() - 2);
        int o = 0;
        int c = 0;
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '(') {
                o++;
            } else if (result.charAt(i) == ')') {
                c++;
            }
        }
        if (c != o) {
            System.out.println("Brackets!");
        }
        return result.toString();
    }

    private String dis(int countElements, int[] row) {
        StringBuilder atom = new StringBuilder();
        atom.append("(".repeat(Math.max(0, countElements - 1)));
        int count = 0;
        for (int i = 0; i < countElements; i++) {
            atom.append((row[i] == 0) ? Configuration.ALL_LITERALS.get(i) : ("(!" + Configuration.ALL_LITERALS.get(i) + ")"));
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
}
