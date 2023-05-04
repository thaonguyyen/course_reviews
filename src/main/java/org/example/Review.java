package org.example;

public class Review {
    private String message;
    private int rating;
    private Student student;
    private Course course;

    public Review(Student student, Course course, String reviewMessage, int reviewRating){
        this.message = reviewMessage;
        this.rating = reviewRating;
        this.student = student;
        this.course = course;
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
