package ru.netology.carddelivery;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryFailTest {

    @BeforeEach
    void setUpAll() {
        Configuration.timeout = 10_000;
        //Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldFailInvalidDate() {
        String Date = generateDate(2);
        $("[data-test-id=city] input").setValue("Москва").pressTab();
        $("[data-test-id=date] .input__control");
        $("[data-test-id=date] .input__control").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] .input__control").setValue(Date).pressTab();
        $("[data-test-id=name] input").setValue("Мария Петрова").pressTab();
        $("[data-test-id=phone] input").setValue("+78009008070").pressTab();
        $("[data-test-id=agreement]").click();
        $(".button__text").click();
        $("[data-test-id=date] .input__sub")
                .shouldBe(visible)
                .shouldHave(text("Заказ на выбранную дату невозможен"));
    }

    @Test
    void shouldFailInvalidCity() {
        String Date = generateDate(4);
        $("[data-test-id=city] input").setValue("Париж").pressTab();
        $("[data-test-id=date] .input__control");
        $("[data-test-id=date] .input__control").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] .input__control").setValue(Date).pressTab();
        $("[data-test-id=name] input").setValue("Мария Петрова").pressTab();
        $("[data-test-id=phone] input").setValue("+78009008070").pressTab();
        $("[data-test-id=agreement]").click();
        $(".button__text").click();
        $("[data-test-id=city] .input__sub")
                .shouldBe(visible)
                .shouldHave(text("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldFailInvalidName() {
        String Date = generateDate(4);
        $("[data-test-id=city] input").setValue("Москва").pressTab();
        $("[data-test-id=date] .input__control");
        $("[data-test-id=date] .input__control").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] .input__control").setValue(Date).pressTab();
        $("[data-test-id=name] input").setValue("Maria Petrova").pressTab();
        $("[data-test-id=phone] input").setValue("+78009008070").pressTab();
        $("[data-test-id=agreement]").click();
        $(".button__text").click();
        $("[data-test-id='name'] .input__sub")
                .shouldBe(visible)
                .shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldFailInvalidPhone() {
        String Date = generateDate(5);
        $("[data-test-id=city] input").setValue("Москва").pressTab();
        $("[data-test-id=date] .input__control");
        $("[data-test-id=date] .input__control").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] .input__control").setValue(Date).pressTab();
        $("[data-test-id=name] input").setValue("Мария Петрова").pressTab();
        $("[data-test-id=phone] input").setValue("+7800900807").pressTab();
        $("[data-test-id=agreement]").click();
        $(".button__text").click();
        $("[data-test-id=phone] .input__sub")
                .shouldBe(visible)
                .shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldFailWithoutAgreement() {
        String Date = generateDate(6);
        $("[data-test-id=city] input").setValue("Москва").pressTab();
        $("[data-test-id=date] .input__control");
        $("[data-test-id=date] .input__control").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] .input__control").setValue(Date).pressTab();
        $("[data-test-id=name] input").setValue("Мария Петрова").pressTab();
        $("[data-test-id=phone] input").setValue("+7800900807").pressTab();
        $(".button__text").click();
        $("[data-test-id=agreement] .checkbox__box").shouldBe(visible);
    }
}