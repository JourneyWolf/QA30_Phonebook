package com_phonebook_tests;

import com_phonebook.models.Contact;
import com_phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        if (!app.getUser().isLoginLinkPresent()){
            app.getUser().clickOnSignOutButton();
        }

        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User().setEmail("jorik@gm.com")
                .setPassword("Jorikvart1234$"));
        app.getUser().clickOnLoginButton();


        app.getContact().clickOnAddLink();
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(new Contact()
                .setName("Jorik")
                .setLastname("Patrik")
                .setPhone("1234567890")
                .setEmail("jorik@gm.com")
                .setAddress("Berlin")
                .setDescription("fussball"));
        app.getContact().clickOnSaveButton();
    }

    @Test
    public void removeContactTest() {
        int sizeBefore = app.getContact().sizeOfContacts();
        app.getContact().removeContact();
        app.getContact().pause(1000);
        int sizeAfter = app.getContact().sizeOfContacts();

        Assert.assertEquals(sizeAfter, sizeBefore - 1);
    }

}
