package lesson16;

import com.example.lesson16.steps.OnlinePaymentsSteps;
import com.example.lesson16.dto.PaymentSectionDto;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import static com.example.lesson16.steps.WebDriverInstance.driver;
import static com.example.lesson16.steps.WebDriverInstance.getInstance;

public class OnlinePaymentsTest {

    private OnlinePaymentsSteps steps;

    @BeforeClass
    public void setUp() {
        driver = getInstance();
        steps = new OnlinePaymentsSteps(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.mts.by/");

        WebElement acceptCookiesButton = driver.findElement(By.id("cookie-agree"));
        if (acceptCookiesButton != null && acceptCookiesButton.isDisplayed()) {
            acceptCookiesButton.click();
        }
    }

    @DataProvider(name = "paymentData")
    public static Object[][] paymentData() {
        return new Object[][] {
                {"Услуги связи", "297777777", "34", "connection@mail.ru"},
                {"Домашний интернет", "297777777", "48", "internet@mail.ru"},
                {"Рассрочка", "446564645387", "75", "installment@mail.ru"},
                {"Задолженность", "207315158812", "62", "debt@mail.ru"}
        };
    }

    @Test(description = "Проверка заполнения полей для различных типов оплаты", dataProvider = "paymentData", priority = 1)
    public void paymentTypeTest(String type, String specText, String sum, String email) {
        PaymentSectionDto paymentSectionDto = PaymentSectionDto.builder()
                .paymentType(type)
                .specialText(specText)
                .sum(sum)
                .email(email)
                .build();
        steps.scrollToPaymentSection();
        steps.clickDropdownButton();
        steps.fillPaymentSection(paymentSectionDto);
    }

    @Test(description = "Проверка корректности данных в модальном окне после нажатия на кнопку 'Продолжить'", priority = 2)
    public void paymentModalTest() {
        PaymentSectionDto paymentSectionDto = PaymentSectionDto.builder()
                .paymentType("Услуги связи")
                .specialText("297777777")
                .sum("50")
                .email("connection@mail.ru")
                .build();

        steps.clickDropdownButton();
        steps.fillPaymentSection(paymentSectionDto);
        steps.submitPayment();
        WebElement frame = driver.findElement(By.xpath("//iframe[@class='bepaid-iframe']"));
        driver.switchTo().frame(frame);

        assertTrue(steps.isModalDisplayed(), "Модальное окно не отображается.");
        assertEquals(steps.getSumOnButton(), "Оплатить 50.00 BYN", "Сумма на кнопке некорректна.");
        assertEquals(steps.getPhoneNumber(), "Оплата: Услуги связи Номер:375297777777",
                "Номер телефона некорректен.");

        String[] expectedCardPlaceholders = {"Номер карты", "CVC"};
        assertTrue(steps.verifyCardPlaceholders(expectedCardPlaceholders),
                "Placeholder'ы для полей карты некорректны.");

        String[] expectedPaymentIcons = {"Visa", "MasterCard", "Белкарт", "Мир"};
        assertTrue(steps.verifyPaymentIcons(expectedPaymentIcons),
                "Иконки платёжных систем некорректны или отсутствуют.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}