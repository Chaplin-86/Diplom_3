package PageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static praktikum.EnvConfig.DEFAULT_TIMEOUT;

public class MainPage {
    private final WebDriver driver;

    //Кнопка "Войти в аккаунт"
    private final By logInButton = By.xpath(".//button[text()='Войти в аккаунт']");

    //Кнопка "Личный кабинет"
    public static By profileButton = By.xpath(".//p[contains(text(),'Личный Кабинет')]");

    //Кнопка "Оформить заказ"
    private final By orderButton = By.xpath("//button[contains(text(),'Оформить заказ')]");

    //Заголовок "Соберите бургер"
    private final By constructorHeader = By.xpath("//h1[text()='Соберите бургер']");

    //Вкладка "Булки"
    private final By bunTab = By.xpath(".//div[span[text()='Булки']]");

    //Вкладка "Соусы"
    private final By sauceTab = By.xpath(".//div[span[text()='Соусы']]");

    //Вкладка "Начинки"
    private final By fillingTab = By.xpath(".//div[span[text()='Начинки']]");

    //Заголовок "Булки"
    private final By buns = By.xpath(".//h2[text()='Булки']");

    //Заголовок "Соусы"
    private final By sauce = By.xpath(".//h2[text()='Соусы']");

    //Заголовок "Начинки"
    private final By filling = By.xpath(".//h2[text()='Начинки']");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод нажатия на кнопку 'Личный кабинет' неавторизованным пользователем
       public SignInPage clickProfileButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(profileButton));

        driver.findElement(profileButton).click();

        return new SignInPage(driver);
    }

    //Метод нажатия на кнопку 'Личный кабинет' авторизованным пользованием
    public ProfilePage clickProfileButtonAuth() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(profileButton));

        driver.findElement(profileButton).click();

        return new ProfilePage(driver);
    }


    //Метод нажатия на кнопку 'Войти в аккаунт'
    public SignInPage clickLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(logInButton));

        driver.findElement(logInButton).click();
        return new SignInPage(driver);
    }

    public boolean isOrderButtonVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderButton));

        return driver.findElement(orderButton).isDisplayed();
    }

    public boolean isConstructorHeaderVisible(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(constructorHeader));

        return driver.findElement(constructorHeader).isDisplayed();
    }

    //Метод нажатия на вкладку 'Булки'
    public MainPage clickBunsTab() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(bunTab));

        driver.findElement(bunTab).click();
        return this;
    }

    //Метод нажатия на вкладку 'Соусы'
    public MainPage clickSauceTab() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(sauceTab));

        driver.findElement(sauceTab).click();
        return this;

    }

    //Метод нажатия на вкладку 'Начинки'
    public MainPage clickFillingTab() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(fillingTab));

        driver.findElement(fillingTab).click();
        return this;

    }

    //Переход на вкладку 'Булки'
    public boolean isBubsVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(buns));

        return driver.findElement(buns).isDisplayed();

    }

    //Переход на вкладку 'Соусы'
    public boolean isSauceVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(sauce));

        return driver.findElement(sauce).isDisplayed();

    }

    //Переход на вкладку 'Начинки'
    public boolean isFillingVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(filling));

        return driver.findElement(filling).isDisplayed();

    }



}
