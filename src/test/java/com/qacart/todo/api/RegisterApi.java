package com.qacart.todo.api;

import com.qacart.todo.config.EndPoint;
import com.qacart.todo.objects.User;
import com.qacart.todo.utils.UserUtils;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

import java.util.List;


import static io.restassured.RestAssured.given;

public class RegisterApi {
    private List<Cookie> restAssuredCookies;
    private String accessToken;
    private String userId;
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public String getUserId() {
        return userId;
    }

    public List<Cookie> getCookies() {
        return restAssuredCookies;
    }

    public String getToken() {
        return accessToken;
    }

    public void register(){
        User user = UserUtils.generateRandomUser();

        Response response =
                given()
                    .baseUri("https://qacart-todo.herokuapp.com")
                    .header("Content-Type","application/json")
                    .body(user)
                    .log().all()
                .when()
                    .post(EndPoint.API_REGISTER_ENDPOINT)
                .then()
                    .log().all()
                    .extract().response();
        if (response.statusCode() != 201){
            throw  new RuntimeException("Samething went wrong with the request");
        }
        restAssuredCookies = response.detailedCookies().asList();
        accessToken = response.path("access_token");
        userId = response.path("userId");
        firstName = response.path("firstName");

    }
}
