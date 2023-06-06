package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MapStorage;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.CALORIES_PER_DAY;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private Storage storage;

    @Override
    public void init() {
        storage = new MapStorage();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Integer id;
        if (request.getParameter("id").isEmpty()) {
            log.info("Create new meal");
            id = null;
        } else {
            log.info("Update meal: " + getId(request));
            id = getId(request);
        }

        Meal meal = new Meal(id, LocalDateTime.parse(request.getParameter("dateTime")), request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        storage.save(meal);
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            log.info("Get all meals");
            request.setAttribute("meals", MealsUtil.getMealListWithExcess(storage.getAll(), CALORIES_PER_DAY));
            request.getRequestDispatcher("meals.jsp").forward(request, response);
            return;
        }

        Meal meal;
        switch (action) {
            case "delete":
                log.info("Delete meal: " + getId(request));
                storage.delete(getId(request));
                response.sendRedirect("meals");
                return;
            case "update":
                log.info("Update meal: " + getId(request));
                meal = storage.get(getId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("update.jsp").forward(request, response);
            case "add":
                log.info("Create new meal");
                meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 0);
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("update.jsp").forward(request, response);
            default:
                log.warn("Action " + action + " is illegal");
                throw new IllegalArgumentException(action);
        }
    }

    private Integer getId(HttpServletRequest request) {
        return Integer.parseInt(request.getParameter("id"));
    }
}