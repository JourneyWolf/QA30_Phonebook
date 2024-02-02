package com_phonebook_tests;

import com_phonebook.models.Contact;
import com_phonebook.models.User;
import com_phonebook.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddNewContactTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();

        }
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User().setEmail("jorik@gm.com")
                .setPassword("Jorikvart1234$"));
        app.getUser().clickOnLoginButton();
    }

    @Test
    public void addNewContactPositiveTest() {
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(new Contact()
                .setName("Jorik")
                .setLastname("Patrik")
                .setPhone("1234567890")
                .setEmail("jorik@gm.com")
                .setAddress("Berlin")
                .setDescription("fussball"));
        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isContactCreated("Jorik"));

    }

    @AfterMethod
    public void postCondition() {
        app.getContact().removeContact();
    }


    @Test(dataProvider = "addNewContact", dataProviderClass = DataProviders.class)
    public void addNewContactPositiveTestFromDataProvider(String name, String lastname, String phone,
                                                          String email, String address, String desc) {
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(new Contact()
                .setName(name)
                .setLastname(lastname)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                .setDescription(desc));
        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isContactCreated(name));

    }


    @Test(dataProvider = "addNewContactFromCSV", dataProviderClass = DataProviders.class)
    public void addNewContactPositiveTestFromDataProviderWithCSV(Contact contact) {
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(contact);
        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isContactCreated(contact.getName()));

    }

}