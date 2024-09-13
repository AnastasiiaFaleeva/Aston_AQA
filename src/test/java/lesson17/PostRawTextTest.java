package lesson17;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostRawTextTest {
    @Test
    public void testPostRawText() {
        RestAssured.baseURI = "https://postman-echo.com";

        given()
                .body("This is expected to be sent back as part of response body.")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo("This is expected to be sent back as part of response body."))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("url", equalTo("https://postman-echo.com/post"));
    }
}