// Лабораторная работа №1;
// Вариант E (Проверка на КНФ)
// Выполнил: студент грруппы 821701, Залесский А.А.

package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import sample.view.Form;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println((int)'A' + " " + (int)'Z');
        Loader loader = new Loader();
        loader.startCheck();

//        Form form = new Form();
//        VBox pane = new VBox();
//        Label label = new Label("Checker for KNF");
//        label.setStyle("-fx-text-fill: black;" +
//                "-fx-font-weight: bold");
//        label.setFont(new Font("Lucida Sans Unicode", 50));
//        pane.getChildren().addAll(label, form.getGridPane());
//        pane.setStyle("-fx-background-color: green;");
//        Scene scene = new Scene(pane, 1366, 700);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("KNF Check");
//        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
