package com.udacity.jwdnd.c1.cloudstorage.pages.sections;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NoteForm {
    @FindBy(id = "note-title")
    private WebElement titleInput;
    @FindBy(id = "note-description")
    private WebElement descriptionInput;
    @FindBy(xpath = "//div[@id='noteModal']//button[contains(text(), 'Save changes')]")
    private WebElement saveButton;
    @FindBy(xpath = "//div[@id='noteModal']//button[contains(text(), 'Close')]")
    private WebElement closeButton;

    private final WebDriverWait wait;

    public NoteForm(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        wait.until(ExpectedConditions.visibilityOf(titleInput));

        return titleInput.getAttribute("value");
    }

    public String getDescription() {
        wait.until(ExpectedConditions.visibilityOf(descriptionInput));

        return descriptionInput.getAttribute("value");
    }

    public NoteForm setTitle(String title) {
        wait.until(ExpectedConditions.visibilityOf(titleInput));
        titleInput.clear();
        titleInput.sendKeys(title);

        return this;
    }

    public NoteForm setDescription(String description) {
        wait.until(ExpectedConditions.visibilityOf(descriptionInput));
        descriptionInput.clear();
        descriptionInput.sendKeys(description);

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