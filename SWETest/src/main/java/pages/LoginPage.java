package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.SlowLoadableComponent;
import org.openqa.selenium.support.ui.SystemClock;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage extends SlowLoadableComponent<LoginPage> {
	private WebDriver driver;
	private WebDriverWait wait;
	private BasePage page;
	private LoadableComponent<HomePage> parent;

	public LoginPage(WebDriver driver, LoadableComponent<HomePage> parent) {
		super(new SystemClock(), 20);
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 10);
		page = new BasePage(this.driver);
		this.parent = parent;
	}

	By usernameBy = By.id("email");
	By passwordBy = By.id("password");
	By loginButtonBy = By.id("loginButton");
	By errorMessageUsernameBy = By.cssSelector("#loginForm .error:nth-of-type(1) .errorMessage");
	By errorMessagePasswordBy = By.cssSelector("#loginForm .error:nth-of-type(2) .errorText");

	@Override
	protected void load() {
		parent.get().goToLoginPage();
	}

	@Override
	protected void isLoaded() throws Error {
		try {
			driver.findElement(usernameBy);
			driver.findElement(passwordBy);
			driver.findElement(loginButtonBy);
		} catch (Exception e) {
			System.out.println("Login page has not been loaded yet!");
			throw new Error(e.getMessage());
		}
	}

	public void loginToN11(String username, String password) {
		page.writeText(usernameBy, username);
		page.writeText(passwordBy, password);
		page.click(usernameBy);
		page.click(loginButtonBy);
	}

	public void verifyLoginUserName(String expectedText) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageUsernameBy));
		Assert.assertEquals(page.readText(errorMessageUsernameBy), expectedText);
	}

	public void verifyLoginPassword(String expectedText) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessagePasswordBy));
		Assert.assertEquals(page.readText(errorMessagePasswordBy), expectedText);
	}
}
