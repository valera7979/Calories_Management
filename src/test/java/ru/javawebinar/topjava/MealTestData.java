package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID = START_SEQ + 2;

    public static final Meal MEAL = new Meal(MEAL_ID, LocalDateTime.of(2017, Month.APRIL, 9, 20, 23, 58), "ужин", 300);
    public static final Meal MEAL1 = new Meal(MEAL_ID + 1, LocalDateTime.of(2017, Month.APRIL, 9, 14, 23, 58), "LANCh", 1000);
    public static final Meal MEAL2 = new Meal(MEAL_ID + 2, LocalDateTime.of(2017, Month.APRIL, 9, 20, 23, 58), "ужин", 300);
    public static final Meal MEAL3 = new Meal(MEAL_ID + 3, LocalDateTime.of(2017, Month.APRIL, 11, 10, 23, 58), "борщ", 301);

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getDateTime(), actual.getDateTime())
                            && Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getDescription(), actual.getDescription())
                            && Objects.equals(expected.getCalories(), actual.getCalories())

                    )
    );

}
