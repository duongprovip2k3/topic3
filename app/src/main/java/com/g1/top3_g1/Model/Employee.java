package com.g1.top3_g1.Model;

public class Employee {
    private int id;
    private String fullName;
    private String hireDate;
    private double salary;

    public Employee(int id, String fullName, String hireDate, double salary) {
        this.id = id;
        this.fullName = fullName;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    public Employee() {

    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

