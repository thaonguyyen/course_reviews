package org.example;

import java.util.Scanner;

import static java.lang.Character.isUpperCase;

public class CommandLineUI {
    private Scanner scanner;

    public static void main(String[] args) {
        CommandLineUI ui = new CommandLineUI();
        ui.initializeScanner();
        ui.login();
        ui.mainMenu();
        ui.logout();
    }
    private void initializeScanner() {
        scanner = new Scanner(System.in);
    }

    private void login(){
        System.out.println("Login: ");
        String login = scanner.nextLine();
        System.out.println("Enter password: ");
        String pass1 = scanner.nextLine();
        System.out.println("Confirm password: ");
        String pass2 = scanner.nextLine();
        if(pass1.equals(pass2)){
            //if passwords match then add to Students table and log in
        }
        else{
            //do not add anything to students table and go back to login()
            System.out.println("Invalid password. Try again.");
            login();
        }
    }

    private void logout(){
        System.out.println("You are now logged out.");
        login();
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
        else{
            logout();
        }
    }

    private void seeReviews(){
        System.out.println("Enter course you would like to see reviews for: ");
        String course = scanner.nextLine();
        String[] splitString = course.split("\\s+");
        String subject = splitString[0];
        int catalogNum = Integer.parseInt(splitString[1]);

        //check subject is 4 or fewer capital letters and catalogNum is 4 digits
        if(!isUpperCase(subject) || countDigits(catalogNum) != 4){ //ADD CONDITIONAL IF COURSE ALREADY EXISTS
            //return to main menu
            System.out.println("Invalid course. Try again.");
            mainMenu();
        }
        else{
            //if course has no reviews

            //else if course has reviews

        }
    }

    private void submitReview(){
        System.out.println("Enter course you would like to review: ");
        String course = scanner.nextLine();
        String[] splitString = course.split("\\s+");
        String subject = splitString[0];
        int catalogNum = Integer.parseInt(splitString[1]);

        //check subject is 4 or fewer capital letters and catalogNum is 4 digits
        if(!isUpperCase(subject) || countDigits(catalogNum) != 4){ //ADD CONDITIONAL IF COURSE ALREADY EXISTS
            //return to main menu
            System.out.println("Invalid course. Try again.");
            mainMenu();
        }
        else{
            //add course to table

            //prompt for review
            System.out.println("Write review for course: ");
            String message = scanner.nextLine();

            //prompt for rating
            promptRating();
        }
    }

    private void promptRating(){
        System.out.println("Course rating: ");
        String ratingString = scanner.nextLine();
        int rating = Integer.parseInt(ratingString);
        if(rating < 1 || rating > 5){
            System.out.println("Invalid rating. Try again.");
            promptRating();
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
