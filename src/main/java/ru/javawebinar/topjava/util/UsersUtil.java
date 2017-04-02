package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Валера on 01.04.2017.
 */
public class UsersUtil {
    public static final List<User> USERS = Arrays.asList(
            new User("Vasia-Admin", "vasia@mail.ru", "*****", Role.ROLE_ADMIN, Role.ROLE_USER),
            new User("Yura-User", "yura@mail.ru", "+++++", Role.ROLE_USER),
            new User("Artem-User", "artem@mail.ru", "-----", Role.ROLE_USER)
    );
}


