package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

@ActiveProfiles({Profiles.ACTIVE_DB, "jdbc"})
public class JDBCMealServiceTest extends MealServiceTest {

}
