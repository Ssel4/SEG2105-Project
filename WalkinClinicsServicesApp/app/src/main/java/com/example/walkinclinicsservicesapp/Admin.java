package com.example.walkinclinicsservicesapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Admin extends User {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        role = getIntent().getExtras().getString("role");
        name = getIntent().getExtras().getString("userName");
        email = getIntent().getExtras().getString("email");
//        ID = getIntent().getExtras().getString("ID");

        welcomeMsg = (TextView)findViewById(R.id.textViewWelcome) ;
        welcomeMsg.setText("Welcome "+name+" !\n You are logged in as a "+role);

//        currentID = (TextView)findViewById(R.id.textViewCurrentID);
//        currentID.setText("Current ID# : "+ID);

        currentUserName= (TextView)findViewById(R.id.textViewCurrentUserName);
        currentUserName.setText("UserName : "+name);

        currentEmail=(TextView)findViewById(R.id.textViewCurrentEmail);
        currentEmail.setText("Email : "+email);

        currentRole= (TextView)findViewById(R.id.textViewCurrentRole);
        currentRole.setText("Role : "+role);

        continueButton = (Button)findViewById(R.id.continueButton);


    }
}
