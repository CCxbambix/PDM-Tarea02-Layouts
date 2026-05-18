package com.example.tarea2layouts;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class chilaquiles extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    Intent intendDos;
    private String nombretxt;
    private TextView vistaNombre;

    static final String EXTRA_PEDIDO = "pedido";

    private RadioGroup radiobttn;
    private RadioGroup radioProteina;
    private Button bttnChilaquiles;
    private CheckBox cebolla;
    private CheckBox queso;
    private CheckBox crema;
    private CheckBox frijoles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chilaquiles);

        Toolbar toolbars = findViewById(R.id.toolbar);
        setSupportActionBar(toolbars);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        intendDos = getIntent();
        nombretxt = intendDos.getStringExtra(MainActivity.EXTRA_NOMBRE);
        vistaNombre = findViewById(R.id.textView7);
        vistaNombre.setText(nombretxt);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbars, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        if (drawer != null) {
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.carrito){
            Log.d("ToolBar",(String) "action_Carrito :) ");
            goToNextActivity();
            return true;
        } else if (id==R.id.configuracion) {
            Log.d("ToolBar",(String) "action_Configuracion :) ");
        } else if (id== R.id.cuenta) {
            Log.d("ToolBar",(String) "action_Cerrar Sesión :) ");
            Intent intent = new Intent(chilaquiles.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        int itemId = menuItem.getItemId();
        if (itemId == R.id.nav_agregar){
            drawerLayout.closeDrawer(GravityCompat.START);
            Log.d("Navigation", (String) "Action Agregar :)");
                    return true;
        }else if(itemId == R.id.nav_eliminar){
            drawerLayout.closeDrawer(GravityCompat.START);
            Log.d("Navigation", (String) "Action Eliminar :)");
            return true;
        }else if(itemId == R.id.nav_modificar){
            drawerLayout.closeDrawer(GravityCompat.START);
            Log.d("Navigation", (String) "Action Modificar :)");
            return true;
        }else if(itemId == R.id.nav_chat){
            drawerLayout.closeDrawer(GravityCompat.START);
            Log.d("Navigation", (String) "Action Chat :)");
            return true;
        }
        return false;
    }

    private void goToNextActivity() {


        String pedido= "Chilaquiles";
        radiobttn = findViewById(R.id.radioGroup2);
        int idRadioBttn= radiobttn.getCheckedRadioButtonId();

        if (idRadioBttn == R.id.radioButton5){
            pedido += " Verdes";
        }else if(idRadioBttn == R.id.radioButton6) {
            pedido += "Rojos";
        }else{
            pedido+= "ninguna";
        }
        pedido += ", con proteina: ";
        radioProteina = findViewById(R.id.radioGroup3);
        int idProteina = radioProteina.getCheckedRadioButtonId();
        if(idProteina == R.id.radioButton12){
            pedido += "Huevo";
        }else if(idProteina == R.id.radioButton13){
            pedido += "Pehuga de pollo";
        }else if(idProteina == R.id.radioButton14){
        pedido += "Bistec";
        }else{
            pedido+= "ninguna";
        }
        cebolla = findViewById(R.id.checkBox4);
        queso = findViewById(R.id.checkBox5);
        crema = findViewById(R.id.checkBox6);
        frijoles = findViewById(R.id.checkBox7);
        pedido+= "Con complementos";
        if(cebolla.isChecked()){
            pedido += "cebolla";
        }
        if(queso.isChecked()){
            pedido += "queso";
        }
        if(crema.isChecked()){
            pedido += "crema";
        }
        if(frijoles.isChecked()){
            pedido += "frijoles";
        }

        Intent intent = new Intent(chilaquiles.this, carrito.class);
        intent.putExtra(EXTRA_PEDIDO, pedido);
        startActivity(intent);
    }


}