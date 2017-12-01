package com.mikhadyuk.scholarshipcalculator.action.impl;

import com.mikhadyuk.scholarshipcalculator.action.Action;
import com.mikhadyuk.scholarshipcalculator.service.report.ReportService;
import com.mikhadyuk.scholarshipcalculator.service.report.ScholarshipReportService;
import com.mikhadyuk.scholarshipcalculator.util.SingletonUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ReportCreatingAction implements Action {
    private ReportService reportService;

    public ReportCreatingAction() {
        reportService = SingletonUtil.getInstance(ScholarshipReportService.class);
    }

    @Override
    public boolean doAction(ObjectOutputStream outputStream, ObjectInputStream inputStream) {
        String report = reportService.createReport();
        try {
            outputStream.writeObject(report);
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }
}
