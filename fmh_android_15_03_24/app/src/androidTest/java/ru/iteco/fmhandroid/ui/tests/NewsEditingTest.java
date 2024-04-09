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
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pages.EditingNewsPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@DisplayName("Раздел редактирования новостей")
public class NewsEditingTest {

    AuthorizationPage authorizationPage = new AuthorizationPage();
    EditingNewsPage editingNewsPage = new EditingNewsPage();
    MainPage mainPage = new MainPage();
    NewsPage newsPage = new NewsPage();

    @Before
    public void setUp() {
        authorizationPage.checkLogInAndLogInIfNot();
        mainPage.goToNewsPage();
        newsPage.goToNewsEditingPage();
    }

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule = new ActivityScenarioRule<>(AppActivity.class);

    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE, generateScreenshotName("Failed"));

    @Test
    @DisplayName("Создание новой новости")
    public void createdNewsTest() {
        editingNewsPage.addNews(categoryNotification, tittleNews, dateNews, timeNews, descriptionNews);
        editingNewsPage.editNews(tittleNews);
        editingNewsPage.checkNews();
    }

    @Test
    @DisplayName("Создание новой новости с пустыми полями")
    public void createdNewsWithEmptyFieldsTest() {
        editingNewsPage.addNewsWithEmptyFields();
        editingNewsPage.fieldsDoesNotBeEmpty();
    }

    @Test
    @DisplayName("Создание новой новости с пустым \"Заголовок\"")
    public void createdNewsWithEmptyTittleTest() {
        editingNewsPage.addNewsWithEmptyTittle(categoryNotification, dateNews, timeNews, descriptionNews);
        editingNewsPage.fieldsDoesNotBeEmpty();
    }

    @Test
    @DisplayName("Редактирование новости: смена заголовка")
    public void changeNewsTest() {
        editingNewsPage.addNews(categoryUnion, tittleNews2, dateNews, timeNews, descriptionNews);
        editingNewsPage.editNews(tittleNews2);
        editingNewsPage.changeTittleNews(newTittleNews);
        editingNewsPage.editNews(newTittleNews);
        editingNewsPage.checkTittleAfterChange(newTittleNews);
    }

    @Test
    @DisplayName("Удаление новости")
    public void deleteNewsTest() {
        editingNewsPage.addNews(categoryBirthday, tittleNews3, dateNews, timeNews, descriptionNews);
        editingNewsPage.deleteNews(tittleNews3);
        waitElement(newsPage.newsListId);
        editingNewsPage.checkNewsDeleted(newsPage.getItemCount(), tittleNews3);
    }
}