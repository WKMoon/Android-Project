package com.example.quizapp;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;


public class QuizActivity extends AppCompatActivity {


    String nameInfo;

    ArrayList<String> ques = new ArrayList<String>();
    ArrayList<String> opts = new ArrayList<String>();
    HashMap<String,String> map = new HashMap<>();

    TextView display;
    TextView question;

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button next;


    int grade = 0;
    String strGrade;
    String answer1 = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_gui);

//        display = findViewById(R.id.naming);



        display = findViewById(R.id.name_dis);
        question = findViewById(R.id.question);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        next = findViewById(R.id.btnNext);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        nameInfo = bundle.getString("nameInfo");
        display.setText("Hello " + nameInfo);

        InputStream is = this.getResources().openRawResource(R.raw.test);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String str;
            try{
                while((str = reader.readLine()) != null){
                    String[] stringArr = str.split(":");
                    Arrays.toString(stringArr);
                    map.put(stringArr[0], stringArr[1]);
                    ques.add(stringArr[0]);
                    opts.add(stringArr[1]);
                }//end while

                Button[] btnArr =  { btn1, btn2, btn3, btn4};

                btn1.setEnabled(true);
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                btn4.setEnabled(true);
                next.setEnabled(false);

//                    for (int b = 0; b < 1; b++) {
                        Collections.shuffle(Arrays.asList(btnArr));
                        Collections.shuffle(ques);



                        question.setText(ques.get(0));
                        btnArr[0].setText(map.get(ques.get(0)));
                        if(!map.get(ques.get(0)).equals(opts.get(1)) && !map.get(ques.get(0)).equals(opts.get(2)) && !map.get(ques.get(0)).equals(opts.get(3))){
                            btnArr[1].setText(opts.get(1));
                            btnArr[2].setText(opts.get(2));
                            btnArr[3].setText(opts.get(3));
                        }//end if
                        else{
                            btnArr[1].setText(opts.get(4));
                            btnArr[2].setText(opts.get(5));
                            btnArr[3].setText(opts.get(6));
                        }//end else
                        final String ansBtn = btnArr[0].getText().toString();


                        btn1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                answer1 = btn1.getText().toString();
                                btn1.setEnabled(false);
                                btn2.setEnabled(false);
                                btn3.setEnabled(false);
                                btn4.setEnabled(false);
                                next.setEnabled(true);
                                if(ansBtn == btn1.getText().toString()){
                                    grade++;
                                    Toast.makeText(getApplicationContext(), "Correct",Toast.LENGTH_LONG).show();
                                }//end if
                                else{
//                                    grade = 0;
                                    Toast.makeText(getApplicationContext(), "Incorrect",Toast.LENGTH_LONG).show();

                                }//end else
                            }//end onClick
                        });//end setOn

                        btn2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                answer1 = btn2.getText().toString();
                                btn1.setEnabled(false);
                                btn2.setEnabled(false);
                                btn3.setEnabled(false);
                                btn4.setEnabled(false);
                                next.setEnabled(true);
                                if(ansBtn == btn2.getText().toString()){
                                    grade++;
                                    Toast.makeText(getApplicationContext(), "Correct",Toast.LENGTH_LONG).show();
                                }//end if
                                else{
//                                    grade = 0;
                                    Toast.makeText(getApplicationContext(), "Incorrect",Toast.LENGTH_LONG).show();
                                }//end else
                            }//end onClick
                        });//end setOn

                        btn3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                answer1 = btn3.getText().toString();
                                btn1.setEnabled(false);
                                btn2.setEnabled(false);
                                btn3.setEnabled(false);
                                btn4.setEnabled(false);
                                next.setEnabled(true);
                                if(ansBtn == btn3.getText().toString()){
                                    grade++;
                                    Toast.makeText(getApplicationContext(), "Correct",Toast.LENGTH_LONG).show();
                                }//end if
                                else{
//                                    grade = 0;
                                    Toast.makeText(getApplicationContext(), "Incorrect",Toast.LENGTH_LONG).show();
                                }//end else
                            }//end onClick
                        });//end setOn

                        btn4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                answer1 = btn4.getText().toString();
                                btn1.setEnabled(false);
                                btn2.setEnabled(false);
                                btn3.setEnabled(false);
                                btn4.setEnabled(false);
                                next.setEnabled(true);
                                if(ansBtn == btn4.getText().toString()){
                                    grade++;
                                    Toast.makeText(getApplicationContext(), "Correct",Toast.LENGTH_LONG).show();
                                }//end if
                                else{
//                                    grade = 0;
                                    Toast.makeText(getApplicationContext(), "Incorrect",Toast.LENGTH_LONG).show();
                                }//end else
                            }//end onClick
                        });//end setOn

                        ques.remove(ques.get(0));
                        Collections.shuffle(opts);
