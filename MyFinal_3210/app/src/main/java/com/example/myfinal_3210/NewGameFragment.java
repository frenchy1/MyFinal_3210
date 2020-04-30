package com.example.myfinal_3210;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class NewGameFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spinner1, spinner2;
    private PlayerDB db;
    // this will contain the selected player
    String sPlayer1 = null, sPlayer2 = null;

    public NewGameFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_newgame, container, false);
        // in here link the controls with view.findViewById
        // You will also need this.getActivity().getSharedPreferences if you use things like shared preferences.
        // this.getActivity may also be helpful for communicating between fragments
        this.spinner1 = (Spinner) view.findViewById(R.id.player1);
        this.spinner2 = (Spinner) view.findViewById(R.id.player2);
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        db = new PlayerDB(this.getContext());
        updateDisplay();
        return view;
    }

    private void updateDisplay() {
        // create a List of Map<String, ?> objects
        ArrayList<HashMapToString> data = db.getPlayers();
        ArrayAdapter<HashMapToString> adapter =
                new ArrayAdapter<HashMapToString>(this.getContext(),  android.R.layout.simple_spinner_dropdown_item, data);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
    }

    //*****************************************************
    // Event handler for the Spinner
    //*****************************************************
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position,
                               long id) {
        ArrayList<HashMapToString> data = db.getPlayers();
        if(parent.getId() == this.spinner1.getId()){
            this.sPlayer1 = data.get(position).get("id");
        }else{
            this.sPlayer2 = data.get(position).get("id");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing
    }

    public void restart() {
        this.updateDisplay();
        Toast.makeText(this.getContext(), "Restart Game", Toast.LENGTH_SHORT).show();
    }
}

