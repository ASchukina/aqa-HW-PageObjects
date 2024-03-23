package ru.netology.web.test.pages;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.test.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    public DashboardPage successfulTransfer(DataHelper.Transfer info) {
        $("[data-test-id='amount'] input").setValue(info.getAmount().toString());
        $("[data-test-id='from'] input").setValue(info.getCardFrom());
        $("[data-test-id='action-transfer']").click();
        return new DashboardPage();
    }

    public TransferPage emptyTransfer(String expectedError) {
        $("[data-test-id='amount'] input").setValue("");
        $("[data-test-id='from'] input").setValue("");
        $("[data-test-id='action-transfer']").click();
        $("[data-test-id='error-notification']").shouldBe(visible);
        return new TransferPage();
    }

    public DashboardPage cancelTransfer(DataHelper.Transfer info) {
        $("[data-test-id='amount'] input").setValue(info.getAmount().toString());
        $("[data-test-id='from'] input").setValue(info.getCardFrom());
        $("[data-test-id='action-cancel']").click();
        return new DashboardPage();
    }

}
