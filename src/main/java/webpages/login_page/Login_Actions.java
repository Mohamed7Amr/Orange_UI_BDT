package webpages.login_page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class Login_Actions extends Login_WebElements {

    /*****************************************CONSTRUCTORS*******************************************/
    public Login_Actions(WebDriver driver) {
        super(driver);
    }

    /****************************************ACTIONS_ON_NAV_MENU_WEB-ELEMENTS*******************************/

    @Step("Enter Email")
    public void Enter_Email(String username)
    {
        writeText(username_Txt, username);
    }
    @Step("Enter password")
    public void Enter_Password(String password)
    {
        writeText(password_Txt, password);
    }
    @Step("Clicks \"Login\" button")
    public void click_Login_Btn()
    {
        clickWebElement(login_Btn);
    }


    /**
     * It sums-up several methods for the process of logging-in
     * @param username unique username to login
     * @param password unique password to login
     */
    @Step("Merchant Logins to the portal")
    public void Login(String username, String password)
    {
        Enter_Email(username);
        Enter_Password(password);
        click_Login_Btn();
    }

}