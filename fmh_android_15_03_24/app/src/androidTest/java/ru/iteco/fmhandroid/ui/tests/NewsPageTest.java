package ru.iteco.fmhandroid.ui.tests;


import static ru.iteco.fmhandroid.ui.data.DataHelper.generateScreenshotName;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitElement;
import static ru.iteco.fmhandroid.ui.pages.AuthorizationPage.checkLogInAndLogInIfNot;
import static ru.iteco.fmhandroid.ui.pages.MainPage.goToNewsPage;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.checkHeightAfterDoubleClick;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.doubleClickFirstItem;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.getHeightAfterClick;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.getHeightBeforeClick;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.getRecyclerViewAndScrollToFirstPosition;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.newsListId;

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

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@DisplayName("Раздел \"Новости\"")
public class NewsPageTest {

    @Before
    public void setUp() {
        checkLogInAndLogInIfNot();
        goToNewsPage();
    }

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule = new ActivityScenarioRule<>(AppActivity.class);

    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE, generateScreenshotName("Failed"));


    @Test
    @DisplayName("Свернуть/развернуть выбранную новость в разделе Новости")
    public void collapseNewsTest() {
        Allure.step("Свернуть/развернуть выбранную новость");
        ViewInteraction recyclerView = getRecyclerViewAndScrollToFirstPosition();
        int heightBeforeClick = getHeightBeforeClick(recyclerView);
        int heightAfterClick = getHeightAfterClick(recyclerView);
        waitElement(newsListId);
        doubleClickFirstItem(recyclerView);
        checkHeightAfterDoubleClick(heightBeforeClick, heightAfterClick);
    }
}