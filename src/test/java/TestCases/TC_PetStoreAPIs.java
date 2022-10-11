package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class TC_PetStoreAPIs {
	@Test
	public void TC01_Get_Inventory() {
		
		given()
		
		.when()
			.get("https://petstore.swagger.io/v2/store/inventory")
		
		.then()
			.statusCode(200).log().all();
		
	}
	@Test
	public void TC02_Post_Order() {
		
		HashMap data = new HashMap();
		data.put("id",0);
		data.put("petId",0);
		data.put("quantity",0);
		data.put("shipDate","2022-10-11T04:24:19.397Z");
		data.put("status","placed");
		data.put("complete",true );
		
		Response res=
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("https://petstore.swagger.io/v2/store/order")
		
		.then()
			.statusCode(200)
			.log().body()
			.extract().response();
		String JSONString = res.asString();
		Assert.assertEquals(JSONString.contains("id"), true);
		
	}

}
