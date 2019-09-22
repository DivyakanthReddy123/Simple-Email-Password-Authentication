package com.example.emailauthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth=FirebaseAuth.getInstance();
    }
    @Override
    protected void onStart() {
        FirebaseUser user=mAuth.getCurrentUser();
        if(user==null){
            sendToLogin();
        }

        super.onStart();
    }
    public void sendToLogin(){
        startActivity(new Intent(HomeActivity.this,LoginActivity.class));
        finish();
    }
}
