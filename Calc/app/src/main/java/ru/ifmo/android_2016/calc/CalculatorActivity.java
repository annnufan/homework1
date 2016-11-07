package ru.ifmo.android_2016.calc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by alexey.nikitin on 13.09.16.
 * Changed by Anna Kopeliovich on 07.11.16
 */

public final class CalculatorActivity extends Activity {
    private int value, our_num;
    String operation;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        value = 0;
        our_num = -1;
        operation = "";
        setContentView(R.layout.activity_calculator);
        result = (TextView) findViewById(R.id.result);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("val", value);
        outState.putString("oper", operation);
        outState.putInt("num", our_num);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState == null) {
            return;
        }
        value = savedInstanceState.getInt("val");
        operation = savedInstanceState.getString("oper");
        our_num = savedInstanceState.getInt("num");
        if (our_num == -1) {
            result.setText(Integer.toString(value));
        } else {
            result.setText(Integer.toString(our_num));
        }
    }

    public void Clear(View view) {
        value = 0;
        operation = "";
        our_num = 0;
        result.setText("0");
    }

    public void Digit(View view) {
        Button b = (Button)view;
        if (our_num == -1) {
            our_num = 0;
        }
        our_num = our_num * 10 + Integer.parseInt(b.getText().toString());
        result.setText(Integer.toString(our_num));
    }

    public void Operation(View view) {
        Button b = (Button)view;
        if (!operation.isEmpty() && (our_num != -1)) {
            switch (operation) {
                case "/":
                    value /= our_num;
                    break;
                case "*":
                    value *= our_num;
                    break;
                case "-":
                    value -= our_num;
                    break;
                case "+":
                    value += our_num;
                    break;
            }
            our_num = -1;

        }
        if (our_num != -1) {
            value = our_num;
            our_num = -1;
        }
        operation = b.getText().toString();
        if (operation.equals("=")) {
            operation = "";
        }
        result.setText(Integer.toString(value));
    }
}
