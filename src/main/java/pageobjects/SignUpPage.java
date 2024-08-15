package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static praktikum.EnvConfig.DEFAULT_TIMEOUT;

public class SignUpPage {
    private final WebDriver driver;

    //Заголовок 'Регистрация'
    private final By signUpHeader = By.xpath("//h2[text()='Регистрация']");

    //Поле 'Имя'
    private final By nameField = By.xpath("//label[contains(text(),'Имя')]/../input");

    //Поле 'Email'
    private final By emailField = By.xpath("//label[contains(text(),'Email')]/../input");

    //Поле 'Пароль'
    private final By passwordField = By.xpath("//label[contains(text(),'Пароль')]/../input");

    //Кнопка 'Зарегистрироваться'
    private final By signUpButton = By.xpath(".//button[text()='Зарегистрироваться']");

    //Сообщение об ошибке при невалидном формате пароля (меньше 6 символов)
    private final By passwordErrorMessage = By.xpath(".//p[text() = 'Некорректный пароль']");

    //Кнопка 'Войти'
    private final By signInButton = By.xpath("//a[text()='Войти']");

    private static final String INVALID_PASSWORD = "pass";

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Метод обнаружения заголовка 'Регистрация'")
    private boolean isSignUpHeaderVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(signUpHeader));

        return driver.findElement(signUpHeader).isDisplayed();
    }

    @Step("Заполнение поля 'Имя'")
    public SignUpPage enterName(String name) {
        driver.findElement(nameField).sendKeys(name);

        return this;
    }

    @Step("Заполнение поля 'Email'")
    public SignUpPage enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);

        return this;
    }

    @Step("Заполнение поля 'Пароль'")
    public SignUpPage enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);

        return this;
    }

    @Step("Заполнение поля 'Пароль' невалидным значением меньше шести символов")
    public SignUpPage enterInvalidPassword() {
         driver.findElement(passwordField).sendKeys(INVALID_PASSWORD);

         return this;

    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public SignUpPage clickSignUpButton() {
        driver.findElement(signUpButton).click();

        return this;
    }

    @Step("Клик по кнопке 'Войти'")
    public SignInPage clickSignInButton() {
        driver.findElement(signInButton).click();

        return new SignInPage(driver);
    }

    @Step("Метод заполнения полей 'Имя', 'Email', 'Пароль' при регистрации")
    public SignInPage signUp(String name, String email, String password){

        isSignUpHeaderVisible();
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickSignUpButton();

        return new SignInPage(driver);
    }

    @Step("Метод регистрации пользователя с невалидным паролем")
    public SignUpPage signUpWithInvalidPassword(String name, String email) {

        isSignUpHeaderVisible();
        enterName(name);
        enterEmail(email);
        enterInvalidPassword();
        clickSignUpButton();

        return this;

    }

    @Step("Метод получения сообщения об ошибке при использовании невалидного пароля")
    public String invalidPasswordMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        WebElement passwordErrorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordErrorMessage));

        return passwordErrorMessageElement.getText();

    }

}
