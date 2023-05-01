	package com.nopCommerce2023.testCases;

	import java.io.FileInputStream;
	import java.io.IOException;
	import org.apache.poi.ss.usermodel.DataFormatter;
	import org.apache.poi.xssf.usermodel.XSSFCell;
	import org.apache.poi.xssf.usermodel.XSSFRow;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	import org.testng.Assert;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;

	import com.nopCommerce2023.pageObjects.LoginPage;
	import com.nopCommerce2023.testBase.BaseClass;
	import com.nopCommerce2023.utilities.XLUtils;

	public class TC_LoginDDT_002Work extends BaseClass
	{
		DataFormatter formatter = new DataFormatter();

		@Test(dataProvider="LoginData")
		public void loginTest(String user,String pwd,String exp) throws IOException, InterruptedException
		{
			logger.info("**************** Starting TC_LoginDDT_002 ************* ");
			
			driver.get(configPropObject.getProperty("baseURL"));
			LoginPage lp=new LoginPage(driver);
			
			logger.info("**************** Proving login details ************* ");
			
			lp.setUserName(user);
			lp.setPassword(pwd);
			lp.clickLogin();
			Thread.sleep(5000);
			
			String exp_title="Dashboard / nopCommerce administration";
			String act_title=driver.getTitle();
			
			if(exp_title.equals(act_title))
			{
				if(exp.equals("Pass"))
				{
					logger.info("**************** loginTest is Passed ************* ");
					lp.clickLogout();
					Assert.assertTrue(true);
				}
				else if(exp.equals("Fail"))
				{
					logger.warn("**************** loginTest is Failed************* ");
					lp.clickLogout();
					Assert.assertTrue(false);
				}
						
			}
			else if(!exp_title.equals(act_title))
			{
				if(exp.equals("Pass"))
				{
					logger.warn("**************** loginTest is Failed************* ");
					Assert.assertTrue(false);
				}
				else if(exp.equals("Fail"))
				{
					logger.info("**************** loginTest is Passed ************* ");
					Assert.assertTrue(true);
				}

			}
			logger.info("**************** Finished TC_LoginTest_001 ************* ");
		}

		@DataProvider(name="LoginData")
		public Object [][] getData() throws IOException
		{
			String path=System.getProperty("user.dir")+"/TestData/LoginData.xlsx";
			System.out.println(path);
			FileInputStream fls = new FileInputStream(path);
			XSSFWorkbook wb = new XSSFWorkbook(fls);
			XSSFSheet sheet = wb.getSheetAt(0);
			int rowCount = sheet.getPhysicalNumberOfRows();
			XSSFRow row = sheet.getRow(0);
			int colCount = row.getLastCellNum();
			
			Object[][] data = new Object[rowCount-1][colCount];
			for(int i = 0; i<rowCount-1; i++) {
				row = sheet.getRow(i+1);
				for(int j=0; j<colCount; j++) {
					XSSFCell cell = row.getCell(j);
					data[i][j] = formatter.formatCellValue(cell);
					System.out.println("Hello");
				}
			}
			fls.close();
			wb.close();
			return data;
		}
		
			
		}
		