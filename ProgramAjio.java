package Exercise1;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProgramAjio {

	ChromeDriver driver;
	WebDriverWait wait;
	JavascriptExecutor jse;
	String discValue;

	@BeforeMethod
	public void startUp() {

//		1) Go to https://www.airbnb.co.in/

		String url = "https://www.ajio.com/shop/sale";

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
	public void ajio() throws InterruptedException, ParseException {

		driver.findElementByXPath("//input[@placeholder='Search AJIO']").click();
		driver.findElementByXPath("//input[@placeholder='Search AJIO']").sendKeys("Bags");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Women Handbags'])[1]")));
		driver.findElementByXPath("(//span[text()='Women Handbags'])[1]").click();

		driver.findElementByXPath("//div[@class='five-grid']").click();

		WebElement dropdownVal = driver.findElementByXPath("//select");
		Select drpDwn = new Select(dropdownVal);
		drpDwn.selectByValue("newn");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='price']")));
		driver.findElementByXPath("//span[text()='price']").click();

		driver.findElementByXPath("//input[@name='minPrice']").sendKeys("2000");
		driver.findElementByXPath("//input[@name='maxPrice']").sendKeys("5000", Keys.ENTER);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Ferrari LS Shoulder Bag']")));
		Thread.sleep(1000);
		driver.findElementByXPath("//div[text()='Ferrari LS Shoulder Bag']").click();

		Set<String> win = driver.getWindowHandles();
		List<String> winlist = new ArrayList<String>(win);

		driver.switchTo().window(winlist.get(1));

		boolean displayed = driver.findElementByXPath("//div[text()='EPIC']").isDisplayed();

		if (displayed == true) {
			System.out.println("Coupon Code Applicable");
			 discValue = 
					driver.findElementByXPath("//div[text()='Get it for ']/span").getText().replaceAll("\\D", "");
			System.out.println("Discounted values is : " + discValue);
		} else {
			System.out.println("Coupon Code Not Applicable");
		}

		driver.findElementByXPath("//span[contains(text(),'pin-code')]").click();

		driver.findElementByXPath("//input[@name='pincode']").sendKeys("560043");
		
		driver.findElementByXPath("//button[text()='CONFIRM PINCODE']").click();

		String delDate = driver.findElementByXPath("//li[text()='Expected Delivery: ']/span").getText();
		System.out.println("Expected Delivery Date is : " + delDate);

		driver.findElementByXPath("//div[text()='Other information']").click();

		String customerCareDetails = driver
				.findElementByXPath("//span[text()='Customer Care Address']/following-sibling::span[2]").getText();
		System.out.println("Customer Care DEtails are : " + customerCareDetails);

		driver.findElementByXPath("//span[text()='ADD TO BAG']").click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='GO TO BAG']")));
		Thread.sleep(3000);
		driver.findElementByXPath("//span[text()='GO TO BAG']").click();

		String amtbeforediscount = driver.findElementByXPath("//span[text()='Order Total']/following-sibling::span")
				.getText();
		System.out.println("Amount before discount is : " + amtbeforediscount);

		driver.findElementByXPath("//input[@id='couponCodeInput']").sendKeys("EPIC");
		driver.findElementByXPath("//button[text()='Apply']").click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Order Total']/following-sibling::span")));
		Thread.sleep(2000);
		String amtafterdiscount = driver.findElementByXPath("//span[text()='Order Total']/following-sibling::span")
				.getText();
		System.out.println("Amount after discount is : " + amtafterdiscount);

		String finPrice = driver.findElementByXPath("//div[contains(@class,'net-price')]").getText();
		System.out.println(finPrice);
		
		String finPrice1 = finPrice.replace("Rs. ", "").replace(",", "");
		System.out.println(finPrice1);
		System.out.println(discValue);
		if (finPrice1.contains(discValue)) {
			System.out.println("Value Matches");
		} else {
			System.out.println("Value does not Match");
		}

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Delete']")));
		Thread.sleep(2000);
		driver.findElementByXPath("//div[text()='Delete']").click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='DELETE']")));
		driver.findElementByXPath("//div[text()='DELETE']").click();

		driver.quit();
	}


	}


