package com.imgur.dto.get;

import com.imgur.dto.BaseTest;
import com.imgur.dto.account.GetAccountResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetAccountInfoOjectTests extends BaseTest {

    @Test
    void getAccountInfoPositiveTest() {
        GetAccountResponse response = given()
                .when()
                .get("https://api.imgur.com/3/account/telepnevEvgeniy")
//                .prettyPeek()
                .then()
                .extract()
                .body()
                .as(GetAccountResponse.class);
        System.out.println(response.getStatus().toString());
        assertThat(response.getStatus(), equalTo(200));
        assertThat(response.getAccountData().getId(),equalTo(145660493));
        assertThat(response.getAccountData().getIsBlocked(), equalTo(false));
    }
}
