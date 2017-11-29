package com.mikhadyuk.scholarshipcalculator.service;

import com.mikhadyuk.scholarshipcalculator.model.Mark;

import java.util.List;

public class MarkService {
    public double calculateAverageScore(List<Mark> marks) {
        return marks
                .stream()
                .mapToInt(Mark::getMark)
                .sum() / (double) marks.size();
    }
}
