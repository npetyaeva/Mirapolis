package ru.mirapolis;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPageTest {
    private final LoginPage loginPage = new LoginPage();
    private final ProfilePage profilePage = new ProfilePage();
    private final RecoveryPage recoveryPage = new RecoveryPage();

    // 1. Авторизация с корректными данными (fominaelena/1P73BP4Z)
    @Test
    public void shouldAuthorizationWithCorrectData() {
        loginPage.open();
        loginPage.fillOutForm("fominaelena", "1P73BP4Z");
        assertTrue(profilePage.avatarFullName("Фомина Елена Сергеевна"), "The login and password are correct, but the authorization failed");
        profilePage.logOut();
    }

    // 2. Авторизация с корректным логином, начинающимся и завершающимся несколькими пробелами,
    // и с корректным паролем (  fominaelena  /1P73BP4Z)
    @Test
    public void shouldCheckLoginWithSpaces() {
        loginPage.open();
        loginPage.fillOutForm("  fominaelena  ", "1P73BP4Z");
        assertTrue(profilePage.avatarFullName("Фомина Елена Сергеевна"), "The login and password are correct, but the authorization failed");
        profilePage.logOut();
    }

    //3. Авторизация с корректным логином и с корректным паролем, начинающимся
    // и завершающимся несколькими пробелами (fominaelena/  1P73BP4Z  )
    @Test
    public void shouldCheckPasswordWithSpaces() {
        loginPage.open();
        loginPage.fillOutForm("fominaelena", "  1P73BP4Z  ");
        assertTrue(profilePage.avatarFullName("Фомина Елена Сергеевна"), "The login and password are correct, but the authorization failed");
        profilePage.logOut();
    }

    //4. Авторизация с некорректными данными (fomina/0P73BP4Z)
    @Test
    public void shouldCheckAuthorizationWithIncorrectData() {
        loginPage.open();
        loginPage.fillOutForm("fomina", "0P73BP4Z");
        assertTrue(loginPage.textAlert(getWebDriver().switchTo().alert().getText()));
        Selenide.switchTo().alert().dismiss();
    }

    // 5. Ввод некорректного логина и корректного пароля (fomina/1P73BP4Z)
    @Test
    public void shouldCheckLoginIsIncorrect() {
        loginPage.open();
        loginPage.fillOutForm("fomina", "1P73BP4Z");
        assertTrue(loginPage.textAlert(getWebDriver().switchTo().alert().getText()));
        Selenide.switchTo().alert().dismiss();
    }

    // 6. Ввод корректного логина и некорректного пароля fominaelena/0P73BP4Z
    @Test
    public void shouldCheckPasswordIsIncorrect() {
        loginPage.open();
        loginPage.fillOutForm("fominaelena", "0P73BP4Z");
        assertTrue(loginPage.textAlert(getWebDriver().switchTo().alert().getText()));
        Selenide.switchTo().alert().dismiss();
    }

    // 7. Авторизация с пустыми полями (""/"")
    @Test
    public void shouldCheckLoginPasswordIsEmpty() {
        loginPage.open();
        loginPage.clickLoginBtn();
        assertTrue(loginPage.textAlert(getWebDriver().switchTo().alert().getText()));
        Selenide.switchTo().alert().dismiss();
    }

    // 8. Авторизация с пустым полем "логин" (""/1P73BP4Z)
    @Test
    public void shouldCheckLoginIsEmpty() {
        loginPage.open();
        loginPage.inputPassword("1P73BP4Z");
        loginPage.clickLoginBtn();
        assertTrue(loginPage.textAlert(getWebDriver().switchTo().alert().getText()),
                "Login is incorrect, no error message");
        Selenide.switchTo().alert().dismiss();
    }

    // 9. Авторизация с пустым полем "пароль" (fominaelena/"")
    @Test
    public void shouldCheckPasswordIsEmpty() {
        loginPage.open();
        loginPage.inputLogin("fominaelena");
        loginPage.clickLoginBtn();
        assertTrue(loginPage.textAlert(getWebDriver().switchTo().alert().getText()),
                "Password is incorrect, no error message");
        Selenide.switchTo().alert().dismiss();
    }

    // 10. Ввод пробелов во все поля(" "/" ")
    @Test
    public void shouldCheckLoginPasswordSpaces() {
        loginPage.open();
        loginPage.fillOutForm(" ", " ");
        assertTrue(loginPage.textAlert(getWebDriver().switchTo().alert().getText()));
        Selenide.switchTo().alert().dismiss();
    }

    // 11. Ввод пробела в поле логин (" "/1P73BP4Z)
    @Test
    public void shouldCheckLoginSpace() {
        loginPage.open();
        loginPage.fillOutForm(" ", "1P73BP4Z");
        assertTrue(loginPage.textAlert(getWebDriver().switchTo().alert().getText()));
        Selenide.switchTo().alert().dismiss();
    }

    // 12. Ввод пробела в поле пароль (fominaelena/" ")
    @Test
    public void shouldCheckPasswordSpace() {
        loginPage.open();
        loginPage.fillOutForm("fominaelena", " ");
        assertTrue(loginPage.textAlert(getWebDriver().switchTo().alert().getText()));
        Selenide.switchTo().alert().dismiss();
    }

    // 13. Проверка смены регистра при заполнении логина (FOMINAELENA/1P73BP4Z)
    @Test
    public void shouldCheckLoginIfChangeCase() {
        loginPage.open();
        loginPage.fillOutForm("FOMINAELENA", "1P73BP4Z");
        assertTrue(profilePage.avatarFullName("Фомина Елена Сергеевна"));
        profilePage.logOut();
    }

    // 14. Проверка смены регистра при заполнении пароля (fominaelena/1p73bp4z)
    @Test
    public void shouldCheckPasswordIfChangeCase() {
        loginPage.open();
        loginPage.fillOutForm("fominaelena", "1p73bp4z");
        assertTrue(loginPage.textAlert(getWebDriver().switchTo().alert().getText()));
        Selenide.switchTo().alert().dismiss();
    }

    // 15. Проверка видимости пароля при нажатии на иконку глаз (1p73bp4z)
    @Test
    public void shouldCheckButtonShowPassword() {
        loginPage.open();
        loginPage.inputPassword("1p73bp4z");
        loginPage.clickShowPassword();
        assertEquals("text", $(By.name("password")).getAttribute("type"));
    }

    // 16. Проверка видимости пароля при двойном нажатии на иконку глаз (1p73bp4z)
    @Test
    public void shouldCheckButtonShowPasswordTwice() {
        loginPage.open();
        loginPage.inputPassword("1p73bp4z");
        loginPage.clickShowPassword();
        loginPage.clickShowPassword();
        assertEquals("password", $(By.name("password")).getAttribute("type"));
    }

    // 17. Переход по ссылке «Забыли пароль?» на страницу "Восстановление пароля" и обратно
    @Test
    public void shouldGoRecoveryPageAndBack() {
        loginPage.open();
        loginPage.clickLinkForgotPassword();
        recoveryPage.clickLinkBackLoginPage();
        //assertEquals("Вход в систему", $(By.className("info-title")).getText());
        assertTrue($(By.id("login_form_panel")).exists());
    }

    // 18. Ввод корректного логина на странице "Восстановление пароля" (fominaelena)
    @Test
    public void shouldCheckInputCorrectLoginRecoveryPage() {
        loginPage.open();
        loginPage.clickLinkForgotPassword();
        recoveryPage.inputLoginOrEmail("fominaelena");
        recoveryPage.clickSubmitBtn();
        assertTrue(recoveryPage.textAlertCorrectLoginEmail($(By.className("success")).getText()));
        recoveryPage.clickLinkBackLoginPage();
    }

    // 19. Ввод корректного email на странице "Восстановление пароля" (efomina@company.ru)
    @Test
    public void shouldCheckInputCorrectEmailRecoveryPage() {
        loginPage.open();
        loginPage.clickLinkForgotPassword();
        recoveryPage.inputLoginOrEmail("efomina@company.ru");
        recoveryPage.clickSubmitBtn();
        assertTrue(recoveryPage.textAlertCorrectLoginEmail($(By.className("success")).getText()));
        recoveryPage.clickLinkBackLoginPage();
    }

    // 20. Ввод некорректного логина на странице "Восстановление пароля" (fomina)
    @Test
    public void shouldCheckInputIncorrectLoginRecoveryPage() {
        loginPage.open();
        loginPage.clickLinkForgotPassword();
        recoveryPage.inputLoginOrEmail("fomina");
        recoveryPage.clickSubmitBtn();
        assertTrue(recoveryPage.textAlertWrongLoginEmail($(By.className("alert")).getText()),
                "Login is incorrect, no error message");
        recoveryPage.clickLinkBackLoginPage();
    }

    // 21. Ввод некорректного email на странице "Восстановление пароля" (efomina@compa.ru)
    @Test
    public void shouldCheckInputIncorrectEmailRecoveryPage() {
        loginPage.open();
        loginPage.clickLinkForgotPassword();
        recoveryPage.inputLoginOrEmail("efomina@compa.ru");
        recoveryPage.clickSubmitBtn();
        assertTrue(recoveryPage.textAlertWrongLoginEmail($(By.className("alert")).getText()),
                "Email is incorrect, no error message");
        recoveryPage.clickLinkBackLoginPage();
    }
}
