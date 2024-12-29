package org.olivianeacsu;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResourceTest {
    @Test
    void testGetBooksEndpoint() {
        given()
          .when().get("/api/books")
          .then()
             .statusCode(204);
    }

}