package com_phonebook_tests;

import com_phonebook.models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();

        }
    }

    @Test
    public void loginRegisteredUserPositiveTest() {
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User().setEmail("jorik@gm.com")
                .setPassword("Jorikvart1234$"));
        app.getUser().clickOnLoginButton();
        Assert.assertTrue(app.getUser().isElementPresent(By.cssSelector("button")));
    }


@Test
public void loginRegisteredUserNegativeTestWithoutEmail() {
    app.getUser().clickOnLoginLink();
    app.getUser().fillLoginRegisterForm(new User()
            .setPassword("Jorikvart1234$"));
    app.getUser().clickOnLoginButton();
    Assert.assertTrue(app.getUser().isAlertPresent());
}

}
