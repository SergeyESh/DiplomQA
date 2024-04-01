package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static ru.iteco.fmhandroid.ui.data.DataHelper.getRecyclerViewItemCount;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataHelper;

public class NewsPage {

    public static ViewInteraction editNewsButton = onView(withId(R.id.edit_news_material_button));
    public static ViewInteraction filterNewsButton = onView(withId(R.id.filter_news_material_button));


    public static int newsListId = R.id.news_list_recycler_view;


    public static ViewInteraction getRecyclerViewAndScrollToFirstPosition() {       //Получаем recyclerView для раздела Новости и прокручиваем до его первой позиции
        ViewInteraction recyclerView = onView(withId(R.id.news_list_recycler_view));
        return recyclerView;
    }

    public static int getHeightBeforeClick(ViewInteraction recyclerView) {      //Получаем высоту первого элемента списка до клика
        int[] heightBeforeClick = {0};
        recyclerView.perform(new DataHelper.GetHeightAfterClickViewAction(heightBeforeClick));
        return heightBeforeClick[0];
    }

    public static void doubleClickFirstItem(ViewInteraction recyclerView) {     //Кликаем дважды на первом элементе списка, чтобы элемент развернулся и свернулся
        recyclerView.perform(actionOnItemAtPosition(0, doubleClick()));
    }

    public static int getHeightAfterClick(ViewInteraction recyclerView) {       //Получаем высоту первого элемента списка после клика
        int[] heightAfterClick = {0};
        recyclerView.perform(new DataHelper.GetHeightAfterClickViewAction(heightAfterClick));
        return heightAfterClick[0];
    }

    public static void checkHeightAfterDoubleClick(int heightBeforeClick, int heightAfterClick) {       //Проверяем, что высота первого элемента списка осталась той же после двойного клика
        assertEquals(heightBeforeClick, heightAfterClick);
    }

    public static int getItemCount() {      //Получаем количество элементов в списке новостей
        int itemCount = getRecyclerViewItemCount(newsListId);
        return itemCount;
    }

}