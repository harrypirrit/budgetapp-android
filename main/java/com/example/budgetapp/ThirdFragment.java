package com.example.budgetapp;

import android.app.Activity;
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
import com.example.budgetapp.databinding.FragmentThirdBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.content.Context;

import budgetapp.*;

import static budgetapp.CSVIterator.createData;
import static budgetapp.CSVIterator.createList;
import static budgetapp.CSVIterator.filterData;
import static budgetapp.UserIterator.initCategories;
import static budgetapp.Summary.displaySummary;

public class ThirdFragment extends Activity {

    private FragmentThirdBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentThirdBinding.inflate(inflater, container, false);


        /** create bundle for review data */
        // Bundle bundle = this.getArguments();

        return binding.getRoot();

    }

}

