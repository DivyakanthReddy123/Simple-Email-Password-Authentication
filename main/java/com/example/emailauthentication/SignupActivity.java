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

public class SignupActivity extends AppCompatActivity {
    EditText mail,pass,cpass;
    Button reg;
    TextView haveAcc;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Register");
        mail=findViewById(R.id.remail);
        pass=findViewById(R.id.rpassword);
        cpass=findViewById(R.id.rcpass);
        mAuth=FirebaseAuth.getInstance();
        reg=findViewById(R.id.regBtn);
        haveAcc=findViewById(R.id.alreadyAcc);
        haveAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=mail.getText().toString();
                String password=pass.getText().toString();
                String cpassword=cpass.getText().toString();
                if(!TextUtils.isEmpty(email) && password.equals(cpassword) && !TextUtils.isEmpty(password)){
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignupActivity.this,"Registered",Toast.LENGTH_LONG).show();
                               sendToHome();
                            }else{
                                Toast.makeText(SignupActivity.this,"Registered Failed",Toast.LENGTH_LONG).show();

                            }
                        }
                    });

                }
            }
        });
    }
    public void sendToHome(){
        startActivity(new Intent(SignupActivity.this,HomeActivity.class));
        finish();
    }
}
