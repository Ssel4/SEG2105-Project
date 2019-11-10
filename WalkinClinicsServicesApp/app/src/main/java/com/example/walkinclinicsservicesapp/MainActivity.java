package com.example.walkinclinicsservicesapp;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

//new account
    public void SignIn(View view) {
        Intent intent = new Intent(getApplicationContext(), SignIn.class);
        startActivityForResult (intent,0);
    }


// existing account
    public void LogIn(View view) {
        Intent intent = new Intent(getApplicationContext(), LogIn.class);
        startActivityForResult (intent,0);
    }


}
