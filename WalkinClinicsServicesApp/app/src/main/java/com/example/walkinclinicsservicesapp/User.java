package com.example.walkinclinicsservicesapp;


import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class User extends AppCompatActivity {
    protected String name, role, email, ID;
    protected TextView welcomeMsg,currentID, currentUserName, currentRole, currentEmail ;
    protected Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);




    }


}