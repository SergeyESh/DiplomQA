package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.data.Data.categoryBirthday;
import static ru.iteco.fmhandroid.ui.data.Data.categoryNotification;
import static ru.iteco.fmhandroid.ui.data.Data.categoryUnion;
import static ru.iteco.fmhandroid.ui.data.Data.dateNews;
import static ru.iteco.fmhandroid.ui.data.Data.descriptionNews;
import static ru.iteco.fmhandroid.ui.data.Data.newTittleNews;
import static ru.iteco.fmhandroid.ui.data.Data.timeNews;
import static ru.iteco.fmhandroid.ui.data.Data.tittleNews;
import static ru.iteco.fmhandroid.ui.data.Data.tittleNews2;
import static ru.iteco.fmhandroid.ui.data.Data.tittleNews3;
import static ru.iteco.fmhandroid.ui.data.DataHelper.generateScreenshotName;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitElement;
import static ru.iteco.fmhandroid.ui.pages.AuthorizationPage.checkLogInAndLogInIfNot;
import static ru.iteco.fmhandroid.ui.pages.EditingNewsPage.addNews;
import static ru.iteco.fmhandroid.ui.pages.EditingNewsPage.changeTittleNews;
import static ru.iteco.fmhandroid.ui.pages.EditingNewsPage.checkNews;
import static ru.iteco.fmhandroid.ui.pages.EditingNewsPage.checkNewsDeleted;
import static ru.iteco.fmhandroid.ui.pages.EditingNewsPage.checkTittleAfterChange;
import static ru.iteco.fmhandroid.ui.pages.EditingNewsPage.deleteNews;
import static ru.iteco.fmhandroid.ui.pages.EditingNewsPage.editNews;
import static ru.iteco.fmhandroid.ui.pages.MainPage.goToNewsEditingPage;
import static ru.iteco.fmhandroid.ui.pages.MainPage.goToNewsPage;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.getItemCount;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.newsListId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.AfterClass;
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
@DisplayName("Раздел редактирования новостей")
public class NewsEditingTest {

    @Before
    public void setUp() {
        checkLogInAndLogInIfNot();
        goToNewsPage();
        goToNewsEditingPage();
    }

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule = new ActivityScenarioRule<>(AppActivity.class);

    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE, generateScreenshotName("Failed"));

    @Test
    @DisplayName("Создание новой новости")
    public void createdNewsTest() {
        addNews(categoryNotification, tittleNews, dateNews, timeNews, descriptionNews);
        editNews(tittleNews);
        checkNews();
    }

    @Test
    @DisplayName("Редактирование новости: смена заголовка")
    public void changeNewsTest() {
        addNews(categoryUnion, tittleNews2, dateNews, timeNews, descriptionNews);
        editNews(tittleNews2);
        changeTittleNews(newTittleNews);
        editNews(newTittleNews);
        checkTittleAfterChange(newTittleNews);
    }

    @Test
    @DisplayName("Удаление новости")
    public void deleteNewsTest() {
        addNews(categoryBirthday, tittleNews3, dateNews, timeNews, descriptionNews);
        deleteNews(tittleNews3);
        waitElement(newsListId);
        checkNewsDeleted(getItemCount(), tittleNews3);
    }
}