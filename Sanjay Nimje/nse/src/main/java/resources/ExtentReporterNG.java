package resources;

import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG implements IReporter {

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        
        ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);

        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();

            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();

                buildTestNodes(extent, context.getPassedTests(), Status.PASS);
                buildTestNodes(extent, context.getFailedTests(), Status.FAIL);
                buildTestNodes(extent, context.getSkippedTests(), Status.SKIP);
            }
        }

        extent.flush();
    }

    private void buildTestNodes(ExtentReports extent, IResultMap tests, Status status) {
        if (tests.size() == 0) return;

        for (ITestResult result : tests.getAllResults()) {
            ExtentTest test = extent.createTest(result.getMethod().getMethodName());
            test.log(status, "Test " + status.toString().toLowerCase() + "ed");

            if (result.getThrowable() != null) {
                test.log(status, result.getThrowable());
            }
        }
    }
}