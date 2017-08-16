package dao;

import models.Campsite;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oCampsiteDao implements CampsiteDao{

    private final Sql2o sql2o;

    public Sql2oCampsiteDao(Sql2o sql2o){
        this.sql2o = sql2o; //making the sql2o object available everywhere so we can call methods in it
    }

    @Override
    public void add(Campsite campsite) {
        String sql = "INSERT INTO campsites (name, rating, cost, tourId) VALUES (:name, :rating, :cost, :tourId)"; //raw sql
        try(Connection con = sql2o.open()){ //try to open a connection
            int id = (int) con.createQuery(sql) //make a new variable
                    .addParameter("name", campsite.getName())
                    .addParameter("rating", campsite.getRating())
                    .addParameter("cost", campsite.getCost())
                    .addParameter("tourId", campsite.getTourId())
                    .addColumnMapping("NAME", "name")
                    .addColumnMapping("RATING", "rating")
                    .addColumnMapping("COST", "cost")
                    .addColumnMapping("TOURID", "tourId")
                    .executeUpdate() //run it all
                    .getKey(); //int id is now the row number (row “key”) of db
            campsite.setId(id); //update object to set id now from database
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }


    @Override
    public List<Campsite> getAll() {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM campsites")
                    .executeAndFetch(Campsite.class);
        }
    }

    @Override
    public List<Campsite> getAllByTour(int tourId) {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM campsites WHERE tourID = :tourId")
                    .addParameter("tourId", tourId)
                    .executeAndFetch(Campsite.class);
        }

    }

    @Override
    public Campsite findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM campsites WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Campsite.class);
        }
    }

    @Override
    public void update(String name, int cost, int rating, int id, int tourId) {
        try (Connection con = sql2o.open()) {
            con.createQuery("UPDATE campsites SET (rating, cost, name, tourId) = (:rating, :cost, :name, :tourId) WHERE id = :id")
                    .addParameter("id", id)
                    .addParameter("rating", rating)
                    .addParameter("cost", cost)
                    .addParameter("name", name)
                    .addParameter("tourId", tourId)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }


    @Override
    public void deleteById(int id){
        try (Connection con = sql2o.open()) {
            con.createQuery("DELETE FROM campsites WHERE id = :id")
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
