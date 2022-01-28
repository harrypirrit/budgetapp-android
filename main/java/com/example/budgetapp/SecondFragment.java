package com.example.budgetapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.budgetapp.databinding.FragmentSecondBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;

import budgetapp.*;

import static budgetapp.CSVIterator.createData;
import static budgetapp.CSVIterator.createList;
import static budgetapp.CSVIterator.filterData;
import static budgetapp.UserIterator.categorizeItems;
import static budgetapp.UserIterator.initCategories;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);


        /** create bundle for review data */
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String title = bundle.getString("title");
            String type = bundle.getString("type");
            String categories = bundle.getString("categories");
            String fromDate = bundle.getString("fromDate");
            String toDate = bundle.getString("toDate");
        }

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)    {
        super.onViewCreated(view, savedInstanceState);

        // retrieve variables from Arguments
        String title = SecondFragmentArgs.fromBundle(getArguments()).getTitle();
        String titleText = getString(R.string.title_text, title);
        TextView title_view = binding.titleDisplayText;
        title_view.setText(titleText);

        String type = "Earning_test";
        String typeText = getString(R.string.review_type_text, type);
        TextView review_type = binding.reviewTypeDisplayText;
        review_type.setText(typeText);

        String categories = SecondFragmentArgs.fromBundle(getArguments()).getCategories();
        String categoriesText = categories;
        TextView categories_view = binding.categoriesDisplayText;
        categories_view.setText(categoriesText);

        String fromDate = SecondFragmentArgs.fromBundle(getArguments()).getFromDate();
        String fromDateText = String.format("From Date : %s.", fromDate);
        TextView fromDate_view = binding.fromDateDisplayText;
        fromDate_view.setText(fromDateText);

        String toDate = SecondFragmentArgs.fromBundle(getArguments()).getToDate();
        String toDateText = String.format("To Date : %s.", toDate);
        TextView toDate_view = binding.toDateDisplayText;
        toDate_view.setText(toDateText);


        binding.buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}