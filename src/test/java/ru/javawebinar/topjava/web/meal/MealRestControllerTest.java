package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.javawebinar.topjava.MealTestData.*;

import static ru.javawebinar.topjava.MealTestData.MATCHER;
import static ru.javawebinar.topjava.MealTestData.MATCHER_WITH_EXCEED;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by Валера on 14.05.2017.
 */
public class MealRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = MealRestController.REST_URL + '/';

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + MEAL1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(MEAL1));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MEAL1_ID))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2), mealService.getAll(USER_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = new Meal(MEAL1);
        updated.setDescription("UpdatedName");
        updated.setCalories(1001);
        mockMvc.perform(put(REST_URL + MEAL1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated, mealService.get(MEAL1_ID, USER_ID));
    }

    @Test
    public void testCreate() throws Exception {
        Meal created = new Meal(now(), "new", 1234);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)))
                .andExpect(status().isCreated());

        Meal returned = MealTestData.MATCHER.fromJsonAction(action);
        created.setId(returned.getId());

        MealTestData.MATCHER.assertEquals(created, returned);
        MATCHER.assertCollectionEquals(Arrays.asList(created, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1), mealService.getAll(USER_ID));
    }

    @Test
    public void testGetAll() throws Exception {
        List<MealWithExceed> list = MealsUtil.getWithExceeded
                (Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1)
                        ,AuthorizedUser.getCaloriesPerDay());

     //   ModelMatcher<Meal> WITH_EXCEED_MATCHER = ModelMatcher.of(MealWithExceed.class);*/

        TestUtil.print(mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
              .andExpect(MATCHER_WITH_EXCEED.contentListMatcher(list)));
    }

    @Test
    public void testGetBetween() throws Exception {
        List<MealWithExceed> list = MealsUtil.getWithExceeded
                (Arrays.asList(MEAL6, MEAL5, MEAL4)
                        ,AuthorizedUser.getCaloriesPerDay());

     //   LocalDate startDate = LocalDate.parse("2015-05-31",ISO_LOCAL_DATE);
        String startDate = "2015-05-31";
        mockMvc.perform(put(REST_URL + "/filter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(startDate)))
                .andExpect(status().isOk())
                .andExpect(MATCHER_WITH_EXCEED.contentListMatcher(list));

    }

}