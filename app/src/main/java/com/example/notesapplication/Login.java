package com.example.notesapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText userName,password;
    Button btnLogin;
    CheckBox rememberMe;
    Context context=this;
    Shared_Preferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        init();
        if(preferences.getBooleanValue(context,"remember")){
            userName.setText(preferences.getUserName(context,"username"));
            rememberMe.setChecked(preferences.getBooleanValue(context,"remember"));
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userName.getText().toString().equals(getString(R.string.userName)) &&
                        password.getText().toString().equals(getString(R.string.password))){
                    Intent intent=new Intent(context,Notes.class);
                    startActivity(intent);
                    if(rememberMe.isChecked()){
                        preferences.saveText(context,"username",userName.getText().toString());
                    }else {
                        preferences.saveText(context,"password",password.getText().toString());
                    }
                    preferences.saveBoolean(context,"remember",rememberMe.isChecked());
                }else{
                    Toast.makeText(context,"Wrong username or password!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void init(){
        userName=findViewById(R.id.userName);
        password=findViewById(R.id.password);
        btnLogin=findViewById(R.id.btnLogin);
        rememberMe=findViewById(R.id.rememberMe);
        preferences=new Shared_Preferences();
    }
}