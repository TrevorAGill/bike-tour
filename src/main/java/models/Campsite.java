package models;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Campsite {
    private String name;
    private int rating;
    private int cost;
    private int id;
    private int tourId;
    private String [] amenities;
    private String showers;
    private String bikeRepair;
    private String reservation;
    private String foodAvailable;
    private String phone;
    int scoreTotal;
    int reviewCount;

    public Campsite (String name, int rating, int cost, int tourId, String showers, String bikeRepair, String reservation, String foodAvailable, String phone, String[] amenities){
        this.name = name;
        this.rating = rating;
        this.cost = cost;
        this.tourId = tourId;
        this.showers = showers;
        this.bikeRepair = bikeRepair;
        this.reservation =reservation;
        this.foodAvailable = foodAvailable;
        this.phone = phone;
        this.amenities = amenities;
        this.scoreTotal = 0;
        this.reviewCount = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Campsite campsite = (Campsite) o;

        if (rating != campsite.rating) return false;
        if (cost != campsite.cost) return false;
        if (id != campsite.id) return false;
        if (tourId != campsite.tourId) return false;
        if (!name.equals(campsite.name)) return false;
        if (!showers.equals(campsite.showers)) return false;
        if (!bikeRepair.equals(campsite.bikeRepair)) return false;
        if (!reservation.equals(campsite.reservation)) return false;
        if (!foodAvailable.equals(campsite.foodAvailable)) return false;
        return phone.equals(campsite.phone);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + rating;
        result = 31 * result + cost;
        result = 31 * result + id;
        result = 31 * result + tourId;
        result = 31 * result + showers.hashCode();
        result = 31 * result + bikeRepair.hashCode();
        result = 31 * result + reservation.hashCode();
        result = 31 * result + foodAvailable.hashCode();
        result = 31 * result + phone.hashCode();
        return result;
    }

    public String translateRadio(String reservationRaw){
        String reservation;
        if((!reservationRaw.equals("yes") && !reservationRaw.equals("no")) || reservationRaw == null || reservationRaw == ""){
            reservation = "no";
        } else {
            reservation = reservationRaw;
        }
        return reservation;
    }

    public void breakUp(String[] amenities){
        if (Arrays.asList(amenities).contains("showers")){
            this.showers = "yes";
        } else {
            this.showers = "no";
        }
        if (Arrays.asList(amenities).contains("reservations")){
            this.reservation = "yes";
        } else {
            this.reservation = "no";
        }
        if (Arrays.asList(amenities).contains("bikeRepair")){
            this.bikeRepair = "yes";
        } else {
            this.bikeRepair = "no";
        }
    }

    public int calcScores(List<Review> reviewList, int rating){
        for(Review review : reviewList) {
            this.reviewCount++;
            this.scoreTotal += rating;
        }
        int aScore = this.scoreTotal;
        int thisReviewCt = this.reviewCount;
        return aScore/thisReviewCt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public String getShowers() {
        return showers;
    }

    public void setShowers(String showers) {
        this.showers = showers;
    }

    public String getBikeRepair() {
        return bikeRepair;
    }

    public void setBikeRepair(String bikeRepair) {
        this.bikeRepair = bikeRepair;
    }

    public String getReservation() {
        return reservation;
    }

    public void setReservation(String reservation) {
        this.reservation = reservation;
    }

    public String getFoodAvailable() {
        return foodAvailable;
    }

    public void setFoodAvailable(String foodAvailable) {
        this.foodAvailable = foodAvailable;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
