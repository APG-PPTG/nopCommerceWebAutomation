package com.nopCommerce2023.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopCommerce2023.pageObjects.LoginPage;
import com.nopCommerce2023.testBase.BaseClass;

public class TC_LoginTest_001 extends BaseClass {

	@Test
	public void loginTest() throws IOException
	{
		logger.info("*************** Starting TC_LoginTest_001 ***************");
		driver.get(configPropObject.getProperty("baseURL"));
		LoginPage lp = new LoginPage(driver);
		
		logger.info("*************** Providing Logging details ***************");
		lp.setUserName(configPropObject.getProperty("useremail"));
		lp.setPassword(configPropObject.getProperty("password"));
		lp.clickLogin();
		
		String exp_title = "Dashboard / nopCommerce administration";
		String act_title = driver.getTitle();
		
		if(exp_title.equals(act_title))
		{
			logger.info("*************** loginTest is passed ***************");
			Assert.assertTrue(true);
		}
		else {
			logger.info("*************** loginTest is failed ***************");
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
		}
		logger.info("*************** Finished TC_LoginTest_001 ***************");
	}
}
