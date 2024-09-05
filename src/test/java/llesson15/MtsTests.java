package llesson15;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.*;

public class MtsTests {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.mts.by/");
        wait = new WebDriverWait(driver, 10);
        WebElement acceptCookiesButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cookie-agree")));
        if (acceptCookiesButton != null && acceptCookiesButton.isDisplayed()) {
            acceptCookiesButton.click();
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Наличие заголовка блока 'Онлайн пополнение без комиссии'")
    public void testCheckBlockTitle() {
        WebElement blockTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//div[@class='pay__wrapper']//h2[contains(text(), 'Онлайн пополнение') and " +
                        "contains(., 'без комиссии')]")));
        assertTrue(blockTitle.isDisplayed(), "Заголовок блока не найден или не отображается");
    }

    @Test
    @DisplayName("Проверка наличия логотипов платежных систем")
    public void testCheckPaymentLogos() {
        WebElement paymentLogos = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'pay__partners')]/ul")));

        String[] expectedAlts = {"Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт"};
        for (String altText : expectedAlts) {
            WebElement logo = paymentLogos.findElement(By.xpath(".//img[@alt='" + altText + "']"));
            assertTrue(logo.isDisplayed(), "Логотип с alt-текстом '" + altText +
                    "' не найден или не отображается");
        }
    }

    @Test
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    public void testCheckMoreInfoLik() {
        WebElement moreInfoLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Подробнее о сервисе")));
        moreInfoLink.click();
        assertTrue(driver.getCurrentUrl().contains(
                        "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"),
                "Переход по ссылке 'Подробнее о сервисе' не привел к ожидаемому URL");
    }

    @Test
    @DisplayName("Проверка кнопки 'Продолжить' при оплате")
    public void testCheckContinueButton() {
        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-phone")));
        phoneInput.clear();
        phoneInput.sendKeys("297777777");
        WebElement sumInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-sum")));
        sumInput.clear();
        sumInput.sendKeys("300");
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//button[@type='submit' and contains(@class, 'button__default') " +
                        "and text()='Продолжить']")));
        continueButton.click();
        WebElement modalWindow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//div[contains(@class, 'payment-page__container')]")));
        assertTrue(modalWindow.isDisplayed(), "Модальное окно не отображается.");
    }
}