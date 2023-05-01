package org.example;

public class Course {
    private int id;
    private String department;
    private int catalogNumber;

    public Course(int courseID, String courseDepartment, int courseNumber){
        this.id = courseID;
        this.department = courseDepartment;
        this.catalogNumber = courseNumber;
    }

    public int getCourseID(){
        return id;
    }

    public String getCourseDepartment(){
        return department;
    }

    public int getCourseCatalogNumber(){
        return catalogNumber;
    }

    public void setCourseID(int newID){
        this.id = newID;
    }

    public void setCourseDepartment(String newDepartment){
        this.department = newDepartment;
    }

    public void setCourseCatalogNumber(int newCatalog) {
        this.catalogNumber = newCatalog;
    }
}
