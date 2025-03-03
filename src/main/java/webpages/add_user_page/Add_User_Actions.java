package webpages.add_user_page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class Add_User_Actions extends Add_User_WebElements {

    /*****************************************CONSTRUCTORS*******************************************/
    public Add_User_Actions(WebDriver driver) {
        super(driver);
    }

    /*****************************************METHODS***********************************************/
    @Step("Opens \"User Role\" DDL")
    public void open_User_Role_DDL()
    {
        clickWebElement(find_Element(user_Role_DDL_Locator));
    }
    @Step("Select \"Admin\" option")
    public void select_Admin_Option()
    {
        arrow_Down(find_Element(user_Role_DDL_Locator));
        press_Enter(find_Element(user_Role_DDL_Locator));
    }
    @Step("Write employee name")
    public void write_Employee_Name(String employee_Name)
    {
        writeText(find_Element(employee_Name_input_Locator),employee_Name);
        thread_Sleep(3000);
    }

    /**
     * There is a bug (one of many in the UI) regarding clicking on the option
     * where it displays "Invalid" even after choosing the proper option, thus I decided
     * to use arrow down and submit the option instead-of clicking on it, still It displays
     * same error, then I tried to just write the employee name then press "tab" to move to the element-after,
     * I found that the written data gets erased. Eventually, I figured out that one of the bugs related to
     * clicking the element that it does not create extra space (the system accepts only with extra space which is wrong),
     * but when I press enter on one of the options, it creates extra space between first and last names which the
     * system accepts. Also, I made the instance-thread pause for few seconds....
     */
    @Step("Choose \"Charles Carter\" option")
    public void choose_Charles_Option()
    {
        arrow_Down(find_Element(employee_Name_input_Locator));
        thread_Sleep(3000);
        press_Enter(find_Element(employee_Name_input_Locator));
        press_Tab(find_Element(employee_Name_input_Locator));
    }
    @Step("Opens \"Status\" DDL")
    public void open_Status_DDL()
    {
        clickWebElement(find_Element(status_DDL_Locator));
    }
    @Step("Choose status option")
    public void choose_Status_Option(String status_Option)
    {
        switch (status_Option)
        {
            case "E" -> {
                arrow_Down(find_Element(status_DDL_Locator));
                press_Enter(find_Element(status_DDL_Locator));
            }
            case "D" -> {
                arrow_Down(find_Element(status_DDL_Locator));
                arrow_Down(find_Element(status_DDL_Locator));
                press_Enter(find_Element(status_DDL_Locator));
            }
            default -> System.out.println("Wrong Status option");
        }
    }
    @Step("Enter username")
    public void enter_Username(String username)
    {
        writeText(find_Element(username_Txt_Locator),username);
    }
    @Step("Enter password")
    public void enter_Password(String password)
    {
        writeText(find_Element(password_Txt_Locator),password);
    }
    @Step("Enter password confirmation")
    public void enter_Confirm_Password(String password)
    {
        writeText(find_Element(confirm_Password_Txt_Locator),password);
    }
    @Step("Click \"Save\" button")
    public void click_Save()
    {
        clickWebElement(find_Element(save_Btn_Locator));
    }


}
