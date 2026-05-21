import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

public class apiTest {

    final String BASE_URL = "https://reqres.in/api/users/2";
    final String API_KEY = "reqres-free-v1";

    public void retrieveUser() {
        RestAssured.baseURI = BASE_URL;

        Response response = RestAssured
                .given()
                .header("reqres-free-v1", API_KEY)
                .get(BASE_URL);
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("First Name: " + response.jsonPath().getString("data.first_name"));
        System.out.println("Avatar URL: " + response.jsonPath().getString("data.avatar"));

        Assert.assertEquals(response.statusCode(), 200, "Expected status code 200");
    }

    public void updateEmailAddress() {
//        RestAssured.baseURI = BASE_URL;
        String payload = "{ \"email\": \"ignatius@gmail.com\" }";

        Response res = RestAssured
                .given()
                .header("reqres-free-v1", API_KEY)
                .header("Content-Type", "application/json")
                .body(payload)
                .put(BASE_URL)
                .then()
                .extract().response();

        Assert.assertEquals(res.statusCode(), 200);
        Assert.assertEquals(res.jsonPath().getString("email"), "ignatius@example.com");
    }




}


