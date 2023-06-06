package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MealsUtil {
    public static List<Meal> defaultMealList = Arrays.asList(
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
    );
    public static final int CALORIES_PER_DAY = 2000;


    public static void main(String[] args) {
        List<MealTo> mealsTo = filteredByLocalTime(defaultMealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);
    }

    public static List<MealTo> filteredByLocalTime(Collection<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        return filteredByPredicate(meals, caloriesPerDay, meal -> TimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime));
    }

    public static List<MealTo> getMealListWithExcess(Collection<Meal> meals, int caloriesPerDay) {
        return filteredByPredicate(meals, caloriesPerDay, meal -> true);
    }

    public static List<MealTo> filteredByPredicate(Collection<Meal> meals, int caloriesPerDay, Predicate<Meal> predicate) {
        return meals.stream()
                .filter(predicate)
                .map(meal -> createTo(meal, getCaloriesSumByDate(meals, meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    private static int getCaloriesSumByDate(Collection<Meal> meals, LocalDate date) {
        Map<LocalDate, Integer> caloriesSumPerDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
                );
        return caloriesSumPerDate.get(date);
    }

    public static MealTo createTo(Meal meal, boolean excess) {
        return new MealTo(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }
}
