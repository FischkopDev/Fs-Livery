package de.fischkop.dev.main.utils;

import java.io.*;
import java.util.ArrayList;

/*
    This class manages all events that happening with the config file.
    The config contains needed information about the system or preset
    program states. With the following methods you are able to read,write
    or edit specific lines.
 */
public class ConfigManager {

    //directory and file
    private File dir = new File(System.getenv("APPDATA")+"\\FS-Manager\\");
    private File file = new File(System.getenv("APPDATA")+"\\FS-Manager\\config.conf");

    //Constructor which creates the actual directory and file
    public ConfigManager(){
        try{
            dir.mkdirs();
            file.createNewFile();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //
    public String readFromFile(String key){
        try{
            String value = "";
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = "";
            while((line = br.readLine()) != null){
                if(line.contains(key)){
                    return line.split("=")[1];
                }
            }

            fr.close();
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean existValue(String key){
        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = "";
            while((line = br.readLine()) != null){
                if(line.contains(key)){
                    return true;
                }
            }

            fr.close();
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }

    public void editLine(String key, String value){
        try{
            //Reading from file
            ArrayList<String> content = new ArrayList<>();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = "";
            while((line = br.readLine()) != null) {
                content.add(line);
            }

            //editing the value
            for(int i= 0; i <content.size(); i++){
                if(content.get(i).contains(key)){
                    content.set(i, key+"="+value);
                }
            }

            //write back in file
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            for(int i= 0; i <content.size(); i++){
                bw.write(content.get(i));
                bw.newLine();
            }

            //closing everything
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void writeInFile(String key, String value){
        try{
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);

            br.write(key + "=" + value);
            br.newLine();

            br.close();
            fr.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
