package com.g1.top3_g1.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import com.g1.top3_g1.Model.Employee;

import java.util.ArrayList;
import java.util.List;

public class DB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "EmployeeDatabase";
    private static final int DATABASE_VERSION = 1;

    // Table name and columns
    private static final String TABLE_EMPLOYEE = "employees";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FULL_NAME = "full_name";
    private static final String COLUMN_HIRE_DATE = "hire_date";
    private static final String COLUMN_SALARY = "salary";

    public DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //khoi dong lan dau` se tao table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EMPLOYEE_TABLE = "CREATE TABLE " + TABLE_EMPLOYEE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FULL_NAME + " TEXT,"
                + COLUMN_HIRE_DATE + " TEXT,"
                + COLUMN_SALARY + " REAL" + ")";
        db.execSQL(CREATE_EMPLOYEE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
        onCreate(db);
    }


    // them employee
    public void addEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_FULL_NAME, employee.getFullName());
        values.put(COLUMN_HIRE_DATE, employee.getHireDate());
        values.put(COLUMN_SALARY, employee.getSalary());

        db.insert(TABLE_EMPLOYEE, null, values);
        db.close();
    }


    // lay tat employees
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_EMPLOYEE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Employee employee = new Employee();
                employee.setId(Integer.parseInt(cursor.getString(0)));
                employee.setFullName(cursor.getString(1));
                employee.setHireDate(cursor.getString(2));
                employee.setSalary(cursor.getDouble(3));

                employeeList.add(employee);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return employeeList;
    }

    public int updateEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_FULL_NAME, employee.getFullName());
        values.put(COLUMN_HIRE_DATE, employee.getHireDate());
        values.put(COLUMN_SALARY, employee.getSalary());

        return db.update(TABLE_EMPLOYEE, values, COLUMN_ID + " = ?",
                new String[] { String.valueOf(employee.getId()) });
    }

    public void deleteEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EMPLOYEE, COLUMN_ID + " = ?",
                new String[] { String.valueOf(employee.getId()) });
        db.close();
    }

    public List<Employee> searchEmployees(String fullName, String hireDate, double salary) {
        List<Employee> employeeList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_EMPLOYEE + " WHERE ";
        List<String> conditions = new ArrayList<>();
        if (!fullName.isEmpty()) {
            conditions.add(COLUMN_FULL_NAME + " LIKE '%" + fullName + "%'");
        }
        if (!hireDate.isEmpty()) {
            conditions.add(COLUMN_HIRE_DATE + "='" + hireDate + "'");
        }
        if(salary != 0.0) {
            conditions.add(COLUMN_SALARY + "=" + salary);
        }

        selectQuery += TextUtils.join(" AND ", conditions);


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Employee employee = new Employee();
                employee.setId(Integer.parseInt(cursor.getString(0)));
                employee.setFullName(cursor.getString(1));
                employee.setHireDate(cursor.getString(2));
                employee.setSalary(cursor.getDouble(3));

                employeeList.add(employee);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return employeeList;
    }
}
