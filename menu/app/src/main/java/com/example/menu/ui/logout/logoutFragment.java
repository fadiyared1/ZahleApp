package com.example.menu.ui.logout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.menu.LoginActivity;
import com.example.menu.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link logoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class logoutFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        super.onCreate(savedInstanceState);
    }
}