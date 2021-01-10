package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = ", баланс: ";
    private final String balanceFinish = " р. ";

    public DashboardPage() {
        heading.shouldBe(Condition.visible);
    }

    public MoneyTransfer moneyTransfer0001() {
        $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'].button").click();
        return new MoneyTransfer();
    }

    public MoneyTransfer moneyTransfer0002() {
        $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'].button").click();
        return new MoneyTransfer();
    }
}
