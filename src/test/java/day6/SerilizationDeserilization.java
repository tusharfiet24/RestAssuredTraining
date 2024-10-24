package day6;

import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

//Pojo ---- Serilize ---- Json Object ---- De-Serilize --- Pojo

public class SerilizationDeserilization {
	
	// Pojo ----> Json (Serilization) 
//	@Test(priority = 1)
	void convertPojo2Json() throws JsonProcessingException {
		Register reg = new Register();
		reg.setEmail("eve.holt@reqres.in");
		reg.setPassword("pistol");
		
		//Convert java object ---> json object (Serilization)
		ObjectMapper objMapper = new ObjectMapper();
		String jsondata = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(reg);
		System.out.println(jsondata);

//		given()
//			.contentType("application/json")
//			.body(reg)
//		.when()
//			.post("https://reqres.in/api/register")
//		.then()
//			.statusCode(200)
//			.log().all();
	}
	
	// Json ----> Pojo (De-Serilization) 
	@Test(priority = 2)
	void convertJson2Pojo() throws JsonMappingException, JsonProcessingException {
		String jsondata = "{\r\n"
				+ "  \"email\" : \"eve.holt@reqres.in\",\r\n"
				+ "  \"password\" : \"pistol\"\r\n"
				+ "}";
		
		//convert json data ---> Pojo object
		
		ObjectMapper objMapper = new ObjectMapper();
		Register reg = objMapper.readValue(jsondata, Register.class); //Convert Json to Pojo
		
		System.out.println("Email: "+reg.getEmail());
		System.out.println("Password: "+reg.getPassword());
	}

}
