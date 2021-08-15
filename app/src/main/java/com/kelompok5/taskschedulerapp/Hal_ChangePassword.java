package com.kelompok5.taskschedulerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Hal_ChangePassword extends AppCompatActivity {
    Button btn_bersihkan;
    EditText et1, et2, et3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepassword);

        btn_bersihkan = findViewById(R.id.btn_reset);
        et1 = findViewById(R.id.edt_old_password);
        et2 = findViewById(R.id.edt_new_password);
        et3 = findViewById(R.id.edt_confirm_password);

        btn_bersihkan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                et1.setText("");
                et2.setText("");
                et3.setText("");
            }
        });
    }
}


