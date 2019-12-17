package com.DocStudio.testcases;
import io.restassured.RestAssured;
import io.restassured.http.Method;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.DocStudio.base.TestBase;

public class TC002_get_single_employee_data extends TestBase {
	
	@BeforeClass()
	public void getAllEmployees() throws InterruptedException {

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		httpRequest = RestAssured.given();

		response = httpRequest.request(Method.GET, "/employees");

		Thread.sleep(3);
	}

	@Test
	public void getEmployeeData() {

		String body = response.getBody().asString();

		Assert.assertEquals(body.contains("186681"), true);

	}

	@AfterClass()
	public void tearDown() {

		logger.info("######Suites Ends Here######");
	}
	

}
