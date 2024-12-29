package org.olivianeacsu;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.spi.HttpResponseCodes;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class BookControllerTest {
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

    @Disabled
    @Test
    void testCreateBookEndpoint() {
        Book book = new Book();
        given()
                .body(book)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).
                when()
                .post("/api/books").
                then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .body("id", is(1));
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