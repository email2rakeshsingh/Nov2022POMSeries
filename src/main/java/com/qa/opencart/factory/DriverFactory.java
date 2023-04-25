package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

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

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// remote execution:
				init_remoteDriver("chrome");

			} else {
				// local execution
				WebDriverManager.chromedriver().setup();
				tlDriver.set(new ChromeDriver(opetionManager.getChromeOptions()));

			}

		} else if (browsserName.equalsIgnoreCase("Firefox")) {

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// remote execution:
				init_remoteDriver("Firefox");

			} else {
				// local execution
				WebDriverManager.firefoxdriver().setup();
				tlDriver.set(new FirefoxDriver(opetionManager.getFirefoxOptions()));

			}

		} else if (browsserName.equalsIgnoreCase("Edge")) {

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// remote execution:
				init_remoteDriver("Edge");

			} else {
				// local execution
				WebDriverManager.edgedriver().setup();
				tlDriver.set(new EdgeDriver());

			}

		} else {
			System.out.println("Please pass the right browser :" + browsserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();

	}

	private void init_remoteDriver(String browserName) {
		System.out.println("Running tests on remote grid server: " + browserName);

		if (browserName.equalsIgnoreCase("chrome")) {

			try {
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), opetionManager.getChromeOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

		}

		else {
			if (browserName.equalsIgnoreCase("firefox")) {

				try {
					tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),
							opetionManager.getFirefoxOptions()));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}

			}
		}

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

	public int getTestCount() {
		return 100;
	}

}
