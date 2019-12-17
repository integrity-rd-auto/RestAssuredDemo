package com.DocStudio.testcases;

import io.restassured.RestAssured;
import io.restassured.http.Method;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.DocStudio.base.TestBase;
import com.DocStudio.utilities.RestUtil;

public class TC004_update_emp_record extends TestBase {
	String empName = RestUtil.empName();
	String empSal = RestUtil.empSal();
	String empAge = RestUtil.empAge();

	@BeforeClass()
	public void putData() throws InterruptedException {

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		httpRequest = RestAssured.given();

		Thread.sleep(3);

		JSONObject requestParams = new JSONObject();

		requestParams.put("name", empName);
		requestParams.put("salary", empSal);
		requestParams.put("age", empAge);

		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());

		response = httpRequest.request(Method.PUT, "/update/" + empID);

	}

	@Test
	public void checkEmployeeData() {
		logger.info("######TC003_post_employees_data######");

		String responsebody = response.getBody().asString();

		logger.info(responsebody);

		Assert.assertEquals(responsebody.contains(empName), true);

	}

	@Test
	public void checkStatusLine() {
		logger.info("######TC004_validate_Status_Line######");

		String statusline = response.statusLine();
		logger.info(statusline);

		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");

	}

	@Test
	public void checkStatusCode() {
		logger.info("######TC004_validate_Status_Code######");

		int statuscode = response.getStatusCode();
		logger.info(statuscode);

		Assert.assertEquals(statuscode, 200);

	}

	@Test
	public void checkContentType() {
		logger.info("######TC004_validate_Content_Type######");

		String contentType = response.header("Content-Type");
		logger.info(contentType);

		Assert.assertEquals(contentType, "text/html; charset=UTF-8");

	}
	@AfterClass()
	public void tearDown() {

		logger.info("######Suites Ends Here######");
	}
}
