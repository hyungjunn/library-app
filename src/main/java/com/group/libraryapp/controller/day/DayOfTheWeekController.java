package com.group.libraryapp.controller.day;

import com.group.libraryapp.domain.Day;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class DayOfTheWeekController {

    @GetMapping("/api/v1/day-of-the-week")
    public Day dayOfTheWeek(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return new Day(date);
    }
}
