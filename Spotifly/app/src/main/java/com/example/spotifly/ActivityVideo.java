package com.example.spotifly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class ActivityVideo extends AppCompatActivity {
    VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        video = findViewById(R.id.videoView);

        String vid = "android.resource://" + getPackageName() + "/" + R.raw.video;

        Uri uri = Uri.parse(vid);
        video.setMediaController((new MediaController(this)));
        video.setVideoURI(uri);
        video.requestFocus();
        video.start();
    }

    //Asignar menu layout al menu de esta ventana
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem visble = menu.findItem(R.id.abrir_playlist);
        visble.setVisible(false);

        MenuItem visible2 = menu.findItem(R.id.icono_user);
        visible2.setVisible(false);
        
        MenuItem visble3 = menu.findItem(R.id.menu_item_video);
        visble3.setVisible(false);

        return super.onCreateOptionsMenu(menu);
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
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
