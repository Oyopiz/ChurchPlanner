package com.elitechinc.churchplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnAdmin=(Button) findViewById(R.id.buttonAdmin);
        btnAdmin.setOnClickListener(new View.OnClickListener() {



            public void onClick(View v) {

                Intent myintent=new Intent(MainActivity.this,MovieScheduler.class);


                startActivity(myintent);

            }

        });


        // Button to go to Guest / customer module
        Button btnGuest =(Button) findViewById(R.id.buttonGuest);
        btnGuest.setOnClickListener(new View.OnClickListener() {



            public void onClick(View v) {

                Intent myintent=new Intent(MainActivity.this,LoginActivity.class);

                Bundle bundle=getIntent().getExtras();
                startActivity(myintent);

            }

        });


        // Button to go to About Us page
        Button btnAboutUs =(Button) findViewById(R.id.buttonAboutUs);
        btnAboutUs.setOnClickListener(new View.OnClickListener() {



            public void onClick(View v) {

                Intent myintent=new Intent(MainActivity.this,AboutUs.class);

                startActivity(myintent);

            }

        });


        // Button to go to Contact Us Page
        Button btnContactUs =(Button) findViewById(R.id.buttonContactUs);
        btnContactUs.setOnClickListener(new View.OnClickListener() {



            public void onClick(View v) {

                Intent myintent=new Intent(MainActivity.this,ContactUs.class);

                startActivity(myintent);

            }

        });

    }
}