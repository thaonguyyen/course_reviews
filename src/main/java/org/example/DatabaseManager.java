package org.example;
import java.sql.*;

public class DatabaseManager {
    Connection connection;

    public void connect(){

    }

    public void createTables(){
        //how to make student name unique
        String studentTableCreate = "CREATE TABLE Students (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Name VARCHAR(32) NOT NULL, Password VARCHAR(32) NOT NULL)";
        String coursesTableCreate = "CREATE TABLE Courses (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Department VARCHAR(5) NOT NULL, CatalogNumber INTEGER NOT NULL)";
        String reviewsTableCreate = "CREATE TABLE Reviews (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, StudentID INTEGER NOT NULL, CourseID INTEGER NOT NULL, Review VARCHAR(200) NOT NULL, Rating INTEGER NOT NULL" +
                                    "FOREIGN KEY(StudentID) REFERENCES Student(ID) ON DELETE CASCADE, FOREIGN KEY(CourseID) REFERENCES Course(ID) ON DELETE CASCADE)";
    }
}
