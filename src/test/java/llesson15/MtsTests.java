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

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.mts.by/");
        WebDriverWait wait = new WebDriverWait(driver, 10);

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
    @DisplayName("Проверка наличия заголовка блока 'Онлайн пополнение без комиссии'")
    public void testCheckBlockTitle() {
        WebElement blockTitle = driver.findElement(By.xpath(
                "//div[@class='pay__wrapper']//h2[contains(text(), " +
                        "'Онлайн пополнение') and contains(., 'без комиссии')]"));
        assertNotNull(blockTitle, "Заголовок блока не найден");
    }

    @Test
    @DisplayName("Проверка наличия логотипов платежных систем")
    public void testCheckPaymentLogos() {
        WebElement paymentLogos = driver.findElement(By.xpath("//div[contains(@class, 'pay__partners')]/ul"));
        assertNotNull(paymentLogos, "Блок с логотипами платежных систем не найден");

        String[] expectedAlts = {"Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт"};
        for (String altText : expectedAlts) {
            WebElement logo = paymentLogos.findElement(By.xpath(".//img[@alt='" + altText + "']"));
            assertNotNull(logo, "Логотип с alt-текстом '" + altText + "' не найден");
        }
    }

    @Test
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    public void testCheckMoreInfoLik() {
        WebElement moreInfoLink = driver.findElement(By.linkText("Подробнее о сервисе"));
        assertNotNull(moreInfoLink, "Ссылка 'Подробнее о сервисе' не найдена");
        moreInfoLink.click();
        assertTrue(driver.getCurrentUrl().contains(
                        "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"),
                "Переход по ссылке 'Подробнее о сервисе' не выполнен");
    }

    @Test
    @DisplayName("Проверка кнопки 'Продолжить' при оплате")
    public void testCheckContinueButto() {
        WebElement phoneInput = driver.findElement(By.id("connection-phone"));
        assertNotNull(phoneInput, "Поле для ввода номера телефона не найдено");
        phoneInput.clear();
        phoneInput.sendKeys("297777777");

        WebElement sumInput = driver.findElement(By.id("connection-sum"));
        assertNotNull(sumInput, "Поле для ввода суммы не найдено");
        sumInput.clear();
        sumInput.sendKeys("300");

        WebElement continueButton = driver.findElement(By.xpath(
                "//button[@type='submit' and contains(@class, 'button__default')" +
                        " and text()='Продолжить']"));
        assertNotNull(continueButton, "Кнопка 'Продолжить' не найдена");
        continueButton.click();
    }
}