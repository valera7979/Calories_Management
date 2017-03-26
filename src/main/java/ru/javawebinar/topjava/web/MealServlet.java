package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.model.repository.InMemoryMealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Валера on 25.03.2017.
 */
public class MealServlet extends HttpServlet {

    private static final Logger LOG = getLogger(MealServlet.class);

    private List<MealWithExceed> mealWithExceedList;
    private List<Meal> mealsList;

    public MealServlet() {
        mealsList = new InMemoryMealRepository().getMeals();
        mealWithExceedList = MealsUtil.getFilteredWithExceeded(mealsList, LocalTime.MIN, LocalTime.MAX, 2000);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("meals", mealWithExceedList);
        LOG.debug("meals size " + mealWithExceedList.get(4).toString());
        LOG.debug("meals size " + mealsList.get(4).toString());

        request.getRequestDispatcher("/meals.jsp").forward(request, response);
        LOG.debug("meals SUCCESSFULLY added to meals.jsp");
    }
}
