package com.g1.top3_g1.Presenter;

import android.content.Context;

import com.g1.top3_g1.DB.DB;
import com.g1.top3_g1.Model.Employee;
import com.g1.top3_g1.View.EmployeeView;

import java.util.Collections;
import java.util.List;

public class PreImplement implements EmployeePresenter {
    private EmployeeView view;
    private DB DB;

    public PreImplement(EmployeeView view, Context context) {
        this.view = view;
        this.DB = new DB(context);
    }

    @Override
    public void getAllEmployees() {
        List<Employee> employees = DB.getAllEmployees();
        view.showEmployees(employees);
    }

    @Override
    public void searchEmployees(String fullName, String hireDate, double salary) {
        List<Employee> employees = DB.searchEmployees(fullName, hireDate, salary);
        view.showEmployees(employees);
    }

    @Override
    public void addEmployee(Employee employee) {
        DB.addEmployee(employee);
        view.employeeAdded();
        view.showEmployees(Collections.singletonList(employee));
    }

    @Override
    public void updateEmployee(Employee employee) {
        DB.updateEmployee(employee);
        view.employeeUpdated();
    }

    @Override
    public void deleteEmployee(Employee employee) {
        DB.deleteEmployee(employee);
        view.employeeDeleted();
    }
}

