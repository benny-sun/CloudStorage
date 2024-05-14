package com.udacity.jwdnd.c1.cloudstorage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    @FindBy(id = "nav-files-tab")
    private WebElement filesTab;
    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;
    @FindBy(xpath = "//div[@id='nav-notes']//button[contains(text(), 'Add a New Note')]")
    private WebElement addNoteButton;
    private NoteModal noteModal;
    @FindBy(id = "noteTable")
    private WebElement noteTable;

    private WebDriverWait wait;

    public HomePage(WebDriver driver, int waitSecond) {
        PageFactory.initElements(driver, this);
        Duration duration = Duration.ofSeconds(waitSecond);
        wait = new WebDriverWait(driver, duration);
        noteModal = new NoteModal(driver, waitSecond);
    }

    public void clickFilesTab() {
        filesTab.click();
    }

    public void clickNotesTab() {
        wait.until(ExpectedConditions.visibilityOf(notesTab));
        notesTab.click();
    }

    public void clickCredentialsTab() {
        credentialsTab.click();
    }

    public void clickAddNoteButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addNoteButton));
        addNoteButton.click();
    }

    public NoteModal getNoteModal() {
        return noteModal;
    }

    public WebElement getNoteTitle(String text) {
        String xpath = String.format("//th[contains(text(), '%s')]", text);

        return noteTable.findElement(By.xpath(xpath));
    }

    public WebElement getNoteDescription(String text) {
        String xpath = String.format("//td[contains(text(), '%s')]", text);

        return noteTable.findElement(By.xpath(xpath));
    }
}