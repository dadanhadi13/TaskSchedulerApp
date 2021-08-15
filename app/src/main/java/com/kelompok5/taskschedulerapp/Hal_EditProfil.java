package com.kelompok5.taskschedulerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Hal_EditProfil extends AppCompatActivity {
    Button btn_reset;
    EditText et1, et2, et3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofil);

        btn_reset = findViewById(R.id.btn_reset2);
        et1 = findViewById(R.id.et_nama);
        et2 = findViewById(R.id.et_email);
        et3 = findViewById(R.id.et_job);

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et1.setText("");
                et2.setText("");
                et3.setText("");
            }
        });
    }
}