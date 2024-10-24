package day5;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import java.util.List;

public class ParsingXMLResponse {

	@Test(priority = 1)
	void testXMLResponse() {
		
		//Approach1
		/*given()
		.when()
			.get("https://thetestrequest.com/authors.xml")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/xml; charset=utf-8")
			.body("objects.object[0].name", equalTo("Karl Zboncak"))
			.log().all();*/
		
		//Approach2
		
		Response res = given()
		.when()
			.get("https://thetestrequest.com/authors.xml");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");
		
		String name = res.xmlPath().get("objects.object[0].name").toString();
		Assert.assertEquals(name, "Karl Zboncak");	
	}
	
	@Test(priority = 2)
	void testXMLResponseBody() {
		
		Response res = given()
						.when()
							.get("https://thetestrequest.com/authors.xml");
		
		XmlPath xmlobj = new XmlPath(res.asString());
		
		//Verify total number of objects
		List<String> objects = xmlobj.getList("objects.object");
		Assert.assertEquals(objects.size(), 5);
		
		//Verify object name is present in response
		boolean status = false;
		List<String> names = xmlobj.getList("objects.object.name");
		
		for(String name:names) {
			if(name.equals("Leonard Champlin")) {
				status = true;
				break;
			}
		}
		Assert.assertTrue(status);	
	}
}
