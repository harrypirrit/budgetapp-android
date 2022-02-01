package com.example.budgetapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.budgetapp.databinding.FragmentSecondBinding;

import java.util.HashMap;
import java.util.Locale;

import budgetapp.*;

import static budgetapp.UserIterator.initCategories;
import static budgetapp.Summary.displaySummary;

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
            String[] categories = bundle.getStringArray("categories");
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
        String[] categories = SecondFragmentArgs.fromBundle(getArguments()).getCategories();
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


        TextView itemNoDisplay_view = binding.textviewItemNoDisplay;
        String itemNo = String.format(Locale.getDefault(), "Item #%s/%s", String.valueOf(count), String.valueOf(itemArray.length));
        itemNoDisplay_view.setText(itemNo);

        // Set Buttons to Current Category Data
        binding.buttonCat.setText(categories[0]);
        binding.buttonCat2.setText(categories[1]);
        binding.buttonCat3.setText(categories[2]);
        binding.buttonCat4.setText(categories[3]);

        // define Categories HashMap
        HashMap<String, Category> categoryHashMap = initCategories(categories);


        // Category Buttons
        binding.buttonCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category category;
                int count = Integer.parseInt(binding.textviewItemNo.getText().toString());
                Item item = itemArray[count];

                if ((category = categoryHashMap.get(binding.buttonCat.getText())) != null){
                    category.size++;
                    category.dollarTotal = category.dollarTotal.add(item.amount);
                }

                binding.buttonSkip.performClick();
            }

        });

        binding.buttonCat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category category;
                int count = Integer.parseInt(binding.textviewItemNo.getText().toString());
                Item item = itemArray[count];

                if ((category = categoryHashMap.get(binding.buttonCat2.getText())) != null){
                    category.size++;
                    category.dollarTotal = category.dollarTotal.add(item.amount);
                }

                binding.buttonSkip.performClick();
            }

        });

        binding.buttonCat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category category;
                int count = Integer.parseInt(binding.textviewItemNo.getText().toString());
                Item item = itemArray[count];

                if ((category = categoryHashMap.get(binding.buttonCat3.getText())) != null){
                    category.size++;
                    category.dollarTotal = category.dollarTotal.add(item.amount);
                }
                binding.buttonSkip.performClick();
            }

        });

        binding.buttonCat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category category;
                int count = Integer.parseInt(binding.textviewItemNo.getText().toString());
                Item item = itemArray[count];

                if ((category = categoryHashMap.get(binding.buttonCat4.getText())) != null){
                    category.size++;
                    category.dollarTotal = category.dollarTotal.add(item.amount);
                }

                binding.buttonSkip.performClick();
            }

        });



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

                    TextView itemNoDisplay_view = binding.textviewItemNoDisplay;
                    String itemNo = String.format(Locale.getDefault(), "Item #%s/%s", String.valueOf(count), String.valueOf(itemArray.length));
                    itemNoDisplay_view.setText(itemNo);
                }
                else {
                    // REDIRECT TO SUMMARY SCREEN
                    System.out.println("FINAL ITEM REACHED");
                    //Category[] categoryList = initCategories(categories);
                    //updateCategoryData(itemArray, categoryHashMap);
                    displaySummary(categoryHashMap);
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