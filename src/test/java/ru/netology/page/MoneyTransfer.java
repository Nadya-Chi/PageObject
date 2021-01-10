package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class MoneyTransfer {

    private SelenideElement heading = $(byText("Пополнение карты"));

    public MoneyTransfer() {
        heading.shouldBe(Condition.visible);
    }

    public DashboardPage validTransfer(DataHelper.TransferInfo transferInfo, int value) {
        $("[class='input__box'] [type='text']").setValue(String.valueOf(value));
        $("[class='input__box'] [type='tel']").setValue(transferInfo.getCardNumber());
        $("[data-test-id='action-transfer'] [class='button__text']").click();
        return new DashboardPage();
    }

    public SelenideElement noValidTransfer() {
        return $(withText("Недостаточно средств")).shouldBe(Condition.visible);
    }

}
