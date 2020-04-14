package lab9;

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

    @Test
    public void buttonLoginDispalyTheCorrectPage() throws Exception {
        mainMenu.openMainPage(session);
        topMenu.waitUntilLoad(session);
        topMenu.clickOnLoginButton(session);
        loginPage.waitUntilLoad(session);
        Assert.assertTrue(mainMenu.titleContainString(session, "Вход"));
    }

    @Test
    public void buttonRegistrationDispalyTheCorrectPage() throws Exception {
        mainMenu.openMainPage(session);
        topMenu.waitUntilLoad(session);
        topMenu.clickOnRegistrButton(session);
        registrPage.waitUntilLoad(session);
        Assert.assertTrue(mainMenu.titleContainString(session, "Регистрация"));
    }
}
