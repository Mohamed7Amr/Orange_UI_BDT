package hooks;

import static dataReader.Load_Properties.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Hooks {

    /************************************OBJECTS_DECLARATIONS/INSTANTIATIONS*****************************/
    public static WebDriver driver;

    /**************************************GLOBAL_VARIABLES***********************************************/
    private final String URL = environment_Data.getProperty("URL");
    private final String browser = environment_Data.getProperty("browser");

    /******************************************METHODS**************************************************/

    @Before
    public void setUp_Environment()
    {
        switch (browser)
        {
            case "chrome" -> driver = new ChromeDriver();
            case "firefox" -> driver = new FirefoxDriver();
            case "edge" -> driver = new EdgeDriver();
            case "ChromeHeadless" -> {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
//            options.addArguments("--window-size = 1920,1080");
                driver = new ChromeDriver(options);}
            default -> driver = new ChromeDriver();
        }
        driver.get(URL);
        driver.manage().window().maximize();
    }


    @After
    public void clearUp_Environment()
    {
        driver.quit();
    }

}
