package dao;

import models.Tour;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;


public class Sql2oTourDao implements TourDao {

    private final Sql2o sql2o;

    public Sql2oTourDao(Sql2o sql2o){
        this.sql2o = sql2o; //making the sql2o object available everywhere so we can call methods in it
    }

    @Override
    public void add(Tour tour) {
        String sql = "INSERT INTO tours (name, difficulty, rating, season, distance, description) VALUES (:name, :difficulty, :rating, :season, :distance, :description)"; //raw sql
        try(Connection con = sql2o.open()){ //try to open a connection
            int id = (int) con.createQuery(sql) //make a new variable
                    .addParameter("name", tour.getName())
                    .addParameter("difficulty", tour.getDifficulty())
                    .addParameter("rating", tour.getRating())
                    .addParameter("season", tour.getSeason())
                    .addParameter("distance", tour.getDistance())
                    .addParameter("description", tour.getDescription())
                    .addColumnMapping("NAME", "name")
                    .addColumnMapping("DIFFICULTY", "difficulty")
                    .addColumnMapping("RATING", "rating")
                    .addColumnMapping("SEASON", "season")
                    .addColumnMapping("DISTANCE", "distance")
                    .addColumnMapping("DESCRIPTION", "description")
                    .executeUpdate() //run it all
                    .getKey(); //int id is now the row number (row “key”) of db
            tour.setId(id); //update object to set id now from database
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }


    @Override
    public List<Tour> getAll() {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM tours")
                    .executeAndFetch(Tour.class);
        }
    }

    @Override
    public Tour findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM tours WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Tour.class);
        }
    }

    @Override
    public void update(String name, int difficulty, int rating, int id, String season, int distance, String description) {
        try (Connection con = sql2o.open()) {
            con.createQuery("UPDATE tours SET (rating, difficulty, name, season, distance, description) = (:rating, :difficulty, :name, :season, :distance, :description) WHERE id = :id")
                    .addParameter("id", id)
                    .addParameter("rating", rating)
                    .addParameter("difficulty", difficulty)
                    .addParameter("season", season)
                    .addParameter("distance", distance)
                    .addParameter("name", name)
                    .addParameter("description", description)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }


    @Override
    public void deleteById(int id){
        try (Connection con = sql2o.open()) {
            con.createQuery("DELETE FROM tours WHERE id = :id")
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
