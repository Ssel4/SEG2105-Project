package com.example.walkinclinicsservicesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    private String name, role;
    private TextView welcomeMsg, currentUserName, currentRole;
    private Button continueButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        role = getIntent().getExtras().getString("role");
        name = getIntent().getExtras().getString("userName");

        welcomeMsg = (TextView)findViewById(R.id.textViewWelcome) ;
        welcomeMsg.setText("Welcome "+name+" !\n You are logged in as a "+role);

        currentUserName= (TextView)findViewById(R.id.textViewCurrentUserName);
        currentUserName.setText("UserName : "+name);

        currentRole= (TextView)findViewById(R.id.textViewCurrentRole);
        currentRole.setText("Role : "+role);

        continueButton = (Button)findViewById(R.id.continueButton);


        switch (role) {
            case "Patient":
                break;
            case "employee":
                break;
            case "Admin":
                break;
        }

    }


}
