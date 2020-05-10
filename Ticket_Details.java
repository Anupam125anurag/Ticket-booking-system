package com.example.anupamanurag.trainticketing1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Ticket_Details extends AppCompatActivity {


    TextView t1, t2, t3, t4,t5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ticket__details);


        t1 = (TextView) findViewById(R.id.textView11);
        t2 = (TextView) findViewById(R.id.textView12);
        t3 = (TextView) findViewById(R.id.textView13);
        t4 = (TextView) findViewById(R.id.textView14);
        t5 = (TextView) findViewById(R.id.textView20);

        Intent i2 = getIntent();
        Bundle bundle = i2.getExtras();
        String a = bundle.getString("from");
        String b = bundle.getString("to");
        String c = bundle.getString("fare");
        String d = bundle.getString("date");
        String e = bundle.getString("Name");

        t1.setText(a);
        t2.setText(b);
        t3.setText(c);
        t4.setText(d);
        t5.setText(e);

    }
}