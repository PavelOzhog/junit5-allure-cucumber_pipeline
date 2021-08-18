package ru.appline.framework.managers;

import org.apache.commons.exec.OS;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.appline.framework.utils.PropConst;

import java.net.MalformedURLException;
import java.net.URI;
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
            switch (testPropManager.getProperty(TYPE_BROWSER)) {
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", testPropManager.getProperty("webdriver.gecko.driver"));
                    driver = new FirefoxDriver();
                    break;
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("incognito");
                    options.addArguments("--disable-notifications");
                    System.setProperty("webdriver.chrome.driver", testPropManager.getProperty("webdriver.chrome.driver.windows"));
                    driver = new ChromeDriver(options);
                    break;
                case "remote":
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setBrowserName("chrome");
                    capabilities.setVersion("73.0");
                    capabilities.setCapability("enableVNC", true);
                    capabilities.setCapability("enableVideo", false);
                    try {
                        driver = new RemoteWebDriver(
                                URI.create("http://selenoid.appline.ru:4445/wd/hub/").toURL(),
                                capabilities);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    Assertions.fail("Типа браузера '" + testPropManager.getProperty("browser") + "' не существует во фреймворке");
            }
        }

        return driver;
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
            case "remote":
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName("chrome");
                capabilities.setVersion("73.0");
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", false);
                try {
                    driver = new RemoteWebDriver(
                            URI.create("http://selenoid.appline.ru:4445/wd/hub/").toURL(),
                            capabilities);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                Assertions.fail("Типа браузера '" + testPropManager.getProperty(TYPE_BROWSER) + "' не существует во фреймворке");
        }
    }


    public void quitDriver() {
        driver.quit();
    }


}
