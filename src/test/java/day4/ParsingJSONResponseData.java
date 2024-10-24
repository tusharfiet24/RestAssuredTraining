package day4;

import static io.restassured.RestAssured.*;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParsingJSONResponseData {
	
//	@Test(priority = 1)
	void testJsonResponse() {
		
		//Approach 1
		/*given()
		.when()
			.get("https://reqres.in/api/users?page=2&id=5")
		.then()
			.log().body()
			.header("Content-Type", "application/json; charset=utf-8")
			.body("data.first_name", equalTo("Charles"));*/
		
		//Approach2
		Response res = given()
			.contentType(ContentType.JSON)
		.when()
			.get("https://reqres.in/api/users?page=2&id=5");
		
		Assert.assertEquals(res.getStatusCode(), 200); //validation1
		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
		
		String bookname = res.jsonPath().get("data.first_name").toString();
		Assert.assertEquals(bookname, "Charles");
	}
	
	@Test(priority = 2)
	void testJsonResponseBodyData() {
		
//		Response res = given()
//			.contentType(ContentType.JSON)
//		.when()
//			.get("https://reqres.in/api/users?page=2&id=5");
		
//		Assert.assertEquals(res.getStatusCode(), 200); //validation1
//		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
//		
//		String bookname = res.jsonPath().get("data.first_name").toString();
//		Assert.assertEquals(bookname, "Charles");
		
		//JSONObject class
		JSONObject jo = new JSONObject(Payload.coursePrice()); //converting response to json object type
//		for(int i=0;i<jo.getJSONArray("courses").length();i++) {
//			String bookTitle = jo.getJSONArray("courses").getJSONObject(i).get("title").toString();
//			System.out.println(bookTitle);
//		}
		
		//search title of book in json - validation1
		boolean status = false;
		
		for(int i=0;i<jo.getJSONArray("courses").length();i++) {
			String bookTitle = jo.getJSONArray("courses").getJSONObject(i).get("title").toString();
			if(bookTitle.equals("RPA")) {
				status = true;
				break;
			}
		}
		
		Assert.assertTrue(status);
		
		int totalPrice = 0;
		for(int i=0;i<jo.getJSONArray("courses").length();i++) {
			String price = jo.getJSONArray("courses").getJSONObject(i).get("price").toString();
			String copies = jo.getJSONArray("courses").getJSONObject(i).get("copies").toString();
			totalPrice += (Integer.parseInt(price) * Integer.parseInt(copies));
		}
		
		int purchaseAmount = Integer.parseInt(jo.getJSONObject("dashboard").get("purchaseAmount").toString());
		Assert.assertEquals(totalPrice, purchaseAmount);
	}
}
