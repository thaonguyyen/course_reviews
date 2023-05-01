package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CourseReviewImplementation implements CourseReview{
    DatabaseManager databaseManager = new DatabaseManager();
    public Student loggedInStudent;
    public void connect(){
        databaseManager.connect();
        try{
            databaseManager.createTables();
        }catch(IllegalStateException e){

        }
    }
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
        databaseManager.addStudent(username, password);

    }
    public void logout(){
        loggedInStudent = null;
        databaseManager.disconnect();
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
}
