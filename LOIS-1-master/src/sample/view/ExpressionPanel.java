package sample.view;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class ExpressionPanel {

    private TextField expressionRowTextField;

    private ScrollPane expRowScrollPane;

    public ExpressionPanel() {
        expressionRowTextField = new TextField();
        configureExpressionRowTextField();
        expRowScrollPane = new ScrollPane();
        configureExpRowScrollPane();
    }

    public ScrollPane getExpRowScrollPane() {
        return expRowScrollPane;
    }

    public TextField getExpressionRowTextField() {
        return expressionRowTextField;
    }

    private void configureExpressionRowTextField() {
        expressionRowTextField.setEditable(true);
        expressionRowTextField.setFocusTraversable(false);
        expressionRowTextField.setFont(new Font("Lucida Sans Unicode", 18));
        expressionRowTextField.setPrefHeight(45);
        expressionRowTextField.setMinWidth(500);

        expressionRowTextField.textProperty().addListener(e -> {
            expressionRowTextField.positionCaret(expressionRowTextField.getText().length());
        });
    }

    private void configureExpRowScrollPane() {
        expRowScrollPane.setContent(expressionRowTextField);
        expRowScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        expRowScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        expRowScrollPane.setPrefWidth(300);
    }
}
