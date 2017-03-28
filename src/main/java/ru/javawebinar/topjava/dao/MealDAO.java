package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;

/**
 * Created by Валера on 26.03.2017.
 */
public interface MealDAO {

    public void addMeal(Meal meal);

    public Meal getMeal(int id);

    public List<Meal> getMeals();

    public List<MealWithExceed> getMealsWithExceed(int maxCaloriesInDay);

    public void updateMeal(Meal meal);

    public void deleteMeal(int id);


}
