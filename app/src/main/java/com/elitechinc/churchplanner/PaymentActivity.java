package com.elitechinc.churchplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.elitechinc.churchplanner.Classes.MovieDetailsEntry;
import com.elitechinc.churchplanner.Classes.databaseMovieDetailsClass;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class PaymentActivity extends AppCompatActivity {
    SQLiteDatabase wdb;

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wdb = databaseMovieDetailsClass.getWritable(this);
        setContentView(R.layout.activity_payment);
        Bundle bundle = getIntent().getExtras();
        final String movieTitle = bundle.getString("movieTitle");
        final int movieId = bundle.getInt("movieId");
        final String movieStartTime = bundle.getString("movieStartTime");
        final int infants = bundle.getInt("infants");
        final int adults = bundle.getInt("adults");
        final int seniors = bundle.getInt("seniors");
        final String movieDate = bundle.getString("movieDate");
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        // list of selected seats from previous page
        final ArrayList<Integer> myList = (ArrayList<Integer>) getIntent().getSerializableExtra("selectedSeatsList");

        try {
            long newRowId = 0;
            ContentValues values = new ContentValues();
            for (int j = 0; j < myList.size(); j++) {
                // Update MovieDetails table with BookedSeatNo , there will be one row inserted for each seat booked
                values.put(MovieDetailsEntry.COLUMN_NAME_Movie_Date, movieDate);
                values.put(MovieDetailsEntry.COLUMN_NAME_Movie_Number, movieId);
                values.put(MovieDetailsEntry.COLUMN_NAME_Movie_Name, movieTitle);
                values.put(MovieDetailsEntry.COLUMN_NAME_Movie_startTime, movieStartTime);
                values.put(MovieDetailsEntry.COLUMN_NAME_Movie_BookedSeatNo, myList.get(j));
                newRowId = wdb.insert(MovieDetailsEntry.TABLE_NAME, null, values);

            }
        } catch (SQLiteException exception) {
            Toast.makeText(PaymentActivity.this, "Error", Toast.LENGTH_SHORT).show();

        }

        // calculate total and tax and display on Receipt

        Double infantPrice = 5.0;
        Double adultPrice = 10.0;
        Double seniorPrice = 7.0;
        DecimalFormat df2 = new DecimalFormat(".##");

        Double totalPrice = infants * infantPrice + adults * adultPrice + seniors * seniorPrice;
        Double GST = Double.parseDouble(String.format("%.2f", totalPrice * 0.05f));
        Double finalAmount = totalPrice + GST;
        TextView tv = (TextView) (findViewById(R.id.receiptContent));

        // Build a HTML foramtted list showing  details of purchase

        String receiptText =
                "    <b>Service :</b>" + " " + movieTitle + "<br /><br />" +
                        "    <b>DATE of Show :</b>" + " " + movieDate + "<br /><br />" +
                        "    <b>Time of Show :</b>" + " " + movieStartTime + "<br /><br />" +
                        "    <b>Booked on :</b>" + " " + currentDateTimeString + "<br /><br />";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv.setText(Html.fromHtml(receiptText, Html.FROM_HTML_MODE_COMPACT));
        } else
            tv.setText(Html.fromHtml(receiptText));


        // Button to Go to print tickets Page
        Button btnBack = (Button) findViewById(R.id.btnPrintTicket);


        btnBack.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {

                Intent myintent = new Intent(PaymentActivity.this, Ticket.class);
                myintent.putExtra("movieDate", movieDate);
                myintent.putExtra("movieTitle", movieTitle);
                myintent.putExtra("movieId", movieId);
                myintent.putExtra("movieStartTime", movieStartTime);
                myintent.putExtra("seatList", myList);

                startActivity(myintent);


            }

        });

    }
}