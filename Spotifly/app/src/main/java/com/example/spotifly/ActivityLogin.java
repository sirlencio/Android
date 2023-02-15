package com.example.spotifly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityLogin extends AppCompatActivity {

    public EditText txt_nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txt_nombre = findViewById(R.id.editTextTextNombre);

        Button login = findViewById(R.id.buttonLogin);
        login.setOnClickListener(view -> logear());

        TextView registrar = findViewById(R.id.textView);
        registrar.setOnClickListener(view -> abrirRegistrar());
    }

    public void logear() {
        try {
            String nombre = txt_nombre.getText().toString();
            if (comprobarNombre(nombre)) {
                Intent loged = new Intent(this, ActivityInicio.class);
                loged.putExtra("id_usuario", cogerID(nombre));
                startActivity(loged);
            } else {
                Toast.makeText(this, "El usuario \"" + nombre + "\" no existe", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Abre la ventana de registrar
    public void abrirRegistrar() {
        Intent intent = new Intent(this, ActivityRegistrar.class);
        startActivity(intent);
    }

    //Comprueba si el nombre ya esta registrado
    public boolean comprobarNombre(String nombre) {
        BaseDatos admin = new BaseDatos(this);
        SQLiteDatabase bbdd = admin.getReadableDatabase();
        String sql = "Select * from usuarios where nombre like '" + nombre + "'";
        Cursor fila = bbdd.rawQuery(sql, null);
        if (!fila.moveToFirst()) {
            fila.close();
            bbdd.close();
            return false;
        } else {
            fila.close();
            bbdd.close();
            return true;
        }
    }

    //Coge el id del usuario introducido
    public int cogerID(String nombre) {
        BaseDatos admin = new BaseDatos(this);
        SQLiteDatabase bbdd = admin.getReadableDatabase();
        String sql = "Select id_usuario from usuarios where nombre like '" + nombre + "'";
        Cursor fila = bbdd.rawQuery(sql, null);
        fila.moveToFirst();
        int id = fila.getInt(0);
        fila.close();
        bbdd.close();
        return id;
    }

    //Asignar menu layout al menu de esta ventana
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem visble = menu.findItem(R.id.abrir_playlist);
        visble.setVisible(false);

        MenuItem visble2 = menu.findItem(R.id.icono_user);
        visble2.setVisible(false);

        MenuItem visble3 = menu.findItem(R.id.menu_item_video);
        visble3.setVisible(false);

        return super.onCreateOptionsMenu(menu);
    }

    //Asignar acciones a los items del menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_salir:
                finish();
                break;
            case R.id.menu_acerca:
                Toast.makeText(this, "Aplicacion realizada por Carlos Lozano", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
