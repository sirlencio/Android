package com.example.spotifly;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.spotifly.Adapters.MyMediaPlayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class ActivityReproductor extends AppCompatActivity {

    TextView titleTv, artistTv, currentTimeTv, totalTimeTv;
    SeekBar seekBar;
    ImageView pausePlay, nextBtn, previousBtn, musicIcon, loop, shuffle, btnprincipio, btnfinal;
    ArrayList<Cancion> songsList, ordenAnt;
    Cancion currentSong;
    MediaPlayer mediaPlayer = MyMediaPlayer.getInstance();
    int x = 0, loopStatus = 0;
    boolean shuffleStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);

        titleTv = findViewById(R.id.song_title);
        artistTv = findViewById(R.id.artist);
        currentTimeTv = findViewById(R.id.current_time);
        totalTimeTv = findViewById(R.id.total_time);
        seekBar = findViewById(R.id.seek_bar);
        pausePlay = findViewById(R.id.pause_play);
        nextBtn = findViewById(R.id.next);
        btnprincipio = findViewById(R.id.irprincipio);
        btnfinal = findViewById(R.id.irfinal);
        previousBtn = findViewById(R.id.previous);
        musicIcon = findViewById(R.id.music_icon_big);
        loop = findViewById(R.id.loop);
        shuffle = findViewById(R.id.shuffle);

        titleTv.setSelected(true);

        songsList = (ArrayList<Cancion>) getIntent().getSerializableExtra("LIST");

        ordenAnt = new ArrayList<>(songsList);

        setResourcesWithMusic();

        ActivityReproductor.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    currentTimeTv.setText(convertToMMSS(mediaPlayer.getCurrentPosition() + ""));

                    if (mediaPlayer.isPlaying()) {
                        pausePlay.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24);
                        musicIcon.setRotation(x++);
                    } else {
                        pausePlay.setImageResource(R.drawable.ic_baseline_play_circle_outline_24);
                        musicIcon.setRotation(0);
                    }
                    if (shuffleStatus) {
                        shuffle.setImageResource(R.drawable.shuffle);
                    } else {
                        shuffle.setImageResource(R.drawable.shuffle_marcado);
                    }
                    switch (loopStatus) {
                        case 0:
                            loop.setImageResource(R.drawable.loop);
                            break;
                        case 1:
                            loop.setImageResource(R.drawable.loop_one);
                            break;
                        case 2:
                            loop.setImageResource(R.drawable.loop_two);
                            break;
                    }
                    if (convertToMMSS(mediaPlayer.getCurrentPosition() + "").equals(convertToMMSS(currentSong.getDuracion()))) {
                        playNextSong();
                    }

                }
                new Handler().postDelayed(this, 100);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mediaPlayer != null && fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    void setResourcesWithMusic() {
        currentSong = songsList.get(MyMediaPlayer.currentIndex);

        titleTv.setText(currentSong.getTitulo());
        artistTv.setText(currentSong.getArtista());

        totalTimeTv.setText(convertToMMSS(currentSong.getDuracion()));

        pausePlay.setOnClickListener(v -> pausePlay());
        nextBtn.setOnClickListener(v -> playNextSong());
        previousBtn.setOnClickListener(v -> playPreviousSong());

        btnfinal.setOnClickListener(v -> end());
        btnprincipio.setOnClickListener(v -> start());

        shuffle.setOnClickListener(v -> shuffleMode());
        loop.setOnClickListener(v -> loopMode());
        playMusic();
    }

    private void start() {
        MyMediaPlayer.currentIndex = 0;
        mediaPlayer.reset();
        setResourcesWithMusic();
    }

    private void end() {
        MyMediaPlayer.currentIndex = songsList.size() - 1;
        mediaPlayer.reset();
        setResourcesWithMusic();
    }

    private void playMusic() {

        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(currentSong.getRuta());
            mediaPlayer.prepare();
            mediaPlayer.start();
            seekBar.setProgress(0);
            seekBar.setMax(mediaPlayer.getDuration());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loopMode() {
        loopStatus++;
        if (loopStatus == 3) {
            loopStatus = 0;
        }
    }

    private void shuffleMode() {
        if (shuffleStatus) { //Va a mezclar
            Collections.shuffle(songsList);
            shuffleStatus = false;
        } else { // No va a mezclar
            songsList.clear();
            songsList.addAll(ordenAnt);
            shuffleStatus = true;
        }
    }

    private void playNextSong() {
        if (loopStatus == 2) {
            mediaPlayer.reset();
            setResourcesWithMusic();
            return;
        } else if (loopStatus == 1) {
            if (MyMediaPlayer.currentIndex == songsList.size() - 1) {
                MyMediaPlayer.currentIndex = 0;
                mediaPlayer.reset();
                setResourcesWithMusic();
                return;
            }
            MyMediaPlayer.currentIndex++;
            mediaPlayer.reset();
            setResourcesWithMusic();
        }
        if (MyMediaPlayer.currentIndex == songsList.size() - 1)
            return;
        MyMediaPlayer.currentIndex++;
        mediaPlayer.reset();
        setResourcesWithMusic();

    }

    private void playPreviousSong() {
        if(loopStatus == 1){
            if (MyMediaPlayer.currentIndex == 0){
                MyMediaPlayer.currentIndex = songsList.size() - 1;
                mediaPlayer.reset();
                setResourcesWithMusic();
                return;
            }
        }
        if (MyMediaPlayer.currentIndex == 0)
            return;
        MyMediaPlayer.currentIndex -= 1;
        mediaPlayer.reset();
        setResourcesWithMusic();
    }

    private void pausePlay() {
        if (mediaPlayer.isPlaying())
            mediaPlayer.pause();
        else
            mediaPlayer.start();
    }


    public static String convertToMMSS(String duration) {
        Long millis = Long.parseLong(duration);
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.stop();
        finish();
    }
}
