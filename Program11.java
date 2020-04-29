package Exercise1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Program11 {

	public static void main(String[] args) throws InterruptedException {
		
		String url = "https://www.snapdeal.com/";
		
		// Initialization

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver();
		
//		1) Go to https://www.snapdeal.com/
		
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


//			‎2) Mouse over on Toys, Kids' Fashion & more and click on Toys
		
		WebElement toyLink = driver.findElementByXPath("//span[contains(text(),'Fashion & more')]");
		Actions action = new Actions(driver);
		action.moveToElement(toyLink).build().perform();
		
//			3) Click Educational Toys in Toys & Games
		
		driver.findElementByXPath("//span[text()='Educational Toys']").click();
		
//			‎4) Click the Customer Rating 4 star and Up 
		
		driver.findElementByXPath("//label[@for='avgRating-4.0']").click();
		
//			5) Click the offer as 40-50
		
		Thread.sleep(5000);
		WebElement percent = driver.findElementByXPath("//a[text()=' 40 - 50']/parent::label");
		action.moveToElement(percent).build().perform();
		percent.click();
		
//			6) Check the availability for the pincode
		
		driver.findElementByXPath("//input[@placeholder='Enter your pincode']").sendKeys("600063");
		driver.findElementByXPath("//button[text()='Check']").click();
		
//			7) Click the Quick View of the first product 
		
		Thread.sleep(3000);
		WebElement product1 = driver.findElementByXPath("(//p[@class='product-title'])[1]");
		action.moveToElement(product1).build().perform();
		driver.findElementByXPath("(//div[contains(text(),'Quick View')])[1]").click();
		
//			8) Click on View Details
		
		driver.findElementByXPath("//a[contains(text(),'view details')]").click();
		
//			9) Capture the Price of the Product and Delivery Charge
//		
//		Set<String> winSet = driver.getWindowHandles();
//		List<String> winList = new ArrayList<String>(winSet);
//		driver.switchTo().window(winList.get(1));
		
		Thread.sleep(3000);
		
		String val = driver.findElementByXPath("//span[@itemprop='price']").getText();
		int productVal = Integer.parseUnsignedInt(val);
		String delCharge = driver.findElementByXPath("(//span[@class='availCharges'])[2]").getText();
		
		String delVal = delCharge.replaceAll("\\D", "");
		
		int findelivery = Integer.parseInt(delVal);
		
		int totalPrice = productVal+findelivery;
		
		
		System.out.println(totalPrice);
		
		driver.findElementByXPath("//span[text()='add to cart']").click();
		
		
//			10) Validate the You Pay amount matches the sum of (price+deliver charge)
		
		String cartPrice = driver.findElementByXPath("//div[@class='you-pay']/span").getText().replaceAll("\\D", "");
		
		int finalPrice = Integer.parseInt(cartPrice);
		
		if (totalPrice==finalPrice) {
			System.out.println("Value Matches");
			
		} else {
			System.out.println("Value Doesnt Match");
		}
		
		
//			11) Search for Sanitizer
		
		WebElement newItem = driver.findElementByXPath("//input[@id='inputValEnter']");
		newItem.click();
		newItem.sendKeys("Sanitizer",Keys.ENTER);
		
//			12) Click on Product "BioAyurveda Neem Power Hand Sanitizer"
		
		Thread.sleep(3000);
		driver.findElementByXPath("(//p[contains(text(),'BioAyurveda Neem Power')])[1]").click();
		
		Set<String> winSet = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(winSet);
		driver.switchTo().window(winList.get(1));
		
//			13) Capture the Price and Delivery Charge
		
		Thread.sleep(3000);
		
		String val1 = driver.findElementByXPath("//span[@itemprop='price']").getText();
		int productVal1 = Integer.parseUnsignedInt(val1);
		String delCharge1 = driver.findElementByXPath("(//span[@class='availCharges'])[2]").getText();
		
		String delVal1 = delCharge1.replaceAll("\\D", "");
		
		int findelivery1 = Integer.parseInt(delVal1);
		
		int totalPrice1 = productVal1+findelivery1;
		
//			14) Click on Add to Cart
		
		driver.findElementByXPath("(//span[text()='ADD TO CART'])[1]").click();
		Thread.sleep(6000);
		
//			15) Click on Cart 
		
		
		driver.findElementByXPath("//span[@class='cartQuantity']/parent::i").click();
		
//			16) Validate the Proceed to Pay matches the total amount of both the products
		
		String fincartPrice = driver.findElementByXPath("//input[contains(@value,'PROCEED')]").getAttribute("value");
		
		int myfinalPrice = Integer.parseInt(fincartPrice.replaceAll("\\D", ""));
		
		Thread.sleep(3000);
		String item1 = driver.findElementByXPath("(//div[@class='cart-collapsed']//span[@class='item-subtotal-black'])[1]").getText();
		String item2 = driver.findElementByXPath("(//div[@class='cart-collapsed']//span[@class='item-subtotal-black'])[2]").getText();
		
		int itemval1 = Integer.parseInt(item1.replaceAll("\\D", ""));
		int itemval2 = Integer.parseInt(item2.replaceAll("\\D", ""));
		int itemvalue=itemval1+itemval2;
		
		if (itemvalue==myfinalPrice) {
			System.out.println("Product Value Matches");
			
		} else {
			System.out.println("Product Value does not match");
		}
		
		
//			17) Close all the windows
		driver.quit();
		
	}

}
