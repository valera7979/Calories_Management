package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.DateTimeUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by Валера on 01.05.2017.
 */
@Controller
public class MealController extends MealRestController {

    @Autowired
    private MealService service;

    public MealController(MealService service) {
        super(service);
    }

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("meals", getAll());
        return "meals";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.POST)
    public String getBetween(@RequestParam("startDate") String startDate,
                             @RequestParam("endDate") String endDate,
                             @RequestParam("startTime") String startTime,
                             @RequestParam("endTime") String endTime,
                             Model model) {
        model.addAttribute("meals", getBetween(
                DateTimeUtil.parseLocalDate(startDate),
                DateTimeUtil.parseLocalTime(startTime),
                DateTimeUtil.parseLocalDate(endDate),
                DateTimeUtil.parseLocalTime(endTime)));
        return "meals";
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String addMeal(HttpServletRequest request) {
        String Id = request.getParameter("id");
        Integer id = Id.length() == 0 ? null : Integer.valueOf(Id);
        Meal meal = new Meal(
                id,
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));
        if (meal.isNew())
            create(meal);
        else update(meal, meal.getId());
        return "redirect:meals";
    }

    @RequestMapping("meals/delete/{id}")
    public String remove(@PathVariable("id") int id) {
        delete(id);
        return "redirect:/meals";
    }

    @RequestMapping("meal")
    public String create(Model model) {
        model.addAttribute(
                new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000));
        model.addAttribute("param.action","create");
        return "meal";
    }

    @RequestMapping(value = "meal", method = RequestMethod.POST)
    public String update(@RequestParam("id") int id,
                         @RequestParam("dateTime") String dateTime,
                         @RequestParam("description") String description,
                         @RequestParam("calories") int calories,
                         Model model) {
        model.addAttribute(
                new Meal(id, LocalDateTime.parse(dateTime), description, calories));
           return "meal";
    }


}
