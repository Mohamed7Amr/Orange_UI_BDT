package webpages.navigation_menu;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class Navigation_Menu_Actions extends Navigation_Menu_WebElements {

    /*****************************************CONSTRUCTORS*******************************************/
    public Navigation_Menu_Actions(WebDriver driver) {
        super(driver);
    }

    /*****************************************METHODS***********************************************/
    @Step("clicks \"Admin\" link in nav menu")
    public void clicks_Admin_Link() {
        clickWebElement(admin_Link);
    }
}




