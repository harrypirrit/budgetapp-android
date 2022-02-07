package com.example.budgetapp;

import static budgetapp.CSVIterator.createData;
import static budgetapp.CSVIterator.createList;
import static budgetapp.CSVIterator.filterData;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.budgetapp.databinding.FragmentFirstBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import budgetapp.Category;
import budgetapp.Item;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        // Make the Button navigate to the Second Screen (Fragment)
        binding.buttonBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create an (action) bundle with form information

                // variables
                String title = binding.titleText.getText().toString();
                String type = "EaRnInG";
                // String categories = binding.categoryTextview.getText().toString();
                String[] categories = {"Food", "Rent", "Alcohol", "Other"};
                String fromDate = binding.fromdateDate.getText().toString();
                String toDate = binding.todateDate.getText().toString();

                /** execute backend files from form data */
                String CSV_Path = "src/main/assets/19Julytestcopy.csv";
                String FILENAME = "19Julytestcopy.csv";

                Context context = getContext();
                InputStream iS = null;

                try {
                    iS = context.getAssets().open(FILENAME);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(iS));
                try {
                    String s = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("BUFFERED READER NULL");
                }


                // CSVIterator
                List<String[]> data = createData(reader);
                List<String[]> filtered_data = filterData(data);

                ArrayList<Item> itemList = createList(filtered_data);
                Item[] itemArray;
                int count = 0;

                //UserIterator
                itemArray = itemList.toArray(new Item[0]);

                // test -- to be updated
                String test;
                if (itemList.size() > 0) {
                    test = itemList.get(1).description;
                }
                else {
                    test = "FAIL";
                }

                // create an action object filled with arguments that are to be passed in the .navigate() call
                FirstFragmentDirections.ActionFirstFragmentToSecondFragment action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(itemArray, count, title, type, categories, fromDate, toDate);

                NavHostFragment.findNavController(FirstFragment.this)
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