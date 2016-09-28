package com.troyward;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jodd.json.JsonSerializer;

import java.io.File;
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

    public static void main(String[] args) {
        launch(args);
    }
}
