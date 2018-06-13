package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.SlowLoadableComponent;
import org.openqa.selenium.support.ui.SystemClock;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends SlowLoadableComponent<HomePage> {
	private String baseURL = "https://www.n11.com/";
	private WebDriver driver;
	@SuppressWarnings("unused")
	private WebDriverWait wait;
	private BasePage basePage;

	public HomePage(WebDriver driver) {
		super(new SystemClock(), 20);
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 10);
		basePage = new BasePage(driver);
	}

	By signInButtonBy = By.className("btnSignIn");

	@Override
	protected void load() {
		this.driver.get(baseURL);
	}

	@Override
	protected void isLoaded() throws Error {
		try {
			driver.findElement(signInButtonBy);
		} catch (Exception e) {
			System.out.println("Home page has not been loaded yet!");
			throw new Error(e.getMessage());
		}
	}

	public LoginPage goToLoginPage() {
		basePage.click(signInButtonBy);
		return new LoginPage(this.driver, this);
	}
}
