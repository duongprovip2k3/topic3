package com.g1.top3_g1.View;

import android.view.View;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.g1.top3_g1.Model.Employee;
import com.g1.top3_g1.Presenter.EmployeePresenter;
import com.g1.top3_g1.R;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {
    private List<Employee> employeeList;
    private Context context;
    private EmployeePresenter presenter;

    public EmployeeAdapter(List<Employee> employeeList, Context context, EmployeePresenter presenter) {
        this.employeeList = employeeList;
        this.context = context;
        this.presenter = presenter;
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
        holder.idTextView.setText(String.valueOf(employee.getId()));
        holder.edtFullName.setText(employee.getFullName());
        holder.edtHireDate.setText(employee.getHireDate());
        holder.edtSalary.setText(String.format("%.0f", employee.getSalary()));

        holder.edtBtn.setOnClickListener(v -> {
            boolean canEdt = holder.edtFullName.isEnabled();
            holder.edtFullName.setEnabled(!canEdt);
            holder.edtHireDate.setEnabled(!canEdt);
            holder.edtSalary.setEnabled(!canEdt);

            if(canEdt){
            employee.setFullName(holder.edtFullName.getText().toString());
            employee.setHireDate(holder.edtHireDate.getText().toString());
            employee.setSalary(Double.parseDouble(holder.edtSalary.getText().toString()));
            presenter.updateEmployee(employee);
            }

        });

        holder.delBtn.setOnClickListener(v -> {
            presenter.deleteEmployee(employee);

        });
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public void updateData(List<Employee> employees) {
        employeeList.clear();
        employeeList.addAll(employees);
        notifyDataSetChanged();
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {
        EditText edtFullName, edtHireDate, edtSalary;
        TextView idTextView;
        Button edtBtn, delBtn;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.text_view_id);
            edtFullName = itemView.findViewById(R.id.edit_text_full_name);
            edtHireDate = itemView.findViewById(R.id.edit_text_hire_date);
            edtSalary = itemView.findViewById(R.id.edit_text_salary);
            edtBtn = itemView.findViewById(R.id.button_edit);
            delBtn = itemView.findViewById(R.id.button_delete);
        }
    }
}

