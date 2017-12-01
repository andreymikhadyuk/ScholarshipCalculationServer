package com.mikhadyuk.scholarshipcalculator.service.report;

import com.mikhadyuk.scholarshipcalculator.model.Scholarship;
import com.mikhadyuk.scholarshipcalculator.model.ScholarshipProperty;
import com.mikhadyuk.scholarshipcalculator.service.BaseAmountService;
import com.mikhadyuk.scholarshipcalculator.service.ScholarshipService;
import com.mikhadyuk.scholarshipcalculator.util.SingletonUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ScholarshipReportService implements ReportService {
    private StringBuilder report;
    private static final int LINE_LENGTH = 139;
    private static final String[] headers = {"  Название стипендии  ", "       Тип       ", " Учебная ", " Min средний балл ",
            " Max средний балл ", " Коэффициент увеличения ", " Сумма стипендии(руб.) "};

    private ScholarshipService scholarshipService;
    private BaseAmountService baseAmountService;

    public ScholarshipReportService() {
        scholarshipService = SingletonUtil.getInstance(ScholarshipService.class);
        baseAmountService = SingletonUtil.getInstance(BaseAmountService.class);
    }

    // Название стипендии |       Тип       | Учебная | Min средний балл | Max средний балл | Коэффициент увеличения | Сумма стипендии
    @Override
    public String createReport() {
        List<Scholarship> scholarships = scholarshipService.findAll();

        report = new StringBuilder();

        addReportHeader();
        addEnter();
        addEnter();

        addDelimiter(ReportDelimiter.HORIZONTAL_LINE, LINE_LENGTH);
        addEnter();
        createTableHeaders();

        for (Scholarship scholarship : scholarships) {
            if (scholarship.isEducational()) {
                createEducationalRow(scholarship);
            } else {
                createNonEducationalRow(scholarship);
            }
        }
        addDelimiter(ReportDelimiter.HORIZONTAL_LINE, LINE_LENGTH);
        addEnter();

        return report.toString();
    }

    private void addReportHeader() {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");

        Date date = new Date(System.currentTimeMillis());
        String dateString = format.format(date);

        report.append("Отчет о студенческих стипендиях от ");
        report.append(dateString);
    }

    private void createTableHeaders() {
        addDelimiter(ReportDelimiter.VERTICAL_LINE, 1);
        report.append(headers[0]); // 22
        addDelimiter(ReportDelimiter.VERTICAL_LINE, 1);
        report.append(headers[1]); // 17
        addDelimiter(ReportDelimiter.VERTICAL_LINE, 1);
        report.append(headers[2]); // 9
        addDelimiter(ReportDelimiter.VERTICAL_LINE, 1);
        report.append(headers[3]); // 18
        addDelimiter(ReportDelimiter.VERTICAL_LINE, 1);
        report.append(headers[4]); // 18
        addDelimiter(ReportDelimiter.VERTICAL_LINE, 1);
        report.append(headers[5]); // 24
        addDelimiter(ReportDelimiter.VERTICAL_LINE, 1);
        report.append(headers[6]); // 23
        addDelimiter(ReportDelimiter.VERTICAL_LINE, 1);
        addEnter();
    }

    private void createEducationalRow(Scholarship scholarship) {
        for (ScholarshipProperty scholarshipProperty : scholarship.getScholarshipProperties()) {
            addDelimiterWithinTable();
            addEnter();

            addDelimiter(ReportDelimiter.VERTICAL_LINE, 1);

            addInTheRowCenter(scholarship.getType(), headers[0].length());
            addDelimiter(ReportDelimiter.VERTICAL_LINE, 1);

            addInTheRowCenter(scholarshipProperty.getEducationalType().getLabel(), headers[1].length());
            addDelimiter(ReportDelimiter.VERTICAL_LINE, 1);

            addInTheRowCenter(scholarship.isEducational() ? "Да" : "Нет", headers[2].length());
            addDelimiter(ReportDelimiter.VERTICAL_LINE, 1);

            addInTheRowCenter(String.valueOf(scholarshipProperty.getMinAverageScore()), headers[3].length());
            addDelimiter(ReportDelimiter.VERTICAL_LINE, 1);

            addInTheRowCenter(String.valueOf(scholarshipProperty.getMaxAverageScore()), headers[4].length());
            addDelimiter(ReportDelimiter.VERTICAL_LINE, 1);

            addInTheRowCenter(String.valueOf(scholarshipProperty.getIncreaseCoefficient()), headers[5].length());
            addDelimiter(ReportDelimiter.VERTICAL_LINE, 1);

            double amount = scholarshipProperty.getIncreaseCoefficient() *
                    baseAmountService.getScholarshipPropertyBaseAmount(scholarshipProperty);
            addInTheRowCenter(String.format("%.2f", amount).replace(",", "."), headers[6].length());
            addDelimiter(ReportDelimiter.VERTICAL_LINE, 1);

            addEnter();
        }
    }

    private void createNonEducationalRow(Scholarship scholarship) {
        if (scholarship.getScholarshipProperties() == null || scholarship.getScholarshipProperties().isEmpty()) {
            return;
        }
        ScholarshipProperty scholarshipProperty = scholarship.getScholarshipProperties().get(0);

        addDelimiterWithinTable();
        addEnter();

        addDelimiter(ReportDelimiter.VERTICAL_LINE, 1);

        addInTheRowCenter(scholarship.getType(), headers[0].length());
        addDelimiter(ReportDelimiter.VERTICAL_LINE, 1);

        addInTheRowCenter("-", headers[1].length());
        addDelimiter(ReportDelimiter.VERTICAL_LINE, 1);

        addInTheRowCenter(scholarship.isEducational() ? "Да" : "Нет", headers[2].length());
        addDelimiter(ReportDelimiter.VERTICAL_LINE, 1);

        addInTheRowCenter("-", headers[3].length());
        addDelimiter(ReportDelimiter.VERTICAL_LINE, 1);

        addInTheRowCenter("-", headers[4].length());
        addDelimiter(ReportDelimiter.VERTICAL_LINE, 1);

        addInTheRowCenter("-", headers[5].length());
        addDelimiter(ReportDelimiter.VERTICAL_LINE, 1);

        addInTheRowCenter(String.valueOf(scholarshipProperty.getAmount()), headers[6].length());
        addDelimiter(ReportDelimiter.VERTICAL_LINE, 1);

        addEnter();
    }

    private void addDelimiter(ReportDelimiter delimiter, int count) {
        for (int i = 0; i < count; i++) {
            report.append(delimiter.getDelimiter());
        }
    }

    private void addDelimiterWithinTable() {
        addDelimiter(ReportDelimiter.VERTICAL_LINE, 1);
        addDelimiter(ReportDelimiter.HORIZONTAL_LINE, LINE_LENGTH - 2);
        addDelimiter(ReportDelimiter.VERTICAL_LINE, 1);
    }

    private void addEnter() {
        addDelimiter(ReportDelimiter.ENTER, 1);
    }

    private void addOffsets(int count) {
        addDelimiter(ReportDelimiter.SPACE, count);
    }

    private void addInTheRowCenter(String element, int rowLength) {
        int leftOffset = (rowLength - element.length()) / 2;
        addOffsets(leftOffset);
        report.append(element);
        addOffsets(rowLength - leftOffset - element.length());
    }
}
