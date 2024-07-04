package com.g1.top3_g1.View;
import android.view.View;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.g1.top3_g1.Model.Employee;
import com.g1.top3_g1.R;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {
    private List<Employee> employeeList;
    private Context context;

    public EmployeeAdapter(List<Employee> employeeList, Context context) {
        this.employeeList = employeeList;
        this.context = context;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = employeeList.get(position);
        holder.fullNameTextView.setText(employee.getFullName());
        holder.hireDateTextView.setText(employee.getHireDate());
        holder.salaryTextView.setText(String.valueOf(employee.getSalary()));

        holder.editButton.setOnClickListener(v -> {
            // Handle edit functionality
        });

        holder.deleteButton.setOnClickListener(v -> {
            // Handle delete functionality
        });
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {
        TextView fullNameTextView, hireDateTextView, salaryTextView;
        Button editButton, deleteButton;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            fullNameTextView = itemView.findViewById(R.id.text_view_full_name);
            hireDateTextView = itemView.findViewById(R.id.text_view_hire_date);
            salaryTextView = itemView.findViewById(R.id.text_view_salary);
            editButton = itemView.findViewById(R.id.button_edit);
            deleteButton = itemView.findViewById(R.id.button_delete);
        }
    }
}

