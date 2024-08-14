package api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClient {
    private static final String CREATE_ENDPOINT = "/api/auth/register";
    private static final String USER_ENDPOINT = "/api/auth/user";

    @Step("Создание пользователя")
    public ValidatableResponse createUser(User user) {

        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(user)
                .when()
                .post(CREATE_ENDPOINT)
                .then().log().all();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .auth().oauth2(accessToken.substring(accessToken.indexOf(" ") + 1))
                .contentType(ContentType.JSON)
                .when()
                .delete(USER_ENDPOINT)
                .then().log().all();
    }
}
