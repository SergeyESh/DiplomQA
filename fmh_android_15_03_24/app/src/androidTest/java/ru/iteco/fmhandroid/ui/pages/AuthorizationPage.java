package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.data.Data.validLogin;
import static ru.iteco.fmhandroid.ui.data.Data.validPassword;
import static ru.iteco.fmhandroid.ui.data.DataHelper.checkToast;
import static ru.iteco.fmhandroid.ui.data.DataHelper.isLogIn;
import static ru.iteco.fmhandroid.ui.data.DataHelper.isLogOut;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitElement;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitUntilVisible;
import static ru.iteco.fmhandroid.ui.pages.MainPage.LogOutId;
import static ru.iteco.fmhandroid.ui.pages.MainPage.logOutButton;

import androidx.test.espresso.ViewInteraction;


import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class AuthorizationPage {

    public static ViewInteraction fieldLogin = onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.login_text_input_layout))));
    public static ViewInteraction fieldPassword = onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.password_text_input_layout))));
    public static ViewInteraction enterButton = onView(allOf(withId(R.id.enter_button)));
    public static int enterButtonId = R.id.enter_button;

    @Step("Ввод логина и пароля.")
    public static void login(String login, String password){
            Allure.step("Вводим логин и пароль: " + login + ", " + password);
        waitElement(AuthorizationPage.enterButtonId);
        fieldLogin.perform(replaceText(login));
        fieldPassword.perform(replaceText(password));
        enterButton.check(matches(isDisplayed())).perform(click());
    }

    @Step("Выход из аккаунта.")
    public static void logOut() {
        Allure.step("Выходим из аккаунта, если авторизованы.");
        onView(withId(LogOutId)).perform(click());
        onView(withId(android.R.id.title)).check(matches(isDisplayed()));
        onView(withId(android.R.id.title)).perform(click());
    }

    @Step("Вход в аккаунт, если не авторизован.")
    public static void checkLogInAndLogInIfNot() {
        if (isLogIn()) {
            login(validLogin, validPassword);
        }
    }

    @Step("Выход из аккаунта, если авторизован.")
    public static void checkLogOutAndLogOutIfNot() {
        if (isLogOut()) {
            logOut();
        }
    }

    @Step("Проверка видимости кнопки выхода из аккаунта.")
    public static void logOutIsVisible() {
        logOutButton.check(matches(isDisplayed()));
    }

    @Step("Проверка видимости сообщения об ошибке при пустых логине и/или пароле.")
    public static void loginOrPasswordDoesNotBeEmpty() {
        waitUntilVisible(checkToast(R.string.empty_login_or_password, true));
    }

    @Step("Проверка видимости сообщения об ошибке при неправильных логине и/или пароле.")
    public static void loginOrPasswordIsWrong() {
        waitUntilVisible(checkToast(R.string.wrong_login_or_password, true));
    }

}