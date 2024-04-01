package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.data.Data.urlPrivacyPolicy;
import static ru.iteco.fmhandroid.ui.data.Data.urlTermsOfUse;
import static ru.iteco.fmhandroid.ui.data.DataHelper.generateScreenshotName;
import static ru.iteco.fmhandroid.ui.pages.AboutPage.isPageExists;
import static ru.iteco.fmhandroid.ui.pages.AuthorizationPage.checkLogInAndLogInIfNot;
import static ru.iteco.fmhandroid.ui.pages.MainPage.goToAboutPage;

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
@DisplayName("Раздел \"О приложении\"")
public class AboutPageTest {

    @Before
    public void setUp(){
        checkLogInAndLogInIfNot();
        goToAboutPage();
    }

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule = new ActivityScenarioRule<>(AppActivity.class);

    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE, generateScreenshotName("Failed"));

    @Test
    @DisplayName("Переход по ссылке на страницу с текстом Политики конфиденциальности")
    public void switchingUrlPrivacyPolicyTest() {
        isPageExists(urlPrivacyPolicy);
    }

    @Test
    @DisplayName("Переход по ссылке на страницу с текстом Пользовательского соглашения")
    public void switchingUrlTermsOfUseTest() {
        isPageExists(urlTermsOfUse);
    }

}