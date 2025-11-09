package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class LoginLogoutDdt extends BasePage {
	
	public LoginLogoutDdt(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "input-email")
	WebElement username;
	@FindBy(id = "input-password")
	WebElement password;
	
	@FindBy(xpath = "//input[@type='submit']")
	WebElement loginBtn;

	
	public String enterUsn(String usn) {
		
		username.sendKeys(usn);
		return usn;
	}
	
public String enterPwd(String pwd) {
		
	password.sendKeys(pwd);
		return pwd;
	}

public void clickLoginBtn() {
	
	loginBtn.click();
	}


}
