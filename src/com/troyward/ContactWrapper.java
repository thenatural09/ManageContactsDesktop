package com.troyward;

import javafx.collections.ObservableList;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Troy on 9/27/16.
 */
public class ContactWrapper {
    public ArrayList<Contact> contacts;

    public ContactWrapper() {
    }

    public ContactWrapper(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }
}
