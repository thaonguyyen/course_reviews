package org.example;
import java.sql.*;

public class DatabaseManager {
    Connection connection;

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            String path = "jdbc:sqlite:Reviews.sqlite3";
            connection = DriverManager.getConnection(path);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createTables(){
        try {
            //how to make student name unique
            String studentTableCreate = "CREATE TABLE Students (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Name VARCHAR(32) NOT NULL, Password VARCHAR(32) NOT NULL)";
            String coursesTableCreate = "CREATE TABLE Courses (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Department VARCHAR(5) NOT NULL, CatalogNumber INTEGER NOT NULL)";
            String reviewsTableCreate = "CREATE TABLE Reviews (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, StudentID INTEGER NOT NULL, CourseID INTEGER NOT NULL, Review VARCHAR(200) NOT NULL, Rating INTEGER NOT NULL" +
                    "FOREIGN KEY(StudentID) REFERENCES Student(ID) ON DELETE CASCADE, FOREIGN KEY(CourseID) REFERENCES Course(ID) ON DELETE CASCADE)";
            Statement studentTableStatement = connection.createStatement();
            Statement coursesTableStatement = connection.createStatement();
            Statement reviewsTableStatement = connection.createStatement();

            studentTableStatement.close();
            coursesTableStatement.close();
            reviewsTableStatement.close();
        }
        catch(SQLException e){
            throw new IllegalStateException(e);
        }
    }

    public void clear() {
        checkNotConnected();
        try{
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet rsStudents = dbm.getTables(null, null, "Students", null);
            ResultSet rsReviews = dbm.getTables(null, null, "Reviews", null);
            ResultSet rsCourse = dbm.getTables(null, null, "Courses", null);

            Statement statement = connection.createStatement();
            boolean doesExist = false;
            if (rsStudents.next()){
                String sqlStops =  "DELETE FROM Students;";
                statement.execute(sqlStops);
            } else doesExist = true;

            if (rsReviews.next()){
                String sqlBusLines =  "DELETE FROM Reviews;";
                statement.execute(sqlBusLines);
            } else doesExist = true;

            if (rsCourse.next()){
                String sqlRoutes =  "DELETE FROM Courses;";
                statement.execute(sqlRoutes);
            } else doesExist = true;
            if (doesExist) throw new IllegalStateException("At least one table did not exist");
            statement.close();
        }
        catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void deleteTables() {
        checkNotConnected();
        try{
            Statement statement = connection.createStatement();
            try {
                String sqlStudents = "DROP TABLE Students";
                statement.execute(sqlStudents);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println("ERROR: Error deleting Students table. Make sure you have created it first!");
                throw new IllegalStateException(e);
            }
            try {
                String sqlCourses = "DROP TABLE Courses";
                statement.execute(sqlCourses);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println("Error: Error deleting the Courses table. Check to make sure you have created it first!");
                throw new IllegalStateException(e);}
            try {
                String sqlReviews = "DROP TABLE Reviews";
                statement.execute(sqlReviews);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println("Error: Error deleting the Reviews table. Check to make sure you have created it first!");
                throw new IllegalStateException(e);}
            statement.close();}
        catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    //checks if connection is null or closed and throws error
    private void checkNotConnected(){
        try{
            if (connection == null || connection.isClosed()) throw new IllegalStateException("Connection is not open");
        } catch (SQLException e){
            throw new IllegalStateException(e);
        }
    }

    //checks if table is already made if not throw an error
    private void checkTableExists(String tableName) {
        try{
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet resultSet = dbm.getTables(null, null, tableName, null);
            if (!resultSet.next()) throw new IllegalStateException(String.format("%s table does not exist", tableName));
        } catch (SQLException e){
            throw new IllegalStateException(e);
        }
    }

    public void disconnect() {
        try {
            checkNotConnected();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
