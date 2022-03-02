package com.ebookfrenzy.viewmodeldemo.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ebookfrenzy.viewmodeldemo.R;
import com.ebookfrenzy.viewmodeldemo.databinding.MainFragmentBinding;

import java.util.Locale;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private MainFragmentBinding binding;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = MainFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding.resultText.setText(String.format(Locale.ENGLISH, "%.2f", mViewModel.getResult()));

        // TODO: Use the ViewModel

        binding.convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!binding.dollarText.getText().toString().equals("")) {
                    mViewModel.setAmount(String.format(Locale.ENGLISH, "%s", binding.dollarText.getText()));
                    binding.resultText.setText(String.format(Locale.ENGLISH, "%f", mViewModel.getResult()));
                }
                else {
                    binding.resultText.setText("No Value");
                }

            }
        });

    }
}