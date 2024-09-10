package com.example.lesson16.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class OnlinePaymentsPopup extends BasePage {

    private final By modalWindow = By.xpath("//div[@class='app-wrapper__content-container " +
            "app-wrapper__content-container_full']//section[@class='payment-page payment-page_mobile payment-page_pays']");
    private final By sumOnButton = By.xpath("//button[contains(@class, 'colored') " +
            "and contains(text(), 'Оплатить  50.00 BYN')]");
    private final By phoneNumber = By.xpath("//div[contains(@class, 'pay-description__text')]" +
            "//span[contains(text(), 'Номер:375297777777')]");

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
        List<String> actualPlaceholders = new ArrayList<>();
        List<WebElement> labels = driver.findElements(By.xpath("//label"));
        for (WebElement label : labels) {
            actualPlaceholders.add(label.getText());
        }
        for (String expectedPlaceholder : expectedPlaceholders) {
            if (!actualPlaceholders.contains(expectedPlaceholder)) {
                System.out.println("Ожидаемый placeholder не найден: " + expectedPlaceholder);
                return false;
            }
        }
        return true;
    }

    public boolean verifyPaymentIcons(String[] expectedIcons) {
        List<WebElement> icons = driver.findElements(By.xpath("//div[@class='cards-brands__container']//img"));
        List<String> actualIcons = new ArrayList<>();

        for (WebElement icon : icons) {
            String src = icon.getAttribute("src");
            if (src != null) {
                if (src.contains("visa-system")) {
                    actualIcons.add("Visa");
                } else if (src.contains("mastercard-system")) {
                    actualIcons.add("MasterCard");
                } else if (src.contains("belkart-system")) {
                    actualIcons.add("Белкарт");
                } else if (src.contains("maestro-system")) {
                    actualIcons.add("Maestro");
                } else if (src.contains("mir-system-ru")) {
                    actualIcons.add("Мир");
                }
            }
        }
        return Arrays.asList(expectedIcons).containsAll(actualIcons);
    }
}