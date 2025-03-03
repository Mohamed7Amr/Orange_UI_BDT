    package webpages;

    import lombok.SneakyThrows;
    import org.apache.commons.io.FileUtils;
    import org.junit.Assert;
    import org.openqa.selenium.Dimension;
    import org.openqa.selenium.Point;
    import org.openqa.selenium.*;
    import org.openqa.selenium.interactions.Actions;
    import org.openqa.selenium.support.PageFactory;
    import org.openqa.selenium.support.ui.ExpectedConditions;
    import org.openqa.selenium.support.ui.WebDriverWait;

    import java.awt.*;
    import java.awt.datatransfer.Clipboard;
    import java.awt.datatransfer.StringSelection;
    import java.awt.event.KeyEvent;
    import java.io.File;
    import java.time.Duration;
    import java.time.LocalDate;
    import java.time.format.DateTimeFormatter;
    import java.util.List;
    import java.util.Set;

    /**
     * @author MAmr
     * 1) This class is where I delacre object (WebDriver data-type) that will have the same value of the object created
     * in the TestBase.
     * 2) This class will have all the common web-elements that can be displayed between all webpages such as nav-menu.
     * 3) This class will have all the common methods that can be used by all other classes in pagesActions package.
     */
    public class Page_Base {

        /*****************************************OBJECTS_DECLARATIONS******************************************/
        protected WebDriver driver;
        private Actions action;
        private JavascriptExecutor js;
        private WebDriverWait wait;
        private Robot robot;

        /*****************************************CONSTRUCTORS*******************************************/
        public Page_Base(WebDriver driver)
        {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        /******************************************USER_ACTIONS**************************************/
        /**
         * 1) This method keeps checking for the element visibility for 5 seconds.
         * 2) If visible, the webpage will be scrolled to the target webElement, and will have background-color as green &
         * borders as black.
         * 3) try code; click on the element exactly as shown on the UI (if there's overlay, won't be clicked).
         * 4) catch code; Click on the element from the DOM, thus even if the element is covered by an overlay but already
         * exist in the DOM, then it will be clicked.
         * @param ele the target WebElement
         */
        protected void clickWebElement(WebElement ele)
        {
            try {
                wait_Element_Visibility(60,ele);
            }
            catch (Exception e) {
                wait_Invisible_Element(10,ele);
                System.out.println(ele.getAccessibleName() + ": is invisible ");
            }

            scrollTo_Highlight(ele, "green");

            try {
                ele.click();
            }
            catch (Exception e) {
                js_Click(ele);
                System.out.println("JS click has been used on: " + ele.getAccessibleName());
            }
        }

        /**
         * 1) This method keeps checking for the element visibility for 5 seconds.
         * 2) If visible, the webpage will be scrolled to the target webElement, and will have background-color as yellow &
         * borders as black.
         * 3) Send text to the Target WebElement.
         * @param ele the target WebElement
         * @param text the text that shall be written in the target webElement
         */
        protected void writeText(WebElement ele, String text)
        {
            wait_Element_Visibility(60, ele);
            scrollTo_Highlight(ele,"yellow");
            ele.sendKeys(text);
        }
        protected String getText(WebElement ele)
        {
            wait_Element_Visibility(60, ele);
            scrollTo_Highlight(ele,"yellow");
            return ele.getText();
        }
        protected void thread_Sleep(int seconds)
        {
            try {
                Thread.sleep(seconds);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        /****************************************NAVIGATION_ACTIONS*******************************************/
        public void reload()
        {
            driver.navigate().refresh();
        }

        /*****************************************LOCATING_ELEMENTS********************************************/
        /**
         * It's better using locators as parameters with ExpectedCondition class to wait upon,
         * they are more stable than web-elements, and in this method you can send locators extracted
         * by different selectors to this method
         * @param locator it must have "By" data-type because it carries value of By.Selector(xpath,cssSelect,id,etc..)
         * @return web-element object of data-type "WebElement" to interact with.
         */
        protected WebElement find_Element(By locator)
        {
            return driver.findElement(wait_Locator_Visibility(locator));
        }

        /*****************************************ASSISTIVE_METHODS******************************************/
        protected void scrollTo_Highlight(WebElement ele, String color)
        {
            js_Scroll_View_Element_Center_With_Window(ele);
            js_Highlight_Element(ele,color);
        }

        /******************************************JS_INJECTION*********************************************/
        protected void js_Click(WebElement ele)
        {
            js = (JavascriptExecutor) driver;
            js_Focus(ele);
            js.executeScript("arguments[0].click()", ele);
        }
        protected void js_Write_Text(WebElement ele, String text)
        {
            js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value='"+text+"';", ele);
        }
        protected void js_Focus(WebElement ele)
        {
            js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].focus();",ele);
        }
        protected void js_Highlight_Element(WebElement ele, String color)
        {
            js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.background = '"+ color + "'", ele);
            js.executeScript("arguments[0].style.border='2px solid red'", ele);
        }
        protected void js_Scroll_View_Element_Center_With_Window(WebElement ele)
        {
            js = (JavascriptExecutor) driver;

            Point coordinates = ele.getLocation();
            int x = coordinates.getX();
            int y = coordinates.getY();

            Dimension dims = driver.manage().window().getSize();
            int windowWidth = dims.getWidth();
            int windowHeight = dims.getHeight();

            js.executeScript("window.scrollTo(" + (x - windowWidth/2) + "," + (y - windowHeight/2) + ");");
        }

        /******************************************EXPLICIT_WAIT*********************************************/
        protected By wait_Locator_Visibility(By locator)
        {
            wait = new WebDriverWait(driver,Duration.ofSeconds(60));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return locator;
        }
        protected WebElement wait_Element_Visibility(int seconds, WebElement ele)
        {
            wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.visibilityOf(ele));
            return ele;
        }
        protected void wait_Invisible_Element(int seconds, WebElement ele)
        {
            wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.invisibilityOf(ele));
        }

        /**
         * This method checks the default value of the src attribute, then keeps checking (polling) if it has its value
         * changed after uploading a new image. Condition shall be fulfilled once the condition of having a default value
         * is inverted.
         * @param seconds is the time to make sure that the value of src attribute has changed from its default value
         * @param ele img-tag webElement that has src attribute with its default value used by the dev team
         * @param src_Attribute attribute that has its value changed after uploading a new image to its img-tag component
         * @param src_Attribute_Value default value that I do my validation upon, making sure it changed after uploading
         */
        protected void wait_Element_Src_Attribute_Value(int seconds, WebElement ele, String src_Attribute, String src_Attribute_Value)
        {
            wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(ele, src_Attribute, src_Attribute_Value)));
        }

        /**
         * this method is to wait for a specific element to have "disabled" attribute which makes the element dimmed and
         * not intractable with, also its value is empty string in the DOM.
         */
        protected void wait_Disable_Element(int seconds, WebElement ele)
        {
            wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.attributeToBe(ele,"disabled",""));
        }

        /******************************************ROBOT_CLASS******************************************/
        /**
         * Method to select anything String (path,data) and then put it in the Windows-system's clipboard to be pasted later.
         * @param sth_To_Copy of String data-type
         */
        protected void copy_File_Inside_Project(String sth_To_Copy)
        {
            StringSelection selection = new StringSelection(sth_To_Copy);
            Clipboard system_Clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            system_Clipboard.setContents(selection,null);
        }
        @SneakyThrows
        protected void paste_Ctrl_V()
        {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.delay(1000);
            robot.keyPress(KeyEvent.VK_V);
            robot.delay(2000);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
            robot.delay(2000);
        }
        @SneakyThrows
        protected void press_Enter_Button()
        {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }

        /**
         * Using Robot class to upload image by simulating mouse & keyboard actions upon windows features,
         * I am calling a method that can copy a String to system's clipboard, then using Robot methods to paste and
         * click enter. Then waiting for the src default value to be changed before asserting that it really changed.
         * @param sth_To_Copy the String (path or data) that I want to put in the system-clipboard
         * @param img_Ele is the img-element that I want to check its src value
         * @param src_Attribute is the attribute that has a default value
         * @param src_Attribute_Value default value of src attribute
         */
        protected void upload_Robot(String sth_To_Copy, WebElement img_Ele, String src_Attribute, String src_Attribute_Value)
        {
            copy_File_Inside_Project(sth_To_Copy);
            paste_Ctrl_V();
            press_Enter_Button();
            wait_Element_Src_Attribute_Value(60, img_Ele, src_Attribute, src_Attribute_Value);
        }

        /******************************************KEYS_CLASS********************************************/
        public void arrow_Down(WebElement ele)
        {
            ele.sendKeys(Keys.ARROW_DOWN);
        }
        public void arrow_Up(WebElement ele)
        {
            ele.sendKeys(Keys.ARROW_UP);
        }
        public void press_Enter(WebElement ele)
        {
            ele.sendKeys(Keys.ENTER);
        }
        public void press_Tab(WebElement ele)
        {
            ele.sendKeys(Keys.TAB);
        }
        /******************************************COMMON_ASSERTIONS*************************************/
        protected void assert_Actual_Text_Contains_Expected_Text(WebElement ele1, WebElement ele2, String assertion_Message)
        {
            String expected_Result = getText(ele1);
            String actual_Result = getText(ele2);
            Assert.assertTrue(assertion_Message,actual_Result.contains(expected_Result));
        }
        protected void assert_Actual_Text_Contains_Expected_Text(String text, WebElement ele, String assertion_Message)
        {
            String actual_Result = getText(ele);
            Assert.assertTrue(assertion_Message,actual_Result.contains(text));
        }
        protected void assert_Actual_Text_contains_Expected_Text(By locator1, By locator2, String assertion_Message)
        {
            String expected_Result = getText(find_Element(locator1));
            String actual_Result = getText(find_Element(locator2));
            Assert.assertTrue(assertion_Message,actual_Result.contains(expected_Result));
        }
        protected void assert_Actual_Text_Contains_Expected_Text(String text, By locator, String assertion_Message)
        {
            String actual_Result = getText(find_Element(locator));
            Assert.assertTrue(assertion_Message,actual_Result.contains(text));
        }
        protected void assert_Element_Is_Visible(WebElement ele, String assertion_Message) {
            Assert.assertTrue(assertion_Message, wait_Element_Visibility(60, ele).isDisplayed());
        }

        /******************************************DATA_TYPES_CONVERSIONS********************************/
        protected int convert_String_To_Integer(String data)
        {
            return Integer.parseInt(data);
        }

        /******************************************FORMAT_CHANGE****************************************/
        protected String fwdSlash_To_Hyphen_Date_Format(String hyphen_Date_Format)
        {
            return hyphen_Date_Format.replaceAll("/","-");
        }

        /******************************************SCREENSHOT_INTERFACE***************************************/
        @SneakyThrows
        public void take_ScreenShot(String pass_Fail_Dir, String ssName_Extension) {
            TakesScreenshot ss = (TakesScreenshot) driver;
            File sourceFile = ss.getScreenshotAs(OutputType.FILE);
            File targetFile = new File("Screenshots/"+ pass_Fail_Dir + ssName_Extension);
            FileUtils.copyFile(sourceFile,targetFile);
        }


    }
