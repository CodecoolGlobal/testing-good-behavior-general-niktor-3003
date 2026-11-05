package com.codecool.pokemon.hooks;

import com.codecool.pokemon.context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {
    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before("@ui")
    public void setUp() {
        context.setDriver(new ChromeDriver());
    }

    @After("@ui")
    public void tearDown(Scenario scenario) {
        if (context.getDriver() == null) {
            return;
        }

        try {
            if (scenario.isFailed()) {
                byte[] screenshot =
                        ((TakesScreenshot) context.getDriver())
                                .getScreenshotAs(OutputType.BYTES);

                scenario.attach(
                        screenshot,
                        "image/png",
                        "State at UI failure"
                );
            }
        } finally {
            context.getDriver().quit();
        }
    }
}
