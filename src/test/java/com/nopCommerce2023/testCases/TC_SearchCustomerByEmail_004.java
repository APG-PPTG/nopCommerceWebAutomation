package com.nopCommerce2023.testCases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopCommerce2023.pageObjects.AddcustomerPage;
import com.nopCommerce2023.pageObjects.LoginPage;
import com.nopCommerce2023.pageObjects.SearchCustomerPage;
import com.nopCommerce2023.testBase.BaseClass;

public class TC_SearchCustomerByEmail_004 extends BaseClass
{
	
	@Test(priority=1)
	public void searchCustomerbyEmail() throws IOException, InterruptedException
	{
		driver.get(configPropObject.getProperty("baseURL"));
		
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(configPropObject.getProperty("useremail"));
		lp.setPassword(configPropObject.getProperty("password"));
		lp.clickLogin();
		
		//Goto Search Page
		AddcustomerPage addcust=new AddcustomerPage(driver);
		
		addcust.clickOnCustomersMenu();
		addcust.clickOnCustomersMenuItem();
			
		//Provide EMAIL in Search Page
			
		SearchCustomerPage serachcust=new SearchCustomerPage(driver);
		serachcust.setEmail("victoria_victoria@nopCommerce.com");
		
		serachcust.clickSearch();
		
		Thread.sleep(5000);
		
		boolean status=serachcust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertEquals(true, status);
	}
	
}