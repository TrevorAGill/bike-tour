package dao;

import models.Campsite;
import models.Review;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oReviewDao implements ReviewDao{

    private final Sql2o sql2o;

    public Sql2oReviewDao(Sql2o sql2o){
        this.sql2o = sql2o; //making the sql2o object available everywhere so we can call methods in it
    }

    @Override
    public void add(Review review) {
        String sql = "INSERT INTO reviews (comment, rating, campsiteId) VALUES (:comment, :rating, :campsiteId)"; //raw sql
        try(Connection con = sql2o.open()){ //try to open a connection
            int id = (int) con.createQuery(sql) //make a new variable
                    .addParameter("comment", review.getComment())
                    .addParameter("rating", review.getRating())
                    .addParameter("campsiteId", review.getCampsiteId())

                    .addColumnMapping("COMMENT", "comment")
                    .addColumnMapping("RATING", "rating")
                    .addColumnMapping("CAMPSITEID", "campsiteId")

                    .executeUpdate() //run it all
                    .getKey(); //int id is now the row number (row “key”) of db
            review.setId(id); //update object to set id now from database
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }


    @Override
    public List<Review> getAllByCampsite(int campsiteId) {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM reviews WHERE campsiteId = :campsiteId")
                    .addParameter("campsiteId", campsiteId)
                    .executeAndFetch(Review.class);
        }

    }


}
