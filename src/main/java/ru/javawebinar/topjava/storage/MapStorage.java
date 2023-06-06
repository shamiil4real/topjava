package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.javawebinar.topjava.util.MealsUtil.defaultMealList;

public class MapStorage implements Storage {
    private final AtomicInteger idCounter = new AtomicInteger(0);
    private final Map<Integer, Meal> mealMap = new ConcurrentHashMap<>();

    {
        for (Meal meal : defaultMealList) {
            save(meal);
        }
    }

    @Override
    public void save(Meal meal) {
        if (meal.getId() == null) {
            meal.setId(idCounter.addAndGet(1));
        }
        mealMap.put(meal.getId(), meal);
    }

    @Override
    public void delete(int id) {
        mealMap.remove(id);
    }

    @Override
    public Meal get(int id) {
        return mealMap.get(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return mealMap.values();
    }
}
