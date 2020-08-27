package de.fischkop.dev.main.controller;

import de.fischkop.dev.main.LiveryManager;
import de.fischkop.dev.main.utils.UtilsManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

import java.io.*;

public class MainController {

    @FXML
    TextField path;

    @FXML
    Button select;

    public void initialize(){
        if(path.getText() == null)
            select.setDisable(true);
    }

    @FXML
    protected void selectFolder() {
        File selectedDirectory;
            System.out.println("Opening new directory");
            DirectoryChooser directoryChooser = new DirectoryChooser();
            selectedDirectory = directoryChooser.showDialog(LiveryManager.stage);
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if(LiveryManager.fsPath.contains("FS 2020") && LiveryManager.fsPath.contains("Community")) {
                            UtilsManager.copy(selectedDirectory, new File(LiveryManager.fsPath + "\\" + selectedDirectory.getName()));

                            UtilsManager.informationWindow("FS 2020 Livery Manager","Files copied","The new Livery is installed and ready to use!");
                        }
                        else{
                            UtilsManager.warningWindow("FS 2020 Livery Manager","Error during installation","Please select the correct directory");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();

                        UtilsManager.warningWindow("FS 2020 Livery Manager","Error during installation",e.getMessage());
                    }
                    catch(NullPointerException ex){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                System.err.println("File chooser closed without a selected file");
                                UtilsManager.warningWindow("FS 2020 Livery Manager","Error: No file selected!","Please select a folder for this action");

                            }
                        });
                     }
                }
            });
            t.start();

    }

    @FXML
    protected void selectPath(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(LiveryManager.stage);

        LiveryManager.fsPath = selectedDirectory.getAbsolutePath();
        path.setText(selectedDirectory.getAbsolutePath());

        select.setDisable(false);
    }

    @FXML
    protected void openMarketPlace(){
        System.out.println("Locating to site");
    }

    @FXML
    protected void listFiles(){
        LiveryManager.changeScene(LiveryManager.WindowState.LIST);
    }

    @FXML
    protected void finish(){
        System.exit(0);
    }
}
