package com.udacity.jwdnd.c1.cloudstorage;

import com.udacity.jwdnd.c1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.c1.cloudstorage.pages.ResultPage;
import com.udacity.jwdnd.c1.cloudstorage.pages.sections.CredentialTabPanel;
import com.udacity.jwdnd.c1.cloudstorage.pages.sections.NoteForm;
import com.udacity.jwdnd.c1.cloudstorage.pages.sections.NoteTabPanel;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doMockSignUp(String firstName, String lastName, String userName, String password){
		// Create a dummy account for logging in later.

		// Visit the sign-up page.
		Duration durationSeconds = Duration.ofSeconds(2);
		WebDriverWait webDriverWait = new WebDriverWait(driver, durationSeconds);
		driver.get("http://localhost:" + this.port + "/signup");
		webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));

		// Fill out credentials
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
		WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
		inputFirstName.click();
		inputFirstName.sendKeys(firstName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
		WebElement inputLastName = driver.findElement(By.id("inputLastName"));
		inputLastName.click();
		inputLastName.sendKeys(lastName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement inputUsername = driver.findElement(By.id("inputUsername"));
		inputUsername.click();
		inputUsername.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement inputPassword = driver.findElement(By.id("inputPassword"));
		inputPassword.click();
		inputPassword.sendKeys(password);

		// Attempt to sign up.
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
		WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
		buttonSignUp.click();

		/* Check that the sign up was successful.
		// You may have to modify the element "success-msg" and the sign-up
		// success message below depening on the rest of your code.
		*/
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success-msg")));
		Assertions.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));
	}

	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doLogIn(String userName, String password)
	{
		// Log in to our dummy account.
		driver.get("http://localhost:" + this.port + "/login");
		Duration durationSeconds = Duration.ofSeconds(2);
		WebDriverWait webDriverWait = new WebDriverWait(driver, durationSeconds);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement loginUserName = driver.findElement(By.id("inputUsername"));
		loginUserName.click();
		loginUserName.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement loginPassword = driver.findElement(By.id("inputPassword"));
		loginPassword.click();
		loginPassword.sendKeys(password);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		webDriverWait.until(ExpectedConditions.titleContains("Home"));
	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
	 * rest of your code.
	 * This test is provided by Udacity to perform some basic sanity testing of
	 * your code to ensure that it meets certain rubric criteria.
	 *
	 * If this test is failing, please ensure that you are handling redirecting users
	 * back to the login page after a succesful sign up.
	 * Read more about the requirement in the rubric:
	 * https://review.udacity.com/#!/rubrics/2724/view
	 */
	@Test
	@Transactional
	public void testRedirection() throws InterruptedException {
		// Create a test account
		doMockSignUp("Redirection","Test","RT","123");

		// Check if we have been redirected to the login page.
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
	 * rest of your code.
	 * This test is provided by Udacity to perform some basic sanity testing of
	 * your code to ensure that it meets certain rubric criteria.
	 *
	 * If this test is failing, please ensure that you are handling bad URLs
	 * gracefully, for example with a custom error page.
	 *
	 * Read more about custom error pages at:
	 * https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page
	 */
	@Test
	public void testBadUrl() {
		// Create a test account
		doMockSignUp("URL","Test","UT","123");
		doLogIn("UT", "123");

		// Try to access a random made-up URL.
		driver.get("http://localhost:" + this.port + "/some-random-page");
		Assertions.assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
	}


	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
	 * rest of your code.
	 * This test is provided by Udacity to perform some basic sanity testing of
	 * your code to ensure that it meets certain rubric criteria.
	 *
	 * If this test is failing, please ensure that you are handling uploading large files (>1MB),
	 * gracefully in your code.
	 *
	 * Read more about file size limits here:
	 * https://spring.io/guides/gs/uploading-files/ under the "Tuning File Upload Limits" section.
	 */
	@Test
	public void testLargeUpload() {
		// Create a test account
		doMockSignUp("Large File","Test","LFT","123");
		doLogIn("LFT", "123");

		// Try to upload an arbitrary large file
		Duration durationSeconds = Duration.ofSeconds(2);
		WebDriverWait webDriverWait = new WebDriverWait(driver, durationSeconds);
		String fileName = "upload5m.zip";

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
		WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
		fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());

		WebElement uploadButton = driver.findElement(By.id("uploadButton"));
		uploadButton.click();
		try {
			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println("Large File upload failed");
		}
		Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));
	}

	@Test
	public void testSignupLoginFlow() {
		// Visit home page without login, redirect to login page
		String homePageUrl = "http://localhost:" + this.port + "/home";
		driver.get(homePageUrl);
		Assertions.assertEquals("Login", driver.getTitle());

		// Signup and login, visit home page again
		doMockSignUp("SignupLoginFlow","Test","SLF","123");
		doLogIn("SLF", "123");
		driver.get(homePageUrl);
		Assertions.assertEquals("Home", driver.getTitle());

		// Logout, visit home page again
		Duration durationSeconds = Duration.ofSeconds(2);
		WebDriverWait webDriverWait = new WebDriverWait(driver, durationSeconds);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logoutDiv")));
		WebElement logoutDiv = driver.findElement(By.id("logoutDiv"));
		WebElement logoutButton = logoutDiv.findElement(By.tagName("button"));
		logoutButton.click();
		driver.get(homePageUrl);
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void testNoteManagement() {
		// Create a test account
		doMockSignUp("Note Management","Test","NMT","123");
		doLogIn("NMT", "123");

		// init testing data
		String addedTitle = "note title";
		String editedTitle = "edited note title";
		String addedDescription = "note description testing";
		String editedDescription = "edited note description";

		// run feature test
		testAddNewNote(addedTitle, addedDescription);
		testEditNote(addedTitle, editedTitle, addedDescription, editedDescription);
		testDeleteNote(editedTitle, editedDescription);
	}

	private void testAddNewNote(String newTitle, String newDescription) {
		// arrange
		HomePage homePage = new HomePage(driver, 2);
        NoteTabPanel noteTabPanel = homePage.clickNotesTab();

		// check new record does not in the list
		Assertions.assertThrows(NoSuchElementException.class, () -> noteTabPanel.getTitle(newTitle));
		Assertions.assertThrows(NoSuchElementException.class, () -> noteTabPanel.getDescription(newDescription));
		
		// popup note form, fill form data
		noteTabPanel.clickAddButton()
                .setTitle(newTitle)
                .setDescription(newDescription)
                .submit();

		// check success page
		Assertions.assertEquals("Result", driver.getTitle());
		ResultPage resultPage = new ResultPage(driver);
		resultPage.clickContinueLink();

		// check new record in the list
		Assertions.assertEquals(newTitle, noteTabPanel.getTitle(newTitle));
		Assertions.assertEquals(newDescription, noteTabPanel.getDescription(newDescription));
	}

	private void testEditNote(
			String addedTitle,
			String editedTitle,
			String addedDescription,
			String editedDescription
	) {
		// arrange
		HomePage homePage = new HomePage(driver, 2);
		NoteTabPanel noteTabPanel = homePage.clickNotesTab();
		NoteForm noteForm = noteTabPanel.clickEditButton(addedTitle);

		// check new record in the list same as edit input fields
		Assertions.assertEquals(addedTitle, noteForm.getTitle());
		Assertions.assertEquals(addedDescription, noteForm.getDescription());

		// edit input fields
		noteForm
				.setTitle(editedTitle)
				.setDescription(editedDescription)
				.submit();

		// check success page
		Duration duration = Duration.ofSeconds(2);
		WebDriverWait webDriverWait = new WebDriverWait(driver, duration);
		webDriverWait.until(ExpectedConditions.titleContains("Result"));
		Assertions.assertEquals("Result", driver.getTitle());
		ResultPage resultPage = new ResultPage(driver);
		resultPage.clickContinueLink();

		// check edited result
		noteForm = noteTabPanel.clickEditButton(editedTitle);
		Assertions.assertEquals(editedTitle, noteForm.getTitle());
		Assertions.assertEquals(editedDescription, noteForm.getDescription());
		noteForm.close();
	}

	private void testDeleteNote(String targetTitle, String targetDescription) {
		// arrange
		HomePage homePage = new HomePage(driver, 5);

		// click delete button but dismiss confirm
		homePage.clickNotesTab()
				.clickDeleteButton(targetTitle)
				.cancelConfirm();

		// check record still exist
		NoteTabPanel noteTabPanel = homePage.clickNotesTab();
		Assertions.assertEquals(targetTitle, noteTabPanel.getTitle(targetTitle));
		Assertions.assertEquals(targetDescription, noteTabPanel.getDescription(targetDescription));

		// click delete button and accept confirm
		homePage.clickNotesTab()
				.clickDeleteButton(targetTitle)
				.acceptConfirm();

		// check record already deleted (re-retrieve the elements to avoid StaleElementReferenceException)
		Assertions.assertThrows(NoSuchElementException.class, () -> noteTabPanel.getTitle(targetTitle));
		Assertions.assertThrows(NoSuchElementException.class, () -> noteTabPanel.getDescription(targetDescription));
    }

	@Test
	public void testCredentialManagement() {
		// Create a test account
		doMockSignUp("Credential Management","Test","CMT","123");
		doLogIn("CMT", "123");

		// init testing data
		String addedUrl = "https://learn.udacity.com/";
		String editedUrl = "https://github.com/";
		String addedUsername = "udacity";
		String editedUsername = "benny";
		String addedPassword = "jxtnXpzu3Xn7uPX4";
		String editedPassword = "RS6dLHfHQ7X8YVjH";

		// run feature test
		testAddNewCredential(addedUrl, addedUsername, addedPassword);
	}

	private void testAddNewCredential(String newUrl, String newUsername, String newPassword) {
		// arrange
		HomePage homePage = new HomePage(driver, 2);
		CredentialTabPanel credentialTabPanel = homePage.clickCredentialsTab();

		// check new record does not in the list
		Assertions.assertThrows(NoSuchElementException.class, () -> credentialTabPanel.getUrl(newUrl));
		Assertions.assertThrows(NoSuchElementException.class, () -> credentialTabPanel.getUsername(newUsername));

		// popup form and fill form data
		credentialTabPanel.clickAddButton()
				.setUrl(newUrl)
				.setUsername(newUsername)
				.setPassword(newPassword)
				.submit();

		// check success page
		Assertions.assertEquals("Result", driver.getTitle());
		ResultPage resultPage = new ResultPage(driver);
		resultPage.clickContinueLink();

		// check new record in the list
		Assertions.assertEquals(newUrl, credentialTabPanel.getUrl(newUrl));
		Assertions.assertEquals(newUsername, credentialTabPanel.getUsername(newUsername));
		Assertions.assertNotEquals(newPassword, credentialTabPanel.getPassword(newUsername));
	}
}
