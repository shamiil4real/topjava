package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {

    public static final int USER_ID = 100000;
    public static final int ADMIN_ID = 100001;

    public static final int MEAL_6_ID = 100008;
    public static final int MEAL_5_ID = 100007;
    public static final int MEAL_4_ID = 100005;
    public static final int MEAL_3_ID = 100006;
    public static final int MEAL_2_ID = 100004;
    public static final int MEAL_1_ID = 100003;

    public static final Meal MEAL_6 = new Meal(MEAL_6_ID, LocalDateTime.of(2010, 2, 3, 19, 0), "Ужин", 900);
    public static final Meal MEAL_5 = new Meal(MEAL_5_ID, LocalDateTime.of(2010, 2, 2, 12, 30), "Завтрак", 1100);
    public static final Meal MEAL_4 = new Meal(MEAL_4_ID, LocalDateTime.of(2010, 2, 1, 12, 0), "Перекус", 400);
    public static final Meal MEAL_3 = new Meal(MEAL_3_ID, LocalDateTime.of(2010, 2, 1, 18, 0), "Ужин", 1100);
    public static final Meal MEAL_2 = new Meal(MEAL_2_ID, LocalDateTime.of(2010, 2, 2, 12, 0), "Обед", 2100);
    public static final Meal MEAL_1 = new Meal(MEAL_1_ID, LocalDateTime.of(2010, 2, 1, 10, 0), "Завтрак", 1500);

    public static Meal getNew() {
        return new Meal(LocalDateTime.of(2015, 2, 1, 20, 0), "Обед", 1000);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(MEAL_1);
        updated.setDateTime(LocalDateTime.of(2015, 2, 1, 20, 0));
        updated.setDescription("Updated");
        updated.setCalories(1000);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }
}
