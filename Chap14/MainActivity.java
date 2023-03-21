package com.example.pj0321;

import static android.os.Environment.DIRECTORY_MUSIC;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvMp3;
    Button btnPlay, btnPause, btnStop;
    TextView tvMp3, tvTime;
    ProgressBar pbMp3;
    SeekBar sbMp3;
    ArrayList<String> mp3List = new ArrayList<>();
    String selectedMp3;
    String mp3Path = Environment.getExternalStoragePublicDirectory(DIRECTORY_MUSIC).getPath() + "/";
    MediaPlayer mPlayer;
    boolean PAUSED = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        File[] listFiles = new File(mp3Path).listFiles();
        String fileName, extName;

        for (File file : listFiles) {
            fileName = file.getName();
            extName = fileName.substring(fileName.length()-3);

            if (extName.equals((String) "mp3"))
                mp3List.add(fileName);
        }

        lvMp3 = findViewById(R.id.listViewMp3);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, mp3List);
        lvMp3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvMp3.setAdapter(adapter);
        lvMp3.setItemChecked(0, true);
        lvMp3.setOnItemClickListener(((parent, view, position, id) -> {
            selectedMp3 = mp3List.get(position);
        }));
        selectedMp3 = mp3List.get(0);

        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnStop = findViewById(R.id.btnStop);
        tvMp3 = findViewById(R.id.tvMp3);
        tvTime = findViewById(R.id.tvTime);
        pbMp3 = findViewById(R.id.pbMp3);

        btnPlay.setOnClickListener(v -> {
            try {
                mPlayer = new MediaPlayer();
                mPlayer.setDataSource(mp3Path + selectedMp3);
                mPlayer.prepare();
                mPlayer.start();
                btnPlay.setClickable(false);
                btnPause.setClickable(true);
                btnStop.setClickable(true);
                tvMp3.setText("실행중인 음악 : " + selectedMp3);

                new Thread() {
                    SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");

                    public void run() {
                        if (mPlayer == null) return;
                        pbMp3.setMax(mPlayer.getDuration());
                        while (mPlayer.isPlaying()) {
                            runOnUiThread(() -> {
                                pbMp3.setProgress(mPlayer.getCurrentPosition());
                                tvTime.setText(timeFormat.format(mPlayer.getCurrentPosition()));
                            });
                            SystemClock.sleep(200);
                        }
                    }
                }.start();
            } catch (IOException e) {
            }
        });

        btnPause.setOnClickListener(v -> {
            if (!PAUSED) {
                mPlayer.pause();
                btnPause.setText("이어듣기");
                PAUSED = true;

                new Thread() {
                    SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");

                    public void run() {
                        if (mPlayer == null) return;
                        pbMp3.setMax(mPlayer.getDuration());
                        while (mPlayer.isPlaying()) {
                            runOnUiThread(() -> {
                                pbMp3.setProgress(mPlayer.getCurrentPosition());
                                tvTime.setText(timeFormat.format(mPlayer.getCurrentPosition()));
                            });
                            SystemClock.sleep(200);
                        }
                    }
                }.start();
            } else {
                mPlayer.start();
                PAUSED = false;
                btnPause.setText("일시정지");

                new Thread() {
                    SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");

                    public void run() {
                        if (mPlayer == null) return;
                        pbMp3.setMax(mPlayer.getDuration());
                        while (mPlayer.isPlaying()) {
                            runOnUiThread(() -> {
                                pbMp3.setProgress(mPlayer.getCurrentPosition());
                                tvTime.setText(timeFormat.format(mPlayer.getCurrentPosition()));
                            });
                            SystemClock.sleep(200);
                        }
                    }
                }.start();
            }
        });
        btnPause.setClickable(false);

        btnStop.setOnClickListener(v -> {
            mPlayer.stop();
            mPlayer.reset();
            btnPlay.setClickable(true);
            btnPause.setClickable(false);
            btnPause.setText("일시정지");
            btnStop.setClickable(false);
            tvMp3.setText("실행중인 음악 : ");
            pbMp3.setProgress(0);
            tvTime.setText("00:00");
        });
        btnStop.setClickable(false);


        sbMp3 = findViewById(R.id.pbMp3);
        sbMp3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mPlayer.seekTo(progress);
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
}