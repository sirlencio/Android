package com.example.spotifly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spotifly.Adapters.CancionListAdapter;

import java.io.File;
import java.util.ArrayList;

public class ActivityInicio extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView noMusicTextView;
    public int id_usuario;
    ArrayList<Cancion> songsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Intent i = getIntent();
        id_usuario = i.getIntExtra("id_usuario", 0);

        recyclerView = findViewById(R.id.recycler_view);
        noMusicTextView = findViewById(R.id.no_songs_text);

        if (!checkPermission()) {
            requestPermission();
            return;
        }

        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION
        };

        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        String sortorder = MediaStore.Audio.Media.TITLE + " ASC";

        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, selection, null, sortorder);
        while (cursor.moveToNext()) {
            Cancion songData = new Cancion(cursor.getString(1), cursor.getString(0), cursor.getString(2));
            if (new File(songData.getRuta()).exists() && songData.getRuta().contains("/Music"))
                songsList.add(songData);
        }

        if (songsList.size() == 0) {
            noMusicTextView.setVisibility(View.VISIBLE);
        } else {
            //recyclerview
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new CancionListAdapter(songsList, getApplicationContext()));
        }
    }

    boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(ActivityInicio.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(ActivityInicio.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(ActivityInicio.this, "READ PERMISSION IS REQUIRED,PLEASE ALLOW FROM SETTTINGS", Toast.LENGTH_SHORT).show();
        } else
            ActivityCompat.requestPermissions(ActivityInicio.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (recyclerView != null) {
            recyclerView.setAdapter(new CancionListAdapter(songsList, getApplicationContext()));
        }
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
            case R.id.icono_user:
                crearDialogoInformacion();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void crearDialogoInformacion() {
        AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
        final View infoPopup = getLayoutInflater().inflate(R.layout.info_usuario, null);
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