package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.dao.MealDAOImpl;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Валера on 25.03.2017.
 */
public class MealServlet extends HttpServlet {

    private static final Logger LOG = getLogger(MealServlet.class);

    private MealDAO mealDAO;

    private static String INSERT_OR_EDIT = "/meal.jsp";
    private static String LIST_USER = "/meals.jsp";

    private static final int MAX_CALORIES_IN_DAY = 2000;

    public MealServlet() {
        super();
        mealDAO = new MealDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Meal meal = new Meal();
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        if (!(id == null || id.equals("")))
            meal.setId(Integer.parseInt(id));
        meal.setCalories(Integer.parseInt(request.getParameter("calories")));
        meal.setDescription(request.getParameter("description"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d HH:mm");
        meal.setDateTime(LocalDateTime.parse(request.getParameter("dateTime")
                .replace('T', ' '), formatter));

        mealDAO.addMeal(meal);
        request.setAttribute("meals", mealDAO.getMealsWithExceed(MAX_CALORIES_IN_DAY));

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();


        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String forward = "";

        if (action == null) {
            request.setAttribute("meals", mealDAO.getMealsWithExceed(MAX_CALORIES_IN_DAY));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }

        if (action.equalsIgnoreCase("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            mealDAO.deleteMeal(id);
            forward = LIST_USER;
            request.setAttribute("meals", mealDAO.getMealsWithExceed(MAX_CALORIES_IN_DAY));
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int id = Integer.parseInt(request.getParameter("id"));
            Meal meal = mealDAO.getMeal(id);
            request.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("meals")) {
            forward = LIST_USER;
            request.setAttribute("meals", mealDAO.getMealsWithExceed(MAX_CALORIES_IN_DAY));
        } else if (action.equalsIgnoreCase("add")) {
            forward = INSERT_OR_EDIT;
        } else {
            request.setAttribute("meals", mealDAO.getMealsWithExceed(MAX_CALORIES_IN_DAY));
            forward = LIST_USER;

        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);


    }
}
