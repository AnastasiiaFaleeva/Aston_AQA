package com.example.lesson16.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OnlinePaymentsPopup extends BasePage {

    private final By modalWindow = By.xpath("///div[@class='app-wrapper__content-container " +
            "app-wrapper__content-container_full']//section[@class='payment-page payment-page_mobile payment-page_pays']");
    private final By sumOnButton = By.xpath("//button[contains(@class, 'colored') " +
            "and contains(text(), 'Оплатить  50.00 BYN')]");
    private final By phoneNumber = By.xpath("//div[contains(@class, 'pay-description__text')]" +
            "//span[contains(text(), 'Номер:375297777777')]");
    private final By paymentIcons = By.xpath("//div[contains(@class, 'cards-brands')]//img");

    public OnlinePaymentsPopup(WebDriver driver) {
        super(driver);
    }

    public boolean isModalDisplayed() {
        WebElement modal = waitForElementToBeVisible(driver.findElement(modalWindow));
        return modal.isDisplayed();
    }

    public String getSumOnButton() {
        return waitForElementToBeVisible(driver.findElement(sumOnButton)).getText();
    }

    public String getPhoneNumber() {
        return waitForElementToBeVisible(driver.findElement(phoneNumber)).getText();
    }

    public boolean verifyCardPlaceholders(String[] expectedPlaceholders) {
        for (String expectedPlaceholder : expectedPlaceholders) {
            WebElement field = driver.findElement(By.xpath("//input[@placeholder='" + expectedPlaceholder + "']"));
            if (!field.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public boolean verifyPaymentIcons(String[] expectedIcons) {
        WebElement icons = driver.findElement(paymentIcons);
        for (String icon : expectedIcons) {
            WebElement iconElement = icons.findElement(By.xpath(".//img[@alt='" + icon + "']"));
            if (!iconElement.isDisplayed()) {
                return false;
            }
        }
        return true;
    }
}