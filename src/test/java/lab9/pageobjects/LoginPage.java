package lab9.pageobjects;

import io.qameta.allure.Step;
import lab9.Session;
import lab9.helpers.TestHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

@Component
public class LoginPage {

    private static Logger Log = LogManager.getLogger(LoginPage.class);

    By loc_email = By.cssSelector("input[type='email']");

    @Step
    public void waitUntilLoad (Session session){
        TestHelper.isPageLoad(session.getWaiter(), loc_email, "LoginPage");
    }
}
