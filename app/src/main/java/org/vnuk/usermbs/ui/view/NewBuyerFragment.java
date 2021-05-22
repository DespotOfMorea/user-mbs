package org.vnuk.usermbs.ui.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.vnuk.usermbs.R;
import org.vnuk.usermbs.data.room.entity.Employee;
import org.vnuk.usermbs.databinding.FragmentNewBuyerBinding;
import org.vnuk.usermbs.util.Helper;
import org.vnuk.usermbs.viewmodel.BuyerViewModel;
import org.vnuk.usermbs.viewmodel.EmployeeViewModel;

import static org.vnuk.usermbs.data.room.WarehouseDB.BAD_INSERT;

public class NewBuyerFragment extends Fragment {
    private static final String TAG = NewBuyerFragment.class.getSimpleName();

    private FragmentNewBuyerBinding binding;
    private BuyerViewModel buyerViewModel;

    public NewBuyerFragment() {
        super(R.layout.fragment_new_buyer);
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
        binding = FragmentNewBuyerBinding.inflate(inflater, container, false);
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
        buyerViewModel = new ViewModelProvider(this).get(BuyerViewModel.class);
        binding.setBuyerViewModel(buyerViewModel);
        binding.setLifecycleOwner(this);

        buyerViewModel.getMldBuyerID().observe(getViewLifecycleOwner(), buyerID -> {
            if (!buyerID.isHandled())
                if (buyerID.getContentIfNotHandled().equals(BAD_INSERT))
                    buyerViewModel.error.setValue(getString(R.string.error_create_buyer));
                else {
                    Helper helper = Helper.getInstance();
                    helper.alerter(getContext(), R.string.msg_title_buyer_inserted, R.string.msg_buyer_inserted);
                    buyerViewModel.error.setValue("");
                }
        });

        EmployeeViewModel employeeViewModel = new ViewModelProvider(requireActivity()).get(EmployeeViewModel.class);

        employeeViewModel.getMldEmployees().observe(getViewLifecycleOwner(), employees -> {
            if (employees != null && employees.size() > 0)
                buyerViewModel.employees.setValue(employees);
        });

        employeeViewModel.fetchEmployees();
    }

    private void setupEmployeeSpinner() {
        Log.v(TAG, "Setting up Spinner.");
        Spinner spinner = getView().findViewById(R.id.spinner_employees);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Employee employee = (Employee) parent.getSelectedItem();
                buyerViewModel.employeeID.setValue(employee.getEmployeeID());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}