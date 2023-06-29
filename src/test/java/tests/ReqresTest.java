package tests;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import reqres.User;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;

public class ReqresTest {

    String baseUrl = "https://reqres.in";

    @Test
    public void getListUsersTest() {
        Response response = given()
                .log().all()
                .when()
                .get(baseUrl+"/api/users?page=2")
                .then()
                .log().all()
                .extract().response();
        Assert.assertEquals(response.statusCode(),HTTP_OK);
    }

    @Test
    public void getSingleUserTest() {
        Response response = given()
                .log().all()
                .when()
                .get(baseUrl+"/api/users/2")
                .then()
                .log().all()
                .extract().response();
        Assert.assertEquals(response.statusCode(),HTTP_OK);
    }

    @Test
    public void postCreateTest() {
        User expectedUser = User.builder()
                .name("morpheus")
                .job("leader")
                .id(679)
                .createdAt("2023-06-29T09:35:29.220Z")
                .build();

        User requestUser = User.builder()
                .name("morpheus")
                .job("leader")
                .build();

        String bodyResponse = given()
                .body(requestUser)
                .when()
                .post(baseUrl+"/api/users")
                .then()
                .log().all()
                .statusCode(HTTP_CREATED)
                .extract().body().asString();
        User responseUser = new Gson().fromJson(bodyResponse, User.class);
        Assert.assertEquals(responseUser, expectedUser);
    }
}
