package test_runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@CucumberOptions(
        features = "src\\main\\Features",
        glue = {"stepDefinitions", "hooks"},
        tags = "@Regression",
        plugin = {"pretty",
                "html:target/cucumber/cucumber-html-report.html",
                "json:target/cucumber/cucumber-json-report.json",
                }
)
@RunWith(Cucumber.class)
public class Flow_Runner {
}