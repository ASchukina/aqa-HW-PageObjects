package ru.netology.web.test.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.test.data.DataHelper;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.awt.SystemColor.text;

public class DashboardPage {
    private SelenideElement header = $("[data-test-id='dashboard']");
    private SelenideElement firstCardId = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private SelenideElement secondCardId = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
    String cardsButton = "[data-test-id='action-deposit']";

    public DashboardPage() {
        header.shouldBe(visible);
    }

    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
        val text = cards.get(1).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TransferPage clickOnFirstButton() {
        firstCardId.$(cardsButton).click();
        return new TransferPage();
    }

    public TransferPage clickOnSecondButton() {
        secondCardId.$(cardsButton).click();;
        return new TransferPage();
    }

}
