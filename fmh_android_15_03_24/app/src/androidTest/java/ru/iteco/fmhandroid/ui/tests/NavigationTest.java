package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.data.DataHelper.generateScreenshotName;

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
import ru.iteco.fmhandroid.ui.pages.AboutPage;
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage;
import ru.iteco.fmhandroid.ui.pages.QuotesPage;


@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@DisplayName("Навигация по приложению")
public class NavigationTest {

    AboutPage aboutPage = new AboutPage();
    AuthorizationPage authorizationPage = new AuthorizationPage();
    MainPage mainPage = new MainPage();
    NewsPage newsPage = new NewsPage();
    QuotesPage quotesPage = new QuotesPage();


    @Before
    public void setUp(){
        authorizationPage.checkLogInAndLogInIfNot();
    }

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule = new ActivityScenarioRule<>(AppActivity.class);

    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE, generateScreenshotName("Failed"));


    @Test
    @DisplayName("Переход через \"Главное меню\" (бургер-меню) в раздел в раздел \"Новости\"")
    public void goToNewsPageTest() {
        mainPage.goToNewsPage();
        newsPage.checkNewsPage();
    }

    @Test
    @DisplayName("Переход в раздел \"Новости\" из раздела \"Главная\" через кнопку-ссылку \"Все новости\"")
    public void goToNewsPageByAllNewsButtonTest() {
        mainPage.goToNewsPageByAllNewsButton();
        newsPage.checkNewsPage();
    }

    @Test
    @DisplayName("Переход через \"Главное меню\" (бургер-меню) в раздел в раздел \"О приложении\"")
    public void goToAboutPageTest() {
        mainPage.goToAboutPage();
        aboutPage.checkAboutPage();
    }

    @Test
    @DisplayName("Переход в раздел \"Тематические цитаты\" с помощью кнопки \"Наша миссия\" (бабочка) на верхней панели")
    public void goToQuotesPageTest() {
        mainPage.goToQuotesPage();
        quotesPage.checkQuotesPage();
    }

}