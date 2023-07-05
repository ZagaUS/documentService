package com.zaga.resource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@QuarkusTest
public class PdfGeneratorApiTest {

    @Test
    public void testCreateCreditNote() throws IOException {
        String path = getClass().getClassLoader().getResource("fixtures/createCreditNote.json").getPath();
        String jsonFixture = Files.readString(Path.of(path));

        given()
            .contentType(ContentType.JSON)
            .body(jsonFixture)
            .when()
            .post("/Zaga/document-service/createCreditNote")
            .then()
            .statusCode(200)
            .contentType("application/pdf");
    }

}
