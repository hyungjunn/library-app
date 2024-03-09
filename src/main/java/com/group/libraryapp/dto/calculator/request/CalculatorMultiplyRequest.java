package com.group.libraryapp.dto.calculator.request;

public class CalculatorMultiplyRequest {
    private int number1; // 왜 final이 아닌지
    private int number2;

    // 왜 POST 일 때는 생성자가 필요없는지

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }
}
