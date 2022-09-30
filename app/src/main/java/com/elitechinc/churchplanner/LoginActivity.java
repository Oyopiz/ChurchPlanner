package com.elitechinc.churchplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import com.elitechinc.churchplanner.Classes.databaseValidateUserClass;

public class LoginActivity extends AppCompatActivity {
    TableLayout theView;
    SQLiteDatabase wdb;
    EditText edit_name;
    EditText edit_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        wdb = databaseValidateUserClass.getWritable(this);
        theView = (TableLayout) findViewById(R.id.theTable);
        edit_name = (EditText) findViewById(R.id.editName);
        edit_name.setInputType(InputType.TYPE_NULL);
        edit_email = (EditText) findViewById(R.id.editEmail);

// select query to select user and email from db and validate according to login or register button clicked
        final String selectQuery = "SELECT  UserName,Email  FROM USER ";

        Button bRegister = (Button) findViewById(R.id.btnRegister);
      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            bRegister.setFocusedByDefault(true);
        } */

        // Validate when  user clicks on register
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText Name = (EditText)findViewById(R.id.editName);
                EditText Email = (EditText)findViewById(R.id.editEmail);
                boolean isValid = databaseValidateUserClass.checkUser1(Name.getText().toString(), Email.getText().toString(), selectQuery);
                // if username or email is blank
                if (TextUtils.isEmpty(Name.getText().toString()) || TextUtils.isEmpty(Email.getText().toString()))
                {


                    Name.setText(null);
                    Email.setText(null);
                    Toast.makeText(LoginActivity.this, "Please enter User Name or Email", Toast.LENGTH_SHORT).show();

                }
                else if(isValid)   // if username already exists in database
                {

                    Name.setText(null);
                    Name.setHint("Enter your Name");
                    Email.setText(null);
                    Email.setHint("Enter Email");
                    Toast.makeText(LoginActivity.this, "This username is already registered.Please Pick another or Log In!!", Toast.LENGTH_SHORT).show();

                }
                else{     // if username is not found in database , then register new user
                    ContentValues values = new ContentValues();
                    values.put("UserName", Name.getText().toString());
                    values.put("Email", Email.getText().toString());
                    long newRowId = wdb.insert("USER", null, values);
                    Toast.makeText(LoginActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                }
            }  });

        //  Validate when  user clicks on LOGIN
        Button login=(Button) findViewById(R.id.btnLogIn);
        login.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name = edit_name.getText().toString();


                String email = edit_email.getText().toString();
                if (name != " " && email != " ") {

                    boolean isValid = databaseValidateUserClass.checkUser(name, email, selectQuery);

                    if (isValid) {  // if username and email are valid , go to PickMovie Activity

                        Intent myIntent = new Intent(LoginActivity.this, PickMovieGuest.class);

                        startActivity(myIntent);
                    } else {        // if for a registered user ,email id is not correct
                        Toast.makeText(LoginActivity.this, "Please enter Correct User Name or email", Toast.LENGTH_SHORT).show();

                        edit_name.setText(null);
                        edit_name.setHint("Enter your Name");
                        edit_email.setText(null);
                        edit_name.setHint("Enter Email");

                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Please enter User Name or Email", Toast.LENGTH_SHORT).show();

                }
            }  }));
    }
}