package com.example.myapplication;

import android.widget.TextView;

public class Calculator {


    public static String equallyPress(String number1, String number2, String sign, TextView textView) {
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
                    textView.setText(number2 + "% от " + number1 + "=");
                    break;
                default:
                    resultString = "";
                    break;
            }
        } else resultString = "";
        if (resultString.length() > 16) return "Строка переполнена";
        else return resultString;
    }

    static void resultViewEdit(String number1, String number2, String sign, TextView textView) {
        if (!number1.equals("") && !number2.equals("") && !sign.equals("")) {
            if (textView.getText().length() < 21) {
                textView.setText("");
                textView.setText(number1 + sign + number2 + "=");
            } else {
                textView.setText("Строка переполнена");
            }
        } else textView.setText("");
    }
}
