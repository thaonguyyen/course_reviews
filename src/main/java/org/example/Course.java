package org.example;

public class Course {
    private String department;
    private int catalogNumber;


    public Course(String courseDepartment, int courseNumber){
        this.department = courseDepartment;
        this.catalogNumber = courseNumber;
    }

    public String getCourseDepartment(){
        return department;
    }

    public int getCourseCatalogNumber(){
        return catalogNumber;
    }


    public void setCourseDepartment(String newDepartment){
        this.department = newDepartment;
    }

    public void setCourseCatalogNumber(int newCatalog) {
        this.catalogNumber = newCatalog;
    }
}
