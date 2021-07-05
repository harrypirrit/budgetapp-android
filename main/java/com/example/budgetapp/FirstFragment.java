package com.example.budgetapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.budgetapp.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    // FirstFragmentDirections.ActionFirstFragmentToSecondFragment action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(currentCount);

    private FragmentFirstBinding binding;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Change the title on the Action bar to Screen Title.
            //ActionBar.setTitle(Integer.parseInt("Start a Review"));


        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        // Make the Button navigate to the Second Screen (Fragment)
        binding.buttonBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create a bundle with form information

                // variables
                String title = binding.titleText.getText().toString();
                String type = "EaRnInG";
                String categories = binding.categoryTextview.getText().toString();
                String fromDate = binding.fromdateDate.getText().toString();
                String toDate = binding.todateDate.getText().toString();

                //FirstFragment.this.setArguments(bundle);
                FirstFragmentDirections.ActionFirstFragmentToSecondFragment action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(title, type, categories, fromDate, toDate);

                NavHostFragment.findNavController(FirstFragment.this)
                    //.navigate(R.id.action_FirstFragment_to_SecondFragment);
                      .navigate(action);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}