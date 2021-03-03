package sample;

import sample.parser.Parser;

import java.util.Scanner;

public class Loader {

    private Parser parser;

    private Scanner scanner = new Scanner(System.in);

    public void startCheck() throws Exception {
        while (true) {
            System.out.println("Enter the formula or type 0 to exit");
            String expression = scanner.nextLine();
            System.out.println(expression);
            if ("0".equals(expression)) {
                System.exit(0);
            } else {
                parser = new Parser(expression);
                if (parser.isCNF()) {
                    System.out.println("It is CNF");
                } else {
                    System.out.println("It is not CNF");
                }
            }
        }
    }
}
