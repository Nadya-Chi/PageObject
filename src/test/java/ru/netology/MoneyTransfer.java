package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MoneyTransfer {
    private SelenideElement heading = $(byText("Пополнение карты"));

    public MoneyTransfer() {
        heading.shouldBe(Condition.visible);
    }

    public void validTransfer(String value,DataHelper.TransferInfo transferInfo) {
        $("[data-test-id='amount'] [type='text']").setValue(value);
        $("[data-test-id='from'] [type='tel']").setValue(transferInfo.getCardNumber());
        $("[data-test-id='action-transfer']").click();
    }

}
