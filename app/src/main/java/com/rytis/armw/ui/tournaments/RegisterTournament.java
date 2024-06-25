package com.rytis.armw.ui.tournaments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
                try {
                    Intent intent = new Intent(RegisterTournament.this, RegisterPogrupis.class);
                    intent.putExtra("pavadinimas", pavadinimas.getText().toString());
                    intent.putExtra("data", data.getText().toString());
                    intent.putExtra("pradzia", pradzia.getText().toString());
                    intent.putExtra("pabaiga", pabaiga.getText().toString());
                    intent.putExtra("lokacija", lokacija.getText().toString());
                    intent.putExtra("stalu_sk", Integer.parseInt(stalu_sk.getText().toString()));
                    intent.putExtra("aprasas", aprasas.getText().toString());
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    Toast.makeText(RegisterTournament.this, e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}