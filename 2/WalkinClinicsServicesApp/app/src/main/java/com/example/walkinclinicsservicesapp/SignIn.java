package com.example.walkinclinicsservicesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SignIn extends InformationInsertion implements AdapterView.OnItemSelectedListener{
//used to create a new account


    private static final  String [] allRole= { "Patient", "Employee","Admin"};

    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        name = (EditText)findViewById(R.id.editTextUserName) ;
        password = (EditText)findViewById(R.id.editTextPassword) ;
        email = (EditText)findViewById(R.id.editTextEmail) ;


        spinner = (Spinner)findViewById(R.id.spinnerRole);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SignIn.this, android.R.layout.simple_spinner_item,allRole);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        createAccountButton = (Button) findViewById(R.id.createAccountButton);


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
                this.role=allRole[2];
                break;
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }


    private boolean realEmail(){
        String tmp = email.getText().toString();
        if (tmp.indexOf("@")==-1 || tmp.indexOf(".")==-1 || tmp.length()<6 ){
            return false;
        }
        return true;
    }


    private boolean addData(){

        DBHelper account_DB = new DBHelper(this);

        if(name.getText().toString().isEmpty() || email.getText().toString().isEmpty() || role.isEmpty() || password.getText().toString().isEmpty()){
            return false;
        }
        if(!realEmail()){
            return false;
        }
        if(account_DB.emailExist(email.getText().toString())){
            return false;
        }

        account_DB = new DBHelper(this);

        return account_DB.createAccount(name.getText().toString(), email.getText().toString(), role, toHashValue(password.getText().toString()));
    }


    public void createAccount(View v) {

        if(addData()){
            Bundle bundle = new Bundle();
            openNewClassAccount( bundle);
        }
        else{
            Toast.makeText(SignIn.this,"Account not created.\n",Toast.LENGTH_SHORT).show();
        }
    }

}




