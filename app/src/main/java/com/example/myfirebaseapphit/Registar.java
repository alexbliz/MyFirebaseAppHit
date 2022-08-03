package com.example.myfirebaseapphit;

import androidx.annotation.NonNull;
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
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registar extends AppCompatActivity {

    EditText Mname, Mphone, Memail, Mpass;
    Button lahtzan;
    FirebaseAuth fAuth;
    TextView mloginbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);


        Mname= findViewById(R.id.name);
        Mphone=findViewById(R.id.phone);
        Memail=findViewById(R.id.email);
        Mpass=findViewById(R.id.password);
        lahtzan=findViewById(R.id.button);
        mloginbtn=findViewById(R.id.llogin);

        fAuth= FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }



        lahtzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = Memail.getText().toString().trim();
                String password= Mpass.getText().toString().trim();

                if (TextUtils.isEmpty(email))
                {
                    Memail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password))
                {
                    Mpass.setError("Password is required");
                    return;
                }
                if (password.length() <6)
                {
                    Mpass.setError("Password must be >=6");
                    return;
                }
              fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      if (task.isSuccessful())
                      {
                          Toast.makeText(Registar.this, "user created", Toast.LENGTH_SHORT).show();
                          startActivity(new Intent(getApplicationContext(),MainActivity.class));
                      }
                      else
                      {
                          Toast.makeText(Registar.this, "Error !"+ task.getException().getMessage() , Toast.LENGTH_SHORT).show();

                      }
                  }



              });
            }
        });



    }
}