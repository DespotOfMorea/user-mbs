package org.vnuk.usermbs.ui.view.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import org.vnuk.usermbs.R;
import org.vnuk.usermbs.viewmodel.EmployeeViewModel;
import org.vnuk.usermbs.viewmodel.WarehouseViewModel;

import static org.vnuk.usermbs.data.room.WarehouseDB.BAD_INSERT;

public class CreateEmployeeActivity extends AppCompatActivity {
    private static final String TAG = CreateEmployeeActivity.class.getSimpleName();

    private EmployeeViewModel employeeViewModel;
    private WarehouseViewModel warehouseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "OnCreate started.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_employee);

        setupViewModels();
        Log.i(TAG, "OnCreate finished.");
    }

    private void setupViewModels() {
        Log.v(TAG, "Setting up ViewModels.");
        employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);
        warehouseViewModel = new ViewModelProvider(this).get(WarehouseViewModel.class);

        employeeViewModel.getMldEmployeeID().observe(this, employeeID -> {
            if (!employeeID.equals(BAD_INSERT))
                employeeViewModel.fetchEmployees();
        });

        employeeViewModel.getMldEmployees().observe(this, employees -> {
            if (employees != null && employees.size() > 0)
                warehouseViewModel.employees.setValue(employees);
        });

        employeeViewModel.fetchEmployees();
    }
}