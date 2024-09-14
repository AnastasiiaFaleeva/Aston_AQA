package lesson16;

import com.example.lesson16.steps.OnlinePaymentsSteps;
import com.example.lesson16.dto.PaymentSectionDto;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static com.example.lesson16.steps.WebDriverInstance.driver;
import static com.example.lesson16.steps.WebDriverInstance.getInstance;

public class OnlinePaymentsTest {

    private OnlinePaymentsSteps steps;

    @BeforeClass
    @Description("Настройка WebDriver, открытие сайта и принятие cookies")
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
    @Description("Тест заполняет поля для различных типов оплаты и проверяет корректность заполнения.")
    public void paymentTypeTest(String type, String specText, String sum, String email) {
        PaymentSectionDto paymentSectionDto = PaymentSectionDto.builder()
                .paymentType(type)
                .specialText(specText)
                .sum(sum)
                .email(email)
                .build();
        scrollToPaymentSection();
        clickDropdownButton();
        fillPaymentSection(paymentSectionDto);
    }

    @Test(description = "Проверка корректности данных в модальном окне после нажатия на кнопку 'Продолжить'", priority = 2)
    @Description("Тест проверяет корректность данных в модальном окне, включая сумму, номер телефона и плейсхолдеры.")
    public void paymentModalTest() {
        PaymentSectionDto paymentSectionDto = PaymentSectionDto.builder()
                .paymentType("Услуги связи")
                .specialText("297777777")
                .sum("50")
                .email("connection@mail.ru")
                .build();

        clickDropdownButton();
        fillPaymentSection(paymentSectionDto);
        submitPayment();
        switchToPaymentFrame();

        assertTrue(isModalDisplayed(), "Модальное окно не отображается.");
        assertEquals(getSumOnButton(), "Оплатить 50.00 BYN", "Сумма на кнопке некорректна.");
        assertEquals(getPhoneNumber(), "Оплата: Услуги связи Номер:375297777777",
                "Номер телефона некорректен.");

        String[] expectedCardPlaceholders = {"Номер карты", "CVC"};
        assertTrue(verifyCardPlaceholders(expectedCardPlaceholders),
                "Placeholder'ы для полей карты некорректны.");

        String[] expectedPaymentIcons = {"Visa", "MasterCard", "Белкарт", "Мир"};
        assertTrue(verifyPaymentIcons(expectedPaymentIcons),
                "Иконки платёжных систем некорректны или отсутствуют.");
    }

    @AfterMethod
    @Step("Обновление страницы после теста")
    public void refreshPage() {
        driver.navigate().refresh();
    }

    @AfterClass
    @Description("Закрытие браузера после выполнения тестов")
    @Step("Закрытие браузера")
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    @Step("Скроллим до секции оплаты")
    public void scrollToPaymentSection() {
        steps.scrollToPaymentSection();
    }

    @Step("Нажимаем на кнопку для открытия списка типов оплаты")
    public void clickDropdownButton() {
        steps.clickDropdownButton();
    }

    @Step("Заполняем секцию оплаты")
    public void fillPaymentSection(PaymentSectionDto paymentSectionDto) {
        steps.fillPaymentSection(paymentSectionDto);
    }

    @Step("Нажимаем кнопку 'Продолжить'")
    public void submitPayment() {
        steps.submitPayment();
    }

    @Step("Переходим в iframe для оплаты")
    public void switchToPaymentFrame() {
        WebElement frame = driver.findElement(By.xpath("//iframe[@class='bepaid-iframe']"));
        driver.switchTo().frame(frame);
    }

    @Step("Проверяем, отображается ли модальное окно")
    public boolean isModalDisplayed() {
        return steps.isModalDisplayed();
    }

    @Step("Получаем сумму на кнопке")
    public String getSumOnButton() {
        return steps.getSumOnButton();
    }

    @Step("Получаем номер телефона")
    public String getPhoneNumber() {
        return steps.getPhoneNumber();
    }

    @Step("Проверяем плейсхолдеры для реквизитов карты")
    public boolean verifyCardPlaceholders(String[] expectedPlaceholders) {
        return steps.verifyCardPlaceholders(expectedPlaceholders);
    }

    @Step("Проверяем иконки платёжных систем")
    public boolean verifyPaymentIcons(String[] expectedIcons) {
        return steps.verifyPaymentIcons(expectedIcons);
    }
}