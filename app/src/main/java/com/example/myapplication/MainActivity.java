package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv_console;
    private TextView tv_console_result;
    private Button button_C;
    private Button button_bracket;
    private Button button_percent;
    private Button button_del;
    private Button button_7;
    private Button button_8;
    private Button button_9;
    private Button button_mul;
    private Button button_4;
    private Button button_5;
    private Button button_6;
    private Button button_min;
    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_plus;
    private Button button_char;
    private Button button_0;
    private Button button_dot;
    private Button button_equally;
    private String number1 = "";
    private String sign = "";
    private String number2 = "";
    public static final String KEY_PREFIX_TV_CONSOLE = MainActivity.class.getCanonicalName() + ".tv_console";
    public static final String KEY_PREFIX_TV_CONSOLE_RESULT = MainActivity.class.getCanonicalName() + ".tv_console_result";
    public static final String KEY_PREFIX_NUMBER_1 = MainActivity.class.getCanonicalName() + ".number1";
    public static final String KEY_PREFIX_NUMBER_2 = MainActivity.class.getCanonicalName() + ".number2";
    public static final String KEY_PREFIX_SING = MainActivity.class.getCanonicalName() + ".sing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializingButtons();
        onCreateButtons();
    }

    View.OnClickListener numberButtons = new View.OnClickListener() {
        @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_0:
                    tv_console.setText(tv_console.getText() + "0");
                    break;
                case R.id.button_1:
                    tv_console.setText(tv_console.getText() + "1");
                    break;
                case R.id.button_2:
                    tv_console.setText(tv_console.getText() + "2");
                    break;
                case R.id.button_3:
                    tv_console.setText(tv_console.getText() + "3");
                    break;
                case R.id.button_4:
                    tv_console.setText(tv_console.getText() + "4");
                    break;
                case R.id.button_5:
                    tv_console.setText(tv_console.getText() + "5");
                    break;
                case R.id.button_6:
                    tv_console.setText(tv_console.getText() + "6");
                    break;
                case R.id.button_7:
                    tv_console.setText(tv_console.getText() + "7");
                    break;
                case R.id.button_8:
                    tv_console.setText(tv_console.getText() + "8");
                    break;
                case R.id.button_9:
                    tv_console.setText(tv_console.getText() + "9");
                    break;
                case R.id.button_C:
                    tv_console.setText("");
                    tv_console_result.setText("");
                    break;
                case R.id.button_bracket:
                    if (!tv_console.getText().equals("") && (Double.parseDouble((String) tv_console.getText()) >= 0)) {
                        tv_console_result.setText("Корень из " + tv_console.getText() + " " + "=");
                        double g = Math.sqrt(Double.parseDouble((String) tv_console.getText()));
                        tv_console.setText(String.format("%.5f", g));
                    } else tv_console.setText("Ошибка ввода");
                    break;
                case R.id.button_percent:
                    sign = "%";
                    number2 = (String) tv_console.getText();
                    tv_console.setText(equallyPress(number1, number2, sign));
                    break;
                case R.id.button_del:
                    number1 = (String) tv_console.getText();
                    tv_console.setText("");
                    sign = "/";
                    break;
                case R.id.button_mul:
                    number1 = (String) tv_console.getText();
                    tv_console.setText("");
                    sign = "*";
                    break;
                case R.id.button_min:
                    number1 = (String) tv_console.getText();
                    tv_console.setText("");
                    sign = "-";
                    break;
                case R.id.button_plus:
                    number1 = (String) tv_console.getText();
                    tv_console.setText("");
                    sign = "+";
                    break;
                case R.id.button_char:
                    String string = (String) tv_console.getText();
                    if (!string.equals("")) {
                        if (Double.parseDouble(string) > 0) {
                            tv_console.setText("-" + string);
                        } else tv_console.setText(string.substring(1));
                    } else break;
                    break;
                case R.id.button_dot:
                    if (tv_console.getText().equals("")) {
                        tv_console.setText("0.");
                    } else tv_console.setText(tv_console.getText() + ".");
                    break;
                case R.id.button_equally:
                    if (tv_console.getText().equals("На ноль не делят")
                            || tv_console.getText().equals("Строка переполнена")) {
                        tv_console.setText("");
                        tv_console_result.setText("");
                    } else {
                        number2 = (String) tv_console.getText();
                        tv_console.setText(equallyPress(number1, number2, sign));
                        resultViewEdit(number1, number2, sign);
                    }

                    break;
            }
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putString(KEY_PREFIX_TV_CONSOLE, (String) tv_console.getText());
        state.putString(KEY_PREFIX_TV_CONSOLE_RESULT, (String) tv_console_result.getText());
        state.putString(KEY_PREFIX_NUMBER_1, number1);
        state.putString(KEY_PREFIX_NUMBER_2, number2);
        state.putString(KEY_PREFIX_SING, sign);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tv_console.setText(savedInstanceState.getString(KEY_PREFIX_TV_CONSOLE));
        tv_console_result.setText(savedInstanceState.getString(KEY_PREFIX_TV_CONSOLE_RESULT));
        number1 = savedInstanceState.getString(KEY_PREFIX_NUMBER_1);
        number2 = savedInstanceState.getString(KEY_PREFIX_NUMBER_2);
        sign = savedInstanceState.getString(KEY_PREFIX_SING);
    }

    public String equallyPress(String number1, String number2, String sign) {
        double result = 0;
        String resultString = "";
        if (!number1.equals("") && !number2.equals("") && !sign.equals("")) {
            switch (sign) {
                case "+":
                    result = Double.parseDouble(number1) + Double.parseDouble(number2);
                    resultString = "" + result;
                    break;
                case "-":
                    result = Double.parseDouble(number1) - Double.parseDouble(number2);
                    resultString = "" + result;
                    break;
                case "*":
                    result = Double.parseDouble(number1) * Double.parseDouble(number2);
                    resultString = "" + result;
                    break;
                case "/":
                    if (Double.parseDouble(number2) != 0) {
                        result = Double.parseDouble(number1) / Double.parseDouble(number2);
                        resultString = String.format("%.3f", result);
                    } else resultString = "На ноль не делят";
                    break;
                case "%":
                    result = Double.parseDouble(number2) * Double.parseDouble(number1) / 100;
                    resultString = String.format("%.2f", result);
                    tv_console_result.setText(number2 + "% от " + number1 + "=");
                    break;
                default:
                    resultString = "";
                    break;
            }
        } else resultString = "";
        if (resultString.length() > 16) return "Строка переполнена";
        else return resultString;
    }

    private void resultViewEdit(String number1, String number2, String sign) {
        if (!number1.equals("") && !number2.equals("") && !sign.equals("")) {
            if (tv_console_result.getText().length() < 21) {
                tv_console_result.setText("");
                tv_console_result.setText(number1 + sign + number2 + "=");
            } else {
                tv_console_result.setText("Строка переполнена");
            }
        } else tv_console_result.setText("");
    }

    private void initializingButtons() {
        tv_console = findViewById(R.id.tv_console);
        tv_console_result = findViewById(R.id.tv_console_result);
        button_C = findViewById(R.id.button_C);
        button_bracket = findViewById(R.id.button_bracket);
        button_percent = findViewById(R.id.button_percent);
        button_del = findViewById(R.id.button_del);
        button_7 = findViewById(R.id.button_7);
        button_8 = findViewById(R.id.button_8);
        button_9 = findViewById(R.id.button_9);
        button_mul = findViewById(R.id.button_mul);
        button_4 = findViewById(R.id.button_4);
        button_5 = findViewById(R.id.button_5);
        button_6 = findViewById(R.id.button_6);
        button_min = findViewById(R.id.button_min);
        button_1 = findViewById(R.id.button_1);
        button_2 = findViewById(R.id.button_2);
        button_3 = findViewById(R.id.button_3);
        button_plus = findViewById(R.id.button_plus);
        button_char = findViewById(R.id.button_char);
        button_0 = findViewById(R.id.button_0);
        button_dot = findViewById(R.id.button_dot);
        button_equally = findViewById(R.id.button_equally);
    }

    private void onCreateButtons() {
        button_0.setOnClickListener(numberButtons);
        button_1.setOnClickListener(numberButtons);
        button_2.setOnClickListener(numberButtons);
        button_3.setOnClickListener(numberButtons);
        button_4.setOnClickListener(numberButtons);
        button_5.setOnClickListener(numberButtons);
        button_6.setOnClickListener(numberButtons);
        button_7.setOnClickListener(numberButtons);
        button_8.setOnClickListener(numberButtons);
        button_9.setOnClickListener(numberButtons);
        button_bracket.setOnClickListener(numberButtons);
        button_C.setOnClickListener(numberButtons);
        button_char.setOnClickListener(numberButtons);
        button_percent.setOnClickListener(numberButtons);
        button_del.setOnClickListener(numberButtons);
        button_mul.setOnClickListener(numberButtons);
        button_min.setOnClickListener(numberButtons);
        button_plus.setOnClickListener(numberButtons);
        button_dot.setOnClickListener(numberButtons);
        button_equally.setOnClickListener(numberButtons);
    }
}
