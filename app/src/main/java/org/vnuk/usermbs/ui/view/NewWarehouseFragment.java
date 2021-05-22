package org.vnuk.usermbs.ui.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.vnuk.usermbs.R;
import org.vnuk.usermbs.data.room.entity.Employee;
import org.vnuk.usermbs.databinding.FragmentNewWarehouseBinding;
import org.vnuk.usermbs.util.Helper;
import org.vnuk.usermbs.viewmodel.WarehouseViewModel;

import static org.vnuk.usermbs.data.room.WarehouseDB.BAD_INSERT;

public class NewWarehouseFragment extends Fragment {
    private static final String TAG = NewWarehouseFragment.class.getSimpleName();

    private FragmentNewWarehouseBinding binding;
    private WarehouseViewModel warehouseViewModel;

    public NewWarehouseFragment() {
        super(R.layout.fragment_new_warehouse);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "OnCreate.");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.v(TAG, "OnCreateView.");
        binding = FragmentNewWarehouseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        Log.v(TAG, "OnViewCreated.");
        super.onViewCreated(view, savedInstanceState);
        setupViewModel();
        setupEmployeeSpinner();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupViewModel() {
        Log.v(TAG, "Setting up ViewModel.");
        warehouseViewModel = new ViewModelProvider(requireActivity()).get(WarehouseViewModel.class);
        binding.setWarehouseViewModel(warehouseViewModel);
        binding.setLifecycleOwner(this);

        warehouseViewModel.getMldWarehouseID().observe(getViewLifecycleOwner(), warehouseID -> {
            if (!warehouseID.isHandled())
                if (warehouseID.getContentIfNotHandled().equals(BAD_INSERT))
                    warehouseViewModel.error.setValue(getString(R.string.error_create_warehouse));
                else {
                    Helper helper = Helper.getInstance();
                    helper.alerter(getContext(), R.string.msg_title_warehouse_inserted, R.string.msg_warehouse_inserted);
                    warehouseViewModel.error.setValue("");
                }
        });
    }

    private void setupEmployeeSpinner() {
        Log.v(TAG, "Setting up Spinner.");
        binding.spinnerEmployees.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Employee employee = (Employee) parent.getSelectedItem();
                warehouseViewModel.employeeID.setValue(employee.getEmployeeID());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}