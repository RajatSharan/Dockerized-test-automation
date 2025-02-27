package utills;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportHelper {

    private static ExtentReports reports;

    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    public static ExtentReports getReportObject() {
        // Use a valid relative path inside the project
        String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport_" + dtf.format(LocalDateTime.now()) + ".html";

        ExtentSparkReporter extent = new ExtentSparkReporter(reportPath);
        extent.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        extent.config().setReportName("Web Automation Results");
        extent.config().setDocumentTitle("Test Results");

        // Fix JavaScript - Corrected "getElementsByClassName"
        extent.config().setJs("document.getElementsByClassName('col-sm-12 col-md-4')[0].style.setProperty('min-inline-size', '-webkit-fill-available');");

        reports = new ExtentReports();
        reports.attachReporter(extent);

        // Fix setSystemInfo() format
        reports.setSystemInfo("Tester", "Rajat Sharan");

        return reports;
    }
}
