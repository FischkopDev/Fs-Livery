package de.fischkop.dev.main.controller;

import de.fischkop.dev.main.LiveryManager;
import de.fischkop.dev.main.utils.ConfigManager;
import de.fischkop.dev.main.utils.UtilsManager;
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

    private ConfigManager config;

    //init all needed components
    public void initialize(){
        if(path.getText() == null)
            select.setDisable(true);

        config = new ConfigManager();
        if(config.existValue("path")){
            LiveryManager.fsPath = config.readFromFile("path");
            System.out.println("Path loaded from config");
            path.setText(LiveryManager.fsPath);
        }
    }

    //Copying of the content into the community folder of the fs 2020
    @FXML
    protected void selectFolder() {
        File selectedDirectory;
            System.out.println("Opening new directory");
            DirectoryChooser directoryChooser = new DirectoryChooser();
            selectedDirectory = directoryChooser.showDialog(LiveryManager.stage);
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
            System.err.println("File chooser closed without a selected file");
            UtilsManager.warningWindow("FS 2020 Livery Manager","Error: No file selected!","Please select a folder for this action");

        }

    }

    //Selecting the path to the Flightsimulator
    @FXML
    protected void selectPath(){
        try {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(LiveryManager.stage);

            LiveryManager.fsPath = selectedDirectory.getAbsolutePath();
            path.setText(selectedDirectory.getAbsolutePath());

            if (config.existValue("path")) {
                config.editLine("path", selectedDirectory.getAbsolutePath());
                System.out.println("Edited existing value");
            } else {
                config.writeInFile("path", selectedDirectory.getAbsolutePath());
                System.out.println("Added new value");
            }
        }catch(NullPointerException e){
            System.err.println("File chooser closed without a selected file");
            UtilsManager.warningWindow("FS 2020 Livery Manager","Error: No folder selected!","Your path is not set. " +
                    "You are not able to copy files into your FS 2020 folder. Please select the Community Folder.");
        }
        select.setDisable(false);
    }

    //TODO: Future function
    @FXML
    protected void openMarketPlace(){
        System.out.println("Locating to site");
        //Maybe we will get a site :D
    }

    //Opening of another view/scene
    @FXML
    protected void listFiles(){
        LiveryManager.changeScene(LiveryManager.WindowState.LIST);
    }

    //Closing the program
    @FXML
    protected void finish(){
        System.exit(0);
    }
}
