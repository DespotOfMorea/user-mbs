package org.vnuk.usermbs.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.vnuk.usermbs.R;
import org.vnuk.usermbs.databinding.FragmentLoginBinding;
import org.vnuk.usermbs.ui.view.activity.UserPanelActivity;
import org.vnuk.usermbs.util.Helper;
import org.vnuk.usermbs.viewmodel.UserViewModel;

public class LogInFragment extends Fragment {
    private static final String TAG = LogInFragment.class.getSimpleName();

    public static final String NAME_VALUE = "name_value";

    private FragmentLoginBinding binding;
    private UserViewModel userViewModel;

    public LogInFragment() {
        super(R.layout.fragment_login);
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
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        Log.v(TAG, "OnViewCreated.");
        setupViewModel();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupViewModel() {
        Log.v(TAG, "Setting up ViewModel.");
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        binding.setUserViewModel(userViewModel);
        binding.setLifecycleOwner(this);

        userViewModel.getIsValidLogin().observe(getViewLifecycleOwner(), result -> {
            if (result) {
                Helper helper = Helper.getInstance();
                Context context = getContext();

                String username = userViewModel.username.getValue();

                AlertDialog.Builder builder = helper.getBuilder(context,
                        R.string.msg_title_login,
                        R.string.msg_login);
                builder.setNeutralButton(context.getString(android.R.string.ok),
                        (dialog, which) -> {
                            dialog.dismiss();
                            userViewModel.clearLoginData();
                            startUserPanelActivity(username);
                        });
                builder.create().show();
            } else
                userViewModel.error.setValue(getString(R.string.error_login));
        });
    }

    private void startUserPanelActivity(String usernameValue) {
        Log.i(TAG, "Valid login, opening User panel.");
        Intent userPanelActivityIntent = new Intent(getContext(), UserPanelActivity.class);
        userPanelActivityIntent.putExtra(NAME_VALUE, usernameValue);
        startActivity(userPanelActivityIntent);
    }
}
