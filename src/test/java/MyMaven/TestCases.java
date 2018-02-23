package MyMaven;

import com.sun.tools.doclint.Env;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertNotSame;

public class TestCases {
    //private String AdminURL = "https://52.151.17.83/admin/";
    private String AdminURL = "https://localadmin:8081";
    private boolean verboseMessages = false;                   // true will write each action to the console
    private int secondsToTimeout = 2;
    private int numberRetriesFindElement = 2;
    private enum Browser {CHROME, FIREFOX, IE, EDGE};
    private enum LocateType {ID, NAME, LINK_TEXT, CSS_SELECTOR, CLASS_NAME};
    private enum Rules {USERAGENT, XCARMODEL, XMARKET, XNTGVERION, XSSID, XVIN};
    private enum Environments {DEVELOPMENT, TEST, PRODUCTION}
    private boolean runChrome = true;               // true will runs Chrome cases, false reports Pass without running
    private boolean runFirefox = true;              //                Firefox
    private boolean runIE = true;                   //                Internet Explorer (not running on Win10)
    private boolean runEdge = true;                 //                Edge (only running on Win10)

    @Test
    public void Chrome_AddRemove_UserAgent_Development() {
        if (runChrome)
            RunCase(Browser.CHROME, Rules.USERAGENT, Environments.DEVELOPMENT);
    }

    @Test
    public void Chrome_AddRemove_XCarModel_Development() {
        if (runChrome)
            RunCase(Browser.CHROME, Rules.XCARMODEL, Environments.DEVELOPMENT);
    }

    @Test
    public void Chrome_AddRemove_XMarket_Development() {
        if (runChrome)
            RunCase(Browser.CHROME, Rules.XMARKET, Environments.DEVELOPMENT);
    }

    @Test
    public void Chrome_AddRemove_XNtgVersion_Development() {
        if (runChrome)
            RunCase(Browser.CHROME, Rules.XNTGVERION, Environments.DEVELOPMENT);
    }

    @Test
    public void Chrome_AddRemove_XSSID_Development() {
        if (runChrome)
            RunCase(Browser.CHROME, Rules.XSSID, Environments.DEVELOPMENT);
    }

    @Test
    public void Chrome_AddRemove_XVIN_Development() {
        if (runChrome)
            RunCase(Browser.CHROME, Rules.XVIN, Environments.DEVELOPMENT);
    }

    @Test
    public void Chrome_AddRemove_UserAgent_Test() {
        if (runChrome)
            RunCase(Browser.CHROME, Rules.USERAGENT, Environments.TEST);
    }

    @Test
    public void Chrome_AddRemove_XCarModel_Test() {
        if (runChrome)
            RunCase(Browser.CHROME, Rules.XCARMODEL, Environments.TEST);
    }

    @Test
    public void Chrome_AddRemove_XMarket_Test() {
        if (runChrome)
            RunCase(Browser.CHROME, Rules.XMARKET, Environments.TEST);
    }

    @Test
    public void Chrome_AddRemove_XNtgVersion_Test() {
        if (runChrome)
            RunCase(Browser.CHROME, Rules.XNTGVERION, Environments.TEST);
    }

    @Test
    public void Chrome_AddRemove_XSSID_Test() {
        if (runChrome)
            RunCase(Browser.CHROME, Rules.XSSID, Environments.TEST);
    }

    @Test
    public void Chrome_AddRemove_XVIN_Test() {
        if (runChrome)
            RunCase(Browser.CHROME, Rules.XVIN, Environments.TEST);
    }

    @Test
    public void Firefox_AddRemove_UserAgent_Development() {
        if (runFirefox)
            RunCase(Browser.FIREFOX, Rules.USERAGENT, Environments.DEVELOPMENT);
    }

    @Test
    public void Firefox_AddRemove_XCarModel_Development() {
        if (runFirefox)
            RunCase(Browser.FIREFOX, Rules.XCARMODEL, Environments.DEVELOPMENT);
    }

    @Test
    public void Firefox_AddRemove_XMarket_Development() {
        if (runFirefox)
            RunCase(Browser.FIREFOX, Rules.XMARKET, Environments.DEVELOPMENT);
    }

    @Test
    public void Firefox_AddRemove_XNtgVersion_Development() {
        if (runFirefox)
            RunCase(Browser.FIREFOX, Rules.XNTGVERION, Environments.DEVELOPMENT);
    }

    @Test
    public void Firefox_AddRemove_XSSID_Development() {
        if (runFirefox)
            RunCase(Browser.FIREFOX, Rules.XSSID, Environments.DEVELOPMENT);
    }

