package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.BaseEntity;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.NamedEntity;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.UsersUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UsersUtil.USERS.forEach(this::save);
    }

    public static void main(String[] args) {
        InMemoryUserRepositoryImpl u = new InMemoryUserRepositoryImpl();
        u.getAll().forEach(System.out::println);
    }

    @Override
    public User save(User user) {
        if (user.isNew())
            user.setId(counter.incrementAndGet());
        repository.put(user.getId(), user);
        LOG.info("save " + user);
        return user;
    }

    @Override
    public boolean delete(int id) {
        boolean userPresented = repository.remove(id) != null;
        if (userPresented) LOG.info("delete " + id);
        else LOG.error("delete impossible: user with id " + id + " is absent");
        return userPresented;
    }

    @Override
    public User get(int id) {
        LOG.info("get user " + id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        List<User> users = repository.entrySet().stream().map(u -> u.getValue())
                .sorted(comparing(user -> user.getName())).collect(Collectors.toList());
        LOG.info("getAll users");
        return users == null ? Collections.EMPTY_LIST : users;
    }

    @Override
    public User getByEmail(String email) {
        Optional<User> user = repository.entrySet().stream().map(u -> u.getValue())
                .filter(us -> us.getEmail().equals(email)).findFirst();
        if (user.isPresent()) {
            LOG.info("getByEmail " + email);
            return user.get();
        } else {
            LOG.info("getByEmail - no user with " + email);
            return null;
        }
    }
}
