package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @Test
    void shouldTransferMoneyBetweenOwnCardsFrom2To1() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage =  verificationPage.validVerify(verificationCode);
        val value = 5000;
        val firstCardBalance = dashboardPage.getFirstCardBalance();
        val secondCardBalance = dashboardPage.getSecondCardBalance();
        val firstCardBalanceAfter = DataHelper.getBalanceCardReplenish(firstCardBalance, value);
        val secondCardBalanceAfter = DataHelper.getBalanceCardWithdraw(secondCardBalance, value);
        val transferInfo = DataHelper.getCardNumberSecond();
        val transferPage = dashboardPage.moneyTransfer0001();
        transferPage.validTransfer(transferInfo, value);
        assertEquals(firstCardBalanceAfter, dashboardPage.getFirstCardBalance());
        assertEquals(secondCardBalanceAfter, dashboardPage.getSecondCardBalance());
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsFrom1To2() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage =  verificationPage.validVerify(verificationCode);
        val value = 5000;
        val firstCardBalance = dashboardPage.getFirstCardBalance();
        val secondCardBalance = dashboardPage.getSecondCardBalance();
        val firstCardBalanceAfter = DataHelper.getBalanceCardWithdraw(firstCardBalance, value);
        val secondCardBalanceAfter = DataHelper.getBalanceCardReplenish(secondCardBalance, value);
        val transferInfo = DataHelper.getCardNumberFirst();
        val transferPage = dashboardPage.moneyTransfer0002();
        transferPage.validTransfer(transferInfo, value);
        assertEquals(firstCardBalanceAfter, dashboardPage.getFirstCardBalance());
        assertEquals(secondCardBalanceAfter, dashboardPage.getSecondCardBalance());
    }

    @Test
    void shouldNoTransferMoneyBetweenOwnCards() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage =  verificationPage.validVerify(verificationCode);
        val value = 11000;
        val transferInfo = DataHelper.getCardNumberFirst();
        val transferPage = dashboardPage.moneyTransfer0002();
        transferPage.validTransfer(transferInfo, value);
        transferPage.noValidTransfer();
    }
}
