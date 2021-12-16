package ru.mirapolis;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {
    private final SelenideElement avatarMenu = $(By.className("avatar-full-name"));
    private final SelenideElement quitMenu = $("[data-mira-id='Link33']");

    public boolean avatarFullName(String userFullName) {
        return Objects.requireNonNull($(By.className("avatar-full-name")).getAttribute("title")).contains(userFullName);
    }

    public void logOut() {
        $(avatarMenu).click();
        $(quitMenu).click();
    }
}
