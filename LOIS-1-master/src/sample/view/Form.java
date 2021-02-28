package sample.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.awt.*;

public class Form {

    private ExpressionPanel expressionRowPanel;
    private OperButtonPanel operButtonPanel;

    private GridPane gridPane;

    public Form() {
        expressionRowPanel = new ExpressionPanel();
        operButtonPanel = new OperButtonPanel(expressionRowPanel);
        gridPane = new GridPane();
        configGridPane();
    }

    private void configGridPane() {
        gridPane.setAlignment(Pos.BOTTOM_CENTER);
        gridPane.add(expressionRowPanel.getExpRowScrollPane(), 1, 0, 2, 1);
        Label label = new Label("Press the Button:");
        label.setFont(new Font("Lucida Sans Unicode", 18));
        label.setStyle("-fx-text-fill: yellow");
        gridPane.add(label, 2, 2, 2, 1);
        gridPane.add(operButtonPanel.getGridPane(), 1, 3, 2, 2);
        gridPane.setStyle("-fx-background-color: green;");
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}
