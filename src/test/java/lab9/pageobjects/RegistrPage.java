package lab9.pageobjects;

import lab9.Session;
import lab9.helpers.TestHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

@Component
public class RegistrPage {
    private static Logger Log = LogManager.getLogger(RegistrPage.class);

    By loc_email = By.cssSelector("input[type='email']");

    public void waitUntilLoad (Session session){
        TestHelper.isPageLoad(session.getWaiter(), loc_email, "RegistrationPage");
    }
}
