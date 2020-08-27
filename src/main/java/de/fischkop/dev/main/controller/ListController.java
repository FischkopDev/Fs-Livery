package de.fischkop.dev.main.controller;

import de.fischkop.dev.main.LiveryManager;
import de.fischkop.dev.main.utils.UtilsManager;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;

import java.io.File;
import java.util.ArrayList;

public class ListController {

    @FXML
    ListView<String> listView;

    @FXML
    protected void back(){
        LiveryManager.changeScene(LiveryManager.WindowState.MAIN);
    }

    @FXML
    protected void loadList(){
        listView.setOrientation(Orientation.VERTICAL);
        ArrayList<String> list = UtilsManager.listFilesAndFolder(new File(LiveryManager.fsPath));
        for (int i =0; i < list.size(); i++){
            listView.getItems().add(list.get(i));
        }
    }

}
