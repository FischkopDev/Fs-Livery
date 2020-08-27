package de.fischkop.dev.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LiveryManager extends Application {

    private static AnchorPane mainPane;
    private static AnchorPane listPane;
    private static Scene mainScene;
    private static Scene listScene;
    public static Stage stage;
    public static String fsPath = "D:\\Steam\\steamapps\\common\\FS 2020 Packages\\Community\\";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getClassLoader().getResource("Main.fxml"));

            mainPane = fxmlLoader.load();

            FXMLLoader fxmlLoader2 = new FXMLLoader();
            fxmlLoader2.setLocation(getClass().getClassLoader().getResource("List.fxml"));

            listPane = fxmlLoader2.load();

            listScene = new Scene(listPane, 900, 600);
            mainScene = new Scene(mainPane, 900, 600);

            stage.setOnCloseRequest(event -> {
                System.exit(0);
            });
            stage.setScene(mainScene);
            stage.setTitle("FS 2020 Livery Manager");
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeScene(WindowState state){
        switch(state){
            case MAIN:
                stage.setScene(mainScene);
                break;
            case LIST:
                stage.setScene(listScene);
                break;
            default:
                System.out.println("No scene found");
                break;
        }
    }

    public enum WindowState {
        MAIN,
        LIST,
    }
}