    @Test
    public void Firefox_AddRemove_XVIN_Development() {
        if (runFirefox)
            RunCase(Browser.FIREFOX, Rules.XVIN, Environments.DEVELOPMENT);
    }

    @Test
    public void Firefox_AddRemove_UserAgent_Test() {
        if (runFirefox)
            RunCase(Browser.FIREFOX, Rules.USERAGENT, Environments.TEST);
    }

    @Test
    public void Firefox_AddRemove_XCarModel_Test() {
        if (runFirefox)
            RunCase(Browser.FIREFOX, Rules.XCARMODEL, Environments.TEST);
    }

    @Test
    public void Firefox_AddRemove_XMarket_Test() {
        if (runFirefox)
            RunCase(Browser.FIREFOX, Rules.XMARKET, Environments.TEST);
    }

    @Test
    public void Firefox_AddRemove_XNtgVersion_Test() {
        if (runFirefox)
            RunCase(Browser.FIREFOX, Rules.XNTGVERION, Environments.TEST);
    }

    @Test
    public void Firefox_AddRemove_XSSID_Test() {
        if (runFirefox)
            RunCase(Browser.FIREFOX, Rules.XSSID, Environments.TEST);
    }

    @Test
    public void Firefox_AddRemove_XVIN_Test() {
        if (runFirefox)
            RunCase(Browser.FIREFOX, Rules.XVIN, Environments.TEST);
    }

    public void RunCase(Browser browser, Rules rule, Environments environment) {
        WebDriver driver = BrowserFactory(browser);
        DeleteAllRules(driver);

        // Add new rule
        try {
            Add_Rule(driver, rule, environment);
        } catch (Exception ex){}

        // Delete the rule
        try {
            Remove_Rule(driver, 0);
        }
        catch (Exception ex){}

        driver.close();
    }

