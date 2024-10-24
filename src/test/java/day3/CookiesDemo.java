package day3;

import static io.restassured.RestAssured.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesDemo {
	
//	@Test(priority = 1)
	void testCookies() {
		given()
		.when()
			.get("https://www.google.com/")
		.then()
			.statusCode(200)
			.cookie("AEC", "AVYB7cryr3r89wqSWSHa0s7XbzdOwXqSRMnwrzn2RzmuuPGU0EwEMQceuA")
			.log().all();
	}
	
	@Test(priority = 2)
	void getCookiesInfo() {
		Response res = given()
		.when()
			.get("https://www.google.com/")
		.then()
			.statusCode(200)
			.log().all()
			.extract().response();
		
		//get single cookie info
//		String cookie_value = res.getCookie("AEC");
//		System.out.println("Value of cookie is===?"+cookie_value);
		
		//get all cookies info
		Map<String, String> cookies_values = res.getCookies();
		for(Map.Entry<String, String> entry:cookies_values.entrySet()) {
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	}
}
