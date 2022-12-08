package com.example.agendacontactos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BusquedaAvanzada extends AppCompatActivity {
    int i = 0;
    TextView txtcodigo, txtnombre, txtapellido, txttel, txtemail, txtgrupo, txtmax;
    EditText nResultado;
    ArrayList<Contacto> listacontactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_avanzada);

        txtcodigo = (TextView) findViewById(R.id.textView4);
        txtnombre = (TextView) findViewById(R.id.textView3);
        txtapellido = (TextView) findViewById(R.id.textView5);
        txttel = (TextView) findViewById(R.id.textView6);
        txtemail = (TextView) findViewById(R.id.textView7);
        txtgrupo = (TextView) findViewById(R.id.textView8);
        txtmax = (TextView) findViewById(R.id.textView10);
        nResultado = (EditText) findViewById(R.id.editTextPhone);
        listacontactos = (ArrayList<Contacto>) getIntent().getSerializableExtra("list");
        txtmax.setText("de " + listacontactos.size());
        cargar(listacontactos);
    }

    public void cargar(ArrayList<Contacto> p) {
        txtcodigo.setText("Codigo: " + p.get(i).getCodigo());
        txtnombre.setText("Nombre: " + p.get(i).getNombre());
        txtapellido.setText("Apellidos: " + p.get(i).getApellido());
        txttel.setText("Telefono: " + p.get(i).getTelefono());
        txtemail.setText("Email: " + p.get(i).getEmail());
        txtgrupo.setText("Grupo: " + p.get(i).getGrupo());
    }

    public void avanzar(View view) {
        if (i == listacontactos.size() - 1) {
            Toast.makeText(this, "Ultimo contacto alcanzado", Toast.LENGTH_SHORT).show();
        } else {
            i++;
            cargar(listacontactos);
            nResultado.setText((i + 1) + "");
        }
    }

    public void retroceder(View view) {
        if (i == 0) {
            Toast.makeText(this, "Primer contacto avanzado", Toast.LENGTH_SHORT).show();
        } else {
            i--;
            cargar(listacontactos);
            nResultado.setText((i + 1) + "");
        }
    }

    public void localizar(View view) {
        int num = Integer.parseInt(nResultado.getText().toString()) - 1;
        if (num < 0 || num >= listacontactos.size()) {
            Toast.makeText(this, "Numero fuera del limite", Toast.LENGTH_SHORT).show();
            nResultado.setText((i + 1) + "");
        } else {
            i = num;
            cargar(listacontactos);
        }
    }

    public void volver(View view) {
        System.exit(0);
    }
}