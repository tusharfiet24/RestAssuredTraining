package day1;

// given() - content type, set cookies, add auth, add param, set headers info etc...
// when() - get, post, put, delete
// then() - validate status code, extract response, extract headers cookies & response body...

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HttpRequests {
	int id;

	@Test(priority = 1)
	void getUsers() {
		when().get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("page", equalTo(2))
			.log().all();
	}
	
	@Test(priority = 2)
	void createUser() {		
		HashMap<String, String> map = new HashMap<>();
		map.put("name", "morpheus");
		map.put("job", "leader");
		
		id = given()
			.contentType("application/json")
			.body(map)
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
//		.then()
//			.statusCode(201)
//			.log().all();
	}
	
	@Test(priority = 3, dependsOnMethods = {"createUser"})
	void updateUser() {
		HashMap<String, String> map = new HashMap<>();
		map.put("name", "amit");
		map.put("job", "teacher");
		
		given()
			.pathParam("id", id)
			.contentType("application/json")
			.body(map)
		.when()
			.put("https://reqres.in/api/users/{id}")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority = 4)
	void deleteUser() {
		given()
		.when()
			.delete("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(204)
			.log().all();
	}
}
