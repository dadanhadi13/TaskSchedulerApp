package com.kelompok5.taskschedulerapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class Hal_Profil extends Fragment {

    public Intent in, in2;
    public Button btn_logout, btn_changepass, btn_editprofile;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.activity_profile, container, false);

        btn_editprofile = rootview.findViewById(R.id.edit_profile);
        btn_editprofile.setOnClickListener(v -> {
            in = new Intent(getActivity(), Hal_EditProfil.class);
            startActivity(in);
        });

        btn_changepass =  rootview.findViewById(R.id.change_password);
        btn_changepass.setOnClickListener(v -> {
            in2 = new Intent(getActivity(), Hal_ChangePassword.class);
            startActivity(in2);
        });

        btn_logout = rootview.findViewById(R.id.logout);
        btn_logout.setOnClickListener(v -> showDialog());

        return rootview;
    }

    private void showDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getLayoutInflater().getContext(), R.style.MydialogTheme);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.ui_logout, null);
        dialogBuilder.setView(dialogView);

        final Button logout = dialogView.findViewById(R.id.btn_logout);
        final Button cancel = dialogView.findViewById(R.id.btn_cancel);

        AlertDialog show = dialogBuilder.show();

        logout.setOnClickListener(v -> {
            in2 = new Intent(getActivity(), Hal_Login.class);
            startActivity(in2);
            Toast.makeText(getActivity(), "Anda berhasil logout", Toast.LENGTH_LONG).show();
        });

        cancel.setOnClickListener(v -> {
            show.dismiss();
            Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_LONG).show();
        });

    }
}