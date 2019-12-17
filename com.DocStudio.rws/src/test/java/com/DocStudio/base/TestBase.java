package com.DocStudio.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	
	
	public static RequestSpecification httpRequest;
	public static Response response;
	public Logger logger;
	
	public Headers allHeaders ;
	public Header header;
	
	public String empID = "182638";
	
	@BeforeClass
	public void setup(){
		
		logger= Logger.getLogger("DocStudio");
		PropertyConfigurator.configure("D:\\RestAssured\\com.DocStudio.rws\\test-output\\log4j.properties");
		//PropertyConfigurator.configure(("user.dir")+"\\test-output\\log4j.properties");
		logger.setLevel(Level.DEBUG);
		
	}
	
	

}
