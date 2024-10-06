import io.qameta.allure.Step;

import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class UserClient extends Burger {
    @Step("Send GET request to /api/auth/user")
    public ValidatableResponse getUser(String accessToken) {
        return given()
                .spec(getBaseSpec())
                .header("Authorization", accessToken)
                .log().all()
                .get(Endpoints.USER_PATH + "user")
                .then()
                .log().all();
    }

    @Step("Send POST request to /api/auth/register")
    public ValidatableResponse createUser(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .log().all()
                .post(Endpoints.USER_PATH + "register")
                .then()
                .log().all();
    }

    @Step("Send POST request to /api/auth/login")
    public ValidatableResponse loginUser(User user, String accessToken) {
        return given()
                .spec(getBaseSpec())
                .auth().oauth2(accessToken)
                .body(user)
                .log().all()
                .post(Endpoints.USER_PATH + "login")
                .then()
                .log().all();
    }

    @Step("Send POST request to /api/auth/logout")
    public ValidatableResponse logoutUser(String refreshToken) {
        return given()
                .spec(getBaseSpec())
                .body(refreshToken)
                .log().all()
                .post(Endpoints.USER_PATH + "logout")
                .then()
                .log().all();
    }

    @Step("Send DELETE request to /api/auth/user")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .spec(getBaseSpec())
                .auth().oauth2(accessToken)
                .log().all()
                .delete(Endpoints.USER_PATH + "user")
                .then()
                .log().all();
    }

    @Step("Send PATCH request to /api/auth/user")
    public ValidatableResponse updateUserByAuthorization(User user, String accessToken) {
        return given()
                .spec(getBaseSpec())
                .header("Authorization", accessToken)
                .body(user)
                .log().all()
                .patch(Endpoints.USER_PATH + "user")
                .then()
                .log().all();
    }

    @Step("Send PATCH request to /api/auth/user")
    public ValidatableResponse updateUserWithoutAuthorization(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .log().all()
                .patch(Endpoints.USER_PATH + "user")
                .then()
                .log().all();
    }
}
