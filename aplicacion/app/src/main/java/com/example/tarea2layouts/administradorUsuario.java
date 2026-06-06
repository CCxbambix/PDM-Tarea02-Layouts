package com.example.tarea2layouts;

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

import org.w3c.dom.Text;

public class administradorUsuario extends AppCompatActivity {

    private Button bttnInicio;

    private Button bttnCrearCuenta;

    private TextInputLayout layOutName;
    private TextInputLayout layOutPass;

    private TextView nombre;

    private TextView contraseña;

    private Toast toast;
    private SharedPreferences sharedPref;
    private final String nameFile = "myPreference";

    private final String KEY_NUM_CUENTAS = "numCuentas";
    private Toast toastCuenta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_administrador_usuario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bttnInicio = (Button) findViewById(R.id.button3);
        bttnCrearCuenta = (Button) findViewById(R.id.button5);
        nombre = (TextView) findViewById(R.id.textNombreU);
        contraseña = (TextView) findViewById(R.id.textContrasenaU) ;
        layOutPass = (TextInputLayout) findViewById(R.id.textInputLayout5);
        layOutName = (TextInputLayout) findViewById(R.id.textInputLayout4);
        sharedPref = getSharedPreferences(nameFile,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        bttnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(administradorUsuario.this, MainActivity.class);
                startActivity(intent);
            }
        });
        bttnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cadenaNombre= nombre.getText().toString();
                if(cadenaNombre!=null){
                    if (cadenaNombre.length() == 0 ){
                        toastCuenta.makeText(getApplicationContext(),"Error: por favor introduce algun nombre para crear la cuenta ", Toast.LENGTH_SHORT).show();
                        layOutName.setError("Nombre vacio");
                        return;
                    }
                }
                layOutName.setError(null);
                String cadenaContrasena = contraseña.getText().toString();
                if(cadenaContrasena!=null){
                    if (cadenaContrasena.length() == 0 ){
                        toastCuenta.makeText(getApplicationContext(),"Error: por favor introduce alguna contraseña para crear la cuenta", Toast.LENGTH_SHORT).show();
                        layOutPass.setError("Contraseña vacia");
                        return;
                    }
                }
                layOutPass.setError(null);
                int num = sharedPref.getInt(KEY_NUM_CUENTAS,0);
                String keyUsuario = "usuario" +num;
                editor.putString(keyUsuario,cadenaNombre);
                String keyContrasena = "contraseña" + num;
                editor.putString(keyContrasena,cadenaContrasena);
                editor.apply();
                editor.putInt(KEY_NUM_CUENTAS,num+1);
                editor.commit();
                toastCuenta.makeText(getApplicationContext(),"Se creo el usuario con exito", Toast.LENGTH_SHORT).show();
            }
        });

    }
}