package Exercise1;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Program8 {

	public static void main(String[] args) throws InterruptedException, IOException {

//			1) Go to https://www.pepperfry.com/

		String url = "https://www.pepperfry.com/";

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		driver.get(url);

		JavascriptExecutor js = (JavascriptExecutor) driver;

//			2) Mouseover on Furniture and click Office Chairs under Chairs

		for (int i = 0; i < 10; i++) {
			boolean popup = driver.findElementByXPath("//div[@id='regPopUp']//a[@class='popup-close']").isDisplayed();
			if (popup == false) {
				Thread.sleep(6000);

			} else {
				driver.findElementByXPath("//div[@id='regPopUp']//a[@class='popup-close']").click();
				break;
			}

		}

		WebElement eleFurniture = driver.findElementByXPath("(//a[text()='Furniture'])[1]");
		Actions action = new Actions(driver);
		action.moveToElement(eleFurniture).build().perform();

		Thread.sleep(2000);

		driver.findElementByXPath("//a[text()='Office Chairs']").click();

//			3) click Executive Chairs

		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,250)");
		driver.findElementByXPath("//h5[text()='Executive Chairs']").click();

//			4) Change the minimum Height as 50 in under Dimensions

		WebElement height = driver.findElementByXPath(
				"(//ul[@class='clip__filter-dimension']/li/div[@class='clip__filter-dimension-input-holder']/input[@data-key='height']/preceding-sibling::input)[1]");

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",height);
		js.executeScript("window.scrollBy(0,250)");
		Thread.sleep(3000);
		height.click();
		height.clear();
		height.sendKeys("50", Keys.TAB);

//			5) Add "Poise Executive Chair in Black Colour" chair to Wishlist
		
		Thread.sleep(3000);
		boolean displayed = driver.findElementByXPath("//a[@data-productname='Poise Executive Chair in Black Colour' and @data-tooltip='Add to Wishlist']").isDisplayed();

		if (displayed == true) {
			Thread.sleep(3000);
			driver.findElementByXPath(
					"//a[@data-productname='Poise Executive Chair in Black Colour' and @data-tooltip='Add to Wishlist']")
					.click();
		} else {
			System.out.println("Poise Executive Chair Not Available");
		}

//			6) Mouseover on Homeware and Click Pressure Cookers under Cookware

		WebElement eleHomeware = driver.findElementByXPath("(//a[text()='Homeware'])[1]");
		Actions action1 = new Actions(driver);
		action1.moveToElement(eleHomeware).build().perform();

		Thread.sleep(2000);

		driver.findElementByXPath("//a[text()='Pressure Cookers']").click();

//			7) Select Prestige as Brand

		Thread.sleep(3000);
		boolean isSelected = driver.findElementByXPath("//input[@data-value='Prestige']").isSelected();

		if (isSelected == false) {
			Thread.sleep(3000);
			driver.findElementByXPath("//label[@for='brandsnamePrestige']").click();
		}

//			8) Select Capacity as 1-3 Ltr

		boolean isCapSelected = driver.findElementByXPath("//input[@data-value='1 Ltr - 3 Ltr']").isSelected();

		if (isCapSelected == false) {
			Thread.sleep(3000);
			WebElement eleCapacity = driver.findElementByXPath("//label[@for='capacity_db1_Ltr_-_3_Ltr']");
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", eleCapacity);
			eleCapacity.click();
		}

//			9) Add "Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr" to Wishlist

		boolean displayed2 = driver.findElementByXPath("//a[@class='close']").isDisplayed();
		
		if (displayed2==true) {
			driver.findElementByXPath("//a[@class='close']").click();
		}
		
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,250)");
		driver.findElementByXPath("//a[@data-productname='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr' and @data-tooltip='Add to Wishlist']").click();

//			10) Verify the number of items in Wishlist

		Thread.sleep(3000);
		String wishListitems = driver.findElementByXPath("//a[@data-tooltip='Wishlist']/following-sibling::span").getText();
		
		System.out.println(wishListitems);
		int totitems = Integer.parseInt(wishListitems);

		if (totitems == 2) {
			System.out.println("Total wishlist items are 2");
		} else {
			System.out.println("Total items are: " + totitems);
		}

//			11) Navigate to Wishlist

		driver.findElementByXPath("//a[@data-tooltip='Wishlist']").click();

//			12) Move Pressure Cooker only to Cart from Wishlist

		driver.findElementByXPath(
				"//p[@class='item_title pf-bold-txt']/a[contains(text(),'Nakshatra')]/parent::p/following-sibling::div/div[@class='action_block']/a[@data-tooltip='Add to Cart']")
				.click();

//			13) Check for the availability for Pincode 600128

		driver.findElementByXPath("//input[@class='srvc_pin_text']").sendKeys("600063");
		driver.findElementByXPath("//a[text()='Check']").click();

//			14) Click Proceed to Pay Securely

		driver.findElementByXPath("//a[text()='Proceed to pay securely ']").click();

//			15 Click Proceed to Pay

//			16) Capture the screenshot of the item under Order Item
		
		WebElement scrnShot = driver.findElementByXPath("//a[@class='ck-pro-img-link']/img");
		File src = scrnShot.getScreenshotAs(OutputType.FILE);
		File dst = new File("./Screenshots/Program8/Snap.jpg");
		FileUtils.copyFile(src, dst);
		
//			17) Close the browser

		driver.quit();
	}

}
