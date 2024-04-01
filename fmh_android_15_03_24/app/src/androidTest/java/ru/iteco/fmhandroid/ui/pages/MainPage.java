package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitElement;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.editNewsButton;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class MainPage {

    public static ViewInteraction mainMenuButton = onView(allOf(withId(R.id.main_menu_image_button)));
    public static ViewInteraction newsButton = onView(withText(R.string.news));
    public static ViewInteraction logOutButton = onView(allOf(withId(R.id.authorization_image_button)));
    public static ViewInteraction quotesButton = onView(allOf(withId(R.id.our_mission_image_button)));
    public static ViewInteraction aboutButton = onView(withText(R.string.about));
    public static ViewInteraction allNewsButton = onView(allOf(withId(R.id.all_news_text_view)));
    public static int mainMenuButtonId = R.id.main_menu_image_button;
    public static int allNewsButtonId = R.id.all_news_text_view;
    public static int LogOutId = R.id.authorization_image_button;
    public static int quotesButtonID = R.id.our_mission_image_button;

    @Step("Переход в раздел \"Новости\"")
    public static void goToNewsPage() {
        Allure.step("Тап \"Главное меню\" (бургер-меню), выбрать раздел \"Новости\"");
        waitElement(mainMenuButtonId);
        mainMenuButton.perform(click());
        newsButton.check(matches(isDisplayed()));
        newsButton.perform(click());
    }

    @Step("Переход в раздел \"Новости\" через кнопку-ссылку \"Все новости\"")
    public static void goToNewsPageByAllNewsButton() {
        Allure.step("Тап \"Все новости\" на Главной странице");
        waitElement(allNewsButtonId);
        allNewsButton.perform(click());
    }

    @Step("Переход в раздел \"О приложении\"")
    public static void goToAboutPage() {
        Allure.step("Тап \"Главное меню\" (бургер-меню), выбрать раздел \"О приложении\"");
        waitElement(mainMenuButtonId);
        mainMenuButton.perform(click());
        aboutButton.check(matches(isDisplayed()));
        aboutButton.perform(click());
    }

    @Step("Переход в раздел \"Тематические цитаты\"")
    public static void goToQuotesPage() {
        Allure.step("Тап \"Наша миссия\" (значок бабочка)");
        waitElement(quotesButtonID);
        quotesButton.perform(click());
    }

    @Step("Редактировать новость")
    public static void goToNewsEditingPage() {
        Allure.step("Тап на кнопку \"карандаш\"");
        editNewsButton.perform(click());
    }

}