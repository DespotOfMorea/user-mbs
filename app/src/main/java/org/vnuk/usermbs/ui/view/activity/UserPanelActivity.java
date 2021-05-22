package org.vnuk.usermbs.ui.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import org.vnuk.usermbs.R;
import org.vnuk.usermbs.databinding.ActivityUserPanelBinding;

import static org.vnuk.usermbs.ui.view.LogInFragment.NAME_VALUE;

public class UserPanelActivity extends AppCompatActivity {
    private static final String TAG = UserPanelActivity.class.getSimpleName();

    private ActivityUserPanelBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "OnCreate started.");
        super.onCreate(savedInstanceState);
        binding = ActivityUserPanelBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getDataFromIntent();
        setupButtons();
        Log.i(TAG, "OnCreate finished.");
    }

    private void getDataFromIntent() {
        Log.v(TAG, "Getting data from intent.");
        Intent intent = getIntent();
        String title = intent.getStringExtra(NAME_VALUE);
        binding.tvUserPanelHello.setText(getString(R.string.hello_username, title));
    }

    private void setupButtons() {
        Log.v(TAG, "Setting up buttons.");
        Context context = getApplicationContext();

        binding.btnNewEmployee.setOnClickListener(v -> {
            Intent createEmployeeActivityIntent = new Intent(context, CreateEmployeeActivity.class);
            startActivity(createEmployeeActivityIntent);
        });
        binding.btnNewBuyer.setOnClickListener(v -> {
            Intent createBuyerActivityIntent = new Intent(context, CreateBuyerActivity.class);
            startActivity(createBuyerActivityIntent);
        });
        binding.btnViewBuyers.setOnClickListener(v -> {
            Intent viewBuyersActivityIntent = new Intent(context, ViewBuyersActivity.class);
            startActivity(viewBuyersActivityIntent);
        });
        binding.btnViewApplicants.setOnClickListener(v -> {
            Intent weatherActivityIntent = new Intent(context, ViewApplicantsActivity.class);
            startActivity(weatherActivityIntent);
        });
    }
}