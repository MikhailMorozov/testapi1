package tests;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import reqres.*;

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
        SoftAssert softAssert = new SoftAssert();
        User expectedUser = User.builder()
                .name("morpheus")
                .job("leader")
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
        System.out.println(responseUser.toString());
        softAssert.assertEquals(responseUser.getName(),expectedUser.getName());
        softAssert.assertEquals(responseUser.getJob(),responseUser.getJob());
        softAssert.assertAll();
    }

    @Test
    public void postCreateTest1() {
//        User expectedUser = User.builder()
//                .name("morpheus")
//                .job("leader")
//                .build();

        User requestUser = User.builder()
                .name("morpheus")
                .job("leader")
                .build();

        Response bodyResponse = given()
                .body(requestUser)
                .when()
                .post(baseUrl+"/api/users")
                .then()
                .log().all()
                .statusCode(HTTP_CREATED)
                .extract().response();
        User responseUser = new Gson().fromJson(bodyResponse.asString(), User.class);
        System.out.println(responseUser.toString());
        Assert.assertEquals(bodyResponse.statusCode(), HTTP_CREATED);
    }

    @Test
    public void getResourceListTest() {
        Response response = given()
                .when()
                .get(baseUrl+"/api/unknown")
                .then()
                .log().all()
                .extract().response();
        ResourceList resourceList = new Gson().fromJson(response.asString(), ResourceList.class);
        System.out.println(resourceList.toString());
        Assert.assertEquals(response.statusCode(),HTTP_OK);
    }

    @Test
    public void getResourceTest() {
        Data expectedData = Data.builder()
                .id(2)
                .name("fuchsia rose")
                .year(2001)
                .color("#C74375")
                .pantoneValue("17-2031")
                .build();
        Support expextedSupport = Support.builder()
                .url("https://reqres.in/#support-heading")
                .text("To keep ReqRes free, contributions towards server costs are appreciated!")
                .build();
        Resource expectedResource = Resource.builder()
                .data(expectedData)
                .support(expextedSupport)
                .build();


        Response response = given()
                .when()
                .get(baseUrl+"/api/unknown/2")
                .then()
                .log().all()
                .statusCode(HTTP_OK)
                .extract().response();
        Resource resource = new Gson().fromJson(response.asString(), Resource.class);

        System.out.println(resource.toString());
//        Assert.assertEquals(response.statusCode(),HTTP_OK);
        Assert.assertEquals(resource, expectedResource);
    }

    @Test
    public void putUpdateTest() {
//        User expectedUser = User.builder()
//                .name("morpheus")
//                .job("leader")
//                .build();

        User requestUser = User.builder()
                .name("morpheus")
                .job("zion resident")
                .build();

        Response bodyResponse = given()
                .body(requestUser)
                .when()
                .put(baseUrl+"/api/users/2")
                .then()
                .log().all()
                .statusCode(HTTP_OK)
                .extract().response();
        User responseUser = new Gson().fromJson(bodyResponse.asString(), User.class);
        System.out.println(responseUser.toString());
        Assert.assertEquals(bodyResponse.statusCode(), HTTP_OK);
    }
}
