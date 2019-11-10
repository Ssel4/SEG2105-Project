package com.example.walkinclinicsservicesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Patient extends User {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

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
        continueButton.setText("Continue: view services.");

        toServicesList();


    }

    public void toServicesList(){
        continueButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //to service activity
                //Intent intent = new Intent(getApplicationContext(), stuff.class);
                //startActivityForResult(intent, 0);
            }
        });

    }

}
