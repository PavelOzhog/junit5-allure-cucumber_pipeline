package ru.appline.framework.pages;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.appline.framework.utils.FormatString;

import java.util.List;

public class MortagePage extends BasePage {


    @FindBy(xpath = "//div[contains(@class,'xs-top_20 kit-col_lg-top_40')]/h1")
    WebElement mortagePageHeader;

    @FindBy(xpath = "//iframe[@sandbox='allow-forms allow-scripts allow-same-origin allow-popups allow-top-navigation']")
    public static WebElement iFrameCulc;

    @FindBy(xpath = "//div[@class='dc-input__label-4-9-1']")
    List<WebElement> listOfFields;


    @FindBy(xpath = "//div[contains(text(),'Стоимость недвижимости')]/../input")
    WebElement costOfRealty;

    @FindBy(xpath = "//div[contains(text(),'Первоначальный взнос')]/../input")
    WebElement firstContibution;

    @FindBy(xpath = "//div[contains(text(),'Срок кредита')]/../input")
    WebElement creditTerm;

    @FindBy(xpath = "//span[contains(text(), 'Страхование жизни и здоровья')]/../.././/input")
    WebElement insuarenceBtn;

    @FindBy(xpath = "//span[contains(text(), 'Молодая семья')]/../.././/input")
    WebElement youngFamilyBtn;

    @FindBy(xpath = "//div[contains(text(), 'Услуги, снижающие ставку по кредиту')]")
    WebElement services;

    @FindBy(xpath = "//span[contains(text(),'Сумма кредита')]/../span[@data-e2e-id='mland-calculator/mobile-result-credit-sum']")
    WebElement sumOfCredit;

    @FindBy(xpath = "//span[contains(text(),'Ежемесячный платеж')]/../span[@data-e2e-id='mland-calculator/medium-result-monthly-payment']")
    WebElement monthPayment;

    @FindBy(xpath = "//span[contains(text(),'Необходимый доход')]/../span[@data-e2e-id='mland-calculator/result-required-income']")
    WebElement necessaryIncome;

    @FindBy(xpath = "//span[contains(text(),'Процентная ставка')]/../span[@data-e2e-id='mland-calculator/medium-result-credit-rate']")
    WebElement procents;

    @FindBy(xpath = "//span[contains(text(),'Налоговый вычет')]/../..//span[@data-e2e-id='mland-calculator/mobile-result-tax-deduction']/span")
    WebElement taxDeduc;


    //метод заполняющий поле (Любое)
    public MortagePage fillField(String nameField, String value) {
        WebElement element = null;
        switch (nameField) {
            case "Стоимость недвижимости":
                fillPageCostOfRealty(value);
                element = costOfRealty;
                break;
            case "Первоначальный взнос":
                fillFirstContribution(value);
                element = firstContibution;
                break;

            case "Срок кредита":
                fillCreditTerm(value);
                element = creditTerm;
                break;
            default:
                Assert.fail("Поле с наименованием '" + nameField + "' отсутствует на странице " +
                        "'Заполнение заявки на ипотеку'");
        }

//        Assert.assertEquals("Поле '" + nameField + "' было заполнено некорректно",
//                value, changeString(element.getAttribute("value")));
        return this;
    }

    //метод выбора радио кнопки
    public MortagePage selectRadioBtn(String nameRadioBtn, String value) {
        WebElement element = null;
        switch (nameRadioBtn) {
            case "Страхование жизни и здоровья":
                element = insuarenceBtn;
                selectRadioButton(element, value);
                wait.until(ExpectedConditions.attributeContains(element, "aria-checked", value));
                break;
            case "Молодая семья":

                element = youngFamilyBtn;
                selectRadioButton(element, value);
                wait.until(ExpectedConditions.attributeContains(element, "aria-checked", value));
                break;
            default:
                Assert.fail("Кнопка с наименованием '" + nameRadioBtn + "' отсутствует на странице " +
                        "'Заполнение заявки на ипотеку'");

        }
        System.out.println(element.getAttribute("aria-checked"));
        Assertions.assertEquals(value, element.getAttribute("aria-checked"), "Неверное значение радио-кнопки" + nameRadioBtn);
        return this;
    }


    //найти уникальный элемент на странице и подвязать асерт на него
    //методподтверждающий открытия страницы
    public MortagePage checkOpenPage() {
        wait.until(ExpectedConditions.elementToBeClickable(mortagePageHeader));
        Assertions.assertEquals(mortagePageHeader.getText(), "Ипотека от 7,7%* на готовые квартиры", "страница не открылась");
        return pageManager.getMortagePage();
    }

