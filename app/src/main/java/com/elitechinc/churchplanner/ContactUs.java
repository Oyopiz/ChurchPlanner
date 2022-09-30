package com.elitechinc.churchplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        Button btnBack = (Button) findViewById(R.id.btnBack);

// button to go back to HomePage
        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myintent = new Intent(ContactUs.this, MainActivity.class);
                startActivity(myintent);

            }
        });
        TextView tv = (TextView) findViewById(R.id.aboutUscontent);
        tv.setText(Html.fromHtml(getString(R.string.contactUsContent)));
    }
}