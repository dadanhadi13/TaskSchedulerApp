package com.kelompok5.taskschedulerapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Hal_Login extends AppCompatActivity {
    EditText username, password;
    Button btn_login, exit;
    TextView btn_signup;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.tx_username);
        password = findViewById(R.id.tx_password);
        btn_login = findViewById(R.id.btn_login);
        btn_signup = findViewById(R.id.tx_signup);
        exit = findViewById(R.id.btn_keluar);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameKey = username.getText().toString().trim();
                String passwordKey = password.getText().toString().trim();

                if (usernameKey.equals("admin") && passwordKey.equals("admin")) {
                    Toast.makeText(getApplicationContext(), "Login berhasil", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(Hal_Login.this, NavBottom.class);
                    Hal_Login.this.startActivity(in);
                    finish();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Hal_Login.this);
                    builder.setMessage("Username atau Password Anda salah!")
                            .setNegativeButton("Retry", null).create().show();
                }
            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent in = new Intent(Hal_Login.this, Hal_Register.class);
                Hal_Login.this.startActivity(in);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder build = new AlertDialog.Builder(Hal_Login.this);
                build.setMessage("Yakin akan keluar aplikasi?").setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
            }
        });
        }
    }
