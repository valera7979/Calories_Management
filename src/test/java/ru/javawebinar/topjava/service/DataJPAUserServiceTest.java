package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by Валера on 24.04.2017.
 */
@ActiveProfiles({Profiles.ACTIVE_DB, "datajpa"})
public class DataJPAUserServiceTest extends UserServiceTest {
}
