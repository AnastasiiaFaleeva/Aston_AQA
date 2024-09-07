package com.example.lesson16.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OnlinePaymentsSection extends BasePage {

    private final By paymentSection = By.xpath("//section[@class='pay']");
    private final By dropdownButton = By.xpath("//div[@class='select']");
    private final By paymentTypes = By.xpath("//div[@class='select']" +
            "//ul[@class='select__list']/li/p");
    private final By sumField = By.xpath("//form[@class='pay-form opened']" +
            "//input[@placeholder='Сумма']");
    private final By emailField = By.xpath("//form[@class='pay-form opened']" +
            "//input[@placeholder='E-mail для отправки чека']");
    private final By continueButton = By.xpath("//form[@id='pay-connection']" +
            "//button[@type='submit' and contains(@class, 'button__default')]");

    public OnlinePaymentsSection(WebDriver driver) {
        super(driver);
    }

    public WebElement getPaymentSection() {
        return driver.findElement(paymentSection);
    }

    public WebElement getDropdownButton() {
        return driver.findElement(dropdownButton);
    }

    public WebElement getPaymentType(String paymentType) {
        WebElement elementType = null;
        for (WebElement type : driver.findElements(paymentTypes)) {
            if (type.getText().equals(paymentType)) {
                elementType = type;
            }
        }
        return elementType;
    }

    public WebElement getSpecialField(String paymentType) {
        String fieldId = switch (paymentType) {
            case "Услуги связи" -> "connection-phone";
            case "Домашний интернет" -> "internet-phone";
            case "Рассрочка" -> "score-instalment";
            case "Задолженность" -> "score-arrears";
            default -> throw new IllegalStateException("Неизвестный тип оплаты: " + paymentType);
        };
        return driver.findElement(By.id(fieldId));
    }

    public WebElement getSumField() {
        return driver.findElement(sumField);
    }

    public WebElement getEmailField() {
        return driver.findElement(emailField);
    }

    public void clickContinueButton() {
        driver.findElement(continueButton).click();
    }
}