    private void Sleep(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        }
        catch (Exception ex) {}
    }

    private void Add_Rule(WebDriver driver, Rules rule, Environments environment) {
        VerboseMessages("Add_Rule");
        int initialNumRules = GetCountOfRules(driver);
        String ruleValueToEnter = "";

        // Set the Header field
        WebElement headerField = Locate(driver, LocateType.ID, "input-header", "input-header");
        headerField.click();
        Sleep(1000);
        switch (rule)
        {
            case USERAGENT:
                ruleValueToEnter = "user-agent";
            break;
            case XCARMODEL:
                ruleValueToEnter = "x-car-model";
                headerField.sendKeys(Keys.DOWN);
                break;
            case XMARKET:
                ruleValueToEnter = "x-market";
                headerField.sendKeys(Keys.DOWN);
                Sleep(500);
                headerField.sendKeys(Keys.DOWN);
                break;
            case XNTGVERION:
                ruleValueToEnter = "x-ntg-version";
                headerField.sendKeys(Keys.DOWN);
                Sleep(500);
                headerField.sendKeys(Keys.DOWN);
                Sleep(500);
                headerField.sendKeys(Keys.DOWN);
                break;
            case XSSID:
                ruleValueToEnter = "x-ssid";
                headerField.sendKeys(Keys.DOWN);
                Sleep(500);
                headerField.sendKeys(Keys.DOWN);
                Sleep(500);
                headerField.sendKeys(Keys.DOWN);
                Sleep(500);
                headerField.sendKeys(Keys.DOWN);
                break;
            case XVIN:
                ruleValueToEnter = "12345678901234567";
                headerField.sendKeys(Keys.DOWN);
                Sleep(500);
                headerField.sendKeys(Keys.DOWN);
                Sleep(500);
                headerField.sendKeys(Keys.DOWN);
                Sleep(500);
                headerField.sendKeys(Keys.DOWN);
                Sleep(500);
                headerField.sendKeys(Keys.DOWN);
                break;
        }
        Sleep(500);
        headerField.sendKeys(Keys.RETURN);
        Sleep(500);

        // Set the Rule field
        WebElement inputRule = Locate(driver, LocateType.ID, "input-rule", "input-rule");
        inputRule.sendKeys(ruleValueToEnter);
        Sleep(500);

        // Set the Target field
        WebElement inputTarget = Locate(driver, LocateType.ID, "input-target", "input-target");
        inputTarget.click();
        Sleep(500);
        switch(environment) {
            case DEVELOPMENT:
                break;
            case TEST:
                inputTarget.sendKeys(Keys.DOWN);
                Sleep(500);
                break;
            case PRODUCTION:
                inputTarget.sendKeys(Keys.DOWN);
                Sleep(500);
                inputTarget.sendKeys(Keys.DOWN);
                Sleep(500);
                break;
        }

        // Click the Commit field
        WebElement commitField = Locate(driver, LocateType.ID, "btn-add-rule", "btn-add-rule");
        commitField.click();
        Sleep(500);

        // Click the Save button
        WebElement buttonSave = Locate(driver, LocateType.ID, "btn-save", "btn-save");
        buttonSave.click();
        Sleep(500);

        // Verify rule has been added
        assertNotSame("Failed to add rule", initialNumRules, GetCountOfRules(driver));
    }

    private void Remove_Rule(WebDriver driver, int ruleNumberToRemove) {
        VerboseMessages("Remove_Rule");
        int initialNumRules = GetCountOfRules(driver);
        WebElement removeRule = Locate(driver, LocateType.ID, "btn-remove-" + ruleNumberToRemove, "remove rule" + ruleNumberToRemove);
        removeRule.click();
        Sleep(500);

        // Verify rule has been removed
        assertNotSame("Failed to remove rule", initialNumRules, GetCountOfRules(driver));
    }

    private void DeleteAllRules(WebDriver driver) {
        VerboseMessages("DeleteAllRules");
        WebElement existingRule = null;
        WebElement saveButton = null;
        while (1 > -1) {
            try {
                existingRule = CheckExists(driver, LocateType.ID, "btn-remove-0", "Remove Button 0");
                existingRule.click();
                saveButton = Locate(driver, LocateType.ID, "btn-save", "Save Button");
                saveButton.click();
                Sleep(1000);
            } catch (Exception ex) {
                return;
            }
        }
    }

    private int GetCountOfRules(WebDriver driver) {
        VerboseMessages("GetCountOfRules");
        int numRules = 0;
        while (numRules > -1) {
            try {
                CheckExists(driver, LocateType.ID, "btn-remove-" + numRules, "Remove Button " + numRules);
                numRules++;
            } catch (Exception ex) {
                return numRules;
            }
        }
        return numRules;
    }

    private void VerboseMessages(String message) {
        if (verboseMessages)
            System.out.println(message);
    }

    private WebDriver BrowserFactory(Browser browser) {
        VerboseMessages("BrowserFactory " + browser.name());
        WebDriver driver = null;

        switch (browser) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case IE:
                driver = new InternetExplorerDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            default:
                driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(secondsToTimeout, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(secondsToTimeout, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(secondsToTimeout, TimeUnit.SECONDS);

        driver.navigate().to(AdminURL);
        return driver;
    }

    private WebElement CheckExists(WebDriver driver, LocateType lt, String search, String message) {
        VerboseMessages("CheckExists " + message);
        VerboseMessages("find " + message + " using " + lt + " " + search);
        switch (lt) {
            case ID:
                Sleep(500);
                return driver.findElement(By.id(search));
            case NAME:
                Sleep(500);
                return driver.findElement(By.name(search));
            case LINK_TEXT:
                Sleep(500);
                return driver.findElement(By.linkText(search));
            case CSS_SELECTOR:
                Sleep(500);
                return driver.findElement(By.cssSelector(search));
            case CLASS_NAME:
                Sleep(500);
                return driver.findElement(By.className(search));
            default:
        }
        return null;
    }

    private WebElement Locate(WebDriver driver, LocateType lt, String search, String message) {
        VerboseMessages("Locate " + message);
        WebElement found = null;
        VerboseMessages("find " + message + " using " + lt + " " + search);
        switch (lt) {
            case ID:
                for (int i = 0; i < numberRetriesFindElement; i++) {
                    Sleep(500);
                    try {
                        found = driver.findElement(By.id(search));
                        break;
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                break;
            case NAME:
                for (int i = 0; i < numberRetriesFindElement; i++) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        found = driver.findElement(By.name(search));
                        break;
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                break;
            case LINK_TEXT:
                for (int i = 0; i < numberRetriesFindElement; i++) {
                    Sleep(500);
                    try {
                        found = driver.findElement(By.linkText(search));
                        break;
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                break;
            case CSS_SELECTOR:
                for (int i = 0; i < numberRetriesFindElement; i++) {
                    Sleep(500);
                    try {
                        found = driver.findElement(By.cssSelector(search));
                        break;
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                break;
            case CLASS_NAME:
                for (int i = 0; i < numberRetriesFindElement; i++) {
                    Sleep(500);
                    try {
                        found = driver.findElement(By.className(search));
                        break;
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                break;
            default:
                found = null;
        }
        Sleep(500);
        VerboseMessages("found " + found);
        return found;
    }
}
