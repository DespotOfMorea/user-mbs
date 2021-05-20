package org.vnuk.usermbs.ui.view.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.vnuk.usermbs.R;

public class CreateUserActivity extends AppCompatActivity {
    private static final String TAG = CreateUserActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "OnCreate.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
    }
}