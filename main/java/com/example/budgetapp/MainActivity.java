package com.example.budgetapp;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.budgetapp.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    EditText etFromDate;
    EditText etToDate;

    DatePickerDialog.OnDateSetListener setListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // changes opening AppBar caption to be "Start a Review"
        getSupportActionBar().setTitle("Start a Review");

        //FromDate and ToDate field Calendar Dialogue
        etFromDate = findViewById(R.id.fromdate_date);
        etFromDate.setFocusable(false);
        etFromDate.setKeyListener(null);

        etToDate = findViewById(R.id.todate_date);
        etToDate.setFocusable(false);
        etToDate.setKeyListener(null);

        Calendar calendar = Calendar.getInstance();
        final int year= calendar.get(Calendar.YEAR);
        final int month= calendar.get(Calendar.MONTH);
        final int day = calendar.get(calendar.DAY_OF_MONTH);


		  // FromDate Dialog code
        etFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = ""+day+"/"+month+"/"+year;
                        etFromDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
                                      }

        );

		  // ToDate Dialog code
        etToDate.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              DatePickerDialog datePickerDialog = new DatePickerDialog(
                                                      MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                                                  @Override
                                                  public void onDateSet(DatePicker view, int year, int month, int day) {
                                                      month = month+1;
                                                      String date = ""+day+"/"+month+"/"+year;
                                                      etToDate.setText(date);
                                                  }
                                              }, year, month, day);
                                              datePickerDialog.show();
                                          }
                                      }

        );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}