package com.example.tarea2layouts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class carrito extends AppCompatActivity {

    private Button bttnRegreso;

    private String pedidotxt;
    private TextView vistaPedido;
    private final String nameFile = "myPreference";
    private  String KEY_PEDIDOS = "pedido";

    private ArrayList<String> pedidosList;

    private SharedPreferences sharedPreferences;

    private ListView listV;
    Intent intendTres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_carrito);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        intendTres= getIntent();
        pedidotxt= intendTres.getStringExtra(chilaquiles.EXTRA_PEDIDO);
        bttnRegreso = (Button) findViewById(R.id.bttnRegresoC);
        sharedPreferences=getSharedPreferences(nameFile,MODE_PRIVATE);
        Set<String> setPedidos = sharedPreferences.getStringSet(KEY_PEDIDOS, new HashSet<String>());
        pedidosList = new ArrayList<>(setPedidos);
        listV= (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pedidosList);
        listV.setAdapter(adapter);
        bttnRegreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(carrito.this, chilaquiles.class);
                startActivity(intent);
            }
        });
    }
}