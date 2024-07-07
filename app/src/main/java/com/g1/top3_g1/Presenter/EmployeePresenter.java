package com.g1.top3_g1.Presenter;

import com.g1.top3_g1.Model.Employee;

public interface EmployeePresenter {
    void getAllEmployees();
    void searchEmployees(String fullName, String hireDate, double salary);
    void addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployee(Employee employee);
}


