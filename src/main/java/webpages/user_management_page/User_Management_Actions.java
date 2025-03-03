package webpages.user_management_page;

import data_store.Flow_Map_Data;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class User_Management_Actions extends User_Management_Locators {

    /*****************************************CONSTRUCTORS*******************************************/
    public User_Management_Actions(WebDriver driver) {
        super(driver);
    }

    /*****************************************METHODS***********************************************/
    @Step("Get number of records")
    public int get_Number_Of_Records()
    {
        return convert_String_To_Integer(getText(find_Element(records_Number_Label_Locator)).replaceAll("[^0-9]",""));
    }
    @Step("Click \"Add\" button")
    public void click_Add_Btn()
    {
        clickWebElement(find_Element(add_Btn_Locator));
    }
    @Step("filters with username")
    public void filter_With_Username(String username)
    {
        writeText(find_Element(username_Txt_Locator),username);
        clickWebElement(find_Element(search_Btn_Locator));
    }
    @Step("Deletes user")
    public void delete_User()
    {
        clickWebElement(find_Element(delete_Btn_Locator));
    }
    @Step("click \"yes,delete\" button")
    public void click_Yes_Delete()
    {
        clickWebElement(find_Element(yes_Delete_Btn_Locator));
        reload();
    }

    /*****************************************ASSERTIONS*******************************************/
    public void validate_Records_Number_Increased_By_One()
    {
        Assert.assertTrue(Flow_Map_Data.get_Map_Key_Data("records_Number").get(1) == (Flow_Map_Data.get_Map_Key_Data("records_Number").get(0))+1);
    }
    public void validate_Records_Number_Is_Decreased_By_One()
    {
        Assert.assertTrue((Flow_Map_Data.get_Map_Key_Data("records_Number").get(1)-1) == (Flow_Map_Data.get_Map_Key_Data("records_Number").get(2)));
    }

}
