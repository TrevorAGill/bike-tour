package dao;


import models.Campsite;

import java.util.List;

public interface CampsiteDao {

    //create
    void add (Campsite campsite);

    //read
    List<Campsite> getAll();

    List<Campsite> getAllByTour(int tourId);

    //find by id
    Campsite findById(int id);

    //update
    void update(String name, int rating, int cost, int tourId, int id, String showers, String bikeRepair, String reservation, String foodAvailable, String phone);

    //delete
    void deleteById(int id);

}
