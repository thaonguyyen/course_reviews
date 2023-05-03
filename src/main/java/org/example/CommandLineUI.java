package org.example;

import java.util.List;
import java.util.Scanner;

import static java.lang.Character.isDigit;

public class CommandLineUI {
    private Scanner scanner;
    static CourseReviewImplementation db = new CourseReviewImplementation();

    public static void main(String[] args) {
        CommandLineUI ui = new CommandLineUI();
        db.connect();
        ui.initializeScanner();
        ui.loginUI();
    }
    private void initializeScanner() {
        scanner = new Scanner(System.in);
    }

    private void loginUI(){
        System.out.println("Would you like to log-in to existing user (1) or create a new user (2)?");
        int choice = Integer.parseInt(scanner.nextLine());
        if(choice == 1){
            System.out.println("Enter existing username: ");
            String username = scanner.nextLine();
            if(db.existingUser(username)){
                System.out.println("Enter password: ");
                String pass = scanner.nextLine();
                if(db.login(username, pass)){
                    mainMenu();
                }
                else{
                    System.out.println("Invalid password for existing username. Try again.");
                    loginUI();
                }
            }
            else{
                System.out.println("Username does not exist. Try again.");
                loginUI();
            }
        }
        else if(choice == 2){
            System.out.println("Enter new username: ");
            String newUser = scanner.nextLine();
            System.out.println("Enter password: ");
            String pass1 = scanner.nextLine();
            System.out.println("Confirm password: ");
            String pass2 = scanner.nextLine();
            if(pass1.equals(pass2)){
                db.createUser(newUser, pass2);
                mainMenu();
            }
            else{
                System.out.println("Passwords do not match. Try again.");
                loginUI();
            }
        }
        else{
            System.out.println("Invalid choice. Try again.");
            loginUI();
        }
    }

    private void logout(){
        System.out.println("You are now logged out.");
        loginUI();
    }

    private void mainMenu(){
        System.out.println("Would you like to submit a review (1), see reviews for a course (2), or logout (3)?");
        int choice = Integer.parseInt(scanner.nextLine());
        if(choice == 1){
            submitReview();
            mainMenu();
        }
        else if(choice == 2){
            seeReviews();
            mainMenu();
        }
        else if(choice == 3){
            logout();
        }
        else{
            System.out.println("Invalid choice. Try again.");
            mainMenu();
        }
    }

    private void seeReviews(){
        System.out.println("Enter course you would like to see reviews for: ");
        String courseString = scanner.nextLine();
        String[] splitString = courseString.split("\\s+");
        String subject = splitString[0];
        int catalogNum = Integer.parseInt(splitString[1]);

        //check subject is 4 or fewer capital letters and catalogNum is 4 digits
        if(!isUpperCase(subject) || countDigits(catalogNum) != 4 || subject.length() > 4){
            //return to main menu
            System.out.println("Invalid course. Try again.");
            mainMenu();
        }
        else{
            Course course = new Course(subject, catalogNum);
            //if course has no reviews
            if(!db.hasReviews(course)){
                System.out.println("There are no reviews for this course. Try again.");
                mainMenu();
            }
            //else course has reviews
            else{
                List<Review> allReviews = db.getReviewsByCourse(course);
                System.out.println("Reviews for this course: ");
                List<String> allMessages = db.getAllReviewMessages(course);
                for(String s : allMessages){
                    System.out.println(s);
                }
                double average = db.getAverageForReview(course);
                System.out.println("Course average: "+average+"/5");
            }
        }
    }

    private void submitReview(){
        System.out.println("Enter course you would like to review: ");
        String courseString = scanner.nextLine();
        String[] splitString = courseString.split("\\s+");
        String subject = splitString[0];
        int catalogNum = Integer.parseInt(splitString[1]);

        //check subject is 4 or fewer capital letters and catalogNum is 4 digits
        if(!isUpperCase(subject) || countDigits(catalogNum) != 4 || subject.length() > 4){
            //return to main menu
            System.out.println("Invalid course. Try again.");
            mainMenu();
        }
        else {
            Course course = new Course(subject, catalogNum);
            //check if user wrote review for course
            if (db.checkReviewAlreadyExists(course)) {
                System.out.println("Review for this course already exists from this user. Try again.");
                mainMenu();
            }
            else {
                System.out.println("Enter review for course: ");
                String message = scanner.nextLine();
                int rating = -1;
                while(rating == -1){
                    rating = promptRating();
                }
                db.submitReview(course, message, rating);
            }
        }
    }

    private int promptRating(){
        System.out.println("Enter course rating: ");
        String ratingString = scanner.nextLine();
        if(!ratingString.matches("-?\\d+(\\.\\d+)?")){
            System.out.println("Invalid rating. Try again.");
            return -1;
        }
        else{
            int rating = Integer.parseInt(ratingString);
            if(rating < 1 || rating > 5){
                System.out.println("Invalid rating. Try again.");
                return -1;
            }
            else{
                return rating;
            }
        }
    }
    public int countDigits(int n){
        int count = 0;
        while(n != 0) {
            // removing the last digit of the number n
            n = n / 10;
            // increasing count by 1
            count = count + 1;
        }
        return count;
    }

    private static boolean isUpperCase(String str){
        //convert String to char array
        char[] charArray = str.toCharArray();
        for(int i=0; i < charArray.length; i++){
            //if any character is not in upper case, return false
            if( !Character.isUpperCase( charArray[i] ))
                return false;
        }
        return true;
    }


}
