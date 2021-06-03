package com.imgur.dto.post;

import com.imgur.dto.BaseTest;
import com.imgur.dto.EndPoint;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.IOException;
import java.util.Base64;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class PostImageTests extends BaseTest {

    private String uploadImageId;
    static final String INPUT_IMAGE_FILE_PATH = "image.jpg";
    private String fileString;


    @BeforeEach
    void setUp() {
        byte[] fileContent = getFileContent();
        fileString = Base64.getEncoder().encodeToString(fileContent);
    }

    @Test
    @DisplayName("Upload image size 44 kb")
    public void uploadFileTest() {
        uploadImageId = given()
                .headers("Authorization",token)
                .multiPart("image", fileString)
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

    private byte[] getFileContent() {
        ClassLoader classLoader = getClass().getClassLoader();
        File inputFile = new File(classLoader.getResource(INPUT_IMAGE_FILE_PATH)
        .getFile());

        byte[] fileContent = new byte[0];
        try {
            fileContent = FileUtils.readFileToByteArray(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }
}
