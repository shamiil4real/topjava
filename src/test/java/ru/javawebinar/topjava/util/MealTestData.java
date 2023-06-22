package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int MEAL_1_ID = START_SEQ + 3;
    public static final int MEAL_2_ID = START_SEQ + 4;
    public static final int MEAL_4_ID = START_SEQ + 5;
    public static final int MEAL_3_ID = START_SEQ + 6;
    public static final int MEAL_5_ID = START_SEQ + 7;
    public static final int MEAL_6_ID = START_SEQ + 8;

    public static final Meal meal1 = new Meal(MEAL_1_ID, LocalDateTime.of(2010, 2, 1, 10, 0), "Завтрак", 1500);
    public static final Meal meal2 = new Meal(MEAL_2_ID, LocalDateTime.of(2010, 2, 2, 12, 0), "Обед", 2100);
    public static final Meal meal3 = new Meal(MEAL_3_ID, LocalDateTime.of(2010, 2, 1, 18, 0), "Ужин", 1100);
    public static final Meal meal4 = new Meal(MEAL_4_ID, LocalDateTime.of(2010, 2, 1, 12, 0), "Перекус", 400);
    public static final Meal meal5 = new Meal(MEAL_5_ID, LocalDateTime.of(2010, 2, 2, 12, 30), "Завтрак", 1100);
    public static final Meal meal6 = new Meal(MEAL_6_ID, LocalDateTime.of(2010, 2, 3, 19, 0), "Ужин", 900);

    public static Meal getNew() {
        return new Meal(LocalDateTime.of(2015, 2, 1, 20, 0), "Обед", 1000);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(meal1);
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
