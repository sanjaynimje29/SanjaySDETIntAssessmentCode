package nse;

import java.io.IOException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resources.base;

public class nseHomePage extends base {
	public static Logger log = LogManager.getLogger(base.class.getName());

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();

	}

	@Test(priority = 1)
	public void basePageNavigation() throws IOException {
		driver.get(prop.getProperty("nseWebsiteURL"));
		getScreenshot("Homepage");
		log.debug("Home Page Opened Sucessfully");
		log.info("Home Page Opened Sucessfully");
		System.out.println("Home Page Opened Sucessfully");

	}

	@Test(priority = 2)
	public void searchStockHighLowPrices() throws IOException, InterruptedException {
		driver.get(prop.getProperty("nseWebsiteURL"));
		Thread.sleep(5);
		WebElement textBox = driver.findElement(By.xpath("//input[@type='text']"));
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter NIFTY 50 stock symbol (e.g., RCOM / TATAMOTORS): ");
			String symbol = scanner.nextLine().toUpperCase();
			textBox.sendKeys(symbol);
		}
		Thread.sleep(10);
		driver.findElement(By.xpath("(//div[@class='autocompleteList'])[1]")).click();
		Thread.sleep(5);
		String fiftyTwo_week_high_price = driver.findElement(By.xpath("//span[contains(text(),'52 Week High')]/following::td/span")).getText();
		log.debug("Fifty Two week high price: "+fiftyTwo_week_high_price);
		System.out.println("Fifty Two week high price: "+fiftyTwo_week_high_price);
		//52week low
		String fiftyTwo_week_low_price =driver.findElement(By.xpath("//span[contains(text(),'52 Week Low')]/following::td/span")).getText();
		log.debug("Fifty Two week low price: "+fiftyTwo_week_low_price);
		System.out.println("Fifty Two week low price: "+fiftyTwo_week_low_price);
		Thread.sleep(10);
	}

	@AfterTest
	public void teardown() {
		driver.close();
	}

}
