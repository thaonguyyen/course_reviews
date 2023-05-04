package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CourseReviewImplementation implements CourseReview{
    DatabaseManager databaseManager = new DatabaseManager();
    private static CourseReviewImplementation singleInstance;
    public Student loggedInStudent;


    CourseReviewImplementation(){

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

    public void addStarterData(){
        Student user1 = new Student("username1", "pass1");
        Student user2 = new Student("username2", "pass2");
        Student user3 = new Student("username3", "pass3");
        Student user4 = new Student("username4", "pass4");
        Course course1 = new Course("CS", 3140);
        Course course2 = new Course("CS", 3100);
        Course course3 = new Course("CS", 2100);

        if(!existingUser(user1.getStudentName())){
            createUser(user1.getStudentName(), user1.getStudentPassword());
        }
        if(!existingUser(user2.getStudentName())){
            createUser(user2.getStudentName(), user2.getStudentPassword());
        }
        if(!existingUser(user3.getStudentName())){
            createUser(user3.getStudentName(), user3.getStudentPassword());
        }
        if(!existingUser(user4.getStudentName())){
            createUser(user4.getStudentName(), user4.getStudentPassword());
        }



        loggedInStudent = user1;
        submitReview(course1, "Good class", 4);
        submitReview(course2, "Boring class", 2);
        loggedInStudent = user2;
        submitReview(course2, "Hard class", 3);
        loggedInStudent = user3;
        submitReview(course3, "Easy class", 5);
        loggedInStudent = user4;
        submitReview(course1, "Helpful class", 3);
        logout();
    }

}


