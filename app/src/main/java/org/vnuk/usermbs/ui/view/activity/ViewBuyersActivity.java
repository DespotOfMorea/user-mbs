package org.vnuk.usermbs.ui.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.vnuk.usermbs.data.room.entity.Employee;
import org.vnuk.usermbs.databinding.ActivityViewBuyersBinding;
import org.vnuk.usermbs.ui.adapter.BuyerRecyclerViewAdapter;
import org.vnuk.usermbs.viewmodel.EmployeeViewModel;

public class ViewBuyersActivity extends AppCompatActivity {
    private static final String TAG = ViewBuyersActivity.class.getSimpleName();

    private ActivityViewBuyersBinding binding;
    private EmployeeViewModel employeeViewModel;
    private BuyerRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "OnCreate started.");
        super.onCreate(savedInstanceState);
        binding = ActivityViewBuyersBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setupRecycleView();
        setupViewModel();
        setupEmployeeSpinner();
        Log.i(TAG, "OnCreate finished.");
    }

    private void setupRecycleView() {
        Log.v(TAG, "Setting up RecycleView.");
        binding.rvBuyers.setLayoutManager(new LinearLayoutManager(this));
        binding.rvBuyers.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new BuyerRecyclerViewAdapter();
        binding.rvBuyers.setAdapter(adapter);
    }

    private void setupViewModel() {
        Log.v(TAG, "Setting up ViewModel.");
        employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);
        binding.setEmployeeViewModel(employeeViewModel);
        binding.setLifecycleOwner(this);

        employeeViewModel.getMldEmployeeWithBuyers().observe(this, employeeWithBuyers -> {
            if (employeeWithBuyers != null && employeeWithBuyers.size() > 0)
                adapter.setBuyers(employeeWithBuyers.get(0).buyersList);
        });

        employeeViewModel.fetchEmployees();
    }

    private void setupEmployeeSpinner() {
        Log.v(TAG, "Setting up Spinner.");
        binding.spinnerEmployees.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Employee employee = (Employee) parent.getSelectedItem();
                employeeViewModel.fetchBuyers(employee.getEmployeeID());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}