package com.kelompok5.taskschedulerapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Hal_Profil extends Fragment {

    public Intent in, in2;
    public Button btn_logout, btn_changepass, btn_editprofile;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.activity_profile, container, false);

        btn_editprofile = rootview.findViewById(R.id.edit_profile);
        btn_editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(getActivity(), Hal_EditProfil.class);
                startActivity(in);
            }
        });

        btn_changepass =  rootview.findViewById(R.id.change_password);
        btn_changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in2 = new Intent(getActivity(), Hal_ChangePassword.class);
                startActivity(in2);
            }
        });
    return rootview;
    }
}