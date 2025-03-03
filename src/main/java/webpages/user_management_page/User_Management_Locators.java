package webpages.user_management_page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import webpages.Page_Base;

public class User_Management_Locators extends Page_Base {

    /*****************************************CONSTRUCTORS*******************************************/
    public User_Management_Locators(WebDriver driver) {
        super(driver);
    }

    /*****************************************WEB-ELEMENTS******************************************/
    /**
     * Names-Suffix Notes:
     * 1)Txt:TextBox; 2)Link:WebLink; 3)Btn:Button; 4)Li:ListItem;
     * 5)Msg:Message; 6)DDL:DropDownList; 7)Opt:Option; 8)Chbox:CheckBox;
     * 9)Rdo:RadioButton; 10)TxtArea:TextArea; 11)td:table-data(column)
     */
    By records_Number_Label_Locator = By.xpath("//span[contains(.,'Records')]");
    By add_Btn_Locator = By.xpath("//button[contains(.,'Add')]");
    By username_Txt_Locator = By.xpath("//input[@class='oxd-input oxd-input--active'][preceding::label[contains(text(),'Username')]]");
    By search_Btn_Locator = By.cssSelector("button[type='submit']");
    By delete_Btn_Locator = By.xpath("(//button[@class='oxd-icon-button oxd-table-cell-action-space'][@type='button'])[1]");
    By yes_Delete_Btn_Locator = By.xpath("//button[contains(.,' Yes, Delete ')]");
}
