package org.example;

public class Review {
    private String message;
    private int rating;
    private int studentID;
    private int courseID;

    public Review(int student, int course, String reviewMessage, int reviewRating){
        this.message = reviewMessage;
        this.rating = reviewRating;
        this.studentID = student;
        this.courseID = course;
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

}
