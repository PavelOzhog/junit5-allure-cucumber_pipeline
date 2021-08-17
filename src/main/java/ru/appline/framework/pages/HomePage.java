package ru.appline.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BasePage {



    @FindBy(xpath = "//div[@class='kitt-content']//ul[contains(@class,'menu__list_center')]/li")
    List<WebElement> menuBar;


    @FindBy(xpath = "//button[@class='kitt-cookie-warning__close']")
    WebElement btnCookie;

    @FindBy(xpath = "//a[contains(text(), 'Оформить ипотеку')]/../../ul//a[@class='kitt-top-menu__link kitt-top-menu__link_second']")
    List<WebElement> subMenu;




// выбор основного меню из меню
    public HomePage chooseMenu(String string){
        for (WebElement element:menuBar) {
            if (element.getText().trim().equalsIgnoreCase(string)) {
                waitUtilElementToBeClickable(element).click();
                return this;
            }
        }
        Assert.fail("Меню '" + string + "' не было найдено на стартовой странице!");
        return this;
    }


    //выбор из всплывающего меню
    public MortagePage chooseSubMenu(String string){
        for (WebElement element:subMenu) {
            if (element.getText().trim().equalsIgnoreCase(string)) {
                waitUtilElementToBeClickable(element).click();
                return pageManager.getMortagePage();
            }
        }
        Assert.fail("Такое позиции '" + string + "' не было найдено на стартовой странице!");
        return pageManager.getMortagePage();
    }


    //закрытие кнопки Cookie
    public HomePage closeBtnCookie() {
        wait.until(ExpectedConditions.elementToBeClickable(btnCookie));
        btnCookie.click();
        return pageManager.getHomePage();
    }






    }






