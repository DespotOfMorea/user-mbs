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

import org.vnuk.usermbs.MainActivity;
import org.vnuk.usermbs.R;
import org.vnuk.usermbs.databinding.FragmentNewUserBinding;
import org.vnuk.usermbs.util.Helper;
import org.vnuk.usermbs.viewmodel.UserViewModel;

import static org.vnuk.usermbs.data.room.WarehouseDB.BAD_INSERT;

public class NewUserFragment extends Fragment {
    private static final String TAG = NewUserFragment.class.getSimpleName();

    private FragmentNewUserBinding binding;
    private UserViewModel userViewModel;

    public NewUserFragment() {
        super(R.layout.fragment_new_user);
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
        binding = FragmentNewUserBinding.inflate(inflater, container, false);
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
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        binding.setUserViewModel(userViewModel);
        binding.setLifecycleOwner(this);

        userViewModel.getMldUserID().observe(getViewLifecycleOwner(), userID -> {
            if (!userID.isHandled())
                if (userID.getContentIfNotHandled().equals(BAD_INSERT))
                    userViewModel.error.setValue(getString(R.string.error_name_username));
                else {
                    Helper helper = Helper.getInstance();
                    Context context = getContext();

                    AlertDialog.Builder builder = helper.getBuilder(context,
                            R.string.msg_title_user_inserted,
                            R.string.msg_user_inserted);
                    builder.setNeutralButton(context.getString(android.R.string.ok),
                            (dialog, which) -> {
                                dialog.dismiss();
                                startMainActivity();
                            });
                    builder.create().show();
                }
        });
    }

    private void startMainActivity() {
        Log.i(TAG, "User inserted successfully, opening MainActivity.");
        Intent mainActivityIntent = new Intent(getContext(), MainActivity.class);
        startActivity(mainActivityIntent);
    }
}