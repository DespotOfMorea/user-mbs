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
import org.vnuk.usermbs.databinding.FragmentFetchApplicantsBinding;
import org.vnuk.usermbs.viewmodel.PersonViewModel;

public class FetchApplicantsFragment extends Fragment {
    private static final String TAG = FetchApplicantsFragment.class.getSimpleName();

    private FragmentFetchApplicantsBinding binding;
    private PersonViewModel personViewModel;

    public FetchApplicantsFragment() {
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
        binding = FragmentFetchApplicantsBinding.inflate(inflater, container, false);
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
        personViewModel = new ViewModelProvider(this).get(PersonViewModel.class);
        binding.setPersonViewModel(personViewModel);
        binding.setLifecycleOwner(this);
    }
}