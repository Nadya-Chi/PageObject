package ru.netology;

import lombok.val;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransfer {

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    }
}
