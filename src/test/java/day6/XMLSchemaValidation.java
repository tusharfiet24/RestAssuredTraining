package day6;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;

public class XMLSchemaValidation {

	@Test
	void xmlSchemaValidation() {
		given()
		.when()
			.get("https://thetestrequest.com/authors.xml")
		.then()
			.statusCode(200)
			.log().all()
			.assertThat()
			.body(RestAssuredMatchers.matchesXsdInClasspath("storeXMLSchema.xsd"));
	}
}
