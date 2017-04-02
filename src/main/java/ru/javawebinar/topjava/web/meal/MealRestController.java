package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;
    @Autowired
    private UserService userService;

    public List<Meal> getAll() {
        LOG.info("controller: getAll meal");
        return service.getAll(AuthorizedUser.id());
    }

    public Meal get(int id) {
        LOG.info("controller: get meal " + id);
        return service.get(id, AuthorizedUser.id());
    }

    public Meal create(Meal meal) {
        checkNew(meal);
        LOG.info("controller: create " + meal);
        return service.save(meal, AuthorizedUser.id());
    }

    public void delete(int id) {
        LOG.info("controller: delete " + id);
        service.delete(id, AuthorizedUser.id());
    }

    public void update(Meal meal, int id) {
        LOG.info("controller: update " + meal);
        checkIdConsistent(meal, id);
        service.update(meal, AuthorizedUser.id());
    }

    public List<MealWithExceed> getAllMealsWithExceed() {
        return MealsUtil.getFilteredWithExceeded(service.getAll(AuthorizedUser.id()), LocalTime.MIN
                , LocalTime.MAX, userService.get(AuthorizedUser.id()).getCaloriesPerDay());
    }

}