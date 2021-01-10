package ru.netology;

import lombok.val;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val value = 5000;
        val firstCardBalance = dashboardPage.getFirstCardBalance();
        val secondCardBalance = dashboardPage.getSecondCardBalance();
        val firstExpectedBalanceCard = firstCardBalance + value;
        val secondCardExpectedBalance = secondCardBalance - value;
        val moneyTransfer = dashboardPage.moneyTransfer0001();
        moneyTransfer.validTransfer(String.valueOf(value), DataHelper.getCardNumberSecond());
        assertEquals(firstExpectedBalanceCard, dashboardPage.getFirstCardBalance());
        assertEquals(secondCardExpectedBalance, dashboardPage.getSecondCardBalance());
    }
}
