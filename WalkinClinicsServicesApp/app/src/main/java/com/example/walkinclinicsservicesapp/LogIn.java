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
        logInAccount();
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


    public void logInAccount(){
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor nameResult = account_DB.getSpecificAccountData(name.getText().toString(),email.getText().toString(),role);

                if(nameResult.getCount()>0){
                    while(nameResult.moveToNext()){

                        if(nameResult.getString(1).equalsIgnoreCase(name.getText().toString()) && nameResult.getString(2).equalsIgnoreCase(email.getText().toString()) && nameResult.getString(3).equalsIgnoreCase(role)&& nameResult.getString(4).equalsIgnoreCase(toHashValue(password.getText().toString()))){
                            Bundle bundle = new Bundle();
                            bundle.putString("ID",nameResult.getString((0)));
                            openClassAccount(bundle);
                        }

                        else{
                            Toast.makeText(LogIn.this,"Incorrect account info.",Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        });
    }

}
