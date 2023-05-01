package org.example;

import java.util.ArrayList;
import java.util.List;

public interface CourseReview {

    public void connect();
    public boolean login(String username, String password);
    public void createUser(String username, String password);
    public void logout();
    public void submitReview(Course c, String review, int rating);
    public List<Review> getReviewsByCourse(Course c);




}
