package com.example.tarea2layouts;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private Button bttn;
    private TextView nombre;
    private TextInputLayout layOutPass;
    static final String EXTRA_NOMBRE = "nombre";
    private Toast toast;
    private TextView contraseña;
    private TextInputLayout layOutName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
        //  Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        // v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        // return insets;
        //});
        bttn = (Button) findViewById(R.id.button);
        nombre = (TextView) findViewById(R.id.editNombre);
        contraseña = (TextView) findViewById(R.id.editcontrasena);
        layOutPass = (TextInputLayout) findViewById(R.id.textInputLayout2);
        layOutName = (TextInputLayout) findViewById(R.id.textInputLayout);
        bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextActivity();
            }
        });
    }

    private void goToNextActivity() {
        String cadenaNombre= nombre.getText().toString();
        if(cadenaNombre!=null){
            if (cadenaNombre.length() == 0 ){
                toast.makeText(this,"Error: por favor introduce algun nombre para iniciar sesión ", Toast.LENGTH_SHORT).show();
                layOutName.setError("Nombre vacia");
                return;
            }
        }
        layOutName.setError(null);
        String cadenaContraseña = contraseña.getText().toString();
        if(cadenaContraseña!=null){
            if (cadenaContraseña.length() == 0 ){
                toast.makeText(this,"Error: por favor introduce alguna contraseña para iniciar sesión", Toast.LENGTH_SHORT).show();

                layOutPass.setError("Contraseña vacia");
                return;
            }
        }
        layOutPass.setError(null);
        Intent intent = new Intent(MainActivity.this, chilaquiles.class);
        intent.putExtra(EXTRA_NOMBRE, nombre.getText().toString());
        startActivity(intent);
    }
}

