package ru.iteco.fmhandroid.ui.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Data {
    private static LocalDateTime date = LocalDateTime.now();
    private static DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("hh:mm");

    public static final String validLogin = "login2";
    public static final String validPassword = "password2";
    public static final String invalidLogin = "login";
    public static final String invalidPassword = "password";
    public static final String emptyLogin = "";
    public static final String emptyPassword = "";
    public static final String urlPrivacyPolicy = "https://vhospice.org/#/privacy-policy/";
    public static final String urlTermsOfUse = "https://vhospice.org/#/terms-of-use";
    public static final String categoryNotification = "Объявление";
    public static final String categoryBirthday = "День рождения";
    public static final String categoryUnion = "Профсоюз";
    public static final String tittleNews = "Tittle_1";
    public static final String tittleNews2 = "Tittle_2";
    public static final String tittleNews3 = "Tittle_3";
    public static final String newTittleNews = "NewTittle_1";
    public static final String dateNews = formatterDate.format(date);
    public static final String timeNews = formatterTime.format(date);
    public static final String descriptionNews = "Здесь должно быть описание";
}