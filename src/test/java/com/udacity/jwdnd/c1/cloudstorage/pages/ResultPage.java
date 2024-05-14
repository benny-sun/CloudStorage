package com.udacity.jwdnd.c1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {

    @FindBy(xpath = "//a[contains(text(), 'here')]")
    private WebElement continueLink;

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickContinueLink() {
        continueLink.click();
    }
}