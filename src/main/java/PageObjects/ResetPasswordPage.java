package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResetPasswordPage {
    private final WebDriver driver;

    //Кнопка 'Войти'
    private final By signInButton = By.xpath("//a[text()='Войти']");
    public ResetPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    //Клик по кнопке 'Войти'
    public SignInPage clickSignInButton() {
        driver.findElement(signInButton).click();

        return new SignInPage(driver);

    }
}
