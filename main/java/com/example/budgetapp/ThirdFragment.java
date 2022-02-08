package com.example.budgetapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.budgetapp.databinding.FragmentThirdBinding;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import budgetapp.*;

import static budgetapp.Summary.summaryGetTotalAmount;

public class ThirdFragment extends Fragment {

    private FragmentThirdBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentThirdBinding.inflate(inflater, container, false);

        /** create bundle for review data */
        Bundle bundle = this.getArguments();

        return binding.getRoot();

    }

    private void loadPieChartData(Category[] categoryArray, PieChart pieChart){
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (Category category : categoryArray) {
            //entries.add(new PieEntry(category.dollarTotal.floatValue(), category.name));
            if (category.dollarTotal.abs().floatValue() != 0) {
                entries.add(new PieEntry(category.dollarTotal.abs().floatValue(), category.name));
            }
        }

        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Category");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();
    }

    private void setupPieChart(PieChart pieChart){
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Spending By Category");
        pieChart.setCenterTextSize(12);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // retrieve variables from Arguments
        Item[] itemArray = ThirdFragmentArgs.fromBundle(getArguments()).getItemArray();
        Category[] categoryArray = ThirdFragmentArgs.fromBundle(getArguments()).getCategoryArray();

        // Initialise Pie Chart and load entry values
        PieChart pieChart = (PieChart) getView().findViewById(R.id.pieChart);
        loadPieChartData(categoryArray, pieChart);
        setupPieChart(pieChart);


        // set to current item variables
        TextView date_view = binding.textviewTotalAmount;
        String totalAmount = String.valueOf(summaryGetTotalAmount(categoryArray));
        date_view.setText(String.format("Total Amount : $%s", totalAmount));


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}

