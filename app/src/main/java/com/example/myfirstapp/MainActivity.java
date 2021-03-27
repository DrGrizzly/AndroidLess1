package com.example.myfirstapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Calculator calc;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;
    Button btn_0;
    Button btn_clear_all;
    Button btn_clear;
    Button btn_back;
    Button btn_plus;
    Button btn_minus;
    Button btn_mult;
    Button btn_div;
    Button btn_point;
    Button btn_result;
    TextView labelResult;
    TextView labelLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calc);
        init();
    }

    void init() {
        calc = new Calculator();
        btn_1 = findViewById(R.id.button_one);
        btn_2 = findViewById(R.id.button_two);
        btn_3 = findViewById(R.id.button_three);
        btn_4 = findViewById(R.id.button_four);
        btn_5 = findViewById(R.id.button_five);
        btn_6 = findViewById(R.id.button_six);
        btn_7 = findViewById(R.id.button_seven);
        btn_8 = findViewById(R.id.button_eight);
        btn_9 = findViewById(R.id.button_nine);
        btn_0 = findViewById(R.id.button_zero);
        btn_point = findViewById(R.id.button_point);

        btn_clear_all = findViewById(R.id.button_clear_all);
        btn_clear = findViewById(R.id.button_clear);
        btn_back = findViewById(R.id.button_back);
        btn_plus = findViewById(R.id.button_plus);
        btn_minus = findViewById(R.id.button_minus);
        btn_mult = findViewById(R.id.button_mult);
        btn_div = findViewById(R.id.button_div);
        btn_result = findViewById(R.id.button_result);
        labelResult = findViewById(R.id.textView);
        labelLog = findViewById(R.id.textLog);

        btn_1.setOnClickListener(v -> setNumber("1"));
        btn_2.setOnClickListener(v -> setNumber("2"));
        btn_3.setOnClickListener(v -> setNumber("3"));
        btn_4.setOnClickListener(v -> setNumber("4"));
        btn_5.setOnClickListener(v -> setNumber("5"));
        btn_6.setOnClickListener(v -> setNumber("6"));
        btn_7.setOnClickListener(v -> setNumber("7"));
        btn_8.setOnClickListener(v -> setNumber("8"));
        btn_9.setOnClickListener(v -> setNumber("9"));
        btn_0.setOnClickListener(v -> setNumber("0"));
        btn_point.setOnClickListener(v -> setNumber("."));
        btn_clear_all.setOnClickListener(v -> clearAll());
        btn_plus.setOnClickListener(v -> calc.setFunc("+"));
        btn_minus.setOnClickListener(v -> calc.setFunc("-"));
        btn_mult.setOnClickListener(v -> calc.setFunc("*"));
        btn_div.setOnClickListener(v -> calc.setFunc("/"));
        btn_back.setOnClickListener(v -> setBack());
        btn_result.setOnClickListener(v-> calcResult());
    }

    private void setNumber(String num) {
        calc.setInputNumber(num);
        printValue();
    }

    private void printValue() {
        labelResult.setText(calc.getInputNumber());
        labelLog.setText(calc.getOperLog());
    }

    private void clearAll() {
        calc.clearAll();
        printValue();
    }

    private void setBack(){
        calc.funcBack();
        printValue();
    }

    private void calcResult(){
        calc.calcResult();
        printValue();
    }
}