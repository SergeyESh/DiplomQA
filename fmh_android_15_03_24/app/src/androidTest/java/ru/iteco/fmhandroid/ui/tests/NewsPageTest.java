package ru.iteco.fmhandroid.ui.tests;


import static ru.iteco.fmhandroid.ui.data.DataHelper.generateScreenshotName;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitElement;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@DisplayName("Раздел \"Новости\"")
public class NewsPageTest {

    AuthorizationPage authorizationPage = new AuthorizationPage();
    MainPage mainPage = new MainPage();
    NewsPage newsPage = new NewsPage();

    @Before
    public void setUp() {
        authorizationPage.checkLogInAndLogInIfNot();
        mainPage.goToNewsPage();
    }

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule = new ActivityScenarioRule<>(AppActivity.class);

    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE, generateScreenshotName("Failed"));


    @Test
    @DisplayName("Свернуть/развернуть выбранную новость в разделе Новости")
    public void collapseNewsTest() {
        Allure.step("Свернуть/развернуть выбранную новость");
        ViewInteraction recyclerView = newsPage.getRecyclerViewAndScrollToFirstPosition();
        int heightBeforeClick = newsPage.getHeightBeforeClick(recyclerView);
        int heightAfterClick = newsPage.getHeightAfterClick(recyclerView);
        waitElement(newsPage.newsListId);
        newsPage.doubleClickFirstItem(recyclerView);
        newsPage.checkHeightAfterDoubleClick(heightBeforeClick, heightAfterClick);
    }
}