//                        return;
//                    }//end for
                is.close();
            //end try
            }catch(Exception e){
                e.printStackTrace();
                Log.e("ErrorTag", "Error",e);
            }//end catch
    }//end onCreate



    public void nextClick(View view) {
        Button[] btnArr =  { btn1, btn2, btn3, btn4};

        if(ques.size() <= 0) {
            strGrade = Integer.toString(grade);
            nameInfo = display.getText().toString();
            Intent intent2 = new Intent(QuizActivity.this, ScoreActivity.class);
            intent2.putExtra("nameInfo", nameInfo);
            intent2.putExtra("strGrade", strGrade);
            startActivity(intent2);
        }//end if
            //for (int c = 0; c < 10; c++) {
        while (!ques.isEmpty()){
                btn1.setEnabled(true);
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                btn4.setEnabled(true);
                next.setEnabled(false);
                Collections.shuffle(Arrays.asList(btnArr));
                Collections.shuffle(ques);


                question.setText(ques.get(0));
                btnArr[0].setText(map.get(ques.get(0)));
                if(!map.get(ques.get(0)).equals(opts.get(1)) && !map.get(ques.get(0)).equals(opts.get(2)) && !map.get(ques.get(0)).equals(opts.get(3))){
                    btnArr[1].setText(opts.get(1));
                    btnArr[2].setText(opts.get(2));
                    btnArr[3].setText(opts.get(3));
                }//end if
                else{
                    btnArr[1].setText(opts.get(4));
                    btnArr[2].setText(opts.get(5));
                    btnArr[3].setText(opts.get(6));
                }//end else


                final String ansBtn = btnArr[0].getText().toString();

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        answer1 = btn1.getText().toString();
                        btn1.setEnabled(false);
                        btn2.setEnabled(false);
                        btn3.setEnabled(false);
                        btn4.setEnabled(false);
                        next.setEnabled(true);
                        if(ansBtn == btn1.getText().toString()){
                            grade++;
                            Toast.makeText(getApplicationContext(), "Correct",Toast.LENGTH_LONG).show();
                        }//end if
                        else{
//                            grade = 0;
                            Toast.makeText(getApplicationContext(), "Incorrect",Toast.LENGTH_LONG).show();
                        }//end else
                    }//end onClick
                });//end setOn


                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        answer1 = btn2.getText().toString();
                        btn1.setEnabled(false);
                        btn2.setEnabled(false);
                        btn3.setEnabled(false);
                        btn4.setEnabled(false);
                        next.setEnabled(true);
                        if(ansBtn == btn2.getText().toString()){
                            grade++;
                            Toast.makeText(getApplicationContext(), "Correct",Toast.LENGTH_LONG).show();
                        }//end if
                        else{
//                            grade = 0;
                            Toast.makeText(getApplicationContext(), "Incorrect",Toast.LENGTH_LONG).show();
                        }//end else
                    }//end onClick
                });//end setOn

                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        answer1 = btn3.getText().toString();
                        btn1.setEnabled(false);
                        btn2.setEnabled(false);
                        btn3.setEnabled(false);
                        btn4.setEnabled(false);
                        next.setEnabled(true);
                        if(ansBtn == btn3.getText().toString()){
                            grade++;
                            Toast.makeText(getApplicationContext(), "Correct",Toast.LENGTH_LONG).show();
                        }//end if
                        else{
//                            grade = 0;
                            Toast.makeText(getApplicationContext(), "Incorrect",Toast.LENGTH_LONG).show();
                        }//end else
                    }//end onClick
                });//end setOn

                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        answer1 = btn4.getText().toString();
                        btn1.setEnabled(false);
                        btn2.setEnabled(false);
                        btn3.setEnabled(false);
                        btn4.setEnabled(false);
                        next.setEnabled(true);
                        if(ansBtn == btn4.getText().toString()){
                            grade++;
                            Toast.makeText(getApplicationContext(), "Correct",Toast.LENGTH_LONG).show();
                        }//end if
                        else{
//                            grade = 0;
                            Toast.makeText(getApplicationContext(), "Incorrect",Toast.LENGTH_LONG).show();
                        }//end else
                    }//end onClick
                });//end setOn


            ques.remove(ques.get(0));
            Collections.shuffle(opts);

            return;
            }//end for
    }//end NextClick
}//end class








