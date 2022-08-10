package com.unidev.quizapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import com.unidev.quizapp.MainActivity;
import com.unidev.quizapp.R;

public class HomeActivity extends AppCompatActivity {
    private Button btnPlay;
    private Button btnAbout;
    private Button btnQuit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnPlay = findViewById(R.id.btn_play);
        btnAbout = findViewById(R.id.btn_about);
        btnQuit = findViewById(R.id.btn_quit);
        btnPlay.setOnClickListener(v-> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        });
        btnAbout.setOnClickListener(v->{
            Intent intent = new Intent(this,AboutActivity.class);
            startActivity(intent);
        });
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}