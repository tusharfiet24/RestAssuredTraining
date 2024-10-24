package day3;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersDemo {

//	@Test(priority = 1)
	void testHeaders() {
		given()
		.when()
			.get("https://www.google.com/")
		.then()
			.statusCode(200)
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.and()
			.header("Content-Encoding", "gzip")
			.and()
			.header("Server", "gws");
	}
	
	@Test(priority = 2)
	void getHeaders() {
		Response res = given()
		.when()
			.get("https://www.google.com/");
		
		// get single header info
//		String headervalue = res.getHeader("Content-Type");
//		System.out.println("The value of Content-Type header is:"+headervalue);
		
		// get all headers info
		Headers myheaders = res.getHeaders();
		for(Header myheader:myheaders) {
			System.out.println(myheader.getName()+":"+myheader.getValue());
		}
	}
}
