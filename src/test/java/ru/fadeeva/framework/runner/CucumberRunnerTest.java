package ru.fadeeva.framework.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/scenario"},
        glue = {"ru/fadeeva/framework/steps"},
        tags = "@regress"
)
public class CucumberRunnerTest {
}
