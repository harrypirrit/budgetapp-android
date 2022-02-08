package com.example.budgetapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.budgetapp.databinding.FragmentSecondBinding;

import java.util.HashMap;
import java.util.Random;

import budgetapp.*;

import static budgetapp.UserIterator.initCategories;
import static budgetapp.Summary.displaySummary;
import static budgetapp.Summary.getCategoryArray;

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

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // get Review data
        String title = SecondFragmentArgs.fromBundle(getArguments()).getTitle();
        String type = SecondFragmentArgs.fromBundle(getArguments()).getType();
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
        amount_view.setText(String.format("$%s", item.amount.toString()));

        TextView itemNo_view = binding.textviewItemNo;
        itemNo_view.setText(String.valueOf(count));


        TextView itemNoDisplay_view = binding.textviewItemNoDisplay;
        String itemNo = String.format("Item #%s/%s", String.valueOf(count+1), String.valueOf(itemArray.length));
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

                if ((category = categoryHashMap.get(binding.buttonCat.getText())) != null) {
                    category.size++;
                    category.dollarTotal = category.dollarTotal.add(item.amount);
                    item.category = category;
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

                if ((category = categoryHashMap.get(binding.buttonCat2.getText())) != null) {
                    category.size++;
                    category.dollarTotal = category.dollarTotal.add(item.amount);
                    item.category = category;
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

                if ((category = categoryHashMap.get(binding.buttonCat3.getText())) != null) {
                    category.size++;
                    category.dollarTotal = category.dollarTotal.add(item.amount);
                    item.category = category;
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

                if ((category = categoryHashMap.get(binding.buttonCat4.getText())) != null) {
                    category.size++;
                    category.dollarTotal = category.dollarTotal.add(item.amount);
                    item.category = category;
                }

                binding.buttonSkip.performClick();
            }

        });


        binding.buttonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(binding.textviewItemNo.getText().toString());
                count++;

                if (count < itemArray.length) {
                    Item item = itemArray[count];

                    // set to current item variables
                    TextView date_view = binding.textviewDate;
                    date_view.setText(item.date);

                    TextView description_view = binding.textviewDescription;
                    description_view.setText(item.description);

                    TextView amount_view = binding.textviewAmount;
                    amount_view.setText(String.format("$%s", item.amount.toString()));

                    TextView itemNo_view = binding.textviewItemNo;
                    itemNo_view.setText(String.valueOf(count));

                    TextView itemNoDisplay_view = binding.textviewItemNoDisplay;
                    String itemNo = String.format("Item #%s/%s", String.valueOf(count+1), String.valueOf(itemArray.length));
                    itemNoDisplay_view.setText(itemNo);


                } else {
                    // REDIRECT TO SUMMARY SCREEN
                    System.out.println("FINAL ITEM REACHED");
                    displaySummary(categoryHashMap);
                    Category[] categoryArray = getCategoryArray(categoryHashMap);

                    NavHostFragment.findNavController(SecondFragment.this).navigate(SecondFragmentDirections.actionSecondFragmentToThirdFragment(itemArray, categoryArray, title));

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

        binding.buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category category;
                int count = 0;
                Random rand = new Random();

                for (Item item : itemArray) {

                    // if randomly fetched category from category String is valid, add to the category like it was selected
                    if ((category = categoryHashMap.get(categories[rand.nextInt(categories.length)])) != null) {
                        category.size++;
                        category.dollarTotal = category.dollarTotal.add(item.amount);
                    }
                }

                Category[] categoryArray = getCategoryArray(categoryHashMap);
                NavHostFragment.findNavController(SecondFragment.this).navigate(SecondFragmentDirections.actionSecondFragmentToThirdFragment(itemArray, categoryArray, title));
            }
        });

        binding.imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int count = Integer.parseInt(binding.textviewItemNo.getText().toString());
                count--;

                if (count > 0) {
                    Item item = itemArray[count];
                    Category category;
                    String key;

                    // undo previous category data input
                    try {
                        key = item.category.name;
                        category = categoryHashMap.get(key);
                        category.dollarTotal = category.dollarTotal.subtract(item.amount);
                        category.size--;
                    }
                    catch (NullPointerException e){
                        System.out.println("NULL");
                    }

                    // set to current item variables
                    TextView date_view = binding.textviewDate;
                    date_view.setText(item.date);

                    TextView description_view = binding.textviewDescription;
                    description_view.setText(item.description);

                    TextView amount_view = binding.textviewAmount;
                    amount_view.setText(String.format("$%s", item.amount.toString()));

                    TextView itemNo_view = binding.textviewItemNo;
                    itemNo_view.setText(String.valueOf(count));

                    TextView itemNoDisplay_view = binding.textviewItemNoDisplay;
                    String itemNo = String.format("Item #%s/%s", String.valueOf(count+1), String.valueOf(itemArray.length));
                    itemNoDisplay_view.setText(itemNo);
                } else {
                    count++;
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}