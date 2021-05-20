package org.vnuk.usermbs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import org.vnuk.usermbs.ui.view.activity.CreateUserActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "OnCreate.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnNewUser = findViewById(R.id.btn_new_user_activity);
        btnNewUser.setOnClickListener(v -> startCreateUserActivity());
    }

    private void startCreateUserActivity() {
        Log.i(TAG, "Starting create new User activity.");
        Intent createUserActivityIntent = new Intent(this, CreateUserActivity.class);
        startActivity(createUserActivityIntent);
    }
}