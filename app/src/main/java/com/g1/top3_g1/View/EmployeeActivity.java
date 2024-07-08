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
import com.g1.top3_g1.Presenter.Implement;
import com.g1.top3_g1.R;

import java.util.ArrayList;
import java.util.List;

public class EmployeeActivity extends AppCompatActivity implements EmployeeView {
    private EditText edtFullName, edtHireDate, edtSalary;
    private Button addBtn, searchBtn;
    private RecyclerView recyclerView;
    private EmployeeAdapter adapter;
    private EmployeePresenter presenter;
    private List<Employee> employeeList = new ArrayList<>();

    private void bindingView() {
        edtFullName = findViewById(R.id.edit_text_full_name);
        edtHireDate = findViewById(R.id.edit_text_hire_date);
        edtSalary = findViewById(R.id.edit_text_salary);
        addBtn = findViewById(R.id.button_add);
        searchBtn = findViewById(R.id.button_search);
        recyclerView = findViewById(R.id.recycler_view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        bindingView();

        presenter = new Implement(this, this);

        adapter = new EmployeeAdapter(employeeList, this, presenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        addBtn.setOnClickListener(v -> {
            String fullName = edtFullName.getText().toString();
            String hireDate = edtHireDate.getText().toString();
            String salaryStr = edtSalary.getText().toString();
            double salary = 0;

            if (fullName.isEmpty()) {
                edtFullName.setError("Vui long nhap fullname!");
            }
            if (hireDate.isEmpty()) {
                edtHireDate.setError("Vui long nhap hire date!");
            }
            try {
                salary = Double.parseDouble(salaryStr);
            } catch (NumberFormatException e) {
                edtSalary.setError("Vui long nhap salary hop le!");
            }
            if(!fullName.isEmpty() && !hireDate.isEmpty() && !salaryStr.isEmpty()){
                Employee employee = new Employee(0, fullName, hireDate, salary);
                presenter.addEmployee(employee);
            }else{
                failedCRUD();
            }


        });

        searchBtn.setOnClickListener(v -> {
            String fullName = edtFullName.getText().toString();
            String hireDate = edtHireDate.getText().toString();
            double salary = edtSalary.getText().toString().isEmpty() ? 0.0 :
                    Double.parseDouble(edtSalary.getText().toString());
            if (fullName.isEmpty() && hireDate.isEmpty() && salary == 0.0) {
                presenter.getAllEmployees();
            } else {
                presenter.searchEmployees(fullName, hireDate, salary);
            }

        });

    }

    @Override
    public void showEmployees(List<Employee> employees) {
        employeeList.clear();
        employeeList.addAll(employees);
        adapter.updateData(employees);
    }

    @Override
    public void employeeAdded() {
        Toast.makeText(this, "Them employee thanh cong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void employeeUpdated() {
        Toast.makeText(this, "Sua employee thanh cong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void employeeDeleted() {
        Toast.makeText(this, "Xoa employee thanh cong", Toast.LENGTH_SHORT).show();
        presenter.getAllEmployees();
    }

    @Override
    public void failedCRUD() {
        Toast.makeText(this, "That bai", Toast.LENGTH_SHORT).show();
    }
}



