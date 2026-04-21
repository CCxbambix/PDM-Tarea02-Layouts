package com.example.tarea2layouts;

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

import com.google.android.material.navigation.NavigationView;

public class chilaquiles extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chilaquiles);

        Toolbar toolbars = findViewById(R.id.toolbar);
        setSupportActionBar(toolbars);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

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
            return true;
        } else if (id==R.id.configuracion) {
            Log.d("ToolBar",(String) "action_Configuracion :) ");
        } else if (id== R.id.cuenta) {
            Log.d("ToolBar",(String) "action_Cuenta :) ");
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
            Log.d("Navigation", (String) "Action ELiminar :)");
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


}