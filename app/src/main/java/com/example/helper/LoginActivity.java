package com.example.helper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences pref;
    EditText account;
    CheckBox rememberAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref = getSharedPreferences("config", MODE_PRIVATE);
        Button login = this.findViewById(R.id.login);
        account = LoginActivity.this.findViewById(R.id.account);
        rememberAccount = this.findViewById(R.id.rememberAccount);
        if(pref.getBoolean("RememberAccount", false))
        {
            rememberAccount.setChecked(true);
            account.setText(pref.getString("Account", ""));
        }
        login.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText password = LoginActivity.this.findViewById(R.id.password);
                pref.edit().putBoolean("RememberAccount", rememberAccount.isChecked()).commit();

                if (account.getText().toString().equals("user") && password.getText().toString().equals("123456"))
                {
                    Toast.makeText(LoginActivity.this,"登入成功",Toast.LENGTH_SHORT).show();
                    if(rememberAccount.isChecked())
                    {
                        pref.edit().putString("Account", account.getText().toString()).commit();
                    } else {
                        pref.edit().putString("Account", "").commit();
                    }
                    startActivity(new Intent(LoginActivity.this,MenuActivity.class));
                }else {
                    Toast.makeText(LoginActivity.this,"帳號密碼錯誤",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}