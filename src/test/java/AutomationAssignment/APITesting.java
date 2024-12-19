package AutomationAssignment;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class APITesting {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com/";

        //adding valid data and verifying
        String response = given().queryParam("key", "qaclick").body("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": -38.383494,\n" +
                        "    \"lng\": 33.427362\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"Frontline house\",\n" +
                        "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                        "  \"address\": \"29, side layout, cohen 09\",\n" +
                        "  \"types\": [\n" +
                        "    \"shoe park\",\n" +
                        "    \"shop\"\n" +
                        "  ],\n" +
                        "  \"website\": \"http://google.com\",\n" +
                        "  \"language\": \"French-IN\"\n" +
                        "}\n").when().post("/maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).extract().asString();

        JsonPath js = new JsonPath(response);
        String placeId = js.get("place_id");

        //updating the data and verifying
        given().queryParam("key", "qaclick123").queryParam("place_id", placeId).body("{\n" +
                "  \"place_id\": \"" + placeId + "\",\n" +
                "  \"address\": \"70 winter walk, USA\",\n" +
                "  \"key\": \"qaclick123\"\n" +
                "}\n").when().put("/maps/api/place/update/json").then().log().all().assertThat().statusCode(200);

        //extracting data and verifying
        given().queryParam("key", "qaclick123").queryParam("place_id", placeId)
                .when().get("/maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200);

        //extracting data with invalid query parameter and verifying
        given().queryParam("key", "qaclick123").queryParam("place_id", "placeId")
                .when().get("/maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(404);



        //deleting data and verifying
        given().queryParam("key", "qaclick123").body("{\n" +
                "  \"place_id\": \"" + placeId + "\"\n" +
                "}\n").when().post("/maps/api/place/delete/json").then().assertThat().statusCode(200).log().all();

        //extracting data after deleting data and verifying
        given().queryParam("key", "qaclick123").queryParam("place_id", placeId)
                .when().get("/maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(404);
    }

}
