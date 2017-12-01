import com.mikhadyuk.scholarshipcalculator.service.report.ReportService;
import com.mikhadyuk.scholarshipcalculator.service.report.ScholarshipReportService;
import com.mikhadyuk.scholarshipcalculator.util.SingletonUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class ReportTest {
    private static ReportService reportService;

    @BeforeClass
    public static void beforeClass() {
        reportService = SingletonUtil.getInstance(ScholarshipReportService.class);
    }

    @Test
    public void testReportText() {
        System.out.println(reportService.createReport());
    }

    @Test
    public void testSaveReport() {
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream("report.txt"))){
            printWriter.write(reportService.createReport());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
