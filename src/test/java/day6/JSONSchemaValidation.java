package day6;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class JSONSchemaValidation {
	@Test
	void jsonschemavalidation() {
		
		given()
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.assertThat()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeJsonSchema.json"))
			.log().all();
	}
}
