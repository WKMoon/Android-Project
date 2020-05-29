package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText naming;
    Button startBtn;
    String nameInfo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBtn = findViewById(R.id.start_btn);
        naming = findViewById(R.id.naming);


        startBtn.setEnabled(false);


        naming.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String info = naming.getText().toString().trim();
                startBtn.setEnabled(!info.isEmpty());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String info = naming.getText().toString().trim();
                startBtn.setEnabled(!info.isEmpty());
            }
            @Override
            public void afterTextChanged(Editable s) {
                String info = naming.getText().toString().trim();
                startBtn.setEnabled(!info.isEmpty());
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nameInfo = naming.getText().toString();

                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putExtra("nameInfo", nameInfo);
                startActivity(intent);

            }//end onClick

        });//end setOn

    }//end onCreate



}//end class
