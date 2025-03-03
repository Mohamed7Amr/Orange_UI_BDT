package webpages.login_page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webpages.Page_Base;

public class Login_WebElements extends Page_Base {

    /*****************************************CONSTRUCTORS*******************************************/
    public Login_WebElements(WebDriver driver) {
        super(driver);
    }

    /****************************************NAVIGATION_MENU_WEB-ELEMENTS********************************/
    /**
     * Names-Suffix Notes:
     * 1)Txt:TextBox; 2)Link:WebLink; 3)Btn:Button; 4)Li:ListItem;
     * 5)Msg:Message; 6)DDL:DropDownList; 7)Opt:Option; 8)Chbox:CheckBox;
     * 9)Rdo:RadioButton; 10)TxtArea:TextArea;
     */

    @FindBy(name = "username")
    WebElement username_Txt;
    @FindBy(name = "password")
    WebElement password_Txt;
    @FindBy(css = "button[type='submit']")
    WebElement login_Btn;
}
