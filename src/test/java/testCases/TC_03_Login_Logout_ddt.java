package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;

import pageObjects.LoginLogoutDdt;
import pageObjects.MyAccount;
import utilities.DataProviders;

public class TC_03_Login_Logout_ddt extends BaseClass {
	@Test(dataProvider = "LoginLogoutDdt", dataProviderClass = DataProviders.class, groups = "DataDriven")
	public void logInLogout(String usn, String pswd, String exp) {
		
	
			logger.info("start testing TC_03_Login_Logout..");
			try {
			HomePage home= new HomePage(driver);
			home.clickMyAccount();
			logger.info("clicked my account..");
			home.clickLogin();
			logger.info("clicked login..");
			
			LoginLogoutDdt log= new LoginLogoutDdt(driver);
			log.enterUsn(usn);
			logger.info("enterd usn..");
			log.enterPwd(pswd);
			logger.info("enterd usn..");
			log.clickLoginBtn();
			logger.info("clciked login btn..");
			MyAccount myc= new MyAccount(driver);
			boolean checkMyAccountIsDisplayed = myc.checkMyAccountIsDisplayed();
		// scenario  >>
			
			//1st data is valid- a>login success-test pass-logout
//                               b> login failed-test fail



//2nd data is invalid-  a> login success-test fail-logout
 //                     b>login failed-test pass 
				if (exp.equalsIgnoreCase("valid")) {
					if(checkMyAccountIsDisplayed==true) {
					myc.clickLogOutBtn();
					Assert.assertTrue(true);
					
				}
				
				else {
					
					Assert.assertTrue(false);
				}
				}
				if (exp.equalsIgnoreCase("invalid")) {
					if(checkMyAccountIsDisplayed==true) {
					myc.clickLogOutBtn();
					Assert.assertTrue(false);
					
				}
				
				else {
					
					Assert.assertTrue(true);
				}
				}
			
			//Assert.assertEquals(checkMyAccountIsDisplayed, true);
			}
			catch(Exception e) {
				Assert.fail();
				
			}
			logger.info("finished TC_03_Login_Logout..");
	}

}

