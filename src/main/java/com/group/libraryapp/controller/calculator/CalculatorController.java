package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.domain.Calculator;
import com.group.libraryapp.dto.calculator.request.CalculatorAddManyRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import com.group.libraryapp.dto.calculator.response.CalculatorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CalculatorController {

    @GetMapping("/add")
    public int addTwoNumber(CalculatorAddRequest request) {
        return request.getNumber1() + request.getNumber2();
    }

    @PostMapping("/multiply")
    public int multiplyTwoNumber(@RequestBody CalculatorMultiplyRequest request) {
        return request.getNumber1() * request.getNumber2();
    }

    @GetMapping("/api/v1/calc")
    public Calculator calculateTwoNumber(CalculatorResponse response) {
        int add = response.getNum1() + response.getNum2();
        int minus = response.getNum1() - response.getNum2();
        int multiply = response.getNum1() * response.getNum2();
        return new Calculator(add, minus, multiply);
    }

    @PostMapping("/addMany")
    public int addManyNumber(@RequestBody CalculatorAddManyRequest request) {
        int sum = 0;

        List<Integer> numbers = request.getNumbers();

        for (int number : numbers) {
            sum += number;
        }

        return sum;
    }
}
