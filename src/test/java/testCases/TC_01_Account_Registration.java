package testCases;
import org.testng.Assert;

import org.testng.annotations.Test;

import pageObjects.AccountRegistration;
import pageObjects.HomePage;

public class TC_01_Account_Registration extends BaseClass{
	
	
	
	@Test(groups = {"regression","master"})
	public void performRegistration() {
	
		logger.info("starting the TC_01_Account_Registration..");
	
		HomePage home= new HomePage(driver);
		home.clickMyAccount();
		logger.info("clicked my account..");
		home.clickRegisterAccount();
		logger.info("clicked on register account..");
		
		AccountRegistration acc= new AccountRegistration(driver);
		logger.info("setting all the details of user..");
		acc.setFirstName("Suvam");
	
		acc.setLastName("Biswas");
		
		acc.setEmail(randomString()+"@gmail.com");
	
		acc.setTelephone("9090908987");
	
		acc.setPassword("abc@123");
	
		acc.setConfirmPassword("abc@123");
	
		acc.setNoNewsLetter();
	
		acc.setPrivacyCheckbox();
	
		acc.setSubmitBtn();
		logger.info("validating expected message..");
		String confirmationMsg = acc.getConfirmationMsg();
		String expectedMsg = "Your Account Has Been Created!";

		if (confirmationMsg.equals(expectedMsg)) {
		   
		    Assert.assertEquals(confirmationMsg, expectedMsg);
//		    Assert.assertEquals(confirmationMsg, expectedMsg);
//		    is not strictly required inside your if block because you’ve already compared them with
//		    if (confirmationMsg.equals(expectedMsg)).
//
//		    But — it is recommended for proper reporting and clarity in your TestNG results. ✅
		    logger.info("Registration successful. Message validated.");
		} else {
		    
		    Assert.fail("Account registration failed due to mismatched confirmation message.");
		    logger.error("Test failed. Expected: " + expectedMsg + " | Actual: " + confirmationMsg);
		}
		logger.info("!!finished the TC_01_Account_Registration..");
		
		
		
		
	}

	
	
	
	
	
	
	
	
}
