package com.g1.top3_g1.View;

import com.g1.top3_g1.Model.Employee;

import java.util.List;

public interface EmployeeView {
    void showEmployees(List<Employee> employees);

    void employeeAdded();
    void employeeUpdated();
    void employeeDeleted();

    void failedCRUD();
}

