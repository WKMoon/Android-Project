package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {


    TextView display;
    String nameInfo;

    TextView score;
    String strGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);

        display = findViewById(R.id.name_dis_score);
        score = findViewById(R.id.dis_score);

        Intent intent3 = getIntent();

        Bundle bundle = intent3.getExtras();

        nameInfo = bundle.getString("nameInfo");
        strGrade = bundle.getString("strGrade");

        display.setText(nameInfo + "'s score is ");
        score.setText(strGrade);


    }//end onCreate


}//end class
