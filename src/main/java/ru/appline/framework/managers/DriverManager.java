package ru.appline.framework.managers;

import org.apache.commons.exec.OS;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.appline.framework.utils.PropConst;

import java.util.concurrent.TimeUnit;

import static ru.appline.framework.utils.PropConst.*;

public class DriverManager {

    private WebDriver driver = null;
    private TestPropManager testPropManager = TestPropManager.getTestPropManager();

    private static DriverManager INSTANSE = null;

    private DriverManager() {
    }

    public static DriverManager getDriverManager() {
        if (INSTANSE == null) {
            INSTANSE = new DriverManager();
        }
        return INSTANSE;
    }


    public WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", testPropManager.getProperty(PropConst.PATH_CHROME_DRIVER_WINDOWS)); //C:\Users\flash\IdeaProjects\third-project\src\main\resources\drivers\chromedriver.exe
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        return driver;
    }


    private void initDriver() {
        if (OS.isFamilyWindows()) {
            initDriverWindowsOsFamily();
        } else if (OS.isFamilyMac()) {
            initDriverMacOsFamily();
        } else if (OS.isFamilyUnix()) {
            initDriverUnixOsFamily();
        }
    }

    private void initDriverWindowsOsFamily() {
        initDriverWindowsOsFamily();
    }

    private void initDriverMacOsFamily() {
        initDriverAnyOsFamily(PATH_GECKO_DRIVER_MAC, PATH_CHROME_DRIVER_MAC);
    }

    private void initDriverUnixOsFamily() {
        initDriverAnyOsFamily(PATH_GECKO_DRIVER_UNIX, PATH_CHROME_DRIVER_UNIX);
    }

    private void initDriverAnyOsFamily(String gecko, String chrome) {
        switch (testPropManager.getProperty(TYPE_BROWSER)) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", testPropManager.getProperty(gecko));
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", testPropManager.getProperty(chrome));
                driver = new ChromeDriver();
                break;
            default:
                Assertions.fail("Типа браузера '" + testPropManager.getProperty(TYPE_BROWSER) + "' не существует во фреймворке");
        }
    }



    public void quitDriver() {
        driver.quit();
    }



}
