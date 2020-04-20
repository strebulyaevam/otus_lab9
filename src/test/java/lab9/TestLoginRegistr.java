package lab9;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import lab9.pageobjects.LoginPage;
import lab9.pageobjects.MainMenu;
import lab9.pageobjects.RegistrPage;
import lab9.pageobjects.TopMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
@ContextConfiguration(classes = TestConfig.class)
public class TestLoginRegistr extends AbstractTestNGSpringContextTests {

    @Autowired
    MainMenu mainMenu;

    @Autowired
    TopMenu topMenu;

    @Autowired
    LoginPage loginPage;

    @Autowired
    RegistrPage registrPage;

    @Autowired
    Session session;

    @Epic(value = "Lab9")
    @Feature(value = "Test Login and Registration")
    @Story(value = "Login feature selection")
    @Test
    @Owner(value = "Стребуляева М.")
    public void buttonLoginDispalyTheCorrectPage() throws Exception {
        mainMenu.openMainPage(session);
        topMenu.waitUntilLoad(session);
        topMenu.clickOnLoginButton(session);
        loginPage.waitUntilLoad(session);
        Assert.assertTrue(mainMenu.titleContainString(session, "Вход"));
    }

    @Epic(value = "Lab9")
    @Feature(value = "Test Login and Registration")
    @Story(value = "Registration feature selection")
    @Test
    @Owner(value = "Стребуляева М.")
    public void buttonRegistrationDispalyTheCorrectPage() throws Exception {
        mainMenu.openMainPage(session);
        topMenu.waitUntilLoad(session);
        topMenu.clickOnRegistrButton(session);
        registrPage.waitUntilLoad(session);
        Assert.assertTrue(mainMenu.titleContainString(session, "Регистрация"));
    }
}
