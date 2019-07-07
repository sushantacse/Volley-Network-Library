package com.example.onlineapp;

public class Student {

    private String id;
    private String studentid;
    private String name;
    private String email;
    private String phoneno;

    public Student(String id, String studentid, String name, String email, String phoneno) {
        this.id = id;
        this.studentid = studentid;
        this.name = name;
        this.email = email;
        this.phoneno = phoneno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
}
