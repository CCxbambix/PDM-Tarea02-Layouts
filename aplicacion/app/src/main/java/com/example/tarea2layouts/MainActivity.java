package com.example.tarea2layouts;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button bttn;

    private Button bttnCuenta;
    private TextView nombre;
    private TextInputLayout layOutPass;
    static final String EXTRA_NOMBRE = "nombre";
    private Toast toast;
    private TextView contraseña;
    private TextInputLayout layOutName;

    private ArrayList<String> usuariosList;

    private SharedPreferences sharedPreferences;
    private final String nameFile = "myPreference";
    private final String KEY_NUM_CUENTAS = "numCuentas";
    private String user = "usuario";
    String keyContraseña = "contraseña";

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
        bttnCuenta = (Button) findViewById(R.id.button2);
        nombre = (TextView) findViewById(R.id.editNombre);
        contraseña = (TextView) findViewById(R.id.editcontrasena);
        layOutPass = (TextInputLayout) findViewById(R.id.textInputLayout2);
        layOutName = (TextInputLayout) findViewById(R.id.textInputLayout);
        ArrayList<String> usuariosList = new ArrayList<>();
        sharedPreferences = getSharedPreferences(nameFile,MODE_PRIVATE);
        llenarLista(usuariosList);
        bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextActivity();
            }
        });
        bttnCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivityCuenta();
            }
        });
    }

    private void goToNextActivity() {
        String cadenaNombre= nombre.getText().toString();
        if(cadenaNombre!=null){
            if (cadenaNombre.length() == 0 ){
                toast.makeText(this,"Error: por favor introduce algun nombre para iniciar sesión ", Toast.LENGTH_SHORT).show();
                layOutName.setError("Nombre vacio");
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
        for(int j = 0; j<= sharedPreferences.getInt(KEY_NUM_CUENTAS,0);j++){
            String numUser = String.valueOf(j);
            if (cadenaNombre.equals(sharedPreferences.getString(user+numUser,null))){
                if(cadenaContraseña.equals(sharedPreferences.getString(keyContraseña+numUser,null))){
                    layOutName.setError(null);
                    layOutPass.setError(null);
                    Intent intent = new Intent(MainActivity.this, chilaquiles.class);
                    intent.putExtra(EXTRA_NOMBRE, nombre.getText().toString());
                    startActivity(intent);
                    return;
                }else{
                    toast.makeText(this,"Error: contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    layOutPass.setError("Contraseña incorrecta");
                    return;
                }
            }
        }
        toast.makeText(this,"Error: este usuario no existe", Toast.LENGTH_SHORT).show();
        layOutName.setError("Usuario no existente");


    }

    private void goToActivityCuenta(){
        Intent intent = new Intent(MainActivity.this, administradorUsuario.class);
        startActivity(intent);
    }

    private void llenarLista(List<String> list){
        for (int i = 0; i<=sharedPreferences.getInt(KEY_NUM_CUENTAS,0 );i++){
            String numTemp = String.valueOf(i);
            list.add(user + numTemp);
        }
    }
}

