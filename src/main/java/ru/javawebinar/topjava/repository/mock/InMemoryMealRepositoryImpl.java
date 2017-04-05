package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        for (Meal meal : MealsUtil.MEALS)
            this.save(meal, AuthorizedUser.id());
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userId);
            repository.put(meal.getId(), meal);
            LOG.info("save NEW " + meal);
            return meal;
        } else if (repository.get(meal.getId()).getUserId() == userId) {
            repository.put(meal.getId(), meal);
            LOG.info("updated " + meal);
            return meal;
        } else {
            LOG.info("user " + userId + " attempted edit " + repository.get(meal.getId()));
            return null;
        }
    }

    @Override
    public boolean delete(int id, int userId) {
        if (repository.get(id) != null && repository.get(id).getUserId() == userId) {
            repository.remove(id);
            LOG.info("delete meal with id " + id);
            return true;
        } else {
            LOG.info("delete meal " + repository.get(id) + " by user " + userId + " was WRONG");
            return false;
        }
    }

    @Override
    public Meal get(int id, int userId) {
        if (repository.get(id) != null && repository.get(id).getUserId() == userId) {
            LOG.info("get meal " + id);
            return repository.get(id);
        } else {
            LOG.info("get meal " + id + " was WRONG");
            return null;
        }
    }

    @Override
    public List<Meal> getAll(int userId) {
        Comparator<Meal> comparator = Comparator.comparing(Meal::getDateTime);
        List<Meal> meals = repository.entrySet().stream().map(r -> r.getValue())
                .filter(m -> m.getUserId() == userId)
                .sorted(comparator.reversed())
                .collect(Collectors.toList());
        LOG.info("get all meals of user " + AuthorizedUser.id());
        return meals == null ? Collections.EMPTY_LIST : meals;
    }
}

