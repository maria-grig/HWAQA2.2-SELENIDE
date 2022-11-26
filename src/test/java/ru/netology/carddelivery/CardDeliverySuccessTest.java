package ru.netology.carddelivery;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class CardDeliverySuccessTest {

    @BeforeEach
    void setUpAll() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }
    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldRegisterValidDateIn3Days() {
        String Date = generateDate(3);
        $("[data-test-id='city'] input").setValue("Воронеж");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(Date);
        $("[data-test-id='name'] input").setValue("Мария Васильева");
        $("[data-test-id='phone'] input").setValue("+79001112233");
        $$("[data-test-id='agreement']").last().click();
        $(".button__text").click();
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + Date), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
    @Test
    void shouldRegisterValidDateIn7Days() {
        String Date = generateDate(7);
        $("[data-test-id='city'] input").setValue("Волгоград");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(Date);
        $("[data-test-id='name'] input").setValue("Мария Васильева-Петрова");
        $("[data-test-id='phone'] input").setValue("+79000000000");
        $$("[data-test-id='agreement']").last().click();
        $(".button__text").click();
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + Date), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldRegisterValidShortName() {
        String Date = generateDate(7);
        $("[data-test-id='city'] input").setValue("Владивосток");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(Date);
        $("[data-test-id='name'] input").setValue("Петрова");
        $("[data-test-id='phone'] input").setValue("+79000000000");
        $$("[data-test-id='agreement']").last().click();
        $(".button__text").click();
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + Date), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

}
