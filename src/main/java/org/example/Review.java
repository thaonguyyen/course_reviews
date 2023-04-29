package org.example;

public class Review {
    private String message;
    private int rating;

    public Review(String reviewMessage, int reviewRating){
        this.message = reviewMessage;
        this.rating = reviewRating;
    }

    public String getReviewMessage(){
        return message;
    }

    public int getReviewRating(){
        return rating;
    }

    public void setReviewMessage(String newMessage){
        this.message = newMessage;
    }

    public void setReviewRating(int newRating) {
        this.rating = newRating;
    }

    //checks that rating is an integer from 1-5
    public boolean checkRating(int rating){
        if(rating == 1 || rating == 2 || rating == 3 || rating == 4 || rating == 5){
            return true;
        }
        return false;
    }
}
