package com.group.libraryapp.dto.calculator.request;

import java.util.List;

public class CalculatorAddManyRequest {
    List<Integer> numbers;

    // 왜 기본 생성자를 생성해줘야 하지?
    public CalculatorAddManyRequest() {
    }

    public CalculatorAddManyRequest(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
