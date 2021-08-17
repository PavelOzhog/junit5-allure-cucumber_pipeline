package ru.appline.framework.steps;


import io.cucumber.java.bg.И;
import ru.appline.framework.managers.PageManager;

import java.util.List;


public class HomePageSteps {

    PageManager pageManager = PageManager.getPageManager();


    @И("^Закрытие сообщения cookies$")
    public void закрытие_сообщения_cookies() {
        pageManager.getHomePage().closeBtnCookie();
    }


    @И("^Выбираем вкладку (.*) из главного меню$" )
    public void chooseMenu(String nameMenu) {
        pageManager.getHomePage().chooseMenu(nameMenu);
    }

    @И("Из выпадающего меню выбираем подменю (.*)$")
    public void chooseSubMenu(String subMenu){
        pageManager.getHomePage().chooseSubMenu(subMenu);
    }

    @И("^Проверяем что открылась страница 'Ипотека на вторичное жильё'$")
   public void checkOpenPage( ) {
        pageManager.getMortagePage().checkOpenPage();
    }

    @И("^В писываем слудующее сообщение (.*)$")
    public void вписываемСообщение( List<String> list ) {
       // pageManager.getMortagePage().fillcreditTerm(list.get(0));
    }

    @И("Выбираем машину завтра (.*)")
    public  void metod(List<String> list) {
        System.out.println();
    }



}
