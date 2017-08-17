package models;


public class Campsite {
    private String name;
    private int rating;
    private int cost;
    private int id;
    private int tourId;
    private boolean showers;
    private boolean bikeRepair;
    private boolean reservation;
    private String foodAvailable;
    private String phone;

    public Campsite (String name, int rating, int cost, int tourId, boolean showers, boolean bikeRepair, boolean reservation, String foodAvailable, String phone){
        this.name = name;
        this.rating = rating;
        this.cost = cost;
        this.tourId = tourId;
        this.showers = false;
        this.bikeRepair = false;
        this.reservation = false;
        this.foodAvailable = foodAvailable;
        this.phone = phone;
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
        if (showers != campsite.showers) return false;
        if (bikeRepair != campsite.bikeRepair) return false;
        if (reservation != campsite.reservation) return false;
        if (!name.equals(campsite.name)) return false;
        if (foodAvailable != null ? !foodAvailable.equals(campsite.foodAvailable) : campsite.foodAvailable != null)
            return false;
        return phone != null ? phone.equals(campsite.phone) : campsite.phone == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + rating;
        result = 31 * result + cost;
        result = 31 * result + id;
        result = 31 * result + tourId;
        result = 31 * result + (showers ? 1 : 0);
        result = 31 * result + (bikeRepair ? 1 : 0);
        result = 31 * result + (reservation ? 1 : 0);
        result = 31 * result + (foodAvailable != null ? foodAvailable.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
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

    public boolean isShowers() {
        return showers;
    }

    public void setShowers(boolean showers) {
        this.showers = showers;
    }

    public boolean isBikeRepair() {
        return bikeRepair;
    }

    public void setBikeRepair(boolean bikeRepair) {
        this.bikeRepair = bikeRepair;
    }

    public boolean isReservation() {
        return reservation;
    }

    public void setReservation(boolean reservation) {
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
