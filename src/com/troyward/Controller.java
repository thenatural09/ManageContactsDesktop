package com.troyward;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    TextField enterName;

    @FXML
    TextField enterPhone;

    @FXML
    TextField enterEmail;

    @FXML
    ListView list;

    ObservableList<Contact> contacts = FXCollections.observableArrayList();
    public void onAdd() throws Exception {
        String t = enterName.getText();
        String t1 = enterPhone.getText();
        String t2 = enterEmail.getText();
        Contact contact = new Contact(t,t1,t2);
        contacts.add(contact);
        if (t.equals("") || t1.equals("") || t2.equals("")) {
            contacts.remove(contact);
        }
        enterName.clear();
        enterPhone.clear();
        enterEmail.clear();
//        saveToJson (,"contacts.json");
    }

    public void onRemove() {
        Contact contact = (Contact) list.getSelectionModel().getSelectedItem();
        contacts.remove(contact);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.setItems(contacts);
        loadJson("contacts.json");
    }

    public static void saveToJson(ArrayList<Contact> contacts, String fileName) throws Exception {
        File f2 = new File(fileName);
        try {
            JsonSerializer serializer = new JsonSerializer();
            ContactWrapper cw = new ContactWrapper(contacts);
            ArrayList<Contact> jsonContacts = new ArrayList<>();
            jsonContacts.addAll(contacts);
            cw.contacts = jsonContacts;
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

}
