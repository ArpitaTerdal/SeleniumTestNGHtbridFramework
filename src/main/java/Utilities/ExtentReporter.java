package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtentReporter()   
	{
	ExtentReports extentReport=new ExtentReports();
	File extentReportFile=new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
	ExtentSparkReporter sparkReporter=new ExtentSparkReporter(extentReportFile);
	sparkReporter.config().setTheme(Theme.DARK);
	sparkReporter.config().setReportName("tutorialsNinjaReports");
	sparkReporter.config().setDocumentTitle("TN Automation Report");
	sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
	
	extentReport.attachReporter(sparkReporter);
	
	Properties configprop=new Properties();
	File file=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\config\\config.properties");
	try {
	FileInputStream fisdata=new FileInputStream(file);
	configprop.load(fisdata);
	}catch(Throwable e)
	{
		e.printStackTrace();
	}
	extentReport.setSystemInfo("Application Url", configprop.getProperty("url"));
	extentReport.setSystemInfo("Browser", configprop.getProperty("browser"));
	extentReport.setSystemInfo("Email Id", configprop.getProperty("validEmail"));
	extentReport.setSystemInfo("Password", configprop.getProperty("validPassword"));
	extentReport.setSystemInfo("operating system",System.getProperty("os.name"));
	extentReport.setSystemInfo("user name",System.getProperty("user.name"));
	extentReport.setSystemInfo("java version",System.getProperty("java.version"));
	
	return extentReport;
}
}
