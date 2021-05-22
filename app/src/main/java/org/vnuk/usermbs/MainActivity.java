package org.vnuk.usermbs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import org.vnuk.usermbs.databinding.ActivityMainBinding;
import org.vnuk.usermbs.ui.view.activity.CreateUserActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "OnCreate.");
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnNewUserActivity.setOnClickListener(v -> startCreateUserActivity());
    }

    private void startCreateUserActivity() {
        Log.i(TAG, "Starting create new User activity.");
        Intent createUserActivityIntent = new Intent(this, CreateUserActivity.class);
        startActivity(createUserActivityIntent);
    }
}