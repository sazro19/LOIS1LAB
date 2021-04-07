// Лабораторная работа №2 по дисциплине ЛОИС
// Вариант 8: Построить СКНФ для заданной формулы
// Выполнена студентом грруппы 821701 БГУИР Залесский Александр Андреевич

import config.Configuration;
import parser.FormulaService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String expression = "";
        StringBuilder builder = new StringBuilder(expression);
        try (FileInputStream fin = new FileInputStream(Configuration.IN_PATH)) {
            while (fin.available() > 0) {
                int oneByte = fin.read();
                builder.append(((char) oneByte));
            }
            expression = builder.toString();
        } catch (FileNotFoundException ex) {
            System.out.println("File not find!!!");
        }

        System.out.println(expression);
        System.out.println();
        FormulaService formula = new FormulaService(expression);
        if (formula.isResult()) {
            System.out.println(formula.getResultParser() + "\n");
            formula.output();
            System.out.println("\n");
        }
    }
}
