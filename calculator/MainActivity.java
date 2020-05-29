package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean isItFirstNum = true;
    float inputNumber = 0;
    char operator = '+';
    String processStr;

    TextView result;


//
//    Intent i = new Intent("Activity Two");
//    Bundle extras = new Bundle();
//    Bundle extras = getIntent().getExtras();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result_txt);

        Button add = findViewById(R.id.add_btn);
        Button sub = findViewById(R.id.subtract_btn);
        Button mul = findViewById(R.id.multiply_btn);
        Button div = findViewById(R.id.divide_btn);
        Button equ = findViewById(R.id.equal_btn);

        add.setEnabled(false);
        sub.setEnabled(false);
        mul.setEnabled(false);
        div.setEnabled(false);
        equ.setEnabled(false);
    }


    public void btn_click(View view) {
        Button getButton = findViewById(view.getId());

        Button add = findViewById(R.id.add_btn);
        Button sub = findViewById(R.id.subtract_btn);
        Button mul = findViewById(R.id.multiply_btn);
        Button div = findViewById(R.id.divide_btn);
        Button dec = findViewById(R.id.decimal_btn);
        Button equ = findViewById(R.id.equal_btn);



        switch (view.getId()){
            case R.id.all_clear_btn:
                inputNumber = 0;
                operator = '+';
                setClearText("0");
                add.setEnabled(false);
                sub.setEnabled(false);
                mul.setEnabled(false);
                div.setEnabled(false);
                dec.setEnabled(false);
                equ.setEnabled(false);

                break;

            case R.id.clear_entry_btn:
                setClearText("0");
                break;

            case R.id.back_space_btn:
                if (result.getText().toString().length() > 1){
                    String getResult = result.getText().toString();
                    String subString = getResult.substring(0, getResult.length() - 1);
                    result.setText(subString);
                }
                else {
                    setClearText("0");
                }
                break;



            case R.id.decimal_btn:
                result.append(getButton.getText().toString());
                result.setTextColor(0xFF000000);
                dec.setEnabled(false);
                break;

            case R.id.neg_pos_btn:
                String getNeg = result.getText().toString();
                String subNeg = "-" + getNeg;
                result.setText(subNeg);
                result.setTextColor(0xFF000000);
                break;



        }//end switch
    }//end btn_click

    public void numButtonClick(View view){
        Button getButton = findViewById(view.getId());

        Button add = findViewById(R.id.add_btn);
        Button sub = findViewById(R.id.subtract_btn);
        Button mul = findViewById(R.id.multiply_btn);
        Button div = findViewById(R.id.divide_btn);
        Button dec = findViewById(R.id.decimal_btn);


        add.setEnabled(true);
        sub.setEnabled(true);
        mul.setEnabled(true);
        div.setEnabled(true);
        dec.setEnabled(true);


        if(isItFirstNum) {
            result.setText(getButton.getText().toString());
            result.setTextColor(0xFF000000);
            isItFirstNum = false;
        }//end if
        else{
            if(result.getText().toString().equals("0")){
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                result.setText("0");
                return;
            }
            else {
                result.append(getButton.getText().toString());
                result.setTextColor(0xFF000000);
            }
        }//end else

    }//end numButtonClick

    public void setClearText(String clearText){
        isItFirstNum = true;
        result.setText(clearText);
        result.setTextColor(0xFF666666);
    }//end setClearText

    public String Calc(float resultTxt, float last, char op, String s) {
        if (op == '+') {
//            resultTxt = resultTxt + last;
            resultTxt = BusinessClass.plus(resultTxt, last);

            s = String.valueOf(resultTxt);
        } else if (op == '-') {
//            resultTxt = resultTxt - last;
            resultTxt = BusinessClass.minus(resultTxt, last);
            s = String.valueOf(resultTxt);
        } else if (op == '*') {
//            resultTxt = resultTxt * last;
            resultTxt = BusinessClass.multiple(resultTxt, last);
            s = String.valueOf(resultTxt);
        } else if (op == '/') {
//            resultTxt = resultTxt / last;
            resultTxt = BusinessClass.divide(resultTxt, last);
            s = String.valueOf(resultTxt);
//                }
        }//end else if
        return s;
    }//end intCalc




    public void operatorClick(View view){
        Button getButton = findViewById(view.getId());
        float lastNum = Float.parseFloat(result.getText().toString());

        Button add = findViewById(R.id.add_btn);
        Button sub = findViewById(R.id.subtract_btn);
        Button mul = findViewById(R.id.multiply_btn);
        Button div = findViewById(R.id.divide_btn);
        Button dec = findViewById(R.id.decimal_btn);
        Button equ = findViewById(R.id.equal_btn);

        if(view.getId() == R.id.equal_btn){

            equ.setEnabled(false);

            isItFirstNum = true;
            processStr = Calc(inputNumber, lastNum, operator, processStr);


//            inputNumber = Float.parseFloat(result.getText().toString());

//            if(inputNumber == 0 || lastNum == 0){
//                result.setText("Error");
//            }


            if(operator == '/'){
                if(Float.parseFloat(result.getText().toString()) == 0){
                    result.setText("Error");
                }//end if
                else if(lastNum == 0){
                    result.setText("Error");
                }//end else if
                else if(inputNumber == 0){
                    result.setText("Error");
                }//end else if
                else{
                    result.setText(processStr);
//                    inputNumber = 0;
                }//end else
            }//end if
            else{
                result.setText(processStr);
//                inputNumber = 0;
            }
            isItFirstNum = true;
        }//end if
        else {

            add.setEnabled(false);
            sub.setEnabled(false);
            mul.setEnabled(false);
            div.setEnabled(false);
            dec.setEnabled(true);
            equ.setEnabled(true);


//            if(view.getId() == R.id.add_btn) {
//                add_btn.setEnabled(false);
//            }
            processStr = Calc(inputNumber, lastNum, operator, processStr);
//            result.setText(processStr);
            inputNumber = Float.parseFloat(result.getText().toString());

            if(operator == '/'){
                if(Float.parseFloat(result.getText().toString()) == 0){
                    result.setText("Error");
                }//end if
                else if(inputNumber == 0){
                    result.setText("Error");
                }//end else if
                else{
                    result.setText(processStr);
                    inputNumber = Float.parseFloat(result.getText().toString());
//                    inputNumber = 0;

                }//end else
            }//end if
            else{
                result.setText(processStr);
//                inputNumber = 0;
                inputNumber = Float.parseFloat(result.getText().toString());

            }
            operator = getButton.getText().toString().charAt(0);

//            result.setText(String.valueOf(inputNumber));
        }//end else
        isItFirstNum = true;
    }//operatorClick


}//end class
