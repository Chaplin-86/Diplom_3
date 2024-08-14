import PageObjects.MainPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static praktikum.EnvConfig.BASE_URL;

public class ConstructorTests {
    private DriverRule driverRule = new DriverRule();

    @Rule
    public DriverRule driver = new DriverRule();

    @Test
    @DisplayName("Проверка перехода к разделу 'Булки'")
    public void goToBunTabTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URL);

        new MainPage(driver)
                .clickSauceTab()
                .clickBunsTab();

        Boolean actual = new MainPage(driver).isBubsVisible();

        System.out.println(actual);
    }


    @Test
    @DisplayName("Проверка перехода к разделу 'Соусы'")
    public void goToSauceTabTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URL);

        new MainPage(driver)
                .clickSauceTab();

        Boolean actual = new MainPage(driver).isSauceVisible();

        System.out.println(actual);
    }

    @Test
    @DisplayName("Проверка перехода к разделу 'Начинки'")
    public void goToFillingTabTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URL);

        new MainPage(driver)
                .clickFillingTab();

        Boolean actual = new MainPage(driver).isFillingVisible();

        System.out.println(actual);
    }

}
