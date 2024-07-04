package com.g1.top3_g1.Presenter;

import com.g1.top3_g1.DB.DbHelper;
import com.g1.top3_g1.Model.Employee;

import java.util.List;

public class EmployeeInteractor {
    private DbHelper dbHelper;

    public EmployeeInteractor(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public List<Employee> getAllEmployees() {
        return dbHelper.getAllEmployees();
    }

    public List<Employee> searchEmployees(String fullNameQuery, String hireDateQuery, double salaryQuery) {
        return dbHelper.searchEmployees(fullNameQuery, hireDateQuery, salaryQuery);
    }

    // Implement other CRUD operations
}
