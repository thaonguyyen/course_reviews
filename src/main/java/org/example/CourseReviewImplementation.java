package org.example;

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
        databaseManager.addStudent(username, password);

    }
    public void logout(){
        loggedInStudent = null;
        databaseManager.disconnect();
    }
    public boolean checkReviewAlreadyExists(Course c){
        return !(databaseManager.getReviewByStudentAndCourse(loggedInStudent,c) == null);
    }
    public void submitReview(Course c, String review, int rating){
        if(!checkReviewAlreadyExists(c)) {
            databaseManager.addReview(loggedInStudent.getStudentName(), c.getCourseDepartment(), c.getCourseCatalogNumber(), review, rating);
        }
    }
    public List<Review> getReviewsByCourse(Course c){
        return databaseManager.getAllReviews(c.getCourseDepartment(),c.getCourseID());
    }
}
