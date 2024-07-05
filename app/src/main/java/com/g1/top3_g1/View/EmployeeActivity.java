package com.g1.top3_g1.View;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.g1.top3_g1.Model.Employee;
import com.g1.top3_g1.Presenter.EmployeePresenter;
import com.g1.top3_g1.Presenter.EmployeePresenterImpl;
import com.g1.top3_g1.R;

import java.util.ArrayList;
import java.util.List;

// MainActivity.java
public class EmployeeActivity extends AppCompatActivity implements EmployeeView {

    private EditText fullNameEditText, hireDateEditText, salaryEditText;
    private Button addButton, searchButton;
    private RecyclerView recyclerView;
    private EmployeeAdapter adapter;
    private EmployeePresenter presenter;
    private List<Employee> employeeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        fullNameEditText = findViewById(R.id.edit_text_full_name);
        hireDateEditText = findViewById(R.id.edit_text_hire_date);
        salaryEditText = findViewById(R.id.edit_text_salary);
        addButton = findViewById(R.id.button_add);
        searchButton = findViewById(R.id.button_search);
        recyclerView = findViewById(R.id.recycler_view);

        presenter = new EmployeePresenterImpl(this, this); // Initialize presenter

        adapter = new EmployeeAdapter(employeeList, this, presenter); // Pass presenter to adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        addButton.setOnClickListener(v -> {
            String fullName = fullNameEditText.getText().toString();
            String hireDate = hireDateEditText.getText().toString();
            double salary = Double.parseDouble(salaryEditText.getText().toString());

            Employee employee = new Employee(0, fullName, hireDate, salary);
            presenter.addEmployee(employee);
        });

        searchButton.setOnClickListener(v -> {
            String fullName = fullNameEditText.getText().toString();
            String hireDate = hireDateEditText.getText().toString();
            double salary = salaryEditText.getText().toString().isEmpty() ? 0.0 :
                    Double.parseDouble(salaryEditText.getText().toString());

            presenter.searchEmployees(fullName, hireDate, salary);
        });

        //presenter.getAllEmployees(); // Fetch initial data
    }

    @Override
    public void showEmployees(List<Employee> employees) {
        employeeList.clear();
        employeeList.addAll(employees);
        adapter.updateData(employees);
    }

    @Override
    public void employeeAdded() {
        Toast.makeText(this, "Employee added successfully", Toast.LENGTH_SHORT).show();
        presenter.getAllEmployees(); // Refresh data after adding
    }

    @Override
    public void employeeUpdated() {
        Toast.makeText(this, "Employee updated successfully", Toast.LENGTH_SHORT).show();
        presenter.getAllEmployees(); // Refresh data after updating
    }

    @Override
    public void employeeDeleted() {
        Toast.makeText(this, "Employee deleted successfully", Toast.LENGTH_SHORT).show();
        presenter.getAllEmployees(); // Refresh data after deleting
    }
}



