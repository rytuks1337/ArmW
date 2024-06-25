package com.rytis.armw.ui.tournaments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import com.rytis.armw.R;
import com.rytis.armw.models.Varzybos;

public class RegisterTournament extends AppCompatActivity {

    private Varzybos varzybos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_tournament);

        EditText pavadinimas = findViewById(R.id.nameEditText);
        EditText data = findViewById(R.id.dateEditText);
        EditText pradzia = findViewById(R.id.startEditText);
        EditText pabaiga = findViewById(R.id.endEditText);
        EditText lokacija = findViewById(R.id.locationEditText);
        EditText stalu_sk = findViewById(R.id.tablesEditText);
        EditText aprasas = findViewById(R.id.descriptionEditText);

        Button kitas = findViewById(R.id.nextButton);
        kitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                varzybos = new Varzybos();
                varzybos.setPavadinimas(pavadinimas.getText().toString());
                varzybos.setData(data.getText().toString());
                varzybos.setPradzia(pradzia.getText().toString());
                varzybos.setPabaiga(pabaiga.getText().toString());
                varzybos.setLokacija(lokacija.getText().toString());
                varzybos.setStalu_sk(Integer.parseInt(stalu_sk.getText().toString());
                varzybos.setAprasas(aprasas.getText().toString());
            }
        });
    }
    private void openPogrupisFr(){
        RegisterPogrupis registerPogrupis = RegisterPogrupis.newInstance(Varzybos);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.regist, pogrupisFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}