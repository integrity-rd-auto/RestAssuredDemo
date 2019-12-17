package com.DocStudio.testcases;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.DocStudio.base.TestBase;

public class TC001_get_all_employees_data extends TestBase {

	@BeforeClass()
	public void getAllEmployees() throws InterruptedException {

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		httpRequest = RestAssured.given();

		response = httpRequest.request(Method.GET, "/employees");

		Thread.sleep(3);
	}

	@Test()
	public void validateStatusCode() throws InterruptedException {
		logger.info("######TC001_get_all_employees_data######");

		String fullBody = response.getBody().asString();
		logger.info("Response Body" + fullBody);

		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test()
	public void validaterResponseTime() throws InterruptedException {
		logger.info("######TC001_get_all_employees_data######");

		if (response.time() > 5000) {
			logger.info("###Response Time greaer than 5000");

		}
		logger.info("Response Time: " + response.time());
		// logger.info("Response Body" + fullBody);

		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test()
	public void validateResponseIsNotNull() throws InterruptedException {
		logger.info("######TC002_response_is_not_null######");

		String fullBody = response.getBody().asString();
		Assert.assertTrue(fullBody != null);

	}

	@Test()
	public void validateHeaderIsNotNull() throws InterruptedException {
		logger.info("######TC003_verify_content_Length######");

		allHeaders = response.headers();

		Assert.assertTrue(allHeaders != null);
	}

	@Test()
	public void validateAllHeader() throws InterruptedException {
		logger.info("######TC004_verify_header######");

		allHeaders = response.headers();

		for (Header header : allHeaders) {

			logger.info(header.getName() + ".........>" + header.getValue());

		}

	}

	@Test()
	public void validateSingleHeader() throws InterruptedException {
		logger.info("######TC005_verify_single_header######");

		Assert.assertEquals(response.header("Accept-Ranges"), "bytes");

		Assert.assertEquals(response.getCookie("ezCMPCCS"), "true");

	}

	@AfterClass()
	public void tearDown() {

		logger.info("######Suites Ends Here######");
	}
}
