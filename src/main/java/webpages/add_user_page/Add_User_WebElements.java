package webpages.add_user_page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import webpages.Page_Base;

public class Add_User_WebElements extends Page_Base {
    /*****************************************CONSTRUCTORS*******************************************/
    public Add_User_WebElements(WebDriver driver) {
        super(driver);
    }

    /*****************************************WEB-ELEMENTS******************************************/
    /**
     * Names-Suffix Notes:
     * 1)Txt:TextBox; 2)Link:WebLink; 3)Btn:Button; 4)Li:ListItem;
     * 5)Msg:Message; 6)DDL:DropDownList; 7)Opt:Option; 8)Chbox:CheckBox;
     * 9)Rdo:RadioButton; 10)TxtArea:TextArea; 11)td:table-data(column)
     */
    By user_Role_DDL_Locator = By.xpath("(//div[@class='oxd-select-text-input'])[1]");
    By admin_Opt_Locator = By.xpath("//div[contains(.,'ESS')]");
    By employee_Name_input_Locator = By.cssSelector("input[placeholder='Type for hints...']");
    By employee_Name_Opt_Locator = By.xpath("//div[contains(.,'Charles')]");
    By status_DDL_Locator = By.xpath("(//div[@class='oxd-select-text-input'])[2]");
    By enabled_Opt_Locator = By.xpath("//div[contains(.,'Enabled')]");
    By disabled_Opt_Locator = By.xpath("//div[contains(.,'Disabled')]");
    By username_Txt_Locator = By.xpath("(//input[@class='oxd-input oxd-input--active'][preceding::label[contains(text(),'Username')]])[1]");
    By password_Txt_Locator = By.xpath("(//input[@type='password'][preceding::label[text()='Password']])[1]");
    By confirm_Password_Txt_Locator = By.xpath("//input[@type='password'][preceding::label[contains(.,'Confirm Password')]]");
    By save_Btn_Locator = By.cssSelector("button[type='submit']");
}
