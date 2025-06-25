package resources;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParallelTest extends base {
    WebDriver driver;
    public static Logger log = LogManager.getLogger(base.class.getName());

    @Parameters("browser")
    @BeforeMethod
    public void setup(String browser) throws IOException {
    	if (browser.equalsIgnoreCase("chrome")) {
        	driver = initializeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
        	driver = initializeDriver();
        }
    }

    @Test
    public void openSite() throws IOException {
    	driver.get(prop.getProperty("nseWebsiteURL"));
		getScreenshot("Homepage");
        System.out.println("Title: " + driver.getTitle() + " | Thread ID: " + Thread.currentThread().getId());
        log.debug("Parallel Tests executed Sucessfully");        
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }
}

