package com_phonebook.utils;

import com_phonebook.models.Contact;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

    @DataProvider
    public Iterator<Object[]> addNewContact() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Alex1", "Zweig", "1234567890", "zweig@gm.com", "Berlin", "goalkeeper"});
        list.add(new Object[]{"Alex2", "Zweig", "1234567899", "zweig@gm.com", "Berlin", "goalkeeper"});
        list.add(new Object[]{"Alex3", "Zweig", "1234567898", "zweig@gm.com", "Berlin", "goalkeeper"});

        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> addNewContactFromCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader
                (new FileReader(new File("src/test/resources/contact.csv")));

        String line = reader.readLine();
        while (line!=null) {
            String[] split = line.split(",");

            list.add(new Object[]{new Contact().setName(split[0]).setLastname(split[1]).setPhone(split[2])
                    .setEmail(split[3]).setAddress(split[4]).setDescription(split[5])});
            line = reader.readLine();
        }
        return list.iterator();

    }
}

