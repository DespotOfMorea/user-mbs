package org.vnuk.usermbs.ui.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.vnuk.usermbs.R;
import org.vnuk.usermbs.databinding.FragmentNewEmployeeBinding;
import org.vnuk.usermbs.util.Helper;
import org.vnuk.usermbs.viewmodel.EmployeeViewModel;

import static org.vnuk.usermbs.data.room.WarehouseDB.BAD_INSERT;

public class NewEmployeeFragment extends Fragment {
    private static final String TAG = NewEmployeeFragment.class.getSimpleName();

    private FragmentNewEmployeeBinding binding;
    private EmployeeViewModel employeeViewModel;

    public NewEmployeeFragment() {
        super(R.layout.fragment_new_employee);
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
        binding = FragmentNewEmployeeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        Log.v(TAG, "OnViewCreated.");
        super.onViewCreated(view, savedInstanceState);
        setupViewModel();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupViewModel() {
        Log.v(TAG, "Setting up ViewModel.");
        employeeViewModel = new ViewModelProvider(requireActivity()).get(EmployeeViewModel.class);
        binding.setEmployeeViewModel(employeeViewModel);
        binding.setLifecycleOwner(this);

        employeeViewModel.getMldEmployeeID().observe(getViewLifecycleOwner(), employeeID -> {
            if (!employeeID.isHandled())
                if (employeeID.getContentIfNotHandled().equals(BAD_INSERT))
                    employeeViewModel.error.setValue(getString(R.string.error_create_employee));
                else {
                    Helper helper = Helper.getInstance();
                    helper.alerter(getContext(), R.string.msg_title_employee_inserted, R.string.msg_employee_inserted);
                    employeeViewModel.error.setValue("");
                }
        });
    }
}