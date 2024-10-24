package day7;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Authentications {
	
	@Test(priority = 1)
	void testBasicAuthentication() {
		
		given()
			.auth().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	@Test(priority = 1)
	void testDigestAuthentication() {
		
		given()
			.auth().digest("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	@Test(priority = 3)
	void testPreemptiveAuthentication() {
		
		given()
			.auth().preemptive().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	@Test(priority = 4)
	void testBearerTokenAuthentication() {
//		String bearerToken = "apitoken";
		
		given()
//			.header("Authorization","Bearer "+bearerToken)
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority = 5)
	void testOAuth1Authentication() {
		given()
			.auth().oauth("consumerKey", "consumerSecrat", "accessToken", "tokenSecrate")
		.when()
			.get("url")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority = 6)
	void testOAuth2Authentication() {
//		given()
//			.auth().oauth2("apitoken").
		when()
			.get("https://api.github.com/user/repos")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority = 7)
	void testAPIKeyAuthentication() {
		
		given()
			.queryParam("Default", "apitooken")
		.when()
			.get("api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")
		.then()
			.statusCode(200)
			.log().all();
	}
}