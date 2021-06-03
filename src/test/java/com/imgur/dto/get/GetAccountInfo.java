package com.imgur.dto.get;

import com.imgur.dto.BaseTest;
import com.imgur.dto.EndPoint;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class GetAccountInfo extends BaseTest {

    @Test
    public void getAccountInfoTest() {

        given()
                .log()
                .uri()
                .log()
                .method()
                .headers("Authorization", token)
                .when()
                .get(EndPoint.GET_ACCOUNT_REQUEST, username)
                .prettyPeek()
                .then()
                ;
    }
    @Test
    public void getAccountInfoWithReqSpecTest() {

        given()
                .log()
                .uri()
                .log()
                .method()
//                .spec(reqSpec)
                .when()
                .get(EndPoint.GET_ACCOUNT_REQUEST, username)
                .prettyPeek()
                .then()
                ;
    }

//    @Test
//    public void getAccountInfoNegativeTest() {
//
//        given()
//                .headers(headers)
//                .when()
//                .get("/account/{username}", username)
//                .then()
//                .statusCode(400);
//    }
//
//    @Test
//    public void getAccountInfoNegativeWithOutAuthHeadersTest() {
//                given()
//                        .spec(reqSpecWithOutAuth)
//                .when()
//                .get("https://api.imgur.com/3/account/telepnevEvgeniy")
//                .prettyPeek();
//
//    }

    @Test
    public void getAccountPositiveManyChecksTest() {

        given()
                .headers("Authorization", token)
                .expect()
                .body(CoreMatchers.containsString(username))
                .body("success", is(true))
                .body("data.pro_expiration", is(false))
                .when()
                .get(EndPoint.GET_ACCOUNT_REQUEST, username)
                .then()
                .statusCode(200);
    }
}
