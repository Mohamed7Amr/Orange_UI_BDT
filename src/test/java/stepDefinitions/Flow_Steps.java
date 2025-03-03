package stepDefinitions;

import com.github.javafaker.Faker;
import data_store.Flow_Map_Data;
import hooks.Hooks;
import io.cucumber.java.en.*;
import webpages.add_user_page.Add_User_Actions;
import webpages.user_management_page.User_Management_Actions;
import webpages.login_page.Login_Actions;
import webpages.navigation_menu.Navigation_Menu_Actions;
import static dataReader.Load_Properties.*;

public class Flow_Steps {

    /********************************OBJECTS_DECLARATIONS/INSTANTIATIONS*************************************/
    private Login_Actions lp = new Login_Actions(Hooks.driver);
    private Navigation_Menu_Actions nma = new Navigation_Menu_Actions(Hooks.driver);
    private User_Management_Actions uma = new User_Management_Actions(Hooks.driver);
    private Add_User_Actions aua = new Add_User_Actions(Hooks.driver);
    private Faker fake_Data = new Faker();

    /****************************************TEST_DATA************************************************/

    private final String login_Username = login_Data.getProperty("login_Username");
    private final String login_Password = login_Data.getProperty("login_Password");
    private final String enabled_Status_Option = flow_Data.getProperty("enabled_Status_Option");
    private final String disabled_Status_Option = flow_Data.getProperty("disabled_Status_Option");
    private final String add_User_Username = fake_Data.numerify("Mohamed##");
    private final String add_User_Password = flow_Data.getProperty("add_User_Password");
    private final String add_User_Confirm_Password = flow_Data.getProperty("add_User_Confirm_Password");


    /***************************************TEST_STEPS***************************************************/

    @When("user logs-in with valid username and valid password")
    public void login()
    {
        lp.Login(login_Username,login_Password);
    }
    @And("clicks \"Admin\" link in nav menu")
    public void clicks_Admin_Link()
    {
        nma.clicks_Admin_Link();
    }
    @And("get records number")
    public void get_Records_Number()
    {
        Flow_Map_Data.add_Map_Key_Data("records_Number",uma.get_Number_Of_Records());
        System.out.println(Flow_Map_Data.get_Map_Key_Data("records_Number"));
    }
    @And("Click \"Add\" button")
    public void click_Add_Btn()
    {
        uma.click_Add_Btn();
    }
    @And("Opens \"User Role\" DDL")
    public void open_User_Role_DDL()
    {
        aua.open_User_Role_DDL();
    }
    @And("Select \"Admin\" option")
    public void select_Admin_Option()
    {
        aua.select_Admin_Option();
    }
    @And("^Write employee name \"(.*)\"$")
    public void write_Employee_Name(String employee_Name)
    {
        aua.write_Employee_Name(employee_Name);
    }
    @And("Choose \"Charles Carter\" option")
    public void choose_Charles_Option()
    {
        aua.choose_Charles_Option();
    }
    @And("Opens \"Status\" DDL")
    public void open_Status_DDL()
    {
        aua.open_Status_DDL();
    }
    @And("Choose status option")
    public void choose_Status_Option()
    {
        aua.choose_Status_Option(disabled_Status_Option);
    }
    @And("Enter username")
    public void enter_Username()
    {
        aua.enter_Username(add_User_Username);
    }
    @And(("Enter password"))
    public void enter_Password()
    {
        aua.enter_Password(add_User_Password);
    }
    @And("Enter password confirmation")
    public void enter_Confirmation_Password()
    {
        aua.enter_Confirm_Password(add_User_Confirm_Password);
    }
    @And("Click \"Save\" button")
    public void click_Save()
    {
        aua.click_Save();
    }
    @Then("records number is increased by one")
    public void validate_Records_Number_Is_Increased_By_One()
    {
        uma.validate_Records_Number_Increased_By_One();
    }
    @When("filters with username")
    public void filter_With_Username()
    {
        uma.filter_With_Username(add_User_Username);
    }
    @And("Deletes user")
    public void delete_User()
    {
        uma.delete_User();
    }
    @And("click \"yes,delete\" button")
    public void click_Yes_Delete()
    {
        uma.click_Yes_Delete();
    }
    @Then("records number is decreased by one")
    public void validate_Records_Number_Is_Decreased_By_One()
    {
        uma.validate_Records_Number_Is_Decreased_By_One();
    }

}
