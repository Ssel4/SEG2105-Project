package com.example.walkinclinicsservicesapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import androidx.appcompat.app.AppCompatActivity;

public class InformationInsertion extends AppCompatActivity {

    protected static int totalUsers=0;
    protected EditText name, password, email;
    protected String role;
    protected Spinner spinner;
    protected static final String[] allRole = {"Patient", "Employee"};
    protected DataBaseHelper account_DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        account_DB = new DataBaseHelper(this);

    }

    private static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    private static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
    protected static String toHashValue(String password){
        try{
            return toHexString(getSHA(password));
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e);
        }
        return"";
    }

    protected void openClassAccount( Bundle bundle){

        bundle.putString("userName",name.getText().toString());
        bundle.putString("role",role);
        bundle.putString("email",email.getText().toString());
        bundle.putString("password",toHashValue(password.getText().toString()));

        switch (role) {
            case "Patient":

                Intent intentPatient = new Intent(getApplicationContext(), Patient.class);
                intentPatient.putExtras(bundle);
                startActivityForResult(intentPatient, 0);

                break;

            case "Employee":
                Intent intentEmployee = new Intent(getApplicationContext(), Employee.class);
                intentEmployee.putExtras(bundle);
                startActivityForResult (intentEmployee,0);

                break;
            case "Admin":
                Intent intentAdmin = new Intent(getApplicationContext(), Admin.class);
                intentAdmin.putExtras(bundle);
                startActivityForResult (intentAdmin,0);

                break;
        }

    }

    protected void openNewClassAccount( Bundle bundle) {


        bundle.putString("userName",name.getText().toString());
        bundle.putString("role",role);
        bundle.putString("email",email.getText().toString());
        bundle.putString("password",toHashValue(password.getText().toString()));

        switch (role) {
            case "Patient":

                Intent intentPatient = new Intent(getApplicationContext(), Patient.class);
                intentPatient.putExtras(bundle);
                startActivityForResult(intentPatient, 0);

                break;

            case "Employee":
                Intent intentEmployee = new Intent(getApplicationContext(), Employee.class);
                intentEmployee.putExtras(bundle);
                startActivityForResult (intentEmployee,0);

                break;
            case "Admin":
                Intent intentAdmin = new Intent(getApplicationContext(), Admin.class);
                intentAdmin.putExtras(bundle);
                startActivityForResult (intentAdmin,0);

                break;
        }

    }
}