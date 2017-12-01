package com.mikhadyuk.scholarshipcalculator.service.report;

public enum ReportDelimiter {
    VERTICAL_LINE("|"), HORIZONTAL_LINE("-"), ENTER("\r\n"), SPACE(" ");

    private String delimiter;

    ReportDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
