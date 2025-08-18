package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//If using a PDF reporter library (e.g., https://github.com/anshooarora/extentreports-pdf), import:
//import com.aventstack.extentreports.reporter.ExtentPdfReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    private static ExtentReports extent;
    private static final String SPARK_REPORT_PATH = "target/ExtentReports/ExtentSpark.html";
    private static final String HTML_REPORT_PATH = "target/ExtentReports/ExtentHtml.html";
    private static final String PDF_REPORT_PATH   = "target/ExtentReports/ExtentPdf.pdf"; // new PDF file

    public static ExtentReports getExtentReports() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    private static void createInstance() {
        extent = new ExtentReports();

        // === Spark Reporter ===
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(SPARK_REPORT_PATH);
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("RevDoc Automation Spark Report");
        sparkReporter.config().setReportName("RevDoc Admin Tests - Spark");

        // === Classic HTML Reporter ===
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(HTML_REPORT_PATH);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("RevDoc Automation JUnit HTML Report");
        htmlReporter.config().setReportName("RevDoc Admin JUnit Tests - HTML");

        // === PDF Reporter ===
        // Uncomment and configure if using a PDF reporter library
       // ExtentPdfReporter pdfReporter = new ExtentPdfReporter(PDF_REPORT_PATH);
         //pdfReporter.config().setDocumentTitle("RevDoc Automation PDF Report");
        //pdfReporter.config().setReportName("RevDoc Admin JUnit Tests - PDF");

        // Attach reporters
        extent.attachReporter(sparkReporter, htmlReporter /*, pdfReporter */ );

        // System info
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("User", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Browser", "Chrome");
    }

    /** Optional: generate unique filename for reports to avoid caching issues */
    public static String getUniqueReportPath(String basePath) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String folder = "target/extent-reports/";
        new File(folder).mkdirs();
        return folder + basePath.replace(".html", "_" + timestamp + ".html");
    }
}
