package util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
public class AppUtil {
public static Properties conpro;
public static WebDriver driver;
@BeforeMethod
public static void setUp() throws FileNotFoundException, IOException
{
	conpro = new Properties();
	//load property file into class
	conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
	if(conpro.getProperty("browser").equalsIgnoreCase("chrome"))
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(conpro.getProperty("url"));
	}
	else if(conpro.getProperty("browser").equalsIgnoreCase("firefox"))
	{
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(conpro.getProperty("url"));
	}
	else 
	{
		Reporter.log("Browser Value is Not Matching",true);
	}
}
@AfterMethod
public static void tearDown()
{
	driver.quit();
}
}
