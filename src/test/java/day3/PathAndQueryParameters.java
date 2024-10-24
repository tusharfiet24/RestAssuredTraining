package day3;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

public class PathAndQueryParameters {
	
//	https://reqres.in/api/users?page=2&id=8
	
	@Test
	void testQueryAndPathParameters() {
		given()
			.log().all()
			.pathParam("mypath", "users") // path parameter
			.queryParam("page", 2) // query parameter
			.queryParam("id", 5)
		.when()
			.get("https://reqres.in/api/{mypath}")
		.then()
			.statusCode(200)
			.log().all();
	}
}
