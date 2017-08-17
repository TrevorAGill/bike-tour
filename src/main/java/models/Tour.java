package models;


public class Tour {
    private String name;
    private int difficulty;
    private int rating;
    private int id;
    private String season;
    private int distance;
    private String description;


    public Tour(String name, int difficulty, int rating, String season, int distance, String description){
        this.name = name;
        this.difficulty = difficulty;
        this.rating = rating;
        this.season = season;
        this.distance = distance;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tour tour = (Tour) o;

        if (difficulty != tour.difficulty) return false;
        if (rating != tour.rating) return false;
        if (id != tour.id) return false;
        if (distance != tour.distance) return false;
        if (!name.equals(tour.name)) return false;
        if (season != null ? !season.equals(tour.season) : tour.season != null) return false;
        return description != null ? description.equals(tour.description) : tour.description == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + difficulty;
        result = 31 * result + rating;
        result = 31 * result + id;
        result = 31 * result + (season != null ? season.hashCode() : 0);
        result = 31 * result + distance;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    //getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int length) {
        this.distance = length;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
