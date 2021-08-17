package ru.appline.framework.managers;

import ru.appline.framework.pages.HomePage;
import ru.appline.framework.pages.MortagePage;


public class PageManager {
    private static PageManager INSTANCE = null;

    private HomePage homePage;
    private MortagePage mortagePage;


    private PageManager() {

    }


    public static PageManager getPageManager() {
        if (INSTANCE == null) {
            INSTANCE = new PageManager();
        }
        return INSTANCE;
    }



    public HomePage getHomePage() {
        if(homePage==null){
            homePage= new HomePage();
        }
        return homePage;
    }

    public MortagePage getMortagePage() {
        if(mortagePage==null){
            mortagePage= new MortagePage();
        }
        return mortagePage;
    }

}
