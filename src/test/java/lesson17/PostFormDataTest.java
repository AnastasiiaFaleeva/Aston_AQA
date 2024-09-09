package lesson17;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostFormDataTest {
    @Test
    public void testPostFormData() {
        RestAssured.baseURI = "https://postman-echo.com";

        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers['x-forwarded-proto']", equalTo("https"))
                .body("headers['x-forwarded-port']", equalTo("443"));
    }
}