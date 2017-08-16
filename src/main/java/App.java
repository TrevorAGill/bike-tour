import dao.Sql2oCampsiteDao;
import dao.Sql2oTourDao;
import models.Campsite;
import models.Tour;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        String connectionString = "jdbc:h2:~/biketours.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oCampsiteDao campsiteDao = new Sql2oCampsiteDao(sql2o);
        Sql2oTourDao tourDao = new Sql2oTourDao(sql2o);

        //get: show all campsites in all categories and show all categories
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Tour> allTours = tourDao.getAll();
            model.put("tours", allTours);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/tours/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Tour> allTours = tourDao.getAll();
            model.put("tours", allTours);
            return new ModelAndView(model, "tour-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/tours", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("newName");
            int difficulty = Integer.parseInt(request.queryParams("newDifficulty"));
            int rating = Integer.parseInt(request.queryParams("newRating"));
            Tour newTour = new Tour(name,difficulty,rating);
            tourDao.add(newTour);
            response.redirect("/");
            return null;
        });

        get("/tours/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Tour> allTours = tourDao.getAll();
            int idOfTour = Integer.parseInt(request.params("id"));
            model.put("tours", allTours);
            Tour foundTour = tourDao.findById(idOfTour);
            model.put("tour", foundTour);
            List<Campsite> campsites = campsiteDao.getAllByTour(idOfTour);
            model.put("campsites", campsites);
            return new ModelAndView(model, "tour-detail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/campsites/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Tour> allTours = tourDao.getAll();
            model.put("tours", allTours);
            return new ModelAndView(model, "campsite-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/campsites", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            int cost = Integer.parseInt(request.queryParams("cost"));
            int rating = Integer.parseInt(request.queryParams("rating"));
            int tourId = Integer.parseInt(request.queryParams("tour"));
            Campsite newCampsite = new Campsite(name, rating, cost, tourId);
            campsiteDao.add(newCampsite);
            response.redirect("/tours/" + tourId);
            return null;
        });

    }

}
