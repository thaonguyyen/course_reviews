package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            throw new RuntimeException("Database does not exist");
        }
    }
    public void createTables(){
        try {
            //how to make student name unique
            String studentTableCreate = "CREATE TABLE Students (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, StudentName VARCHAR(32) NOT NULL, Password VARCHAR(32) NOT NULL)";
            String coursesTableCreate = "CREATE TABLE Courses (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Department VARCHAR(5) NOT NULL, CatalogNumber INTEGER NOT NULL)";
            String reviewsTableCreate = "CREATE TABLE Reviews (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, StudentID INTEGER NOT NULL, CourseID INTEGER NOT NULL, Review VARCHAR(200) NOT NULL, Rating INTEGER NOT NULL," +
                    "FOREIGN KEY(StudentID) REFERENCES Students(ID) ON DELETE CASCADE, FOREIGN KEY(CourseID) REFERENCES Courses(ID) ON DELETE CASCADE)";
            Statement studentTableStatement = connection.createStatement();
            Statement coursesTableStatement = connection.createStatement();
            Statement reviewsTableStatement = connection.createStatement();

            studentTableStatement.executeUpdate(studentTableCreate);
            coursesTableStatement.executeUpdate(coursesTableCreate);
            reviewsTableStatement.executeUpdate(reviewsTableCreate);

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
                String sqlStudents =  "DELETE FROM Students;";
                statement.execute(sqlStudents);
            } else doesExist = true;

            if (rsReviews.next()){
                String sqlReviews =  "DELETE FROM Reviews;";
                statement.execute(sqlReviews);
            } else doesExist = true;

            if (rsCourse.next()){
                String sqlCourses =  "DELETE FROM Courses;";
                statement.execute(sqlCourses);
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

    public void addStudent(Student s){
        try {
            String username = s.getStudentName();
            String password = s.getStudentPassword();
            String addStudent = String.format("INSERT INTO Students (ID, Student" +
                    "Name, Password) values(NULL, \"%s\", \"%s\")", username, password);
            Statement addStudentStatement = connection.createStatement();

            addStudentStatement.executeUpdate(addStudent);
            addStudentStatement.close();
        }
        catch(SQLException e){
            throw new IllegalStateException(e);
        }
    }
    public void addCourse(String department, int catNumber){
        try {
            String addCourse = String.format("INSERT INTO Courses (ID, Department, CatalogNumber) values(NULL, \"%s\", %d)", department, catNumber);
            Statement addCourseStatement = connection.createStatement();

            addCourseStatement.executeUpdate(addCourse);
            addCourseStatement.close();
        }
        catch(SQLException e){
            throw new IllegalStateException(e);
        }
    }
    public void addReview(String studentName, String department, int catNumber, String review, int rating){
        try {
            int studentID = getStudentID(studentName);
            int courseID = getCourseID(department, catNumber);
            String addReview = String.format("INSERT INTO Reviews (ID, StudentID, CourseID, Review, Rating) values(NULL, %d, %d, \"%s\", %d)", studentID, courseID, review, rating);
            Statement addReviewStatement = connection.createStatement();

            addReviewStatement.executeUpdate(addReview);
            addReviewStatement.close();
        }
        catch(SQLException e){
            throw new IllegalStateException(e);
        }
    }
    public int getCourseID(String department, int catNumber){
        try{
            String queryString = String.format("SELECT ID FROM Courses WHERE Department = \'%s\' AND CatalogNumber = %d", department, catNumber);
            Statement courseStatement = connection.createStatement();
            ResultSet rs = courseStatement.executeQuery(queryString);
            if(rs.isClosed()){
                return -1;
            }
            int courseID = rs.getInt("ID");
            return courseID;
        }
        catch(SQLException e){
            throw new IllegalStateException(e);
        }
    }
    public int getStudentID(String name){
        try{
            String queryString = String.format("SELECT * FROM Students WHERE StudentName = \'%s\'", name);
            //String queryString = "SELECT * FROM Students WHERE StudentName="+name;
            Statement studentStatement = connection.createStatement();
            ResultSet rs = studentStatement.executeQuery(queryString);
            if(rs.isClosed()){
                return -1;
            }
            int studentID = rs.getInt("ID");
            return studentID;
        }
        catch(SQLException e){
            throw new IllegalStateException(e);
        }
    }
    public String getPassword(String studentName){
        try {
            int studentID = getStudentID(studentName);
            String queryString = "SELECT * FROM Students WHERE ID = "+studentID;
            Statement passwordStatement = connection.createStatement();
            ResultSet rs = passwordStatement.executeQuery(queryString);
            return rs.getString("Password");
        }
        catch(SQLException e){
            throw new IllegalStateException(e);
        }
    }
    public Review getReview(ResultSet rs) throws SQLException{
        int studentID = rs.getInt("StudentID");
        String username = getStudentNameByReviewID(studentID);
        String password = getStudentPassByReviewID(studentID);
        Student student = new Student(username, password);
        int courseID = rs.getInt("CourseID");
        String department = getCourseDepartmentByReviewID(courseID);
        int number = getCourseNumberByReviewID(courseID);
        Course course = new Course(department, number);
        String review = rs.getString("Review");
        int rating = rs.getInt("Rating");
        return new Review(student, course, review, rating);
    }

    public String getCourseDepartmentByReviewID(int id){
        try {
            String queryString = "SELECT * FROM Courses WHERE ID = "+id;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(queryString);
            String department = rs.getString("Department");
            return department;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getCourseNumberByReviewID(int id){
        try {
            String queryString = "SELECT * FROM Courses WHERE ID = "+id;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(queryString);
            int number = rs.getInt("CatalogNumber");
            return number;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getStudentNameByReviewID(int id){
        try {
            String queryString = "SELECT * FROM Students WHERE ID = "+id;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(queryString);
            String user = rs.getString("StudentName");
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getStudentPassByReviewID(int id){
        try {
            String queryString = "SELECT * FROM Students WHERE ID = "+id;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(queryString);
            String pass = rs.getString("Password");
            return pass;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Review getReviewByStudentAndCourse(Student s, Course c){
        try {
            String courseDep = c.getCourseDepartment();
            int courseCatNum = c.getCourseCatalogNumber();
            String studentName = s.getStudentName();
            int studentID = getStudentID(studentName);
            int courseID = getCourseID(courseDep, courseCatNum);
            String queryString = "SELECT * FROM Reviews WHERE StudentID = " + studentID + " AND CourseID = " + courseID;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(queryString);
            if(rs.isClosed()){
                return null;
            }
            return getReview(rs);
        }catch(SQLException e){
            throw new IllegalStateException(e);
        }
    }

    public List<Review> getAllReviews(String department, int catNumber){
        try{
            List<Review>  returnList = new ArrayList<Review>();
            int courseID = getCourseID(department, catNumber);
            String queryString = "SELECT * FROM Reviews WHERE CourseID = "+courseID;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(queryString);
            while(rs.next()){
                returnList.add(getReview(rs));
            }
            statement.close();
            return returnList;
        }
        catch(SQLException e){
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

    public static void main(String[] args) throws SQLException {
        DatabaseManager db = new DatabaseManager();
        db.connect();
//        db.deleteTables();
//        db.createTables();
        db.clear();
//        String user1 = "ews9rk";
//        String pass1 = "qwerty";
//        String user2 = "vdk4dy";
//        String pass2 = "password";
//        Student one = new Student(user1, pass1);
//        Student two = new Student(user2, pass2);
//        db.addStudent(one);
//        db.addStudent(two);
//        db.addCourse("CS", 6969);
//        db.addCourse("CS", 1234);
        //db.clear();
        db.disconnect();
    }
}
