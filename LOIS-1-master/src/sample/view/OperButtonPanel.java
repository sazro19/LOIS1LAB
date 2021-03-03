package sample.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import sample.parser.Parser;


public class OperButtonPanel {

    private static final String FIND = "Check";

    private TextField expRowTextField;
    private GridPane gridPane;

    private EventHandler<ActionEvent> findEventHandler = e -> {
        try {
            if (!new Parser(expRowTextField.getText()).isCNF()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("It is not CNF");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("It is CNF");
                alert.showAndWait();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    };
    public OperButtonPanel(ExpressionPanel expressionRowPanel) {
        expRowTextField = expressionRowPanel.getExpressionRowTextField();
        gridPane = new GridPane();
        configGridPane();
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    private void configGridPane() {
        Button find = new Button(FIND);
        find.setFont(new Font("Lucida Sans Unicode", 18));

        find.setOnAction(findEventHandler);

        find.setStyle("-fx-pref-width: 300; " +
                "-fx-pref-height: 50; " +
                "-fx-font-size: 15");

        gridPane.add(find, 0, 0, 1, 1);
    }
}
