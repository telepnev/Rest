package com.imgur.dto.post;

import com.imgur.dto.BaseTest;
import com.imgur.dto.EndPoint;
import com.imgur.dto.utils.FileEncodingUtils;
import io.qameta.allure.Step;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class PostNew extends BaseTest {

    private String uploadImageId;
    MultiPartSpecification multiPartSpec;
    RequestSpecification uploadReqSpec;


    @BeforeEach
    void setUp() {
        byte[] fileContent = FileEncodingUtils.getFileContent();
        multiPartSpec = new MultiPartSpecBuilder(fileContent)
                .controlName("image")
                .build();
        uploadReqSpec = reqSpec.multiPart(multiPartSpec)
                .formParam("title", "For Test from API")
                .formParam("description", "Image for TEST");
    }

    @Test
    @DisplayName("Upload image size 44 kb")
    public void uploadFileTest() {
        uploadImageId = given()
                .spec(uploadReqSpec)
                .multiPart("title", "Title for test")
                .expect()
                .body("success", is(true))
                .body("data.id", is(notNullValue()))
                .when()
                .post(EndPoint.POST_IMAGE_REQUEST)
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.deletehash");
    }

    @AfterEach
    @Step("Remove image after test")
    void tearDown() {
        given()
                .headers("Authorization",token)
                .when()
                .delete(EndPoint.DELETE_IMAGE_REQUEST, username, uploadImageId)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

}
