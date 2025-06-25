package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class base {
	public static WebDriver driver;
	public Properties prop;

	public WebDriver initializeDriver() throws IOException {
		prop = new Properties();
		System.out.println("System.getProperty(\"user.dir\")" + System.getProperty("user.dir"));
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\resources\\data.properties");
		prop.load(fis);

		String strBrowserName = prop.getProperty("testBrowser");
		System.out.println("strBrowserName" + strBrowserName);

		if (strBrowserName.equals("chrome")) {
			System.out.println("Inside chrome browser code");
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\chromedriver138.exe");
			driver = new ChromeDriver();
		}

		else if (strBrowserName.equals("edge")) {
			System.out.println("Inside edge browser code");
			System.setProperty("webdriver.edge.driver",
					System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\msedgedriver137.exe");
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}

	public void getScreenshot(String result) throws IOException {
		// Take screenshot and save it to a file
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot,
				new File(System.getProperty("user.dir") + "\\screenshot\\" + result + "Screenshots.png"));
	}

}
