package com.DocStudio.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

import com.DocStudio.base.TestBase;

public class TC005_delete_emp_record extends TestBase {

	@BeforeClass()
	public void getAllEmployees() throws InterruptedException {

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		httpRequest = RestAssured.given();

		response = httpRequest.request(Method.GET, "/employees");

		JsonPath jsonPathEvaluator = response.jsonPath();
		String empID = jsonPathEvaluator.get("[5].id");

		logger.info(empID);

		response = httpRequest.request(Method.DELETE, "/delete/" + empID);

	}

	@Test
	public void checkSuccesMessage() {
		logger.info("######TC005_validate_Status_Line######");

		String responseBody = response.getBody().asString();

		Assert.assertEquals(
				responseBody.contains("successfully! deleted Records"), true);

	}

	@Test
	public void checkStatusLine() {
		logger.info("######TC005_validate_Status_Line######");

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
}
