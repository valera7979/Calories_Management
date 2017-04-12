package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.time.LocalDateTime.now;
import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

import static ru.javawebinar.topjava.util.DateTimeUtil.*;

/**
 * Created by Валера on 10.04.2017.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void get() throws Exception {
        Meal meal = service.get(MEAL_ID, USER_ID);
        MealTestData.MATCHER.assertEquals(meal, MEAL);
    }

    @Test
    public void delete() throws Exception {
        service.delete(MEAL_ID, USER_ID);
        MealTestData.MATCHER.assertCollectionEquals(Collections.singletonList(MEAL1), service.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void deleteForeign() {
        service.delete(MEAL_ID, ADMIN_ID);
    }

    @Test
    public void getBetweenDates() throws Exception {
        List<Meal> betweenDatesMeals = service.getBetweenDates(MIN_DATE, parseLocalDate("2017-04-10"), ADMIN_ID);
        MealTestData.MATCHER.assertCollectionEquals(Arrays.asList(MEAL2), betweenDatesMeals);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> meals = service.getAll(USER_ID);
        MealTestData.MATCHER.assertCollectionEquals(Arrays.asList(MEAL, MEAL1), meals);
    }

    @Test
    public void update() throws Exception {
        Meal meal = MEAL;
        meal.setCalories(111);
        service.update(meal, USER_ID);
        MealTestData.MATCHER.assertEquals(meal, service.get(MEAL_ID, USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void updateForeign() throws Exception {
        Meal meal = MEAL;
        meal.setCalories(222);
        service.update(meal, ADMIN_ID);
    }

    @Test
    public void save() throws Exception {
        Meal newMeal = new Meal(now(), "description", 333);
        Meal created = service.save(newMeal, USER_ID);
        newMeal.setId(created.getId());
        MealTestData.MATCHER.assertEquals(newMeal, service.get(newMeal.getId(), USER_ID));

    }

}