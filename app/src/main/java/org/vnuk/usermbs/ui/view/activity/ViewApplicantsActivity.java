package org.vnuk.usermbs.ui.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.vnuk.usermbs.R;
import org.vnuk.usermbs.databinding.ActivityViewApplicantsBinding;
import org.vnuk.usermbs.ui.adapter.PersonRecyclerViewAdapter;
import org.vnuk.usermbs.util.Helper;
import org.vnuk.usermbs.viewmodel.PersonViewModel;

import static org.vnuk.usermbs.ui.view.activity.CreateEmployeeActivity.CITY_VALUE;
import static org.vnuk.usermbs.ui.view.activity.CreateEmployeeActivity.FIRST_NAME_VALUE;
import static org.vnuk.usermbs.ui.view.activity.CreateEmployeeActivity.LAST_NAME_VALUE;

public class ViewApplicantsActivity extends AppCompatActivity {
    private static final String TAG = ViewApplicantsActivity.class.getSimpleName();

    private ActivityViewApplicantsBinding binding;
    private PersonViewModel personViewModel;
    private PersonRecyclerViewAdapter adapter;
    private Helper helper = Helper.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "OnCreate started.");
        super.onCreate(savedInstanceState);
        binding = ActivityViewApplicantsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setupRecycleView();
        setupViewModel();
        checkInternetConnection();
        Log.i(TAG, "OnCreate finished.");
    }

    private void setupRecycleView() {
        Log.v(TAG, "Setting up RecycleView.");
        binding.rvPersons.setLayoutManager(new LinearLayoutManager(this));
        binding.rvPersons.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new PersonRecyclerViewAdapter();
        binding.rvPersons.setAdapter(adapter);
        adapter.setOnItemClickListener((view, itemPerson) -> {
            Intent createEmployeeActivityIntent = new Intent(getApplicationContext(), CreateEmployeeActivity.class);
            createEmployeeActivityIntent.putExtra(FIRST_NAME_VALUE, itemPerson.getFirstName());
            createEmployeeActivityIntent.putExtra(LAST_NAME_VALUE, itemPerson.getLastName());
            createEmployeeActivityIntent.putExtra(CITY_VALUE, itemPerson.getCity());
            startActivity(createEmployeeActivityIntent);
        });
    }

    private void setupViewModel() {
        Log.v(TAG, "Setting up ViewModel.");
        personViewModel = new ViewModelProvider(this).get(PersonViewModel.class);
        binding.setPersonViewModel(personViewModel);
        binding.setLifecycleOwner(this);

        personViewModel.getMldPersons().observe(this, persons -> {
            if (persons != null && persons.size() > 0) {
                adapter.setPersons(persons);
                personViewModel.error.setValue("");
            } else if (!helper.isDeviceConnected(this))
                personViewModel.error.setValue(getString(R.string.msg_no_internet));
            else
                personViewModel.error.setValue(getString(R.string.msg_error_load_applicants));
        });
    }

    private void checkInternetConnection() {
        // Assuming this segment is only operational when device is connected to internet,
        // user is informed to connect device.
        Log.v(TAG, "Checking if device is connected to internet.");
        if (!helper.isDeviceConnected(this)) {
            Log.e(TAG, "Device is not connected to internet.");
            personViewModel.error.setValue(getString(R.string.msg_no_internet));
        }
    }
}