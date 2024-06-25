package com.rytis.armw.ui.tournaments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rytis.armw.R;
import com.rytis.armw.models.Pogrupis;

import java.util.ArrayList;
import java.util.List;


public class RegisterPogrupis extends Fragment {

    private List<Pogrupis> pogrupisList = new ArrayList<>();
    private EditText groupNameEditText, weightEditText, ageEditText, genderEditText, handEditText;
    private Button addGroupButton, submitButton;
    public RegisterPogrupis() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        groupNameEditText = findViewById(R.id.groupNameEditText);
        weightEditText = findViewById(R.id.weightEditText);
        ageEditText = findViewById(R.id.ageEditText);
        genderEditText = findViewById(R.id.genderEditText);
        handEditText = findViewById(R.id.handEditText);
        addGroupButton = findViewById(R.id.addGroupButton);
        submitButton = findViewById(R.id.submitButton);

        // Get the data from the previous activity
        final String pavadinimas = getIntent().getStringExtra("pavadinimas");
        final String data = getIntent().getStringExtra("data");
        final String pradzia = getIntent().getStringExtra("pradzia");
        final String pabaiga = getIntent().getStringExtra("pabaiga");
        final String lokacija = getIntent().getStringExtra("lokacija");
        final int stalu_sk = getIntent().getIntExtra("stalu_sk", 0);
        final String aprasas = getIntent().getStringExtra("aprasas");

        addGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            groupNameEditText = findViewById(R.id.groupNameEditText);
            weightEditText = findViewById(R.id.weightEditText);
            ageEditText = findViewById(R.id.ageEditText);
            genderEditText = findViewById(R.id.genderEditText);
            handEditText = findViewById(R.id.handEditText);
            addGroupButton = findViewById(R.id.addGroupButton);
            submitButton = findViewById(R.id.submitButton);

            // Get the data from the previous activity
            final String pavadinimas = getIntent().getStringExtra("pavadinimas");
            final String data = getIntent().getStringExtra("data");
            final String pradzia = getIntent().getStringExtra("pradzia");
            final String pabaiga = getIntent().getStringExtra("pabaiga");
            final String lokacija = getIntent().getStringExtra("lokacija");
            final int stalu_sk = getIntent().getIntExtra("stalu_sk", 0);
            final String aprasas = getIntent().getStringExtra("aprasas");

        addGroupButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pogrupis pogrupis = new Pogrupis();
                    pogrupis.setPavadinimas(groupNameEditText.getText().toString());
                    pogrupis.setSvoris(Integer.parseInt(weightEditText.getText().toString()));
                    pogrupis.setAmzius_k(Integer.parseInt(ageEditText.getText().toString()));
                    pogrupis.setLytis(genderEditText.getText().toString());
                    pogrupis.setRanka(handEditText.getText().toString());
                    pogrupisList.add(pogrupis);

                    // Clear the input fields
                    groupNameEditText.setText("");
                    weightEditText.setText("");
                    ageEditText.setText("");
                    genderEditText.setText("");
                    handEditText.setText("");
                }
            });

        submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Championship championship = new Championship();
                    championship.setPavadinimas(pavadinimas);
                    championship.setData(data);
                    championship.setPradzia(pradzia);
                    championship.setPabaiga(pabaiga);
                    championship.setLokacija(lokacija);
                    championship.setStalu_sk(stalu_sk);
                    championship.setAprasas(aprasas);
                    championship.setPogrupis_sarasas(pogrupisList);

                    // Send the POST request
                    sendPostRequest(championship);
                }
            });
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_pogrupis, container, false);
    }
}