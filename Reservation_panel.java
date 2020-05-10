package com.example.anupamanurag.trainticketing1;

//import android.annotation.SuppressLint;
import android.app.DatePickerDialog;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Reservation_panel extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    Button b1, b2;
    Spinner s1, s2;
    String source, destination;
    int cost = 0;
    TextView t1, t2;
    EditText e1;
    DatePickerDialog.OnDateSetListener date;


    // @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_reservation_panel);



        b1 = (Button) findViewById(R.id.button5);
        b2 = (Button) findViewById(R.id.button6);

        t1 = (TextView) findViewById(R.id.textView5);
        t2 = (TextView) findViewById(R.id.textView6);
        e1 = (EditText) findViewById(R.id.editText6);

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(Reservation_panel.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, date, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayofmonth) {

                month = month + 1;
                String date = dayofmonth + "/" + month + "/" + year;
                t2.setText(date);

            }
        };

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i1=new Intent(Reservation_panel.this,Ticket_Details.class);
                i1.putExtra("from", source);
                i1.putExtra("to", destination);
                i1.putExtra("fare", t1.getText().toString());
                i1.putExtra("date", t2.getText().toString());
                i1.putExtra("Name", e1.getText().toString());
                startActivity(i1);



            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(Reservation_panel.this);
                alert.setMessage("Are you Sure?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog dialogg = alert.create();
                dialogg.show();
            }
        });


        s1= (Spinner) findViewById(R.id.Spinner);
        s2 = (Spinner) findViewById(R.id.spinner2);

        List<String> locations = new ArrayList<String>();
        locations.add("Gorakhpur");
        locations.add("Siwan");
        locations.add("Hajipur");
        locations.add("Barauni");
        locations.add("Asansol");
        locations.add("Durgapur");

        source = locations.get(0);
        destination = locations.get(0);

        ArrayAdapter<String> adapterSpinnerSource = new ArrayAdapter<String>(Reservation_panel.this, android.R.layout.simple_spinner_dropdown_item, locations);
        ArrayAdapter<String> adapterSpinnerDestination = new ArrayAdapter<String>(Reservation_panel.this, android.R.layout.simple_spinner_dropdown_item, locations);
        s1.setAdapter(adapterSpinnerSource);

        s2.setAdapter(adapterSpinnerDestination);

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                source = parent.getItemAtPosition(position).toString();
                calculatecost();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                destination = parent.getItemAtPosition(position).toString();

                calculatecost();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void calculatecost() {
        if ((source.equals("Gorakhpur") && destination.equals("Siwan")) || (source.equals("Siwan") && destination.equals("Gorakhpur"))) {
            cost = 10;
        } else if ((source.equals("Gorakhpur") && destination.equals("Gorakhpur")) || (source.equals("Gorakhpur") && destination.equals("Gorakhpur"))) {
            Toast.makeText(getApplicationContext(), "Source and destination should not be same!!", Toast.LENGTH_SHORT).show();
        } else if ((source.equals("Gorakhpur") && destination.equals("Hajipur")) || (source.equals("Hajipur") && destination.equals("Gorakhpurn"))) {
            cost = 20;
        } else if ((source.equals("Gorakhpur") && destination.equals("Barauni")) || (source.equals("Barauni") && destination.equals("Gorakhpur"))) {
            cost = 30;
        } else if ((source.equals("Gorakhpur") && destination.equals("Asansol")) || (source.equals("Asansol") && destination.equals("Gorakhpur"))) {
            cost = 40;
        } else if ((source.equals("Gorakhpur") && destination.equals("Durgapur")) || (source.equals("Durgapur") && destination.equals("Gorakhpur"))) {
            cost = 50;
        } else if ((source.equals("Siwan") && destination.equals("Gorakhpur")) || (source.equals("Gorakhpur") && destination.equals("Siwan"))) {
            cost = 10;
        } else if ((source.equals("Siwan") && destination.equals("Hajipur")) || (source.equals("Hajipur") && destination.equals("Siwan"))) {
            cost = 10;
        } else if ((source.equals("Siwan") && destination.equals("Siwan")) || (source.equals("Siwan") && destination.equals("Siwan"))) {
            Toast.makeText(getApplicationContext(), "Source and destination should not be same!!", Toast.LENGTH_SHORT).show();
        } else if ((source.equals("Siwan") && destination.equals("Barauni")) || (source.equals("Barauni") && destination.equals("Siwan"))) {
            cost = 20;
        } else if ((source.equals("Siwan") && destination.equals("Asansol")) || (source.equals("Asansol") && destination.equals("Siwan"))) {
            cost = 30;
        } else if ((source.equals("Siwan") && destination.equals("Durgapur")) || (source.equals("Durgapur") && destination.equals("Siwan"))) {
            cost = 40;
        } else if ((source.equals("Hajipur") && destination.equals("Gorakhpur")) || (source.equals("Gorakhpur") && destination.equals("Hajipur"))) {
            cost = 20;
        } else if ((source.equals("Hajipur") && destination.equals("Siwan")) || (source.equals("Siwan") && destination.equals("Hajipur"))) {
            cost = 10;
        } else if ((source.equals("Hajipur") && destination.equals("Hajipur")) || (source.equals("Hajipur") && destination.equals("Hajipur"))) {
            Toast.makeText(getApplicationContext(), "Source and destination should not be same!!", Toast.LENGTH_SHORT).show();
        } else if ((source.equals("Hajipur") && destination.equals("Barauni")) || (source.equals("Barauni") && destination.equals("Hajipur"))) {
            cost = 10;
        } else if ((source.equals("Hajipur") && destination.equals("Asansol")) || (source.equals("Asansol") && destination.equals("Hajipur"))) {
            cost = 20;
        } else if ((source.equals("Hajipur") && destination.equals("Durgapur")) || (source.equals("Durgapur") && destination.equals("Hajipur"))) {
            cost = 30;
        } else if ((source.equals("Barauni") && destination.equals("Gorakhpur")) || (source.equals("Gorakhpur") && destination.equals("Barauni"))) {
            cost = 30;
        } else if ((source.equals("Barauni") && destination.equals("Siwan")) || (source.equals("Siwan") && destination.equals("Barauni"))) {
            cost = 20;
        } else if ((source.equals("Barauni") && destination.equals("Barauni")) || (source.equals("Barauni") && destination.equals("Barauni"))) {
            Toast.makeText(getApplicationContext(), "Source and destination should not be same!!", Toast.LENGTH_SHORT).show();
        } else if ((source.equals("Barauni") && destination.equals("Hajipur")) || (source.equals("Hajipur") && destination.equals("Barauni"))) {
            cost = 10;
        } else if ((source.equals("Barauni") && destination.equals("Asansol")) || (source.equals("Asansol") && destination.equals("Barauni"))) {
            cost = 20;
        } else if ((source.equals("Barauni") && destination.equals("Durgapur")) || (source.equals("Durgapur") && destination.equals("Barauni"))) {
            cost = 30;
        } else if ((source.equals("Asansol") && destination.equals("Durgapur")) || (source.equals("Durgapur") && destination.equals("Asansol"))) {
            cost = 10;
        } else if ((source.equals("Asansol") && destination.equals("Asansol")) || (source.equals("Asansol") && destination.equals("Asansol"))) {
            Toast.makeText(getApplicationContext(), "Source and destination should not be same!!", Toast.LENGTH_SHORT).show();
        } else if ((source.equals("Asansol") && destination.equals("Barauni")) || (source.equals("Barauni") && destination.equals("Asansol"))) {
            cost = 10;
        } else if ((source.equals("Asansol") && destination.equals("Hajipur")) || (source.equals("Hajipur") && destination.equals("Asansol"))) {
            cost = 20;
        } else if ((source.equals("Asansol") && destination.equals("Siwan")) || (source.equals("Siwan") && destination.equals("Asansol"))) {
            cost = 30;
        } else if ((source.equals("Asansol") && destination.equals("Gorakhpur")) || (source.equals("Gorakhpur") && destination.equals("Asansol"))) {
            cost = 40;
        } else if ((source.equals("Durgapur") && destination.equals("Gorakhpur")) || (source.equals("Gorakhpur") && destination.equals("Durgapur"))) {
            cost = 50;
        } else if ((source.equals("Durgapur") && destination.equals("Asansol")) || (source.equals("Asansol") && destination.equals("Durgapur"))) {
            cost = 10;
        } else if ((source.equals("Durgapur") && destination.equals("Durgapur")) || (source.equals("Durgapur") && destination.equals("Durgapur"))) {
            Toast.makeText(getApplicationContext(), "Source and destination should not be same!!", Toast.LENGTH_SHORT).show();
        } else if ((source.equals("Durgapur") && destination.equals("Barauni")) || (source.equals("Barauni") && destination.equals("Durgapur"))) {
            cost = 20;
        } else if ((source.equals("Durgapur") && destination.equals("Hajipur")) || (source.equals("Hajipur") && destination.equals("Durgapur"))) {
            cost = 30;
        } else if ((source.equals("Durgapur") && destination.equals("Siwan")) || (source.equals("Siwan") && destination.equals("Durgapur"))) {
            cost = 40;
        }
        t1.setText(String.valueOf(cost));

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}