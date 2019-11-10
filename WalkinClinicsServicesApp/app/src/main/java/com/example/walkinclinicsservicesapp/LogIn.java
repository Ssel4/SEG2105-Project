package com.example.walkinclinicsservicesapp;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.database.Cursor;
import android.widget.Toast;

public class LogIn extends InformationInsertion implements AdapterView.OnItemSelectedListener{
//used to log in to an already existing account


    private static final  String [] allRole= { "Patient", "Employee","Admin"};
    private Button logInButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        name = (EditText)findViewById(R.id.editTextUserName) ;
        email =(EditText)findViewById(R.id.editTextEmail);
        password = (EditText)findViewById(R.id.editTextPassword);


        spinner = (Spinner)findViewById(R.id.spinnerRole);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(LogIn.this, android.R.layout.simple_spinner_item,allRole);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        logInButton = (Button)findViewById(R.id.logInButton);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                this.role= allRole[0];
                break;
            case 1:
                this.role= allRole[1];
                break;
            case 2:
                this.role= allRole[2];
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }




    public void logInAccount(View v) {

        DBHelper account_DB = new DBHelper(this);
        if(account_DB.logInAccount(name.getText().toString(),email.getText().toString(),role,toHashValue(password.getText().toString()))){

            Bundle bundle = new Bundle();
            openClassAccount(bundle);
        }
    }



}
