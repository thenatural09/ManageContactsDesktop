package com.troyward;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("contacts.fxml"));
        primaryStage.setTitle("Contacts Desktop");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void saveToJson(ObservableList<Contact> contacts, String fileName) throws Exception {
        File f2 = new File(fileName);
        try {
            JsonSerializer serializer = new JsonSerializer();
            ContactWrapper cw = new ContactWrapper(contacts);
            cw.contacts = contacts;
            String json = serializer.deep(true).serialize(cw);
            FileWriter fw = new FileWriter(f2);
            fw.write(json);
            fw.close();
        } catch(Exception e) {
            throw new Exception("Can't save");
        }
    }

    public static void loadJson(String fileName) {
        File f = new File(fileName);
        FileReader fr = null;
        try {
            fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] contents = new char[fileSize];
            fr.read(contents, 0, fileSize);
            JsonParser parser = new JsonParser();
            ContactWrapper cw = parser.parse(contents,ContactWrapper.class);
            System.out.println(cw);
        } catch (Exception e) {
            System.out.println("Couldn't load file");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
