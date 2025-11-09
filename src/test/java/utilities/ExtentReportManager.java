package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extentReports;
	public ExtentTest test;
	String repName ;

	public void onStart(ITestContext context) {
		

		String dateTimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-" + dateTimeStamp+".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

		sparkReporter.config().setDocumentTitle("open cart automation Report");
		sparkReporter.config().setReportName("open cart functonal testing");
		sparkReporter.config().setTheme(Theme.DARK);
		extentReports= new ExtentReports();
		extentReports.attachReporter(sparkReporter);
		
		extentReports.setSystemInfo("application name", "open cart");
		extentReports.setSystemInfo("module name", "admin");
		extentReports.setSystemInfo("sub module name", "customers");
		extentReports.setSystemInfo("user name", System.getProperty("user.name"));
		extentReports.setSystemInfo("environment", "qa");

		String browser = context.getCurrentXmlTest().getParameter("browser");
		extentReports.setSystemInfo("browserType", browser);
		String os = context.getCurrentXmlTest().getParameter("os");
		extentReports.setSystemInfo("operating system", os);
		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {

			extentReports.setSystemInfo("groups", includedGroups.toString());
		}
	}

	public void onTestSuccess(ITestResult result) {

		test = extentReports.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + " got succesfully executed");
	}

	public void onTestSkipped(ITestResult result) {

		test = extentReports.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onTestFailure(ITestResult result) {
		test = extentReports.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName() + " got fail");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		
		try {
			String screenshotPath = new BaseClass().captureScreenshot(result.getName());
			test.addScreenCaptureFromPath(screenshotPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void onFinish(ITestContext context) {
		extentReports.flush();
		String path=System.getProperty("user.dir")+".\\reports\\"+repName;
		File f= new File(path);
		try {
			Desktop.getDesktop().browse(f.toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
