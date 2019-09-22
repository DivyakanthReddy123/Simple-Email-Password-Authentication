package com.example.emailauthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText email,pass;
    FirebaseAuth mAuth;
    TextView needAcc;
    Button log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Login");
        mAuth=FirebaseAuth.getInstance();
        email=findViewById(R.id.email);
        pass=findViewById(R.id.password);
        log=findViewById(R.id.loginBtn);
        needAcc=findViewById(R.id.needAcc);
        needAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a=email.getText().toString();
                String b=pass.getText().toString();
                if(!TextUtils.isEmpty(a) && !TextUtils.isEmpty(b)){
                    mAuth.signInWithEmailAndPassword(a, b)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(LoginActivity.this,"Welcome",Toast.LENGTH_LONG).show();
                                        sendToHome();
                                    }else{
                                        Toast.makeText(LoginActivity.this,"Failed to Login",Toast.LENGTH_LONG).show();

                                    }
                                }
                            });
                }

            }
        });
    }

    @Override
    protected void onStart() {
        FirebaseUser user=mAuth.getCurrentUser();
        if(user!=null){
            sendToHome();
        }

        super.onStart();
    }

    public void sendToHome(){
        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
        finish();
    }

}
