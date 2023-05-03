package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CourseReviewImplementation implements CourseReview{
    DatabaseManager databaseManager = new DatabaseManager();
    private static CourseReviewImplementation singleInstance;
    public Student loggedInStudent;


    private CourseReviewImplementation(){

    }

    //Singleton
    public static CourseReviewImplementation getInstance(){
        if(singleInstance == null){
            singleInstance = new CourseReviewImplementation();
        }
       return singleInstance;
    }

    public void connect(){
        databaseManager.connect();
        try{
            databaseManager.createTables();
        }catch(IllegalStateException e){

        }
    }
    public void disconnect(){databaseManager.disconnect();}



    public boolean login(String username, String password){
        //int id = databaseManager.getStudentID(username);
        String actual = databaseManager.getPassword(username);
        if (actual.equals(password)) {
            loggedInStudent = new Student(username, password);
            return true;
        }
        else{
            return false;
        }
    }
    public boolean existingUser(String username){
        return (databaseManager.getStudentID(username) != -1);
    }
    public void createUser(String username, String password){
        loggedInStudent = new Student(username, password);
        databaseManager.addStudent(loggedInStudent);
    }

    public void logout(){
        loggedInStudent = null;
//        databaseManager.disconnect();
    }

    public boolean checkReviewAlreadyExists(Course c){
        return !(databaseManager.getReviewByStudentAndCourse(loggedInStudent,c) == null);
    }
    public boolean checkCourseExists(Course c){
        return !(databaseManager.getCourseID(c.getCourseDepartment(),c.getCourseCatalogNumber()) == -1);
    }
    public void submitReview(Course c, String review, int rating){
        if(!checkCourseExists(c)){
            databaseManager.addCourse(c.getCourseDepartment(),c.getCourseCatalogNumber());
            databaseManager.addReview(loggedInStudent.getStudentName(), c.getCourseDepartment(), c.getCourseCatalogNumber(), review, rating);
        }
        if(!checkReviewAlreadyExists(c)) {
            databaseManager.addReview(loggedInStudent.getStudentName(), c.getCourseDepartment(), c.getCourseCatalogNumber(), review, rating);
        }
    }
    public boolean hasReviews(Course c){
        List<Review> reviews = databaseManager.getAllReviews(c.getCourseDepartment(),c.getCourseCatalogNumber());
        return reviews.size() > 0;
    }
    public List<Review> getReviewsByCourse(Course c){
        return databaseManager.getAllReviews(c.getCourseDepartment(),c.getCourseCatalogNumber());
    }

    public double getAverageForReview(Course c){
        List<Review> allReviews = getReviewsByCourse(c);
        int sumOfRating = 0;
        double count = 0;
        for(Review review : allReviews){
            sumOfRating += review.getReviewRating();
            count++;
        }
        double average = sumOfRating/count;
        return average;
    }

    public List<String> getAllReviewMessages(Course c){
        List<Review> allReviews = getReviewsByCourse(c);
        List<String> reviews = new ArrayList<>();
        for(Review review : allReviews){
            reviews.add(review.getReviewMessage());
        }
        return reviews;
    }



}


