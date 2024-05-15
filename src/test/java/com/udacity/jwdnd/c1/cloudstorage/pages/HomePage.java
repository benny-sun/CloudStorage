package com.udacity.jwdnd.c1.cloudstorage.pages;

import com.udacity.jwdnd.c1.cloudstorage.pages.sections.NoteTabPanel;
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
    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;
    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;

    private final NoteTabPanel noteTabPanel;

    private final WebDriverWait wait;

    public HomePage(WebDriver driver, int waitSecond) {
        PageFactory.initElements(driver, this);
        Duration duration = Duration.ofSeconds(waitSecond);
        wait = new WebDriverWait(driver, duration);
        noteTabPanel = new NoteTabPanel(driver, wait);
    }

    public void clickFilesTab() {
        filesTab.click();
    }

    public NoteTabPanel clickNotesTab() {
        wait.until(ExpectedConditions.visibilityOf(notesTab));
        notesTab.click();

        return noteTabPanel;
    }

    public void clickCredentialsTab() {
        credentialsTab.click();
    }
}