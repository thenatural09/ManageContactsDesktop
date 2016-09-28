package com.troyward;

import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Created by Troy on 9/27/16.
 */
public class ContactWrapper {
    ObservableList<Contact> contacts;

    public ContactWrapper() {
    }

    public ContactWrapper(ObservableList<Contact> contacts) {
        this.contacts = contacts;
    }

    public ObservableList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ObservableList<Contact> contacts) {
        this.contacts = contacts;
    }
}
