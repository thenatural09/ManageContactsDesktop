package com.troyward;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
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
        saveToJson(contacts,"contacts.json");
    }

    public void onRemove() {
        Contact contact = (Contact) list.getSelectionModel().getSelectedItem();
        contacts.remove(contact);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.setItems(contacts);
    }

    public static void saveToJson(ObservableList<Contact> contacts,String fileName) throws Exception {
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

}
