package com.example.agendacontactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NuevoGrupo extends AppCompatActivity {

    EditText txtGrupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_grupo);
        txtGrupo = (EditText) findViewById(R.id.editTextTextPersonName);
    }

    public void registrar(View view) {
        GrupoSQL SQL = new GrupoSQL(this, "grupos", null, 1);
        SQLiteDatabase conexion = SQL.getWritableDatabase();
        String grupo = txtGrupo.getText().toString();

        if (grupo.isEmpty()) {
            Toast.makeText(this, "Introduzca un nombre de grupo", Toast.LENGTH_SHORT).show();
        } else {
            ContentValues registro = new ContentValues();
            registro.put("nombre", grupo);

            conexion.insert("grupos", null, registro);
            conexion.close();
            txtGrupo.setText("");
        }
    }

    public void volver(View view) {
        System.exit(0);
    }
}