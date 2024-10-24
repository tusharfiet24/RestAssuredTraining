package day2;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class DiffWaysToCreatePostRequestBody {
	String id;
	
//	@Test(priority = 1)
	void testPostusingHashMap() {
		//1) Post request body using Hashmap
		HashMap<String, String> map = new HashMap<>();
		map.put("name", "morpheus");
		map.put("job", "leader");
		
//		String[] courseArr = {"C","C++"};
//		map.put("courses", courseArr);
		
		String response = given()
			.contentType("application/json")
			.body(map)
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.body("name", equalTo(map.get("name")))
			.body("job", equalTo(map.get("job")))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all()
			.extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		id = js.getString("id");
	}
	
//	2) Post request body using org.json library
//	@Test(priority = 1)
	void testPostusingJsonLibrary() {
		// Post request body using Hashmap
		JSONObject data = new JSONObject();
		data.put("name", "morpheus");
		data.put("job", "leader");
		
//		String[] courseArr = {"C","C++"};
//		data.put("courses", courseArr);
		
		String response = given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.body("name", equalTo(data.get("name")))
			.body("job", equalTo(data.get("job")))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all()
			.extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		id = js.getString("id");
	}
	
//	@Test(priority = 1)
	void testPostusingPOJO() {
		//3) Post request body using POJO(Plain Old Java Object) class
		Pojo_PostRequest data = new Pojo_PostRequest();
		data.setName("morpheus");
		data.setJob("leader");
		
		String response = given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.body("name", equalTo(data.getName()))
			.body("job", equalTo(data.getJob()))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all()
			.extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		id = js.getString("id");
		
//		.body("course[0]", equalTo("C"))
	}
	
	@Test(priority = 1)
	void testPostusingExternalJsonFile() throws FileNotFoundException {
		File f = new File(".\\body.json");
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);
		
		String response = given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.body("name", equalTo(data.get("name")))
			.body("job", equalTo(data.get("job")))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all()
			.extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		id = js.getString("id");
		
//		.body("course[0]", equalTo("C"))
	}
	
	@Test(priority = 2)
	void testDelete() {
		given()
		.when()
			.delete("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(204)
			.log().all();
	}
}
