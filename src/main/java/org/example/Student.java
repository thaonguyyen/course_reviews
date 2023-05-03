package org.example;

public class Student {
    private String name;
    private String password;

    public Student(String studentName, String studentPassword){
        this.name = studentName;
        this.password = studentPassword;
    }

    public String getStudentName(){
        return name;
    }

    public String getStudentPassword(){
        return password;
    }

    public void setStudentName(String newName){
        this.name = newName;
    }

    public void setStudentPassword(String newPassword){
        this.password = newPassword;
    }
}
