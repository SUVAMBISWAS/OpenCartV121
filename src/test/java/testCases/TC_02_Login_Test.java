package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.Login;
import pageObjects.MyAccount;

public class TC_02_Login_Test extends BaseClass{
	@Test(groups = {"sanity","master"})
	public void logInToApp() {
		try {
		logger.info("start testing TC_02_Login_Test..");
		
		HomePage home= new HomePage(driver);
		home.clickMyAccount();
		logger.info("clicked my account..");
		home.clickLogin();
		logger.info("clicked login..");
		
		Login log= new Login(driver);
		
		log.enterUsn(pro.getProperty("email"));
		logger.info("entered username..");
		log.enterPwd(pro.getProperty("password"));
		logger.info("entered password..");
		log.clickLoginBtn();
		logger.info("clicked on login btn..");
		logger.info("validating header msg as My Account..");
		MyAccount myc= new MyAccount(driver);
		boolean checkMyAccountIsDisplayed = myc.checkMyAccountIsDisplayed();
		
		Assert.assertEquals(checkMyAccountIsDisplayed, true);
		}
		catch(Exception e) {
			Assert.fail();
			
		}
		logger.info("finished TC_02_Login_Test..");
		
	}

}
