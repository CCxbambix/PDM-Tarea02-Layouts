package com.example.tarea2layouts;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button bttn;
    private TextView nombre;
    static final String EXTRA_NOMBRE = "nombre";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //Comente una linea para poder ver la otra layout
        setContentView(R.layout.activity_main);
        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
        //  Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        // v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        // return insets;
        //});
        bttn = (Button) findViewById(R.id.button);
        nombre = (TextView) findViewById(R.id.editNombre);
        bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextActivity();
            }
        });
    }

    private void goToNextActivity() {
        Intent intent = new Intent(MainActivity.this, chilaquiles.class);
        intent.putExtra(EXTRA_NOMBRE, nombre.getText().toString());
        startActivity(intent);
    }
}

