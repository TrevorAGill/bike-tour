package models;


public class Tour {
    private String name;
    private int difficulty;
    private int rating;
    private int id;

    public Tour(String name, int difficulty, int rating){
        this.name = name;
        this.difficulty = difficulty;
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tour tour = (Tour) o;

        if (difficulty != tour.difficulty) return false;
        if (rating != tour.rating) return false;
        return name.equals(tour.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + difficulty;
        result = 31 * result + rating;
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
}
