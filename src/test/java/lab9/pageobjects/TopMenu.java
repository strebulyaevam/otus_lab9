package lab9.pageobjects;

import lab9.Session;
import lab9.helpers.TestHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TopMenu {
    private static Logger Log = LogManager.getLogger(TopMenu.class);

    By loc_1st_item = By.xpath("//div[@class='main-navbar']//a[@href='https://habr.com/ru/all/']");

    By loc_top_menu = By.cssSelector("ul.nav-links>li a");
    By loc_topmenu_item_byname(String item_name){ return By.xpath("//div[@class='main-navbar']//li[@class='nav-links__item']//a[contains(text(), '" + item_name + "')]");}
    By loc_loginbtn = By.cssSelector("a#login");
    By lo_regist_btn = By.cssSelector("a.btn.btn_medium.btn_navbar_registration");

    public void waitUntilLoad (Session session){
        TestHelper.isPageLoad(session.getWaiter(), loc_1st_item, "Top Menu");
    }

    public void clickTopMenuItemByName (Session session, String menuname) throws Exception {
        Log.info("Try to click on " + menuname + " menu item in TOP menu");
        TestHelper.clickOnElem(session.getWaiter(), loc_topmenu_item_byname(menuname), menuname + " menu item in TOP menu");
        Log.info("Get menu " + menuname + " successfully");
    }

    public List<String> getAllTopMenuItems(Session session){
        return TestHelper.getAllMenuItems(session.getWebDriver(), loc_top_menu);
    }

    public boolean isMenuItemSelected (Session session, String item_name){
        return session.getWebDriver().findElement(loc_topmenu_item_byname(item_name)).getAttribute("class").contains("nav-links__item-link_current");
    }

    public void clickOnLoginButton(Session session) throws Exception {
        TestHelper.clickOnElem(session.getWaiter(), loc_loginbtn, "Login btn");
    }

    public void clickOnRegistrButton(Session session) throws Exception {
        TestHelper.clickOnElem(session.getWaiter(), lo_regist_btn, "Registration btn");
    }

}
