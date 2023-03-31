package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.customexception.FrameWorkException;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author rakesh.singh
 *
 */

public class DriverFactory {
	WebDriver driver;
	Properties prop;
	OptionsManager opetionManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * this method is used to initialize the driver on basis of given browser
	 * 
	 * @param BrowsserName updated to properties prop updated
	 * @return this method will return the webDriver.
	 */
	public WebDriver init_driver(Properties prop) {

		String browsserName = prop.getProperty("browser").trim();

		System.out.println("browser name is :" + browsserName);

		opetionManager = new OptionsManager(prop);

		if (browsserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver(opetionManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(opetionManager.getChromeOptions()));

		} else if (browsserName.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver(opetionManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(opetionManager.getFirefoxOptions()));

		} else if (browsserName.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			// driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());

		} else {
			System.out.println("Please pass the right browser :" + browsserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();

	}

	public synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * this method is used to initlalize the properties from the respective config
	 * file.
	 * 
	 * @return this returns properties class object will all the config properties.
	 */

	public Properties init_prop() {

		FileInputStream ip = null;
		prop = new Properties();

		// mvn command line argument:
		// mvn clean install -Denv="qa"
		String envName = System.getProperty("env");
		System.out.println("Running test on environment: " + envName);

		if (envName == null) {
			System.out.println("No env is given ......hence running it on QA env");
			try {
				ip = new FileInputStream("./src/test/resources/Config/qa.config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}

		else {
			try {
				switch (envName.toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/Config/qa.config.properties");
					break;

				case "dev":
					ip = new FileInputStream("./src/test/resources/Config/dev.config.properties");
					break;

				case "stage":
					ip = new FileInputStream("./src/test/resources/Config/stage.config.properties");
					break;

				case "uat":
					ip = new FileInputStream("./src/test/resources/Config/uat.config.properties");
					break;

				case "prod":
					ip = new FileInputStream("./src/test/resources/Config/config.properties");
					break;
				default:
					System.out.println("please pass the right environment values :" + envName);
					throw new FrameWorkException("No env foud ");

				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (FrameWorkException e) {
				e.printStackTrace();
			}
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * 
	 * take screenshot
	 * 
	 * @return
	 * 
	 */
	public String getScreenshot() {

		File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = "./screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(scrFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
