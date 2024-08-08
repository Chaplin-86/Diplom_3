package PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static praktikum.EnvConfig.DEFAULT_TIMEOUT;

public class LogInPage {

    private final WebDriver driver;

    //Заголовок 'Вход'
    private final By signInHeader = By.xpath("//h2[text()='Вход']");

    //Поле 'Email'
    private final By emailField = By.xpath("//label[contains(text(),'Email')]/../input");

    //Поле 'Пароль'
    private final By passwordField = By.xpath("//label[contains(text(),'Пароль')]/../input");

    //Кнопка 'Войти'
    private final By signInButton = By.xpath("//button[text()='Войти']");

    //Кнопка 'Зарегистрироваться'
    private final By signUpButton = By.xpath("//a[contains(text(),'Зарегистрироваться')]");

    //Кнопка 'Восстановить пароль'
    private final By resetPasswordButton = By.xpath("//a[contains(text(),'Восстановить пароль')]");

    public LogInPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод обнаружения заголовка 'Вход'
    private boolean isSignInHeaderVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(signInHeader));

        return driver.findElement(signInHeader).isDisplayed();
    }

    //Заполнение поля 'Email'
    public LogInPage enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);

        return this;
    }

    //Заполнение поля 'Пароль'
    public LogInPage enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);

        return this;
    }

    //Клик по кнопке 'Войти'
    public LogInPage clickSignInButton() {
        driver.findElement(signInButton).click();

        return this;
    }

   // Клик по кнопке 'Зарегистрироваться'
    public SignUpPage clickSignUpButton() {
        WebElement element = driver.findElement(signUpButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        driver.findElement(signUpButton).click();
        return new SignUpPage(driver);
    }

    //Клик по кнопке 'Восстановить пароль'
    public ResetPasswordPage clickResetPasswordButton() {

        WebElement element = driver.findElement(resetPasswordButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        driver.findElement(resetPasswordButton).click();

        return new ResetPasswordPage(driver);
    }

    //Метод авторизации при нажатии кнопки 'Личный кабинет' на главной странице
    public MainPage signIn(String email, String password){

        clickSignInButton();
        isSignInHeaderVisible();
        enterEmail(email);
        enterPassword(password);
        clickSignInButton();

        return new MainPage(driver);
    }










}
