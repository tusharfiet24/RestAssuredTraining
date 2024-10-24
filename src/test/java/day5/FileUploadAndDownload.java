package day5;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

public class FileUploadAndDownload {
	
//	@Test(priority = 1)
	void singleFileUpload() {
		File myfile1 = new File(".\\src\\test\\java\\day5\\text.txt");
		
		given()
			.header("Api-Key","49e140ef-5a7b-4c7e-b12f-07710808891e")
			.multiPart("file", myfile1)
			.contentType("multipart/form-data")
		.when()
			.post("https://api.pdfrest.com/upload")
		.then()
			.statusCode(401)
//			.body("filename", equalTo("text.txt"))
			.log().all();
	}
	
	@Test(priority = 1)
	void testMultipleFileUpload(){
		File myfile1 = new File(".\\src\\test\\java\\day5\\text1.txt");
		File myfile2 = new File(".\\src\\test\\java\\day5\\text2.txt");
		
		File[] filearr = {myfile1, myfile1};
		
		given()
			.header("Api-Key","49e140ef-5a7b-4c7e-b12f-07710808891e")
			.multiPart("file", filearr)
//			.multiPart("file", myfile1)
//			.multiPart("file", myfile2)
			.contentType("multipart/form-data")
		.when()
			.post("https://api.pdfrest.com/upload")
		.then()
			.statusCode(401)
//			.body("[0].filename", equalTo("text.txt"))
			.log().all();
	}
	
	@Test(priority = 2)
	void testFileDownload() {
		given()
		.when()
			.get("")
		.then()
			.statusCode(200)
			.log().all();
	}
}