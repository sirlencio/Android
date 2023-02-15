package com.example.spotifly.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotifly.ActivityReproductor;
import com.example.spotifly.BaseDatos;
import com.example.spotifly.Cancion;
import com.example.spotifly.R;

public class CancionListAdapter extends RecyclerView.Adapter<CancionListAdapter.ViewHolder> {

    ArrayList<Cancion> songsList;
    Context context;
    int idusario;

    public CancionListAdapter(ArrayList<Cancion> songsList, Context context, int idusario) {
        this.songsList = songsList;
        this.context = context;
        this.idusario = idusario;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cancion_item, parent, false);
        return new CancionListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CancionListAdapter.ViewHolder holder, int position) {
        Cancion songData = songsList.get(holder.getAdapterPosition());
        ImageButton btnanadirplaylist = holder.itemView.findViewById(R.id.imageButton);
        holder.titleTextView.setText(songData.getTitulo());
        holder.artistTextView.setText(songData.getArtista());

        if (esfav(songData)) {
            btnanadirplaylist.setImageResource(R.drawable.playlist_added);
            btnanadirplaylist.setTag(R.drawable.playlist_added);
        } else {
            btnanadirplaylist.setImageResource(R.drawable.playlist_add);
            btnanadirplaylist.setTag(R.drawable.playlist_add);
        }


        holder.itemView.setOnClickListener(v -> {
            //navigate to another acitivty

            MyMediaPlayer.getInstance().reset();
            MyMediaPlayer.currentIndex = holder.getAdapterPosition();
            Intent intent = new Intent(context, ActivityReproductor.class);
            intent.putExtra("LIST", songsList);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        });

        btnanadirplaylist.setOnClickListener(v -> {
            if ((int) btnanadirplaylist.getTag() == R.drawable.playlist_add) { //Si la cancion no esta anadida y le da a anadir
                registrarfav(obtenerID(songData));
                btnanadirplaylist.setImageResource(R.drawable.playlist_added);
                btnanadirplaylist.setTag(R.drawable.playlist_added);
            } else { //Si la cancion esta anadida y le da a quitar
                borrarfav(songData);
                btnanadirplaylist.setImageResource(R.drawable.playlist_add);
                btnanadirplaylist.setTag(R.drawable.playlist_add);
            }
        });

    }

    public void borrarfav(Cancion song) {
        BaseDatos admin = new BaseDatos(context);
        SQLiteDatabase bbdd = admin.getReadableDatabase();
        String sql = "Delete from usuario_cancion where id_usuario like " + idusario + " and id_cancion like " + obtenerID(song);
        bbdd.execSQL(sql);
        bbdd.close();
    }

    public boolean esfav(Cancion song) {
        BaseDatos admin = new BaseDatos(context);
        SQLiteDatabase bbdd = admin.getReadableDatabase();
        String sql = "Select id_cancion from usuario_cancion where id_usuario like " + idusario + " and id_cancion like " + obtenerID(song);
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

    public void registrarfav(int idcan) {
        try {
            BaseDatos admin = new BaseDatos(context);
            SQLiteDatabase bbdd = admin.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("id_usuario", idusario);
            values.put("id_cancion", idcan);

            bbdd.insert("usuario_cancion", null, values);
            bbdd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int obtenerID(Cancion song) {
        BaseDatos admin = new BaseDatos(context);
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


    @Override
    public int getItemCount() {
        return songsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView, artistTextView;
        ImageView iconView, botonimagen;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.music_title_text);
            artistTextView = itemView.findViewById(R.id.artist_text);
            iconView = itemView.findViewById(R.id.icon_view);
            botonimagen = itemView.findViewById(R.id.imageButton);
        }
    }
}



