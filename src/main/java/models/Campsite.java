package models;


public class Campsite {
    private String name;
    private int rating;
    private int cost;
    private int id;
    private int tourId;

    public Campsite (String name, int rating, int cost, int tourId){
        this.name = name;
        this.rating = rating;
        this.cost = cost;
        this.tourId = tourId;
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
        return name.equals(campsite.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + rating;
        result = 31 * result + cost;
        result = 31 * result + id;
        result = 31 * result + tourId;
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
}
