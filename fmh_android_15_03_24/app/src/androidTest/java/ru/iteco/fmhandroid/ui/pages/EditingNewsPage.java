package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.junit.Assert.assertNotEquals;
import static ru.iteco.fmhandroid.ui.data.Data.dateNews;
import static ru.iteco.fmhandroid.ui.data.Data.descriptionNews;
import static ru.iteco.fmhandroid.ui.data.Data.timeNews;
import static ru.iteco.fmhandroid.ui.data.Data.tittleNews;
import static ru.iteco.fmhandroid.ui.data.Data.tittleNews3;
import static ru.iteco.fmhandroid.ui.data.DataHelper.clickChildViewWithId;
import static ru.iteco.fmhandroid.ui.data.DataHelper.getTextFromNews;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitElement;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.newsListId;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class EditingNewsPage {

    public static ViewInteraction addNewsButton = onView(withId(R.id.add_news_image_view));
    public static ViewInteraction categoryField = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public static ViewInteraction tittleField = onView(withId(R.id.news_item_title_text_input_edit_text));
    public static ViewInteraction dateField = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    public static ViewInteraction timeField = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    public static ViewInteraction descriptionField = onView(withId(R.id.news_item_description_text_input_edit_text));
    public static ViewInteraction saveButton = onView(withId(R.id.save_button));
    public static ViewInteraction confirmDeleteNewsButton = onView(allOf(withId(android.R.id.button1)));
    public static int confirmDeleteNewsButtonId = android.R.id.button1;

    public static void scrollNews(int i) {
        onView(withId(newsListId))
                .perform(scrollToPosition(i))
                .perform(actionOnItemAtPosition(i, scrollTo()))
                .check(matches(isDisplayed()));
    }

    @Step("Создание новой новости")
    public static void addNews(String category, String tittle, String date, String time, String description) {
        Allure.step("Тап на кнопку \"+\". Заполняем поля: категория, заголовок, дата, время, описание. Нажать кнопку \"Сохранить\"");
        addNewsButton.perform(click());
        categoryField.perform(replaceText(category));
        tittleField.perform(replaceText(tittle));
        dateField.perform(replaceText(date));
        timeField.perform(replaceText(time));
        descriptionField.perform(replaceText(description));
        saveButton.perform(click());
    }

    @Step("Поиск новости в списке по загаловку")
    public static void findNewsWithTittle(String tittle) {
        Allure.step("Ищем новость в списке по заголовку " + tittle);
        waitElement(newsListId);
        onView(withId(newsListId))
                .check(matches(isDisplayed()))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(allOf(withText(tittle)))));
        onView(withId(newsListId))
                .check(matches(isDisplayed()));
    }

    @Step("Выбираем новость для редактирования")
    public static void editNews(String tittle) {
        Allure.step("Выбираем новость - " +tittle + " для редактирования");
        findNewsWithTittle(tittle);
        onView(allOf(withId(R.id.news_item_material_card_view), hasDescendant(withText(tittle))))
                .perform(clickChildViewWithId(R.id.edit_news_item_image_view));
    }

    @Step("Проверяем, что все поля новости соответствуют заданным при ее создании")
    public static void checkNews() {
        Allure.step("Проверяем, что все поля новости соответствуют заданным");
        onView(withText(tittleNews)).check(matches(isDisplayed()));
        onView(withText(dateNews)).check(matches(isDisplayed()));
        onView(withText(timeNews)).check(matches(isDisplayed()));
        onView(withText(descriptionNews)).check(matches(isDisplayed()));
    }

    @Step("Проверка заголовка после изменения")
    public static void checkTittleAfterChange(String tittle) {
        Allure.step("Проверяем, что заголовок изменен");
        onView(withText(tittle)).check(matches(isDisplayed()));
    }

    @Step("Смена заголовка новости")
    public static void changeTittleNews(String newTittle) {
        Allure.step("Меняем заголовок новости на " + newTittle);
        tittleField.perform(replaceText(newTittle));
        saveButton.perform(click());
        waitElement(newsListId);
    }

    @Step("Удаление новости")
    public static void deleteNews(String tittle) {
        Allure.step("Удаляем выбранную новость - " + tittle);
        findNewsWithTittle(tittle);
        onView(allOf(withId(R.id.news_item_material_card_view), hasDescendant(withText(tittle))))
                .perform(clickChildViewWithId(R.id.delete_news_item_image_view));
        confirmDelete();
    }

    @Step("Проверка, что новость удалена")
    public static void checkNewsDeleted(int itemCount, String tittle) {
        Allure.step("Проверяем, что новость удалена");
        for (int i = 0; i < itemCount; i++) {
            scrollNews(i);
            String actualTittle = getTextFromNews(R.id.news_item_title_text_view, i);
            assertNotEquals(tittle, actualTittle);
        }
    }

    @Step
    public static void confirmDelete() {
        Allure.step("Подтверждаем удаление новости нажимая ОК");
        waitElement(confirmDeleteNewsButtonId);
        confirmDeleteNewsButton.perform(click());
    }
}