package com.tutorialsninja.Base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Utilities.Utils;

public class BaseClass {
	
	WebDriver driver;
    protected Properties prop;
    protected Properties dataProp;
	String path="E:\\ApiTestingEclipse\\MyProject1\\src\\main\\java\\com\\tutorialsninja\\config\\config.properties";
	String datapath="E:\\ApiTestingEclipse\\MyProject1\\src\\main\\java\\com\\tutorialsninja\\testData\\testData.properties";
	
	public void loadConfigFile()
	{
		try
		{
		dataProp=new Properties();
		FileInputStream fisdata=new FileInputStream(datapath);
		dataProp.load(fisdata);
		}catch(Throwable e)
		{
			e.printStackTrace();
		}
		try
		{
		prop=new Properties();
		//File propfile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\config\\config.properties");
		FileInputStream fis=new FileInputStream(path);
		prop.load(fis);
		}catch(Throwable e)
		{
			e.printStackTrace();
		}
	   
	}
	public WebDriver initializeBrowserAndOpenApplication(String browserName)
	{
		 if(browserName.equalsIgnoreCase("chrome"))
		 {
		 driver=new ChromeDriver();
		 }
		 else if(browserName.equalsIgnoreCase("firefox"))
		 {
			 driver=new FirefoxDriver();
		 }
		 else if(browserName.equalsIgnoreCase("edge"))
		 {
			 driver=new EdgeDriver();
		 }
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utils.Implicit_Wait_Time));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utils.Page_load_Time));
			driver.get(prop.getProperty("url"));
			
			return driver;
	}

	
}
