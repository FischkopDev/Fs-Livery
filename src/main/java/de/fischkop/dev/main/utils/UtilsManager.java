package de.fischkop.dev.main.utils;


import javafx.scene.control.Alert;

import java.io.*;
import java.util.ArrayList;

/*
    This is a helper class. It contains things like list something or copy
    from one place to another. Furthermore, Information dialogs are implemented
    here and represent a
 */
public class UtilsManager {

    public static ArrayList<String> listFilesForFolder(File folder) {
        ArrayList<String> list = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                list.add(fileEntry.getName());
            }
        }
        return list;
    }

    public static ArrayList<String> listFilesAndFolder(File folder) {
        ArrayList<String> list = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
                list.add(fileEntry.getName());

        }
        return list;
    }

    public static void copy(File sourceLocation, File targetLocation) throws IOException {
        if (sourceLocation.isDirectory()) {
            copyDirectory(sourceLocation, targetLocation);
        } else {
            copyFile(sourceLocation, targetLocation);
        }
    }

    private static void copyDirectory(File source, File target) throws IOException {
        if (!target.exists()) {
            target.mkdir();
        }

        for (String f : source.list()) {
            copy(new File(source, f), new File(target, f));
        }
    }

    private static void copyFile(File source, File target) throws IOException {
        try (
                InputStream in = new FileInputStream(source);
                OutputStream out = new FileOutputStream(target)
        ) {
            byte[] buf = new byte[1024];
            int length;
            while ((length = in.read(buf)) > 0) {
                out.write(buf, 0, length);
            }
        }
    }

    public static void informationWindow(String title, String header, String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void warningWindow(String title, String header, String msg){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
