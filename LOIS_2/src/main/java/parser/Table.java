// Лабораторная работа №2 по дисциплине ЛОИС
// Вариант 8: Построить СКНФ для заданной формулы
// Выполнена студентом грруппы 821701 БГУИР Залесский Александр Андреевич

package parser;

import java.util.List;

public class Table {
    private final int elements;
    private final int rows;
    private final int[][] table;

    public Table(int n) {
        elements = n;
        rows = (int) Math.pow(2, n);
        table = new int[rows][n + 1];
        createTable(n);
    }

    private void createTable(int n) {
        for (int i = 0; i < rows; i++) {
            for (int j = n - 1; j >= 0; j--) {
                table[i][j] = (i / (int) Math.pow(2, j)) % 2;
            }
        }
    }

    public int[][] getTable() {
        return table;
    }

    public int[] getRow(int i) {
        return table[i];
    }

    public int getValueRow(int i) {
        return table[i][elements];
    }

    public int getRows() {
        return rows;
    }

    public void setValueRow(int i, boolean value) {
        table[i][elements] = value ? 1 : 0;
    }

    public int countDis() {
        int result = 0;
        for (int i = 0; i < rows; i++) {
            if (table[i][elements] == 0) {
                result++;
            }
        }
        return result;
    }

    public void output(List<String> elements) {
        for (String element : elements) {
            System.out.print(element + " ");
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= this.elements; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }

}