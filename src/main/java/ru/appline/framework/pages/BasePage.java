package ru.appline.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.framework.managers.DriverManager;
import ru.appline.framework.managers.PageManager;

import java.util.Locale;

public class BasePage  {





    protected DriverManager driverManager = DriverManager.getDriverManager();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 10, 1000);
    protected JavascriptExecutor js = (JavascriptExecutor) driverManager.getDriver();

    protected PageManager pageManager = PageManager.getPageManager();


    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);

    }

    //метод ожидания кликабельности элемента
    @Step("Ожидаем кликабельность элемента")
    protected WebElement waitUtilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    protected WebElement scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }



    public void switchToFrame(WebElement iFrame){
        driverManager.getDriver().switchTo().defaultContent(); // you are now outside both frames
        driverManager.getDriver().switchTo().frame(iFrame);
    }

    //удаление пробелов в string
    @Step("Вспмогательный метод для преобразования строки в число, удаление пробело")
    protected String changeString(String string) {
        return string.trim().toLowerCase(Locale.ROOT).replaceAll("\\s+", "");
    }

    //преобразование в число
    protected int getIntFromString(String string) {
        return Integer.parseInt(changeString(string).replaceAll("[^\\d.]", ""));
    }



    //удаление знака рубля
    protected String deleteLastSymb(String string) {
        return string = string.substring(0, string.length() - 1);
    }

}
