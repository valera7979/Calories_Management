package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class JdbcUserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertUser;

    @Autowired
    public JdbcUserRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertUser = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public User save(User user) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
        Set<Role> roles = user.getRoles();
        if (user.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(parameterSource);
            user.setId(newKey.intValue());
        } else {
            namedParameterJdbcTemplate.update(
                    "UPDATE users SET name=:name, email=:email, password=:password, " +
                            "registered=:registered, enabled=:enabled, calories_per_day=:caloriesPerDay WHERE id=:id", parameterSource);
        }
        for (Role role : roles)
            jdbcTemplate.update("INSERT INTO user_roles (user_id, role) VALUES (? , ?)", user.getId(), role.name());
        return user;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
    }

    @Override
    public User get(int id) {
        User user = jdbcTemplate.query("SELECT users.*, user_roles.role FROM users " +
                        "INNER JOIN user_roles ON user_roles.user_id = users.id WHERE id=?",
                new ResultSetExtractor<User>() {
                    @Override
                    public User extractData(ResultSet rs) throws SQLException, DataAccessException {
                        User user = new User();
                        Set<Role> roleSet = new LinkedHashSet<>();
                        while (rs.next()) {
                            String role_str = rs.getString("role");
                            Role role = "ROLE_USER".equals(role_str) ? Role.ROLE_USER :
                                    "ROLE_ADMIN".equals(role_str) ? Role.ROLE_ADMIN : null;
                            //if (user==null)
                            user.setId(rs.getInt("id"));
                            user.setName(rs.getString("name"));
                            user.setEmail(rs.getString("email"));
                            user.setPassword(rs.getString("password"));
                            roleSet.add(role);
                        }
                        user.setRoles(roleSet);
                        return user;
                    }
                }, id);

        if (user.getId() != null)
            return user;
        else return null;
    }

    ResultSetExtractor<List<User>> listResultSetExtractor = new ResultSetExtractor<List<User>>() {
        @Override
        public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<Integer, User> users = new HashMap<>();
            while (rs.next()) {
                String role_str = rs.getString("role");
                Role role = "ROLE_USER".equals(role_str) ? Role.ROLE_USER :
                        "ROLE_ADMIN".equals(role_str) ? Role.ROLE_ADMIN : null;
                int id = rs.getInt("id");
                if (users.get(id) == null) {
                    User user = new User();
                    user.setId(id);
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setCaloriesPerDay(rs.getInt("calories_per_day"));
                    user.setRoles(new HashSet<>());
                    users.put(user.getId(), user);

                }
                Set<Role> roles = users.get(id).getRoles();
                roles.add(role);
                users.get(id).setRoles(roles);
            }
            return new ArrayList<>(users.values());
        }
    };

    @Override
    public User getByEmail(String email) {
        List<User> users = jdbcTemplate.query("SELECT users.*, user_roles.role FROM users " +
                "INNER JOIN user_roles ON user_roles.user_id = users.id WHERE email=?", listResultSetExtractor, email);
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> getAll() {

        return jdbcTemplate.query("SELECT users.*, user_roles.role FROM users " +
                "INNER JOIN user_roles ON user_roles.user_id = users.id " +
                "ORDER BY users.name, users.email", listResultSetExtractor);
    }
}
