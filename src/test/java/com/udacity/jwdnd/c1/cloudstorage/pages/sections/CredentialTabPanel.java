package com.udacity.jwdnd.c1.cloudstorage.pages.sections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialTabPanel {
    @FindBy(id = "credentialTable")
    private WebElement credentialTable;
    @FindBy(xpath = "//div[@id='nav-credentials']//button[contains(text(), 'Add a New Credential')]")
    private WebElement addCredentialButton;
    private WebElement targetDeleteButton;

    private final CredentialForm credentialForm;

    private final WebDriver driver;
    private final WebDriverWait wait;

    public CredentialTabPanel(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
        credentialForm = new CredentialForm(driver, wait);
    }

    public String getUrl(String text) {
        String xpath = String.format("//th[contains(text(), '%s')]", text);

        return credentialTable.findElement(By.xpath(xpath)).getText();
    }

    public String getUsername(String text) {
        String xpath = String.format("//td[contains(text(), '%s')]", text);

        return credentialTable.findElement(By.xpath(xpath)).getText();
    }

    public String getPassword(String username) {
        String xpath = String.format("//td[contains(text(), '%s')]//following-sibling::td", username);

        return credentialTable.findElement(By.xpath(xpath)).getText();
    }

    public CredentialForm clickAddButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addCredentialButton));
        addCredentialButton.click();

        return credentialForm;
    }

    public CredentialForm clickEditButton(String url) {
        String xpath = String.format("//button[@data-url='%s' and contains(text(), 'Edit')]", url);
        WebElement editButton = credentialTable.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(editButton));
        editButton.click();

        return credentialForm;
    }

    public CredentialTabPanel clickDeleteButton(String url) {
        String cssSelector = String.format("button[data-url='%s'] + a.btn.btn-danger[data-method='delete']", url);
        WebElement deleteButton = credentialTable.findElement(By.cssSelector(cssSelector));
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        deleteButton.click();

        setTargetDeleteButton(deleteButton);

        return this;
    }

    private void setTargetDeleteButton(WebElement button) {
        targetDeleteButton = button;
    }

    public void acceptConfirm() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        // frontend uses ajax to delete records, so add this line to avoid StaleElementReferenceException.
        wait.until(ExpectedConditions.stalenessOf(targetDeleteButton));
    }

    public void cancelConfirm() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }
}