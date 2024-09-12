package com.example.lesson16.steps;

import com.example.lesson16.dto.PaymentSectionDto;
import com.example.lesson16.pages.OnlinePaymentsSection;
import com.example.lesson16.pages.OnlinePaymentsPopup;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.example.lesson16.steps.WebDriverInstance.driver;

public class OnlinePaymentsSteps {

    private final OnlinePaymentsSection paymentSection;
    private final OnlinePaymentsPopup paymentsPopup;

    public OnlinePaymentsSteps(WebDriver driver) {
        this.paymentSection = new OnlinePaymentsSection(driver);
        this.paymentsPopup = new OnlinePaymentsPopup(driver);
    }

    public void scrollToPaymentSection() {
        WebElement paymentSectionElement = paymentSection.getPaymentSection();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", paymentSectionElement);
    }

    public void clickDropdownButton() {
        paymentSection.getDropdownButton().click();
    }

    public void selectPaymentType(String paymentType) {
        paymentSection.getPaymentType(paymentType).click();
    }

    public void fillSpecialField(String paymentType, String text) {
        paymentSection.getSpecialField(paymentType).sendKeys(text);
    }

    public void fillSumField(String sumAmount) {
        paymentSection.getSumField().sendKeys(sumAmount);
    }

    public void fillEmailField(String email) {
        paymentSection.getEmailField().sendKeys(email);
    }

    public void fillPaymentSection(PaymentSectionDto paymentSectionDto) {
        selectPaymentType(paymentSectionDto.getPaymentType());
        fillSpecialField(paymentSectionDto.getPaymentType(), paymentSectionDto.getSpecialText());
        fillSumField(paymentSectionDto.getSum());
        fillEmailField(paymentSectionDto.getEmail());
    }

    public void submitPayment() {
        paymentSection.clickContinueButton();
    }

    public boolean isModalDisplayed() {
        return paymentsPopup.isModalDisplayed();
    }

    public String getSumOnButton() {
        return paymentsPopup.getSumOnButton();
    }

    public String getPhoneNumber() {
        return paymentsPopup.getPhoneNumber();
    }

    public boolean verifyCardPlaceholders(String[] expectedPlaceholders) {
        return paymentsPopup.verifyCardPlaceholders(expectedPlaceholders);
    }

    public boolean verifyPaymentIcons(String[] expectedIcons) {
        return paymentsPopup.verifyPaymentIcons(expectedIcons);
    }
}