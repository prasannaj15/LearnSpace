package Exercise1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Program14 {

	ChromeDriver driver;
	WebDriverWait wait;
	JavascriptExecutor jse;

	@BeforeMethod
	public void startUp() {

//		1) Go to https://www.zalando.com/

		String url = "https://www.zalando.com/";

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
		driver.get(url);

	}

	@Test
	public void zalando() throws InterruptedException {

//			2) Get the Alert text and print it

		wait.until(ExpectedConditions.alertIsPresent());
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();

//			3) Close the Alert box and click on Zalando.uk

		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Zalando.uk']")));
		driver.findElementByXPath("//a[text()='Zalando.uk']").click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='uc-btn-accept-banner']")));
		driver.findElementByXPath("//button[@id='uc-btn-accept-banner']").click();

//			4) Click Women--> Clothing and click Coat 


		driver.findElementByXPath("(//span[text()='Women'])[1]").click();
		Thread.sleep(1000);
		WebElement selection = driver.findElementByXPath("//span[text()='Clothing']");
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Clothing']")));
		Actions action = new Actions(driver);
		action.moveToElement(selection).build().perform();
		driver.findElementByXPath("//span[text()='Coats']").click();

//			5) Choose Material as cotton (100%) and Length as thigh-length

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Material']")));
		Thread.sleep(1000);
		driver.findElementByXPath("//span[text()='Material']").click();
		driver.findElementByXPath("//ul[contains(@class,'content cat_list')]/li/span[text()='cotton (100%)']").click();
		driver.findElementByXPath("//button[text()='Save']").click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Length']")));
		Thread.sleep(2000);

		driver.findElementByXPath("//span[text()='Length']").click();
		driver.findElementByXPath("//ul[contains(@class,'content cat_list')]/li/span[text()='thigh-length']").click();
		driver.findElementByXPath("//button[text()='Save']").click();

//			6) Click on Q/S designed by MANTEL - Parka coat

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='MANTEL - Parka - navy']")));
		Thread.sleep(1000);
		WebElement item = driver.findElementByXPath("//div[text()='MANTEL - Parka - navy']");
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", item);
		jse.executeScript("scroll(0, 75);");
		driver.findElementByXPath("//div[text()='MANTEL - Parka - navy']").click();

//			7) Check the availability for Color as Olive and Size as 'M'

		driver.findElementByXPath("(//img[@alt='navy'])[2]").click();
		Thread.sleep(1000);
		driver.findElementByXPath("//span[text()='Choose your size']").click();
		driver.findElementByXPath("//span[text()='M']").click();

//			8) If the previous preference is not available, check  availability for Color Navy and Size 'M'
//			9) Add to bag only if Standard Delivery is free

		Thread.sleep(3000);
		String freeDel = driver
				.findElementByXPath("(//span[text()='Standard delivery'])[1]/parent::div/div/div/button/span")
				.getText();

		if (freeDel == "Free") {
			System.out.println("Standard Delivery is Free");
		} else {
			System.out.println("Standard Delivery is not free");
		}

		driver.findElementByXPath("//span[text()='Add to bag']").click();

//			10) Mouse over on Your Bag and Click on "Go to Bag"

		WebElement cart = driver.findElementByXPath("//div[contains(@class,'navToolItem')]/a[contains(@href,'cart')]");
		action.moveToElement(cart).build().perform();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Go to bag']")));
		driver.findElementByXPath("//div[text()='Go to bag']").click();

//			11) Capture the Estimated Deliver Date and print

		String delDate = driver.findElementByXPath("//div[@data-id='delivery-estimation']/span").getText();

//			12) Mouse over on FREE DELIVERY & RETURNS*, get the tool tip text and print

		String delMsg = driver.findElementByXPath("//a[text()='Free delivery & returns*']/parent::span")
				.getAttribute("title");

		System.out.println(delDate);
		System.out.println(delMsg);

//			13) Click on Start chat in the Start chat and go to the new window

		driver.findElementByXPath("//a[text()='Free delivery & returns*']").click();
		WebElement chat = driver.findElementByXPath("//span[text()='Start chat']");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Start chat']")));

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", chat);
		jse.executeScript("scroll(0, 200);");
		Thread.sleep(1000);
		driver.findElementByXPath("//span[text()='Start chat']").click();

//			14) Enter you first name and a dummy email and click Start Chat

		Set<String> windows = driver.getWindowHandles();
		List<String> win = new ArrayList<String>(windows);
		String myWindow = win.get(1);
		driver.switchTo().window(myWindow);

		driver.findElementByXPath("//input[@id='prechat_customer_name_id']").click();
		driver.findElementByXPath("//input[@id='prechat_customer_name_id']").sendKeys("Prasanna", Keys.ENTER);
		driver.findElementByXPath("//input[@id='prechat_customer_email_id']").click();
		driver.findElementByXPath("//input[@id='prechat_customer_email_id']").sendKeys("test@ymail.com", Keys.TAB);

		driver.findElementByXPath("//span[text()='Start Chat']").click();

//			15) Type Hi, click Send and print thr reply message and close the chat window.

		WebElement waitChat = driver.findElementByXPath("//button[text()='Send']");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Send']")));
		boolean displayed = waitChat.isDisplayed();
		Thread.sleep(1000);
		
		if (displayed == true) {
			driver.findElementByXPath("//textarea[contains(@placeholder,'Write a message')]").click();
			driver.findElementByXPath("//textarea[contains(@placeholder,'Write a message')]").sendKeys("Hi");
			waitChat.click();

		}else {
			System.out.println("not found");
		}
			

		String msgText = driver
				.findElementByXPath("//span[@class='client']/following-sibling::span/span[@class='messageText']")
				.getText();
		System.out.println(msgText);
	}

	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

}
