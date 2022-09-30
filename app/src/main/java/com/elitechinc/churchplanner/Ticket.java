package com.elitechinc.churchplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Ticket extends AppCompatActivity {
    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        String seats = "";
        int movieId = PickMovieGuest.id;
        int[] pictures = {R.drawable.p1, R.drawable.blade_runner, R.drawable.beauty_and_beast, R.drawable.fast_and_furious, R.drawable.wonderwoman, R.drawable.justice_league};
        Bundle bundle = getIntent().getExtras();
        final String movieTitle = bundle.getString("movieTitle");
        final String movieStartTime = bundle.getString("movieStartTime");
        final String movieDate = bundle.getString("movieDate");
        // get list of booked seats by guest from previous page
        final ArrayList<Integer> myList = (ArrayList<Integer>) getIntent().getSerializableExtra("seatList");
        for (int j = 0; j < myList.size(); j++) {
            if (j < myList.size() - 1)
                seats = seats + "S" + myList.get(j) + ",";
            else
                seats = seats + "S" + myList.get(j);
        }
        // Imageview to show image of movie booked
        ImageView img = (ImageView) findViewById(R.id.imageView3);
        img.setImageResource(pictures[movieId]);
        String ticketText =
                "    <b>Service:</b>" + " " + movieTitle + "<br /><br />" +
                        "    <b>DATE of Show :</b>" + " " + movieDate + "<br /><br />" +
                        "    <b>Time of Show :</b>" + " " + movieStartTime + "<br /><br />" +
                        "    <b>Service No :</b>" + " " + 1 + "<br /><br />" +
                        "    <b>Seats :</b>" + " " + seats + "<br /><br />";

        TextView tv = (TextView) findViewById(R.id.ticketContent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv.setText(Html.fromHtml(ticketText, Html.FROM_HTML_MODE_COMPACT));
        } else
            tv.setText(Html.fromHtml(ticketText));
        // Button to book another movie
        Button btnBookAnother = (Button) findViewById(R.id.btnbookAnother);
        btnBookAnother.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myintent = new Intent(Ticket.this, PickMovieGuest.class);
                startActivity(myintent);
            }
        });
        // Button to signout
        Button btnSignout = (Button) findViewById(R.id.btnLogout);
        btnSignout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myintent = new Intent(Ticket.this, MainActivity.class);
                startActivity(myintent);

            }
        });
    }

}