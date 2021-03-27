import parser.Parser;

import java.util.Scanner;

public class Loader {

    private Scanner scanner = new Scanner(System.in);

    public void startCheck() throws Exception {
        while (true) {
            System.out.println("Enter the formula or type exit to exit");
            String expression = scanner.nextLine();
            System.out.println(expression);
            if ("exit".equals(expression)) {
                System.exit(0);
            } else {
                Parser parser = new Parser(expression);
                if (parser.transformFormula()) {
                    System.out.println("It is CNF");
                } else {
                    System.out.println("It is not CNF");
                }
            }
        }
    }
}
