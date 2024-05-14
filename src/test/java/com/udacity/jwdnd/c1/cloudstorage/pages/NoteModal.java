package com.udacity.jwdnd.c1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NoteModal {
    @FindBy(id = "note-title")
    private WebElement noteTitleInput;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionInput;

    @FindBy(xpath = "//div[@id='noteModal']//button[contains(text(), 'Save changes')]")
    private WebElement saveButton;

    private WebDriverWait wait;

    public NoteModal(WebDriver driver, int waitSecond) {
        PageFactory.initElements(driver, this);
        Duration duration = Duration.ofSeconds(waitSecond);
        wait = new WebDriverWait(driver, duration);
    }

    public void submit() {
        saveButton.click();
    }

    public void setNoteTitle(String title) {
        wait.until(ExpectedConditions.visibilityOf(noteTitleInput));
        noteTitleInput.sendKeys(title);
    }

    public void setNoteDescription(String description) {
        wait.until(ExpectedConditions.visibilityOf(noteDescriptionInput));
        noteDescriptionInput.sendKeys(description);
    }
}