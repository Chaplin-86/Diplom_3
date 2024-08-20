import api.User;
import api.UserClient;
import pageobjects.SignInPage;
import pageobjects.MainPage;
import pageobjects.ResetPasswordPage;
import pageobjects.SignUpPage;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static api.UserGenerator.randomUser;

import static praktikum.EnvConfig.BASE_URL;

public class LogInTests {
    private User user;
    private UserClient userClient;
    private String accessToken;

    @Rule
    public DriverRule driver = new DriverRule();


    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;

        user = randomUser();
        userClient = new UserClient();

        ValidatableResponse responseToCreateUser = userClient.createUser(user);
        accessToken = responseToCreateUser.extract().path("accessToken");
    }

    @After
    public void deleteUser() {
        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной странице")
    public void signInViaLoginButtonTest() {
        WebDriver driver = DriverRule.getDriver();
        driver.get(BASE_URL);

        new MainPage(driver)
                .clickLoginButton();

        new SignInPage(driver)
                .signIn(user.getEmail(), user.getPassword());

        Boolean actual = new MainPage(driver).isOrderButtonVisible();
        System.out.println(actual);

    }
    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void signInViaProfileButtonTest() {
        WebDriver driver = DriverRule.getDriver();
        driver.get(BASE_URL);

        new MainPage(driver)
                .clickProfileButton();

        new SignInPage(driver)
                .signIn(user.getEmail(), user.getPassword());

        Boolean actual = new MainPage(driver).isOrderButtonVisible();

        System.out.println(actual);
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти' в форме регистрации")
    public void signInViaSignUpPageTest() {
        WebDriver driver = DriverRule.getDriver();
        driver.get(BASE_URL);

        new MainPage(driver)
                .clickLoginButton();
        new SignInPage(driver)
                .clickSignUpButton();
        new SignUpPage(driver)
                .clickSignInButton();
        new SignInPage(driver)
                .signIn(user.getEmail(), user.getPassword());

        Boolean actual = new MainPage(driver).isOrderButtonVisible();

        System.out.println(actual);

    }

    @Test
    @DisplayName("Вход через кнопку 'Войти' в форме восстановления пароля")
    public void signInViaResetPasswordPage() {
        WebDriver driver = DriverRule.getDriver();
        driver.get(BASE_URL);

        new MainPage(driver)
                .clickLoginButton();
        new SignInPage(driver)
                .clickResetPasswordButton();
        new ResetPasswordPage(driver)
                .clickSignInButton();
        new SignInPage(driver)
                .signIn(user.getEmail(), user.getPassword());

        Boolean actual = new MainPage(driver).isOrderButtonVisible();

        System.out.println(actual);

    }


}
