package org.example;

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
    }
    public boolean login(String username, String password){
        int id = databaseManager.getStudentID(username);
        String actual = databaseManager.getPassword(username);
        if (actual.equals(password)) {
            loggedInStudent = new Student(id, username, password);
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
        if(databaseManager.getStudentID(username) == 0) {
            databaseManager.addStudent(username, password);
        }

    }
    public void logout(){
        loggedInStudent = null;
        databaseManager.disconnect();
    }
    public void submitReview(Course c, String review, int rating){
        databaseManager.addReview(loggedInStudent.getStudentName(), c.getCourseDepartment(),c.getCourseCatalogNumber(),review, rating);
    }
    public List<Review> getReviewsByCourse(Course c){
        return databaseManager.getAllReviews(c.getCourseDepartment(),c.getCourseID());
    }
}
