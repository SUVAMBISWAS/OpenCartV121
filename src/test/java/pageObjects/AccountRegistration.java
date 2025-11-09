package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistration extends BasePage{
	public AccountRegistration (WebDriver driver){
		super(driver);
		
	}
	//1
	@FindBy(id = "input-firstname")
	WebElement firstName;
	//2
	@FindBy(id = "input-lastname")
	WebElement lastName;
	//3
	@FindBy(id = "input-email")
	WebElement email;
	//4
	@FindBy(id = "input-telephone")
	WebElement telephone;
	//5
	@FindBy(id = "input-password")
	WebElement password;
	//6
	@FindBy(id = "input-confirm")
	WebElement confirmPassword;
	@FindBy(xpath = "//input[@name=\"newsletter\" and @value=\"1\"]")
	WebElement yesNewsletter;
	@FindBy(xpath = "//input[@name=\"newsletter\" and @value=\"0\"]")
	WebElement noNewsletter;
	@FindBy(xpath ="//input[@type=\"checkbox\"]")
	WebElement privacyCheckbox;
	@FindBy(xpath ="//input[@type=\"submit\"]")
	WebElement submitBtn;
	@FindBy(xpath ="//h1[text()='Your Account Has Been Created!']")
	WebElement confirmationMsg;
	
	public String setFirstName(String firstname) {
		
		firstName.sendKeys(firstname);
		return firstname;
	}
public String setLastName(String lastname) {
		
	lastName.sendKeys(lastname);
		return lastname;
	}
public String setEmail(String emaail) {
	
	email.sendKeys(emaail);
		return emaail;
	}
public String setTelephone(String telephn) {
	
	telephone.sendKeys(telephn);
		return telephn;
	}

public String setPassword(String pwd) {
	
	password.sendKeys(pwd);
		return pwd;
	}

public String setConfirmPassword(String cnfPwd) {
	
	confirmPassword.sendKeys(cnfPwd);
		return cnfPwd;
	}
	public void setNoNewsLetter() {
		noNewsletter.click();
		
	}
	public void setPrivacyCheckbox() {
		
		privacyCheckbox.click();
	}
public void setSubmitBtn() {
		
	submitBtn.click();
	}
public String confirmationMessage() {
	
	String text = confirmationMsg.getText();
	return text;
	
}
public String getConfirmationMsg() {
	
	
	try {
		String confirmationMessage = confirmationMessage();
		return confirmationMessage;
	} catch (Exception e) {
	String message = e.getMessage();
	return message;
	}
	
	
	
}
	
	
	
	

}
