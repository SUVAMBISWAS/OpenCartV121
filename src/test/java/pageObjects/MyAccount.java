package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccount extends BasePage{

	public MyAccount(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//h2[text()='My Account']")
	WebElement myAccount;
	@FindBy(xpath = "(//a[text()='Logout'])[2]")
	WebElement logOutBtn;
	public void clickLogOutBtn() {
		
		logOutBtn.click();
		}
	
	public boolean checkMyAccountIsDisplayed() {
		try {
		return (myAccount.isDisplayed());
		}
		catch(Exception e) {
		return false;
		}
	}

}
