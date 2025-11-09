package pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
	
		super(driver);
	}
	
	@FindBy(xpath = "(//a[@class='dropdown-toggle'])[1]")
	WebElement myAccount;
	
	@FindBy(xpath = "//a[text()='Register']")
	WebElement registerAccount;
	@FindBy(xpath = "//a[text()='Login']")
	WebElement login;
	
public void clickMyAccount() {
	myAccount.click();
	
}

public void clickRegisterAccount() {
	registerAccount.click();
	
}

public void clickLogin() {
	login.click();
	
}

}
