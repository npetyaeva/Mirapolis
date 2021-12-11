package ru.mirapolis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageTest {
    public static ChromeDriver driver;

    @BeforeEach
    public void setUpDriver() {
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://lmslite47vr.demo.mirapolis.ru/mira");
    }

    // 1. Авторизация с корректными данными (fominaelena/1P73BP4Z)
    @Test
    public void shouldCheckLoginPasswordCorrect() {
        driver.findElement(By.name("user")).sendKeys("fominaelena");
        driver.findElement(By.name("password")).sendKeys("1P73BP4Z");
        driver.findElement(By.id("button_submit_login_form")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span.mira-label-text")));

        assertEquals("Главная страница", driver.getTitle());
    }

    // 2. Авторизация с корректным логином, начинающимся и завершающимся несколькими пробелами,
    // и с корректным паролем (  fominaelena  /1P73BP4Z)
    @Test
    public void shouldCheckLoginWithSpaces() {
        driver.findElement(By.name("user")).sendKeys("  fominaelena  ");
        driver.findElement(By.name("password")).sendKeys("1P73BP4Z");
        driver.findElement(By.id("button_submit_login_form")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span.mira-label-text")));

        assertEquals("Главная страница", driver.getTitle());
    }

    //3. Авторизация с корректным логином и с корректным паролем, начинающимся
    // и завершающимся несколькими пробелами (fominaelena/  1P73BP4Z  )
    @Test
    public void shouldCheckPasswordWithSpaces() {
        driver.findElement(By.name("user")).sendKeys("fominaelena");
        driver.findElement(By.name("password")).sendKeys("  1P73BP4Z  ");
        driver.findElement(By.id("button_submit_login_form")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span.mira-label-text")));

        assertEquals("Главная страница", driver.getTitle());
    }

    //4. Авторизация с некорректными данными (fomina/ 0P73BP4Z)
    @Test
    public void shouldCheckLoginPasswordNotCorrect() {
        driver.findElement(By.name("user")).sendKeys("fomina");
        driver.findElement(By.name("password")).sendKeys("0P73BP4Z");
        driver.findElement(By.id("button_submit_login_form")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        assertEquals("Неверные данные для авторизации", alert.getText());
    }

    // 5. Ввод некорректного логина и корректного пароля (fomina/1P73BP4Z)
    @Test
    public void shouldCheckLoginNotCorrect() {
        driver.findElement(By.name("user")).sendKeys("fomina");
        driver.findElement(By.name("password")).sendKeys("1P73BP4Z");
        driver.findElement(By.id("button_submit_login_form")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        assertEquals("Неверные данные для авторизации", alert.getText());
    }

    // 6. Ввод корректного логина и некорректного пароля fominaelena/0P73BP4Z
    @Test
    public void shouldCheckPasswordNotCorrect() {
        driver.findElement(By.name("user")).sendKeys("fominaelena");
        driver.findElement(By.name("password")).sendKeys("0P73BP4Z");
        driver.findElement(By.id("button_submit_login_form")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        assertEquals("Неверные данные для авторизации", alert.getText());
    }

    // 7. Авторизация с пустыми полями (""/"")
    @Test
    public void shouldCheckLoginPasswordIsEmpty() {
        driver.findElement(By.name("user")).sendKeys("");
        driver.findElement(By.name("password")).sendKeys("");
        driver.findElement(By.id("button_submit_login_form")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        assertEquals("Неверные данные для авторизации.", alert.getText());
    }

    // 8. Авторизация с пустым полем "логин" (""/1P73BP4Z)
    @Test
    public void shouldCheckLoginIsEmpty() {
        driver.findElement(By.name("user")).sendKeys("");
        driver.findElement(By.name("password")).sendKeys("1P73BP4Z");
        driver.findElement(By.id("button_submit_login_form")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        assertEquals("Неверные данные для авторизации.", alert.getText());
    }

    // 9. Авторизация с пустым полем "пароль" (fominaelena/"")
    @Test
    public void shouldCheckPasswordIsEmpty() {
        driver.findElement(By.name("user")).sendKeys("fominaelena");
        driver.findElement(By.name("password")).sendKeys("");
        driver.findElement(By.id("button_submit_login_form")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        assertEquals("Неверные данные для авторизации.", alert.getText());
    }

    // 10. Ввод пробелов во все поля(" "/" ")
    @Test
    public void shouldCheckLoginPasswordSpaces() {
        driver.findElement(By.name("user")).sendKeys(" ");
        driver.findElement(By.name("password")).sendKeys(" ");
        driver.findElement(By.id("button_submit_login_form")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        assertEquals("Неверные данные для авторизации.", alert.getText());
    }

    // 11. Ввод пробела в поле логин (" "/1P73BP4Z)
    @Test
    public void shouldCheckLoginSpace() {
        driver.findElement(By.name("user")).sendKeys(" ");
        driver.findElement(By.name("password")).sendKeys("1P73BP4Z");
        driver.findElement(By.id("button_submit_login_form")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        assertEquals("Неверные данные для авторизации.", alert.getText());
    }

    // 12. Ввод пробела в поле пароль (fominaelena/" ")
    @Test
    public void shouldCheckPasswordSpace() {
        driver.findElement(By.name("user")).sendKeys("fominaelena");
        driver.findElement(By.name("password")).sendKeys(" ");
        driver.findElement(By.id("button_submit_login_form")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        assertEquals("Неверные данные для авторизации.", alert.getText());
    }

    // 13. Проверка смены регистра при заполнении логина (FOMINAELENA/1P73BP4Z)
    @Test
    public void shouldCheckLoginIfChangeCase() {
        driver.findElement(By.name("user")).sendKeys("FOMINAELENA");
        driver.findElement(By.name("password")).sendKeys("1P73BP4Z");
        driver.findElement(By.id("button_submit_login_form")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span.mira-label-text")));

        assertEquals("Главная страница", driver.getTitle());
    }

    // 14. Проверка смены регистра при заполнении пароля (fominaelena/1p73bp4z)
    @Test
    public void shouldCheckPasswordChangeCase() {
        driver.findElement(By.name("user")).sendKeys("fominaelena");
        driver.findElement(By.name("password")).sendKeys("1p73bp4z");
        driver.findElement(By.id("button_submit_login_form")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        assertEquals("Неверные данные для авторизации", alert.getText());
    }

    // 15. Проверка видимости пароля при нажатии на иконку глаз (1p73bp4z)
    @Test
    public void shouldCheckButtonPassword() {
        driver.findElement(By.name("password")).sendKeys("1p73bp4z");
        driver.findElement(By.id("show_password")).click();

        assertEquals("text", driver.findElementByName("password").getAttribute("type"));
    }

    // 16. Проверка видимости пароля при двойном нажатии на иконку глаз (1p73bp4z)
    @Test
    public void shouldCheckButtonPasswordTwice() {
        driver.findElement(By.name("password")).sendKeys("1p73bp4z");
        driver.findElement(By.id("show_password")).click();
        driver.findElement(By.id("show_password")).click();

        assertEquals("password", driver.findElementByName("password").getAttribute("type"));
    }

    // 17. Переход по ссылке «Забыли пароль?» на страницу "Восстановление пароля" и обратно
    @Test
    public void shouldGoPagePasswordRecoveryAndBack() {
        driver.findElement(By.className("mira-default-login-page-link")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("info-title")));

        driver.findElement(By.className("mira-page-forgot-password-link")).click();

        WebDriverWait wait1 = new WebDriverWait(driver, 10);
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.className("info-title")));

        assertEquals("Авторизация", driver.getTitle());
    }

    // 18. Ввод корректного логина на странице "Восстановление пароля" (fominaelena)
    @Test
    public void shouldCheckInputCorrectLoginPagePasswordRecovery() {
        driver.findElement(By.className("mira-default-login-page-link")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("info-title")));

        driver.findElement(By.name("loginOrEmail")).sendKeys("fominaelena");
        driver.findElement(By.className("mira-page-forgot-password-button")).click();

        WebDriverWait wait1 = new WebDriverWait(driver, 10);
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.className("success")));

        assertEquals("На ваш электронный адрес отправлена инструкция по восстановлению пароля.",
                driver.findElementByClassName("success").getText());
    }

    // 19. Ввод корректного email на странице "Восстановление пароля" (efomina@company.ru)
    @Test
    public void shouldCheckInputCorrectEmailPagePasswordRecovery() {
        driver.findElement(By.className("mira-default-login-page-link")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("info-title")));

        driver.findElement(By.name("loginOrEmail")).sendKeys("efomina@company.ru");
        driver.findElement(By.className("mira-page-forgot-password-button")).click();

        WebDriverWait wait1 = new WebDriverWait(driver, 10);
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.className("success")));

        assertEquals("На ваш электронный адрес отправлена инструкция по восстановлению пароля.",
                driver.findElementByClassName("success").getText());
    }

    // 20. Ввод некорректного логина на странице "Восстановление пароля" (fomina)
    @Test
    public void shouldCheckInputWrongLoginPagePasswordRecovery() {
        driver.findElement(By.className("mira-default-login-page-link")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("info-title")));

        driver.findElement(By.name("loginOrEmail")).sendKeys("fomina");
        driver.findElement(By.className("mira-page-forgot-password-button")).click();

        WebDriverWait wait1 = new WebDriverWait(driver, 10);
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert")));

        assertEquals("Пользователь с таким именем не найден.", driver.findElementByClassName("alert").getText());
    }

    // 21. Ввод некорректного email на странице "Восстановление пароля" (efomina@compa.ru)
    @Test
    public void shouldCheckInputWrongEmailPagePasswordRecovery() {
        driver.findElement(By.className("mira-default-login-page-link")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("info-title")));

        driver.findElement(By.name("loginOrEmail")).sendKeys("efomina@compa.ru");
        driver.findElement(By.className("mira-page-forgot-password-button")).click();

        WebDriverWait wait1 = new WebDriverWait(driver, 10);
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert")));

        assertEquals("Пользователь с таким именем не найден.", driver.findElementByClassName("alert").getText());
    }

    @AfterEach
    public void finalAction() {
        driver.quit();
    }
}
