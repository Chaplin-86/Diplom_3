import api.User;
import api.UserClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pageobjects.MainPage;
import pageobjects.SignInPage;
import pageobjects.SignUpPage;

import static api.UserGenerator.randomUser;
import static org.junit.Assert.assertEquals;
import static praktikum.EnvConfig.BASE_URL;

public class SignUpTests {
    private User user;

    @Rule
    public DriverRule driver = new DriverRule();


    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;

        user = randomUser();
    }

    @Test
    @DisplayName("Проверка успешной регистрации")
    public void checkSuccessfulRegistrationTest() {
        WebDriver driver = DriverRule.getDriver();
        driver.get(BASE_URL);

        new MainPage(driver)
                .clickProfileButton();

        new SignInPage(driver)
                .clickSignUpButton();

        new SignUpPage(driver)
                .signUp(user.getName(), user.getEmail(), user.getPassword());

        Boolean actual = new SignInPage(driver).isSignInHeaderVisible();
        System.out.println(actual);

    }

    @Test
    @DisplayName("Проверка отображения сообщения об ошибке при вводе невалидного пароля")
    @Description("Невалидный пароль меньше шести символов")
    public void checkInvalidPasswordMessageVisibility() {
        WebDriver driver = DriverRule.getDriver();
        driver.get(BASE_URL);

        new MainPage(driver)
                .clickProfileButton();

        new SignInPage(driver)
                .clickSignUpButton();

        new SignUpPage(driver)
                .signUpWithInvalidPassword(user.getName(),user.getEmail());

        String actual = new SignUpPage(driver).invalidPasswordMessage();

        assertEquals("Некорректный пароль", actual);
    }




}
