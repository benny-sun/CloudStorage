package com.udacity.jwdnd.c1.cloudstorage.pages.sections;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialForm {
    @FindBy(id = "credential-url")
    private WebElement urlInput;
    @FindBy(id = "credential-username")
    private WebElement usernameInput;
    @FindBy(id = "credential-password")
    private WebElement passwordInput;
    @FindBy(xpath = "//div[@id='credentialModal']//button[contains(text(), 'Save changes')]")
    private WebElement saveButton;
    @FindBy(xpath = "//div[@id='credentialModal']//button[contains(text(), 'Close')]")
    private WebElement closeButton;

    private final WebDriverWait wait;

    public CredentialForm(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public String getUrl() {
        wait.until(ExpectedConditions.visibilityOf(urlInput));

        return urlInput.getAttribute("value");
    }

    public String getUsername() {
        wait.until(ExpectedConditions.visibilityOf(usernameInput));

        return usernameInput.getAttribute("value");
    }

    public String getPassword() {
        wait.until(ExpectedConditions.visibilityOf(passwordInput));

        return passwordInput.getAttribute("value");
    }

    public CredentialForm setUrl(String url) {
        wait.until(ExpectedConditions.visibilityOf(urlInput));
        urlInput.clear();
        urlInput.sendKeys(url);

        return this;
    }

    public CredentialForm setUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameInput));
        usernameInput.clear();
        usernameInput.sendKeys(username);

        return this;
    }

    public CredentialForm setPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.clear();
        passwordInput.sendKeys(password);

        return this;
    }

    public void submit() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();
        wait.until(ExpectedConditions.titleIs("Result"));
    }

    public void close() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        closeButton.click();
    }
}