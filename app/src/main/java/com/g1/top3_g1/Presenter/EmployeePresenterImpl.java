package com.g1.top3_g1.Presenter;

import android.content.Context;

import com.g1.top3_g1.DB.DbHelper;
import com.g1.top3_g1.Model.Employee;
import com.g1.top3_g1.View.EmployeeView;

import java.util.List;

public class EmployeePresenterImpl implements EmployeePresenter {
    private EmployeeView view;
    private DbHelper dbHelper;

    public EmployeePresenterImpl(EmployeeView view, Context context) {
        this.view = view;
        this.dbHelper = new DbHelper(context);
    }

    @Override
    public void getAllEmployees() {
        List<Employee> employees = dbHelper.getAllEmployees();
        view.showEmployees(employees);
    }

    @Override
    public void searchEmployees(String fullName, String hireDate, double salary) {
        List<Employee> employees = dbHelper.searchEmployees(fullName, hireDate, salary);
        view.showEmployees(employees);
    }

    @Override
    public void addEmployee(Employee employee) {
        dbHelper.addEmployee(employee);
        view.employeeAdded();
    }

    @Override
    public void updateEmployee(Employee employee) {
        dbHelper.updateEmployee(employee);
        view.employeeUpdated();
    }

    @Override
    public void deleteEmployee(Employee employee) {
        dbHelper.deleteEmployee(employee);
        view.employeeDeleted();
    }
}

