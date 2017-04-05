package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;
import ru.javawebinar.topjava.web.user.ProfileRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    // private MealRepository repository;

    ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");

    MealRestController mealRestController = appCtx.getBean(MealRestController.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        LOG.info("MealServlet INIT");
        super.init(config);
        //   repository = new InMemoryMealRepositoryImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("MealServlet doPOST");
        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("action").equals("filter")) {

            List<MealWithExceed> filteredMeals =
                    mealRestController.filterByDate(request.getParameter("fromDate"),
                            request.getParameter("toDate"),
                            request.getParameter("fromTime"),
                            request.getParameter("toTime"));

            request.setAttribute("meals", filteredMeals);
            request.setAttribute("fromDate", request.getParameter("fromDate"));
            request.setAttribute("toDate", request.getParameter("toDate"));
            request.setAttribute("fromTime", request.getParameter("fromTime"));
            request.setAttribute("toTime", request.getParameter("toTime"));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);

        } else if (request.getParameter("action").equals("setUser")) {
            String authorisedUserId = request.getParameter("authUser");
            AuthorizedUser.setId(Integer.parseInt(authorisedUserId));
            LOG.info("set authorised user " + authorisedUserId);
            request.setAttribute("authUser", request.getParameter("authUser"));
            //request.getRequestDispatcher("/meals.jsp").forward(request, response);
            response.sendRedirect("meals");
        } else {
            String id = request.getParameter("id");

            Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.valueOf(request.getParameter("calories")), AuthorizedUser.id());

            LOG.info(meal.isNew() ? "Create {}" : "Update {}", meal);
            if (id.isEmpty()) mealRestController.create(meal);
            else mealRestController.update(meal, Integer.valueOf(id));
            response.sendRedirect("meals");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("MealServlet doGET");
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                LOG.info("Delete {}", id);
                mealRestController.delete(id);
                response.sendRedirect("meals");
                break;
            case "create":
            case "update":
                final Meal meal = action.equals("create") ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000, AuthorizedUser.id()) :
                        mealRestController.get(getId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/meal.jsp").forward(request, response);
                break;
            case "all":
            default:
                LOG.info("getAll");
                request.setAttribute("meals",
                        mealRestController.getAllMealsWithExceed());
                request.setAttribute("authUser", AuthorizedUser.id());
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}