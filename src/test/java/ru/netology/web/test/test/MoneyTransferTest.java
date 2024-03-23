package ru.netology.web.test.test;

//import lombok.var;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.web.test.data.DataHelper;
import ru.netology.web.test.pages.DashboardPage;
import ru.netology.web.test.pages.LoginPageV1;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.test.data.DataHelper.*;

class MoneyTransferTest {
    LoginPageV1 loginPage;
    DashboardPage dashboardPage;

    @Test
    @DisplayName("Перевод с первой карты на вторую")
    void shouldTransferMoneyFromFirstToSecond() {
        open("http://localhost:9999");

        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);

        var firstCardValue = dashboardPage.getFirstCardBalance();
        var secondCardValue = dashboardPage.getSecondCardBalance();

        var transferPage = dashboardPage.clickOnSecondButton();

        var transfer = DataHelper.getValueTransfer(Math.abs(firstCardValue), card1);

        var anotherPage = transferPage.successfulTransfer(transfer);

        var actualBalanceOfFirstCard = dashboardPage.getFirstCardBalance();
        var actualBalanceOfSecondCard = dashboardPage.getSecondCardBalance();

        Assertions.assertEquals(firstCardValue + transfer.getAmount(), actualBalanceOfFirstCard);
        Assertions.assertEquals(secondCardValue - transfer.getAmount(), actualBalanceOfSecondCard);
    }

    @Test
    @DisplayName("Перевод со второй карты на первую")
    void shouldTransferMoneyFromSecondToFirst() {
        open("http://localhost:9999");

        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);

        var firstCardValue = dashboardPage.getFirstCardBalance();
        var secondCardValue = dashboardPage.getSecondCardBalance();

        var transferPage = dashboardPage.clickOnFirstButton();

        var transfer = DataHelper.getValueTransfer(Math.abs(secondCardValue), card2);

        var anotherPage = transferPage.successfulTransfer(transfer);

        var actualBalanceOfFirstCard = dashboardPage.getFirstCardBalance();
        var actualBalanceOfSecondCard = dashboardPage.getSecondCardBalance();

        Assertions.assertEquals(firstCardValue + transfer.getAmount(), actualBalanceOfFirstCard);
        Assertions.assertEquals(secondCardValue - transfer.getAmount(), actualBalanceOfSecondCard);
    }
}
