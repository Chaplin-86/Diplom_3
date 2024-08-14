import api.User;
import api.UserClient;
import pageobjects.SignInPage;
import pageobjects.MainPage;
import pageobjects.ProfilePage;
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

public class PersonalProfileTests {
    private User user;
    private UserClient userClient;
    private String accessToken;
    private DriverRule driverRule = new DriverRule();

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
    @DisplayName("Переход по кнопке 'Личный кабинет' авторизованным пользователем")
    public void clickProfileButtonAuthTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URL);

        new MainPage(driver)
                .clickLoginButton();

        new SignInPage(driver)
                .signIn(user.getEmail(), user.getPassword());

        new MainPage(driver)
                .clickProfileButtonAuth();

        Boolean actual = new ProfilePage(driver).isProfileHeaderVisible();

        System.out.println(actual);

    }

    @Test
    @DisplayName("Переход по кнопке 'Личный кабинет' неавторизованным пользователем")
    public void clickProfileButtonNotAuthTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URL);

        new MainPage(driver)
                .clickProfileButton();

        new SignInPage(driver)
                .clickProfileButton();

        Boolean actual = new SignInPage(driver).isSignInHeaderVisible();
        System.out.println(actual);
    }

    @Test
    @DisplayName("Переход в конструктор по клику на кнопку 'Конструктор'")
    public void clickConstructorButtonTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URL);

        new MainPage(driver)
                .clickLoginButton();

        new SignInPage(driver)
                .signIn(user.getEmail(), user.getPassword());

        new MainPage(driver)
                .clickProfileButton();

        new ProfilePage(driver)
                .clickConstructorButton();

        Boolean actual = new MainPage(driver).isConstructorHeaderVisible();

        System.out.println(actual);
    }

    @Test
    @DisplayName("Переход в конструктор по клику на логотип")
    public void clickLogoTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URL);

        new MainPage(driver)
                .clickLoginButton();

        new SignInPage(driver)
                .signIn(user.getEmail(), user.getPassword());

        new MainPage(driver)
                .clickProfileButton();

        new ProfilePage(driver)
                .clickLogo();

        Boolean actual = new MainPage(driver).isConstructorHeaderVisible();

        System.out.println(actual);
    }

    @Test
    @DisplayName("Выход из аккаунта по клику на кнопку 'Выход' в личном кабинете")
    public void logOutTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URL);

        new MainPage(driver)
                .clickLoginButton();

        new SignInPage(driver)
                .signIn(user.getEmail(), user.getPassword());

        new MainPage(driver)
                .clickProfileButton();

        new ProfilePage(driver)
                .clickLogOutButton();

        Boolean actual = new SignInPage(driver).isSignInHeaderVisible();

        System.out.println(actual);

    }

}
