package lesson17;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DeleteRequestTest {
    @Test
    public void testDeleteRequest() {
        RestAssured.baseURI = "https://postman-echo.com";

        when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .body("data", equalTo(new HashMap<>()))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("url", equalTo("https://postman-echo.com/delete"));
    }
}