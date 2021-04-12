package parser;

import parser.Exception.FormulaException;

import java.util.ArrayList;
import java.util.List;

import static config.Configuration.*;

public class TableCalc {

    final List<String> LITERALS;

    TableCalc(List<String> LITERALS) {
        this.LITERALS = LITERALS;
    }

    boolean getValue(int[] value, ExpressionNode tree) throws FormulaException {
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
}
