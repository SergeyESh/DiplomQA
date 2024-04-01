package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static ru.iteco.fmhandroid.ui.data.DataHelper.generateScreenshotName;
import static ru.iteco.fmhandroid.ui.pages.AboutPage.versionText;
import static ru.iteco.fmhandroid.ui.pages.AuthorizationPage.checkLogInAndLogInIfNot;
import static ru.iteco.fmhandroid.ui.pages.MainPage.goToAboutPage;
import static ru.iteco.fmhandroid.ui.pages.MainPage.goToNewsPage;
import static ru.iteco.fmhandroid.ui.pages.MainPage.goToNewsPageByAllNewsButton;
import static ru.iteco.fmhandroid.ui.pages.MainPage.goToQuotesPage;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.filterNewsButton;
import static ru.iteco.fmhandroid.ui.pages.QuotesPage.quoteText;

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
@DisplayName("Навигация по приложению")
public class NavigationTest {

    @Before
    public void setUp(){
        checkLogInAndLogInIfNot();
    }

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule = new ActivityScenarioRule<>(AppActivity.class);

    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE, generateScreenshotName("Failed"));


    @Test
    @DisplayName("Переход через \"Главное меню\" (бургер-меню) в раздел в раздел \"Новости\"")
    public void goToNewsPageTest() {
        goToNewsPage();
        filterNewsButton.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Переход в раздел \"Новости\" из раздела \"Главная\" через кнопку-ссылку \"Все новости\"")
    public void goToNewsPageByAllNewsButtonTest() {
        goToNewsPageByAllNewsButton();
        filterNewsButton.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Переход через \"Главное меню\" (бургер-меню) в раздел в раздел \"О приложении\"")
    public void goToAboutPageTest() {
        goToAboutPage();
        versionText.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Переход в раздел \"Тематические цитаты\" с помощью кнопки \"Наша миссия\" (бабочка) на верхней панели")
    public void goToQuotesPageTest() {
        goToQuotesPage();
        quoteText.check(matches(isDisplayed()));
    }

}