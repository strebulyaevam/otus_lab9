package lab9.pageobjects;

import config.Lab8Config;
import lab9.Session;
import lab9.helpers.TestHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MainMenu {
    private static Logger Log = LogManager.getLogger(MainMenu.class);

    @Autowired
    Lab8Config cfg;

    By loc_1st_item = By.xpath("//div[@class = 'tabs__level tabs-level_top tabs-menu']//h3[contains(text(), 'Статьи')]");
    By loc_main_menu = By.cssSelector("div.tabs__level.tabs-level_top.tabs-menu h3.tabs-menu__item-text");
    By loc_menu_item_byname(String item_name){ return By.xpath("//div[@class = 'tabs__level tabs-level_top tabs-menu']//h3[ contains(text(), '" + item_name +"')]");}

    public void waitUntilLoad (Session session){
        TestHelper.isPageLoad(session.getWaiter(), loc_1st_item, "Main Menu");
    }

    public void clickItemByName (Session session,String menuname) throws Exception {
        By loc_item = By.xpath("//div[@class = 'tabs__level tabs-level_top tabs-menu']//h3[contains(text(), '" + menuname + "')]");
        TestHelper.clickOnElem(session.getWaiter(), loc_item, menuname);
    }

/*
    public ArticlesPage selectArticleItem() throws Exception {
        clickItemByName("Статьи");
        return new ArticlesPage();
     }
*/

    public List<String> getAllMainMenuItems(Session session){
        return TestHelper.getAllMenuItems(session.getWebDriver(), loc_main_menu);
    }

    public boolean isMenuItemSelected (Session session, String item_name){
        return session.getWebDriver().findElement(loc_menu_item_byname(item_name)).getAttribute("class").contains("tabs-menu__item-text_active");
   }

   public void openMainPage (Session session) throws Exception {
       TestHelper.getURL(session.getWebDriver(), cfg.hostname());
       TestHelper.isPageLoad(session.getWaiter(), loc_1st_item, "Main Menu");
   }

   public String getTitle (Session session) {
        return session.getWebDriver().getTitle();
   }

   public boolean titleContainString (Session session, String title_part){
        return session.getWebDriver().getTitle().contains(title_part);
   }
}
