package com.example.spotifly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityReproductor extends AppCompatActivity {

    public int id_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);
        Intent i = getIntent();
        id_usuario = i.getIntExtra("id_usuario", 0);
    }

    //Asignar menu layout al menu de esta ventana
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.icono_user);
        byte[] img = getImagen();
        Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
        BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
        menuItem.setIcon(drawable);
        return super.onCreateOptionsMenu(menu);
    }

    public byte[] getImagen() {
        BaseDatos admin = new BaseDatos(this);
        SQLiteDatabase bbdd = admin.getReadableDatabase();

        String sql = "Select imagen from usuarios where id_usuario like " + id_usuario;
        Cursor fila = bbdd.rawQuery(sql, null);
        fila.moveToFirst();
        byte[] img = fila.getBlob(0);
        fila.close();
        bbdd.close();

        return img;
    }

    //Asignar acciones a los items del menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_salir:
                Toast.makeText(this, "Saliendo", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.menu_acerca:
                Toast.makeText(this, "Aplicacion realizada por Carlos Lozano Pérez", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.icono_user:
                crearDialogoInformacion();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void crearDialogoInformacion(){
        AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
        final View infoPopup = getLayoutInflater().inflate(R.layout.ejemplo, null);
        ImageView imagen = infoPopup.findViewById(R.id.imageViewInfo);
        TextView nombre = infoPopup.findViewById(R.id.textViewInfoNombre);
        TextView fecha = infoPopup.findViewById(R.id.textViewInfoFecha);
        TextView sexo = infoPopup.findViewById(R.id.textViewInfoSexo);

        BaseDatos admin = new BaseDatos(this);
        SQLiteDatabase bbdd = admin.getReadableDatabase();
        String sql = "Select * from usuarios where id_usuario like " + id_usuario;
        Cursor fila = bbdd.rawQuery(sql, null);
        fila.moveToFirst();

        nombre.setText(fila.getString(1));
        fecha.setText(fila.getString(2));
        sexo.setText(fila.getString(3));

        byte[] img = fila.getBlob(4);
        Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
        BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
        imagen.setImageDrawable(drawable);

        fila.close();
        bbdd.close();

        dialogbuilder.setView(infoPopup);
        AlertDialog dialog = dialogbuilder.create();
        dialog.show();
    }

}