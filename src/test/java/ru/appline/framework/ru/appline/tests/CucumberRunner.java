package ru.appline.framework.ru.appline.tests;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"ru.appline.framework.steps"},
        features = {"src/test/resources"},
        tags = {"@smoke"}
)





public class CucumberRunner {
}


