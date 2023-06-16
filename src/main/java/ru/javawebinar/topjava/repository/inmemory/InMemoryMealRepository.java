package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Comparator<Meal> MEAL_COMPARATOR = Comparator.comparing(Meal::getDateTime, Comparator.reverseOrder());
    private final Map<Integer, Map<Integer, Meal>> userMeals = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, 1));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        Map<Integer, Meal> meals = userMeals.computeIfAbsent(userId, map -> new ConcurrentHashMap<>());
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meals.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return meals.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Meal> meals = userMeals.get(userId);
        return meals != null && meals.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> meals = userMeals.get(userId);
        return meals == null ? null : meals.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return filteredByPredicate(userId, meal -> true);
    }

    @Override
    public List<Meal> getBetweenTimePeriod(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return filteredByPredicate(userId, meal -> DateTimeUtil.isBetweenHalfOpen(meal.getDateTime(), startDate, endDate));
    }

    private List<Meal> filteredByPredicate(int userId, Predicate<Meal> predicate) {
        Collection<Meal> meals = userMeals.get(userId).values();
        return meals.stream()
                .sorted(MEAL_COMPARATOR)
                .filter(predicate)
                .collect(Collectors.toList());
    }
}

