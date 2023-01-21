package org.zayac.api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class TestApi {

    @Test
    public void checkNameUser2() {

        given()
                .when()
                .log().uri()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("data.id", is(2))
                .body("data.name", is("fuchsia rose"));

    }
    @Test
    public void updateJob() {

        String data = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        given()
                .when()
                .log().uri()
                .contentType(ContentType.JSON)
                .body(data)
                .put("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));

    }
    @Test
    public void createUser() {

        String newUser = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        given()
                .when()
                .log().uri()
                .contentType(ContentType.JSON)
                .body(newUser)
                .post("https://reqres.in/api/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));

    }
    @Test
    public void checkUnknownUser() {

        given()
                .when()
                .log().uri()
                .get("https://reqres.in/api/users/23")
                .then()
                .log().all()
                .statusCode(404);

    }
    @Test
    public void registerNewUser() {

        String newUser = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";

        given()
                .when()
                .log().uri()
                .contentType(ContentType.JSON)
                .body(newUser)
                .post("https://reqres.in/api/register")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", is(notNullValue()))
                .body("token", is(notNullValue()));

    }
}
