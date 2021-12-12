package ru.mirapolis;

import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {

    private final String userFullName;

    public ProfilePage(String userFullName) {
        this.userFullName = userFullName;
    }

    public boolean avatarFullName() {
        return Objects.requireNonNull($(By.className("avatar-full-name")).getAttribute("title")).contains(userFullName);
    }

    public void logOut() {
        $(By.xpath("/html/body/div[1]/div/div/table/tbody/tr[1]/td/div[1]/div[2]/div[1]/div/div[2]/div/div/div/div/div/div/div[2]")).click();
        $(By.xpath("//*[@id=\"DropDownContainer28-DD\"]/div/div/div[3]/a/span[2]")).click();
    }
}
