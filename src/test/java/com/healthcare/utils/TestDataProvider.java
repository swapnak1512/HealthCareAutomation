package com.healthcare.utils;
import org.testng.annotations.DataProvider;


public class TestDataProvider {

	String path = ConfigReader.getTestDataPath();
	@DataProvider(name="loginData")

	public Object[][] getLoginData() {
		//String path = "src/test/resources/testdata/HealthCareTestData.xlsx";
		
		return ExcelUtil.getTestData(path,"LoginDetails");
	}

	@DataProvider(name="appointmentData")

		public Object[][] getAppointmentData() {
		//String path = "src/test/resources/testdata/HealthCareTestData.xlsx";
		return ExcelUtil.getTestData(path,"AppointmentDetails");

	}

}