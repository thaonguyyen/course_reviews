package org.example;

public class Student {
    private int id;
    private String name;
    private String password;

    public Student(int studentID, String studentName, String studentPassword){
        this.id = studentID;
        this.name = studentName;
        this.password = studentPassword;
    }

    public int getStudentID(){
        return id;
    }

    public String getStudentName(){
        return name;
    }

    public String getStudentPassword(){
        return password;
    }

    public void setStudentID(int newID){
        this.id = newID;
    }

    public void setStudentName(String newName){
        this.name = newName;
    }

    public void setStudentPassword(String newPassword){
        this.password = newPassword;
    }
}
