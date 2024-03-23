package ru.netology.web.test.pages;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.test.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    public DashboardPage successfulTransfer(DataHelper.Transfer info) {
        $("[data-test-id='amount'] input").setValue(info.getAmount().toString());
        $("[data-test-id='from'] input").setValue(info.getCardFrom());
        $("[data-test-id='action-transfer']").click();
        return new DashboardPage();
    }

}
