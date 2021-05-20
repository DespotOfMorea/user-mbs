package org.vnuk.usermbs.ui.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.vnuk.usermbs.R;

import static org.vnuk.usermbs.ui.view.LogInFragment.NAME_VALUE;

public class UserPanelActivity extends AppCompatActivity {
    private static final String TAG = UserPanelActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "OnCreate started.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_panel);

        getDataFromIntent();
        setupButtons();
        Log.i(TAG, "OnCreate finished.");
    }

    private void getDataFromIntent() {
        Log.v(TAG, "Getting data from intent.");
        Intent intent = getIntent();
        String title = intent.getStringExtra(NAME_VALUE);

        TextView tvTitle = findViewById(R.id.tv_user_panel_hello);
        tvTitle.setText(getString(R.string.hello_username, title));
    }

    private void setupButtons() {
        Log.v(TAG, "Setting up buttons.");
        Context context = getApplicationContext();
        Button btnCreateEmployee = findViewById(R.id.btn_new_employee);
        Button btnCreateBuyer = findViewById(R.id.btn_new_buyer);
        Button btnViewBuyers = findViewById(R.id.btn_view_buyers);
        Button btnViewApplicants = findViewById(R.id.btn_view_applicants);

        btnCreateEmployee.setOnClickListener(v -> {
            Intent createEmployeeActivityIntent = new Intent(context, CreateEmployeeActivity.class);
            startActivity(createEmployeeActivityIntent);
        });
        btnCreateBuyer.setOnClickListener(v -> {
            Intent createBuyerActivityIntent = new Intent(context, CreateBuyerActivity.class);
            startActivity(createBuyerActivityIntent);
        });
        btnViewBuyers.setOnClickListener(v -> {
            Intent viewBuyersActivityIntent = new Intent(context, ViewBuyersActivity.class);
            startActivity(viewBuyersActivityIntent);
        });
        btnViewApplicants.setOnClickListener(v -> {
            Intent weatherActivityIntent = new Intent(context, ViewApplicantsActivity.class);
            startActivity(weatherActivityIntent);
        });
    }
}