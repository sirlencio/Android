package com.example.spotifly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

public class ActivityPlaylist extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView noMusicTextView;
    public int id_usuario;
    ArrayList<Cancion> songsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        Intent i = getIntent();
        id_usuario = i.getIntExtra("idusuario", 0);

        recyclerView = findViewById(R.id.recycler_viewplaylist);
        noMusicTextView = findViewById(R.id.no_songs_text);

        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ARTIST
        };

        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        String sortorder = MediaStore.Audio.Media.TITLE + " ASC";

        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, selection, null, sortorder);
        while (cursor.moveToNext()) {
            Cancion songData = new Cancion(cursor.getString(1), cursor.getString(0), cursor.getString(2), cursor.getString(3));
            if (new File(songData.getRuta()).exists() && songData.getRuta().contains("/Music")) {
                if (comprobarfav(songData)) {
                    songsList.add(songData);
                }
            }
        }

        if (songsList.size() == 0) {
            noMusicTextView.setVisibility(View.VISIBLE);
        } else {
            //recyclerview
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new CancionListAdapter(songsList, getApplicationContext(), id_usuario));
        }

    }

    public int obtenerID(Cancion song) {
        BaseDatos admin = new BaseDatos(this);
        SQLiteDatabase bbdd = admin.getReadableDatabase();
        String sql = "Select id_cancion from canciones where titulo like ?";
        String[] args = {song.getTitulo()};
        Cursor fila = bbdd.rawQuery(sql, args);
        fila.moveToFirst();
        int n = fila.getInt(0);
        fila.close();
        bbdd.close();
        return n;
    }

    public boolean comprobarfav(Cancion song) {
        BaseDatos admin = new BaseDatos(this);
        SQLiteDatabase bbdd = admin.getReadableDatabase();
        String sql = "Select id_cancion from usuario_cancion where id_usuario like " + id_usuario + " and id_cancion like " + obtenerID(song);
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
        MenuItem menuItem = menu.findItem(R.id.icono_user);
        byte[] img = getImagen();
        Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
        BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
        menuItem.setIcon(drawable);

        MenuItem visble = menu.findItem(R.id.abrir_playlist);
        visble.setVisible(false);

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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}