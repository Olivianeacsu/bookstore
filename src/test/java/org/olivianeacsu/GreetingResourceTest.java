package org.olivianeacsu;

import io.quarkus.test.junit.QuarkusTest;
import org.jboss.resteasy.spi.HttpResponseCodes;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResourceTest {
    @Test
    void testGetBooksEndpoint() {
        given()
          .when().get("/api/books")
          .then()
             .statusCode(HttpResponseCodes.SC_NO_CONTENT);
    }

    @Test
    void testGetBookByIdEndpoint() {
        given()
                .pathParam("id", 1L)
                .when().get("/api/books/{id}")
                .then()
                .statusCode(HttpResponseCodes.SC_NOT_FOUND);
    }

    @Test
    void testCreateBookEndpoint() {
        given()
                .body(new Book(1L, "JAVA 21", "John Doe", BigDecimal.ONE))
                .when().post("/api/books")
                .then()
                .statusCode(HttpResponseCodes.SC_CREATED);
    }

    @Test
    void testDeleteBookByIdEndpoint() {
        given()
                .pathParam("id", 1L)
                .when().delete("/api/books/{id}")
                .then()
                .statusCode(HttpResponseCodes.SC_NO_CONTENT);
    }

}