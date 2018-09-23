package com.example.riya.pumpkintimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView timer;
    Button button;
    CountDownTimer countDownTimer;
    Boolean counterActive = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        timer = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public void controlTimer(View view) {

        if(!counterActive) {
            counterActive = true;
            seekBar.setEnabled(false);
            button.setText("Stop");
           countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {

                public void onTick(long milisUntilFinished) {

                    updateTimer((int) milisUntilFinished / 1000);
                }

                public void onFinish() {
                    //timer.setText("00:00");
                    resetTimer();
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();
                }


            }.start();
        }
        else if(counterActive){
            resetTimer();

        }
    }
    public void updateTimer(int progress){
        int minutes = (int) (progress/60);
        String min = Integer.toString(minutes);
        if(minutes <10)
            min = "0"+minutes;
        int seconds = progress - minutes*60;
        String sec = Integer.toString(seconds);
        if (seconds <= 9)
            sec = "0" + seconds;
        String showTime =min + ":" +sec;
        timer.setText(showTime);
    }
    public void resetTimer(){
        seekBar.setProgress(30);
        timer.setText("00:30");
        countDownTimer.cancel();
        button.setText("GO!");
        seekBar.setEnabled(true);
        counterActive = false;
    }

}
