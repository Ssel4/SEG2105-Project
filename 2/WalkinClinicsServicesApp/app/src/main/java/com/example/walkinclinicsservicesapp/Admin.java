package com.example.walkinclinicsservicesapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.content.Intent;


//

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;
//


public class Admin extends User {


    private Button continueButton;

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


        continueButton =  (Button) findViewById(R.id.continueButton);
        continueToNext();


    }

    public void continueToNext(){
        continueButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(getApplicationContext(), ManageServices.class);
                  startActivityForResult(intent, 0);
              }
        });
    }
    /*
        public void addService(){




            addServiceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //instantiate the popup.xml layout file
                    LayoutInflater layoutInflater = (LayoutInflater) tmp.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View customView = layoutInflater.inflate(R.layout.activity_pop_up_window,null);

                    //instantiate popup window
                    addServicePopupWindow = new PopupWindow(customView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);


                    // create the popup window
                    int width = RelativeLayout.LayoutParams.WRAP_CONTENT;
                    int height = RelativeLayout.LayoutParams.WRAP_CONTENT;
                    final PopupWindow addServicePopupWindow = new PopupWindow(customView, width, height, true);


                    //display the popup window
                    addServicePopupWindow.showAtLocation(alpha, Gravity.CENTER, 0, 0);
                }
            });

         */




}


