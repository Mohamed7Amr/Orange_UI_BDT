package webpages.navigation_menu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webpages.Page_Base;

public class Navigation_Menu_WebElements extends Page_Base {

    /*****************************************CONSTRUCTORS*******************************************/
    public Navigation_Menu_WebElements(WebDriver driver) {
        super(driver);
    }

    /*****************************************WEB-ELEMENTS******************************************/
    /**
     * Names-Suffix Notes:
     * 1)Txt:TextBox; 2)Link:WebLink; 3)Btn:Button; 4)Li:ListItem;
     * 5)Msg:Message; 6)DDL:DropDownList; 7)Opt:Option; 8)Chbox:CheckBox;
     * 9)Rdo:RadioButton; 10)TxtArea:TextArea; 11)td:table-data(column)
     */
    @FindBy(css = "a[href='/web/index.php/admin/viewAdminModule']")
    WebElement admin_Link;

}