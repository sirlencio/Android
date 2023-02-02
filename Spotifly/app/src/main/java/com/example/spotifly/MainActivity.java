package com.example.spotifly;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public EditText txt_nombre, txt_fecha;
    public RadioButton rbHombre, rbMujer;
    public ImageView imagen;
    Integer[] fotos = {R.drawable.men, R.drawable.women};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_nombre = findViewById(R.id.editTextTextNombre);
        txt_fecha = findViewById(R.id.editTextTextFecha);
        rbHombre = findViewById(R.id.radioButtonHombre);
        rbMujer = findViewById(R.id.radioButtonMujer);
        imagen = findViewById(R.id.imageView);
        RadioGroup rg = findViewById(R.id.radioGroup);

        rg.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton radioButton = rg.findViewById(i);
            int index = rg.indexOfChild(radioButton);
            imagen.setImageResource(fotos[index]);
        });

        txt_fecha.setOnClickListener(View -> showDatePickerDialog());

        FloatingActionButton cambiarIMG = findViewById(R.id.floatingActionButton);
        cambiarIMG.setOnClickListener(view -> elegirImagen.launch("image/*"));

        Button registrar = findViewById(R.id.buttonRegistrar);
        registrar.setOnClickListener(view -> registrar());

        Button login = findViewById(R.id.buttonLogin);
        login.setOnClickListener(view -> login());
    }

    ActivityResultLauncher<String> elegirImagen = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
            imagen.setImageURI(result);
        }
    });

    public void registrar() {
        try {
            BaseDatos admin = new BaseDatos(this);
            SQLiteDatabase bbdd = admin.getWritableDatabase();
            String nombre = txt_nombre.getText().toString();
            if (comprobarNombre(nombre)) {
                Toast.makeText(this, "El usuario \"" + nombre + "\" ya existe", Toast.LENGTH_SHORT).show();
                bbdd.close();
            } else {
                String fechaNac = txt_fecha.getText().toString();
                String sexo;
                if (rbMujer.isChecked()) {
                    sexo = "Mujer";
                } else {
                    sexo = "Hombre";
                }

                ContentValues values = new ContentValues();
                values.put("nombre", nombre);
                values.put("sexo", sexo);
                values.put("fecha_nac", fechaNac);
                Bitmap b = BitmapFactory.decodeResource(getResources(),R.id.imageView);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.PNG, 100, bos);
                byte[] img = bos.toByteArray();
                values.put("imagen", img);

                if (comprobaciones(nombre, fechaNac)) {
                    bbdd.insert("usuarios", null, values);
                    txt_nombre.setText("");
                    txt_fecha.setText("");

                    Intent loged = new Intent(this, ActivityReproductor.class);
                    startActivity(loged);
                }
                bbdd.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Comprobamos que el nombre cumpla el requisito y que sea mayor de 16 años
    public boolean comprobaciones(String nombre, String fechanac) {
        Boolean cumple = null;
        if (!nombre.matches("^[A-Za-z0-9_-]{8,}$")) {
            Toast.makeText(this, "El formato de nombre de usuario no es valido", Toast.LENGTH_SHORT).show();
            cumple = false;
        } else if (fechanac.isEmpty()) {
            Toast.makeText(this, "No ha elegido una fecha", Toast.LENGTH_SHORT).show();
            cumple = false;
        } else {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            try {
                Date birthDate = format.parse(fechanac);
                Calendar now = Calendar.getInstance();
                Calendar dob = Calendar.getInstance();
                assert birthDate != null;
                dob.setTime(birthDate);
                int year1 = now.get(Calendar.YEAR);
                int year2 = dob.get(Calendar.YEAR);
                int age = year1 - year2;
                if (now.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
                    age--;
                }
                if (age >= 16) {
                    System.out.println("El usuario es mayor de 16 años");
                    cumple = true;
                } else {
                    Toast.makeText(this, "El usuario no puede ser menor de 16 años", Toast.LENGTH_SHORT).show();
                    cumple = false;
                }
            } catch (ParseException e) {
                System.out.println("No se pudo parsear la fecha de nacimiento");
            }
        }
        return Boolean.TRUE.equals(cumple);
    }

    public void login() {
        try {
            String nombre = txt_nombre.getText().toString();
            if (comprobarNombre(nombre)) {
                Intent loged = new Intent(this, ActivityReproductor.class);
                Bitmap b = BitmapFactory.decodeResource(getResources(),R.id.imageView);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.PNG, 100, bos);
                byte[] img = bos.toByteArray();
                usuario user = new usuario(nombre, img);
                loged.putExtra("usuario", user);
                startActivity(loged);
            } else {
                Toast.makeText(this, "El usuario \"" + nombre + "\" no existe", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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


    //Asignar menu layout al menu de esta ventana
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
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
                Toast.makeText(this,"Aplicacion realizada por Carlos Lozano", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Calendario de fecha nacimiento
    public void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, year, month, day) -> {
            // +1 because January is zero
            final String selectedDate = day + "/" + (month + 1) + "/" + year;
            txt_fecha.setText(selectedDate);
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
