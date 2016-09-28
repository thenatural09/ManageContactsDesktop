package com.troyward;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.*;
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
    ArrayList<Contact> jsonContacts = new ArrayList<>();
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
        jsonContacts.addAll(contacts);
        System.out.println(jsonContacts.toString());
        saveToJson(jsonContacts,"contacts.json");
    }

    public void onRemove() {
        Contact contact = (Contact) list.getSelectionModel().getSelectedItem();
        contacts.remove(contact);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contacts.addAll(jsonContacts);
        try {
            list.setItems(loadJson("contacts.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void saveToJson(ArrayList<Contact> contacts, String fileName) throws IOException {
        File f2 = new File(fileName);
        JsonSerializer serializer = new JsonSerializer();
        ContactWrapper cw = new ContactWrapper(contacts);
        cw.contacts = contacts;
        String json = serializer.deep(true).serialize(cw);
        FileWriter fw = new FileWriter(f2);
        fw.write(json);
        fw.close();
    }

    public static ObservableList<Contact> loadJson(String fileName) throws FileNotFoundException {
        File f = new File(fileName);
        FileReader fr = new FileReader(f);
        int fileSize = (int) f.length();
        char[] contents = new char[fileSize];
        try {
            fr.read(contents, 0, fileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonParser parser = new JsonParser();
        ContactWrapper cw = parser.parse(contents,ContactWrapper.class);
        ObservableList<Contact> contactsToLoad = FXCollections.observableArrayList();
        contactsToLoad.addAll(cw.contacts);
        System.out.println(cw);
        return contactsToLoad;
    }
}
