package com.example.spotifly.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotifly.ActivityReproductor;
import com.example.spotifly.Cancion;
import com.example.spotifly.R;

public class CancionListAdapter extends RecyclerView.Adapter<CancionListAdapter.ViewHolder> {

    ArrayList<Cancion> songsList;
    Context context;

    public CancionListAdapter(ArrayList<Cancion> songsList, Context context) {
        this.songsList = songsList;
        this.context = context;
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
        holder.titleTextView.setText(songData.getTitulo());

        if (MyMediaPlayer.currentIndex == holder.getAdapterPosition()) {
            holder.titleTextView.setTextColor(Color.parseColor("#FF0000"));
        } else {
            holder.titleTextView.setTextColor(Color.parseColor("#000000"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigate to another acitivty

                MyMediaPlayer.getInstance().reset();
                MyMediaPlayer.currentIndex = holder.getAdapterPosition();
                Intent intent = new Intent(context, ActivityReproductor.class);
                intent.putExtra("LIST", songsList);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView, artistTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.text_titulo);
            artistTextView = itemView.findViewById(R.id.text_artista);
        }
    }
}



