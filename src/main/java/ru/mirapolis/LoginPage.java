package ru.mirapolis;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final String currentUrl = "https://lmslite47vr.demo.mirapolis.ru/mira";
    private final SelenideElement userLogin = $("[name = 'user']");
    private final SelenideElement userPassword = $("[name = 'password']");
    private final SelenideElement singInBtn = $("#button_submit_login_form");
    private final SelenideElement linkForgotPassword = $(".mira-default-login-page-link");
    private final SelenideElement iconShowPassword = $("#show_password");
    private final String textWrongLoginPassword = "Неверные данные для авторизации";

    public void open() {
        Selenide.open(currentUrl);
    }

    public void inputLogin(String login) {
        userLogin.setValue(login);
    }

    public void inputPassword(String password) {
        userPassword.setValue(password);
    }

    public void clickLoginBtn() {
        singInBtn.click();
    }

    public void fillOutForm(String login, String password) {
        userLogin.setValue(login);
        userPassword.setValue(password);
        singInBtn.click();
    }

    public boolean textAlert(String text) {
        return text.contains(textWrongLoginPassword);
    }

    public void clickShowPassword() {
        iconShowPassword.click();
    }

    public void clickLinkForgotPassword() {
        linkForgotPassword.click();
    }
}
