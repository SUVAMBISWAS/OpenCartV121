package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	String mainUrl;
	public Properties pro;
	
	@BeforeClass(groups = {"sanity","regression","master"})
	@Parameters({"os","browser"})
	public void setUp(String os, String br) throws IOException {
		FileReader file= new FileReader("./src//test//resources//config.properties");
		pro= new Properties();
		pro.load(file);
		mainUrl = pro.getProperty("appUrl");
		if (pro.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities cap = new DesiredCapabilities();
		if(os.equalsIgnoreCase("windows")) {

			cap.setPlatform(Platform.WIN10);
			
		}
		else if(os.equalsIgnoreCase("linux")) {
			
			cap.setPlatform(Platform.LINUX);
			
		}
		else if(os.equalsIgnoreCase("mac")) {
		
			cap.setPlatform(Platform.MAC);
			
		}else {
			
			System.out.println("not matching found");
			return;
		}
		
		switch (br.toLowerCase()) {
		case "chrome": cap.setBrowserName("chrome");break;
		case "edge":  cap.setBrowserName("MicrosoftEdge");break;
		case "firefox":  cap.setBrowserName("firefox");break;
		default: System.out.println("invalid browser");
			return;
		}
		
		driver= new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);// localhost=10.52.131.76
       
		}
		
		if (pro.getProperty("execution_env").equalsIgnoreCase("local")) {
		switch (br.toLowerCase()) {
		case "chrome": driver= new ChromeDriver();
			
			break;
		case "firefox": driver= new FirefoxDriver();
		break;
		case "edge": driver= new EdgeDriver();
		break;
		default: System.out.println("invalid browser name");
			return;
		}
		}
		logger=LogManager.getLogger(this.getClass());
		
		driver.get(mainUrl);
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
driver.manage().window().maximize();
		
	}
	

	@AfterClass(groups = {"sanity","regression","master"})
public void tearDown() {
	
		driver.quit();
		
	}
	public String randomString() {
		
		String randomeEmail= RandomStringUtils.randomAlphanumeric(4);
		return randomeEmail;
	}
	public String captureScreenshot(String t_name) throws IOException {
		String dateTimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	
		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+t_name+"_"+dateTimeStamp+".png";//current date formt, file name
		
		File targetFile = new File(targetFilePath);
		  FileUtils.copyFile(sourceFile, targetFile);
		return targetFilePath;
	}


}
