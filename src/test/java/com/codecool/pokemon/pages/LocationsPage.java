package com.codecool.pokemon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LocationsPage extends BasePage {
    private final By locationItems = By.cssSelector("ul.locations-list > li");
    private final By locationList = By.cssSelector("ul.locations-list");

    public LocationsPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("http://localhost:5173/");
    }

    public int getLocationCount() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locationItems)).size();
    }

    private By locationByName(String locationName) {
        return By.xpath(
                "//ul[@class='locations-list']/li[normalize-space(.)=" +
                        "'" + locationName + "']");
    }

    public void selectLocation(String locationName) {
        wait.until(ExpectedConditions
                        .elementToBeClickable(locationByName(locationName)))
                .click();
    }

    public boolean waitForLocationListToDisappear() {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locationList));
    }
}
