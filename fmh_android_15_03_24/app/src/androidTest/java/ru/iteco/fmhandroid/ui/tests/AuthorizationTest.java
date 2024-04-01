package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.data.Data.emptyLogin;
import static ru.iteco.fmhandroid.ui.data.Data.emptyPassword;
import static ru.iteco.fmhandroid.ui.data.Data.invalidLogin;
import static ru.iteco.fmhandroid.ui.data.Data.invalidPassword;
import static ru.iteco.fmhandroid.ui.data.Data.validLogin;
import static ru.iteco.fmhandroid.ui.data.Data.validPassword;
import static ru.iteco.fmhandroid.ui.data.DataHelper.generateScreenshotName;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitElement;
import static ru.iteco.fmhandroid.ui.pages.AuthorizationPage.checkLogOutAndLogOutIfNot;
import static ru.iteco.fmhandroid.ui.pages.AuthorizationPage.logOutIsVisible;
import static ru.iteco.fmhandroid.ui.pages.AuthorizationPage.login;
import static ru.iteco.fmhandroid.ui.pages.AuthorizationPage.loginOrPasswordDoesNotBeEmpty;
import static ru.iteco.fmhandroid.ui.pages.AuthorizationPage.loginOrPasswordIsWrong;
import static ru.iteco.fmhandroid.ui.pages.MainPage.LogOutId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;


@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@DisplayName("Раздел \"Авторизация\"")
public class AuthorizationTest {

    @Before
    public void setUp(){
        checkLogOutAndLogOutIfNot();
    }

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule = new ActivityScenarioRule<>(AppActivity.class);

    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE, generateScreenshotName("Failed"));

    @Test
    @DisplayName("Авторизация с тестовыми учетными данными")
    public void validLoginAndPasswordAuthorizationTest() {
        login(validLogin, validPassword);
        waitElement(LogOutId);
        logOutIsVisible();
    }

    @Test
    @DisplayName("Авторизация при вводе невалидного логина")
    public void invalidLoginAuthorizationTest() {
        login(invalidLogin, validPassword);
        loginOrPasswordIsWrong();
    }

    @Test
    @DisplayName("Авторизация при вводе невалиного пароля")
    public void invalidPasswordAuthorizationTest() {
        login(validLogin, invalidPassword);
        loginOrPasswordIsWrong();
    }

    @Test
    @DisplayName("Авторизация при пустой форме")
    public void emptyLoginAndPasswordAuthorizationTest() {
        login(emptyLogin,emptyPassword);
        loginOrPasswordDoesNotBeEmpty();
    }

}