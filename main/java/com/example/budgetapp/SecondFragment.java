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
            Item[] itemArray = (Item[]) bundle.get("itemList");
            int count = bundle.getInt("count");
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

        // get Review data
        String title = SecondFragmentArgs.fromBundle(getArguments()).getTitle();
        String type = SecondFragmentArgs.fromBundle(getArguments()).getType();;
        String categories = SecondFragmentArgs.fromBundle(getArguments()).getCategories();
        String fromDate = SecondFragmentArgs.fromBundle(getArguments()).getFromDate();
        String toDate = SecondFragmentArgs.fromBundle(getArguments()).getToDate();

        // retrieve variables from Arguments

        int count = SecondFragmentArgs.fromBundle(getArguments()).getCount();
        Item[] itemArray = SecondFragmentArgs.fromBundle(getArguments()).getItemArray();
        Item item = itemArray[count];

        // set to current item variables
        TextView date_view = binding.textviewDate;
        date_view.setText(item.date);

        TextView description_view = binding.textviewDescription;
        description_view.setText(item.description);

        TextView amount_view = binding.textviewAmount;
        amount_view.setText(item.amount.toString());

        TextView itemNo_view = binding.textviewItemNo;
        itemNo_view.setText(String.valueOf(count));


//        System.out.println("-- -- -- -- -- -- -- -- --");
//        System.out.println(String.format("Item 1: %s", itemArray[0].description));
//        System.out.println("-- -- -- -- -- -- -- -- --");


        binding.buttonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(binding.textviewItemNo.getText().toString());
                count++;

                if(count < itemArray.length) {
                    Item item = itemArray[count];

                    // set to current item variables
                    TextView date_view = binding.textviewDate;
                    date_view.setText(item.date);

                    TextView description_view = binding.textviewDescription;
                    description_view.setText(item.description);

                    TextView amount_view = binding.textviewAmount;
                    amount_view.setText(item.amount.toString());

                    TextView itemNo_view = binding.textviewItemNo;
                    itemNo_view.setText(String.valueOf(count));
                }
                else {
                    // REDIRECT TO SUMMARY SCREEN
                    System.out.println("FINAL ITEM REACHED");
                }
            }
        });

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