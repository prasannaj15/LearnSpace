package Exercise1;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Azure {
	
	ChromeDriver driver;
	WebDriverWait wait;
	JavascriptExecutor jse;
	String discValue;

	@BeforeMethod
	public void startUp() {

//		1) Go to https://azure.microsoft.com/en-in/

		String url = "https://azure.microsoft.com/en-in/";

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);

//		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
		driver.get(url);

	}

	@Test
	public void azure() throws InterruptedException {
		
//			2) Click on Pricing
		
		driver.findElementByXPath("//a[text()='Pricing']").click();
		
//			3) Click on Pricing Calculator
		
		driver.findElementByXPath("//a[contains(text(),'Pricing calculator')]").click();
		
//			4) Click on Containers
		
		driver.findElementByXPath("//button[text()='Containers']").click();
		
//			5) Select Container Instances
		driver.findElementByXPath("(//span[text()='Container Instances'])[3]").click();
		
//			6) Click on Container Instance Added View
//			7) Select Region as "South India"
		
		WebElement region = driver.findElementByXPath("(//select[@name='region'])[1]");
		Select drpDwn = new Select(region);
		drpDwn.selectByValue("south-india");
		
//			8) Set the Duration as 180000 seconds
		
		WebElement seconds = driver.findElementByXPath("(//input[@name='seconds'])[1]");
		seconds.click();
		Actions action = new Actions(driver);
		action.doubleClick(seconds).build().perform();
		
		seconds.click();
		seconds.clear();
		Thread.sleep(1000);
		seconds.click();
		seconds.clear();
		seconds.sendKeys("80000");
		
//			9) Select the Memory as 4GB
		
		WebElement memory = driver.findElementByXPath("(//select[@name='memory'])[1]");
		Select drpDwn1 = new Select(memory);
		drpDwn1.selectByValue("4");
		
//			10) Enable SHOW DEV/TEST PRICING
		
		driver.findElementByXPath("//button[@name='devTestSelected']").click();
		
//			11) Select Indian Rupee  as currency
		
		WebElement Currency = driver.findElementByXPath("(//select[@aria-label='Currency'])[1]");
		Select drpDwn2 = new Select(Currency);
		drpDwn2.selectByValue("INR");
		
		
//			12) Print the Estimated monthly price
		
		String monPrice = driver.findElementByXPath("//span[text()='Monthly cost']/following-sibling::span/span").getText();
		
		monPrice.replace("?", "Rs. ");
		System.out.println("Monthly Price is : " + monPrice);
		
//			13) Click on Export to download the estimate as excel
		
		driver.findElementByXPath("//button[text()='Export']").click();
		
//			14) Verify the downloded file in the local folder
		
		File f = new File("C:\\Users\\prasanna\\Downloads\\ExportedEstimate.xlsx");
		while (f.exists()!=true) {
			Thread.sleep(1000);
		}
		
		System.out.println("First File Exists");
		
//			15) Navigate to Example Scenarios and Select CI/CD for Containers
		
		WebElement exscenarios = driver.findElementByXPath("//a[text()='Example Scenarios']");
		
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", exscenarios);
		jse.executeScript("scroll(0, -100);");
		exscenarios.click();
		
//			16) Click Add to Estimate
		
		driver.findElementByXPath("//span[text()='CI/CD for Containers']").click();
		
//			17) Change the Currency as Indian Rupee
		
		driver.findElementByXPath("//a[text()='Your Estimate']").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//button[text()='Add to estimate']").click();
		
		
//			18) Enable SHOW DEV/TEST PRICING
		
		WebElement element1 = driver.findElementByXPath("//input[@aria-label='CI/CD for containers']");
		boolean enabled = element1.isDisplayed();
		
		while (enabled!=true) {
			Thread.sleep(1000);
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@aria-label='CI/CD for containers']")));
		driver.findElementByXPath("//button[@name='devTestSelected']").click();
		
//			19) Export the Estimate
		
		WebElement Currency1 = driver.findElementByXPath("(//select[@aria-label='Currency'])[1]");
		Select drpDwn3 = new Select(Currency1);
		drpDwn3.selectByValue("INR");
		
		
		driver.findElementByXPath("//button[text()='Export']").click();
		
//			20) Verify the downloded file in the local folder
		
		File f2 = new File("C:\\Users\\prasanna\\Downloads\\ExportedEstimate (1).xlsx");
		while (f2.exists()!=true) {
			Thread.sleep(1000);
		}
		
		System.out.println("Second File Exists");
		
	}
	

}