    //метод кроскроливания на жлемент
    public MortagePage scrollToElement(WebElement element) {
        scrollToElementJs(element);
        return this;
    }

    //метод переключения на фрейм
    public MortagePage switchToFrameAtPage() {
        switchToFrame(iFrameCulc);
        return this;
    }


    //метод заполнения поля (простой)
    public void fill(WebElement element, String value) {
        element.click();
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        //wait.until(ExpectedConditions.attributeToBe(costOfRealty,"defaultValue"));
        element.sendKeys(value);
    }

    //метод заполнения поля Стоимость ипотеки
    public MortagePage fillPageCostOfRealty(String value) {
        wait.until(ExpectedConditions.visibilityOf(costOfRealty));
        costOfRealty.click();
        costOfRealty.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        costOfRealty.sendKeys(value);
        //wait.until(ExpectedConditions.attributeToBe(firstContibution,"defaultValue", "1 500 000"));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }


    //метод заполнения поля Первоначальный взнос
    public MortagePage fillFirstContribution(String value) {
        wait.until(ExpectedConditions.visibilityOf(firstContibution));
        firstContibution.click();
        firstContibution.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        //wait.until(ExpectedConditions.attributeToBe(costOfRealty,"defaultValue", "5 180 000"));
        firstContibution.sendKeys(value);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return this;
    }

    //метод заполнения поля Скрок кредитования

    public MortagePage fillCreditTerm(String value) {
        wait.until(ExpectedConditions.visibilityOf(creditTerm));
        creditTerm.click();
        creditTerm.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        creditTerm.sendKeys(value);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return this;
    }

    // скрол на кнопку Страхования !!привет кастомность
    public MortagePage scrollToInsuarBtn() {
        scrollToElementJs(insuarenceBtn);
        return this;
    }


    // скрол на кнопку Молодая семья !!привет кастомность
    public MortagePage scrollToYoungFamilyBtn() {
        scrollToElementJs(youngFamilyBtn);
        return this;
    }

    // клик  на кнопку страхования !!привет кастомность
    public MortagePage clickYoungFamilyBtn() {
        wait.until(ExpectedConditions.visibilityOf(youngFamilyBtn));
        youngFamilyBtn.click();
        youngFamilyBtn.click();
        return this;
    }


    //// скрол на полу Услуги !!привет кастомность
    public MortagePage scrollToServices() {
        scrollToElementJs(services);
        return this;
    }

    //метод, проверяющий занчения всех полей
    public MortagePage checkDataCredit() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        checkValue("2 122 000 ₽", sumOfCredit);
        checkValue("16 618 ₽", monthPayment);
        checkValue("21 393 ₽", necessaryIncome);
        checkValue("11%", procents);
        return this;
    }

    //метод проверяющий заполнения поля
    private void checkValue(String expectedValue, WebElement element) {
        Assertions.assertEquals(FormatString.getIntFromString(expectedValue),
                FormatString.getIntFromString(element.getAttribute("innerText")),
                "Значение " + element.getText() + " расчитано некорректно");
    }

//    private void checkValueByString(String expectedValue, String element) {
//        Assertions.assertEquals(FormatString.getIntFromString(expectedValue),
//                FormatString.getIntFromString(element.getAttribute("innerText")),
//                "Значение " + element.getText() + " расчитано некорректно");
//    }


//метод проверяющий выбрана ли радио кнопка
    private void selectRadioButton(WebElement element, String value) {
        if (!element.getAttribute("aria-checked").contains(value)) {
            element.click();
        }
    }

//метод заполняющий поле
    protected void fillInputField(WebElement field, String value) {
        scrollToElementJs(field);
        waitUtilElementToBeClickable(field).click();
        field.sendKeys(value);
    }


    //проверка значений, расчитанных калькулятором
    public MortagePage checkDataResult(String name, String expectedValue) {

        switch (name) {
            case "Ежемесячный платеж":
                checkValue(expectedValue, monthPayment);
                break;
            case "Сумма кредита":
                checkValue(expectedValue, sumOfCredit);
                break;
            case "Процентная ставка":
                checkValue(expectedValue, procents);
                break;
            case "Необходимый доход":
                checkValue(expectedValue, necessaryIncome);
                break;
            default:
                Assertions.fail(("Поле '" + name + "' не было найдено на странице"));
        }
        return this;
    }



}
