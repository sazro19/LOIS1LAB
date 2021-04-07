// Лабораторная работа №2 по дисциплине ЛОИС
// Вариант 8: Построить СКНФ для заданной формулы
// Выполнена студентом грруппы 821701 БГУИР Залесский Александр Андреевич

package config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Configuration {

    public static final String FILE_NAME = "4";
    public static final String FILE_FORMAT = "txt";
    public static final String IN_PATH = System.getProperty("user.dir") + "/files/in/" + FILE_NAME + "." + FILE_FORMAT;
    // public static final String OUT_FILE_PATH = System.getProperty("user.dir") + "/files/out/" + FILE_NAME + "." + FILE_FORMAT;
    public static final List<String> LITERALS = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z","0","1"));
    public static final List<String> OPERATORS = new ArrayList<>(Arrays.asList("!", "/\\", "\\/", "(", ")", "->", "~"));
    // public static final List<String> OPERATIONS = new ArrayList<>(Arrays.asList("/\\", "\\/", "->", "~"));
    public static final String CON = "/\\";
    public static final String DIS = "\\/";
    public static final String NEG = "!";
    public static final String IMPL = "->";
    public static final String EQ = "~";
}
