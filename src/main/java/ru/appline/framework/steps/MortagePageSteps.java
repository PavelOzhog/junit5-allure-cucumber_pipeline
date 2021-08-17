package ru.appline.framework.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.bg.И;
import ru.appline.framework.managers.PageManager;

import java.util.Map;

import static ru.appline.framework.pages.MortagePage.iFrameCulc;

public class MortagePageSteps {

    PageManager pageManager = PageManager.getPageManager();

    @И("^Проверяем что открылась страница 'Ипотека на вторичное жильё$'")
    public void checkOpenPage() {
        pageManager.getMortagePage().checkOpenPage();
    }

    //возможно ли как то параметризировать поиск?
    @И("^Скрол до элемента 'Калькулятор'$")
    public void scrollToCulc() {
        pageManager.getMortagePage().scrollToElement(iFrameCulc);
    }

    @И("^Переключение на элемент iframe$")
    public void main() {
        pageManager.getMortagePage().switchToFrame(iFrameCulc);
    }


    @И("^Заполняем поля:$")
    public void fillField(DataTable mapFieldsAndValue) {
        mapFieldsAndValue.asMap(String.class, String.class).forEach((key, value) ->
                pageManager.getMortagePage().fillField((String) key, (String) value));
    }

    @И("^Выбрать значения у радио кнопок$")
    public void selectRadioBtnName(DataTable mapRadioBtnAndValues) {
        mapRadioBtnAndValues.asMap(String.class, String.class).forEach((key, value) ->
                pageManager.getMortagePage().selectRadioBtn((String) key, (String) value));
    }

    @И("^Проверяем значение полей$")
        public void checkValueField (Map<String, String> arg){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        arg.forEach((name, expectedValue) -> pageManager.getMortagePage().checkDataResult(name, expectedValue));
        }

    }
