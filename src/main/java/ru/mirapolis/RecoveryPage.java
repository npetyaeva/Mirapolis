package ru.mirapolis;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class RecoveryPage {
    private final SelenideElement fieldLoginOrEmail = $(By.name("loginOrEmail"));
    private final SelenideElement linkBackLoginPage = $(By.className("mira-page-forgot-password-link"));
    private final SelenideElement submitBtn = $(By.className("mira-page-forgot-password-button"));
    private final String textCorrectLoginEmail = "На ваш электронный адрес отправлена инструкция по восстановлению пароля.";
    private final String textWrongLoginEmail = "Пользователь с таким именем не найден.";

    public void clickLinkBackLoginPage() {
        linkBackLoginPage.click();
    }

    public void inputLoginOrEmail(String text) {
        fieldLoginOrEmail.setValue(text);
    }

    public void clickSubmitBtn() {
        submitBtn.click();
    }

    public boolean textAlertCorrectLoginEmail(String text) {
        return text.contains(textCorrectLoginEmail);
    }

    public boolean textAlertWrongLoginEmail(String text) {
        return text.contains(textWrongLoginEmail);
    }
}
