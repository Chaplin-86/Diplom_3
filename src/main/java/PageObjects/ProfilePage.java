package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static praktikum.EnvConfig.DEFAULT_TIMEOUT;

public class ProfilePage {
    private final WebDriver driver;

    //Кнопка 'Конструктор'
    private final By constructorButton = By.xpath("//p[text()='Конструктор']");

    //Логотип
    private final By logo = By.className("AppHeader_header__logo__2D0X2");

    //Текст описания функционала профиля
    private final By profileHeader = By.xpath("//a[text()='Профиль']");

    //Кнопка 'Выход'
    private final By logOutButton = By.xpath(".//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод отображения описания функционала профиля
    public boolean isProfileHeaderVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileHeader));

        return driver.findElement(profileHeader).isDisplayed();
    }

    //Клик по кнопке 'Выход'
    public SignInPage clickLogOutButton() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(logOutButton));

        driver.findElement(logOutButton).click();

        return new SignInPage(driver);
    }

    //Клик по кнопке 'Конструктор'
    public MainPage clickConstructorButton() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(constructorButton));

        driver.findElement(constructorButton).click();

        return new MainPage(driver);
    }

    //Клик по логотипу
    public MainPage clickLogo() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(logo));

        driver.findElement(logo).click();

        return new MainPage(driver);
    }





}
