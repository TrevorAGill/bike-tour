package models;

public class Review {
    private int id;
    private String comment;
    private int rating;
    private int campsiteId;

    public Review(String comment, int rating, int campsiteId){
        this.comment = comment;
        this.rating = rating;
        this.campsiteId = campsiteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (id != review.id) return false;
        if (rating != review.rating) return false;
        if (campsiteId != review.campsiteId) return false;
        return comment.equals(review.comment);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + comment.hashCode();
        result = 31 * result + rating;
        result = 31 * result + campsiteId;
        return result;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getCampsiteId() {
        return campsiteId;
    }

    public void setCampsiteId(int campsiteId) {
        this.campsiteId = campsiteId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
