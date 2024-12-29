package org.jakartaeetraining;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.spi.HttpResponseCodes;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class BookControllerTest {
    @Test
    void testGetBooksEndpoint() {
        given()
          .when().get("/api/books")
          .then()
             .statusCode(HttpResponseCodes.SC_OK);
    }

    @Test
    void testGetBookByIdEndpoint() {
        given()
                .pathParam("id", 1001L)
                .when().get("/api/books/{id}")
                .then()
                .statusCode(HttpResponseCodes.SC_OK);
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
                .pathParam("id", 1001L)
                .when().delete("/api/books/{id}")
                .then()
                .statusCode(HttpResponseCodes.SC_NO_CONTENT);
    }

    @Test
    public void testGetNumberOfBooks() {
        given()
                .header(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN).
                when()
                .get("/api/books/count").
                then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

}