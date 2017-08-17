package dao;


import models.Tour;

import java.util.List;

public interface TourDao {

    //create
    void add (Tour tour);

    //read
    List<Tour> getAll();

    //find by id
    Tour findById(int id);

    //update
    void update(String name, int difficulty, int rating, int id, String season, int distance, String description);

    //delete
    void deleteById(int id);

}