package com.udacity.jwdnd.c1.cloudstorage.pages.sections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NoteTabPanel {
    @FindBy(id = "noteTable")
    private WebElement noteTable;
    @FindBy(xpath = "//div[@id='nav-notes']//button[contains(text(), 'Add a New Note')]")
    private WebElement addNoteButton;

    private final NoteForm noteForm;

    private final WebDriverWait wait;

    public NoteTabPanel(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
        PageFactory.initElements(driver, this);
        noteForm = new NoteForm(driver, wait);
    }

    public String getTitle(String text) {
        String xpath = String.format("//th[contains(text(), '%s')]", text);

        return noteTable.findElement(By.xpath(xpath)).getText();
    }

    public String getDescription(String text) {
        String xpath = String.format("//td[contains(text(), '%s')]", text);

        return noteTable.findElement(By.xpath(xpath)).getText();
    }

    public NoteForm clickAddButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addNoteButton));
        addNoteButton.click();

        return noteForm;
    }

    public NoteForm clickEditButton(String title) {
        String xpath = String.format("//button[@data-noteTitle='%s' and contains(text(), 'Edit')]", title);
        WebElement editButton = noteTable.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(editButton));
        editButton.click();

        return noteForm;
    }
}