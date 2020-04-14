package lab9;

import com.google.common.collect.ImmutableList;
import lab9.pageobjects.MainMenu;
import lab9.pageobjects.TopMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Test
@ContextConfiguration(classes = TestConfig.class)
public class TestHabrHomePage extends AbstractTestNGSpringContextTests {

    @Autowired
    MainMenu mainMenu;

    @Autowired
    TopMenu topMenu;

    @Autowired
    Session session;

    @Test
    public void openHabrHomePage() throws Exception {
        mainMenu.openMainPage(session);
        Assert.assertEquals(mainMenu.getTitle(session), "Лучшие публикации за сутки / Хабр");
    }

    @Test
    public void topBarIsOntheHomePage() throws Exception {
        List<String> expResult = ImmutableList.<String>builder()
                .add("Все потоки")
                .add("Разработка")
                .add("Научпоп")
                .add("Администрирование")
                .add("Дизайн")
                .add("Менеджмент")
                .add("Маркетинг")
                .build();

        mainMenu.openMainPage(session);
        topMenu.waitUntilLoad(session);
        List<String> actualResult = topMenu.getAllTopMenuItems(session);
        Assert.assertEquals(expResult, actualResult);
    }

    @Test
    public void firstItemOfTopBarIsSelected() throws Exception {
        mainMenu.openMainPage(session);
        topMenu.waitUntilLoad(session);
        Assert.assertTrue(topMenu.isMenuItemSelected(session,"Все потоки"));
    }
}
