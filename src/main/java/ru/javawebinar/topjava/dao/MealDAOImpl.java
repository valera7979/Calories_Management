package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created by Валера on 26.03.2017.
 */
public class MealDAOImpl implements MealDAO {

    ConcurrentMap<Integer, Meal> mealMap = new ConcurrentSkipListMap<>();
    AtomicInteger idCounter;

    public MealDAOImpl() {
        idCounter = new AtomicInteger(0);
    }

    @Override
    public void addMeal(Meal meal) {
        if (meal.getId() == 0) {
            meal.setId(idCounter.incrementAndGet());
            mealMap.put(meal.getId(), meal);
        } else
            mealMap.replace(meal.getId(), meal);
    }

    @Override
    public Meal getMeal(int id) {
        return mealMap.get(id);

    }

    @Override
    public List<Meal> getMeals() {
        List<Meal> meals = new ArrayList<>();
        for (Map.Entry<Integer, Meal> mealEntry : mealMap.entrySet())
            meals.add(mealEntry.getValue());
        return meals;
    }

    @Override
    public List<MealWithExceed> getMealsWithExceed(int maxCaloriesInDay) {
       return MealsUtil.getFilteredWithExceeded(this.getMeals(),
                LocalTime.MIN, LocalTime.MAX, maxCaloriesInDay);

    }

    @Override
    public void updateMeal(Meal meal) {

    }

    @Override
    public void deleteMeal(int id) {
        mealMap.remove(id);
    }


}
