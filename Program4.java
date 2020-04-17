package Exercise1;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.Select;

public class Program4 {

	public static void main(String[] args) throws InterruptedException {

		String url = "https://store.hp.com/in-en/";

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver();

//		1) Go to https://store.hp.com/in-en/

		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);
		driver.findElementByXPath("(//button[@aria-label='Close'])[1]").click();
		if (driver.findElementByXPath("//iframe[@id='ifr_popup']").isDisplayed()) {
			driver.switchTo().frame("//iframe[@id='ifr_popup']");
			driver.findElementByXPath("//span[@class,'optly-modal-close close-icon']").click();
			driver.switchTo().defaultContent();
		}

//			2) Mouse over on Laptops menu and click on Pavilion

		Actions action = new Actions(driver);
		WebElement eleLaptop = driver.findElementByXPath("//span[text()='Laptops']");
		action.moveToElement(eleLaptop).perform();
		driver.findElementByXPath("//span[text()='Pavilion']").click();

//			3) Under SHOPPING OPTIONS -->Processor -->Select Intel Core i7

		driver.findElementByXPath("(//span[text()='Processor'])[2]").click();
		driver.findElementByXPath("//span[text()='Intel Core i7']").click();

//			4) Hard Drive Capacity -->More than 1TB

		Thread.sleep(6000);
		driver.findElementByXPath("//span[contains(text(),'More than 1 TB')]").click();

//			5) Select Sort By: Price: Low to High

		Thread.sleep(3000);
		WebElement sortBy = driver.findElementByXPath("(//select[@id='sorter'])[1]");
		Select dropdown = new Select(sortBy);
		dropdown.selectByValue("price_asc");

//			6) Print the First resulting Product Name and Price

		List<WebElement> eleName = driver.findElementsByXPath("//a[@class='product-item-link']");
		System.out.println("Name of the Product is : " + eleName.get(0).getText());

		List<WebElement> elePrice = driver.findElementsByXPath("//span[@data-price-type='finalPrice']/span");
		String priceval = elePrice.get(0).getText();
		String finPrice = priceval.replaceAll("\\D", "");
		System.out.println("Price of the Product is : " + finPrice);

//			7) Click on Add to Cart

		Thread.sleep(5000);
		List<WebElement> eleAddCart = driver.findElementsByXPath("//span[text()='Add To Cart']");
		eleAddCart.get(0).click();

//			8) Click on Shopping Cart icon --> Click on View and Edit Cart

		Thread.sleep(3000);
		driver.findElementByXPath("//a[@class='action showcart']").click();

//			9) Check the Shipping Option --> Check availability at Pincode

		driver.findElementByXPath("//span[text()='View and edit cart']").click();

//			10) Verify the order Total against the product price

		Thread.sleep(3000);
		driver.findElementByXPath("//input[@name='pincode']").click();
		driver.findElementByXPath("//input[@name='pincode']").sendKeys("600063");
		driver.findElementByXPath("//button[text()='check']").click();

		WebElement confirm = driver.findElementByXPath("//span[@class='available']");

		if (confirm.isDisplayed()) {
			System.out.println("Delivery is Available");
		} else {
			System.out.println("Delivery is not available");
		}

//			11) Proceed to Checkout if Order Total and Product Price matches

		WebElement eleTotOrder = driver.findElementByXPath("//tr[@class='grand totals']/td/strong/span");
		String grandTotal = eleTotOrder.getText();
		String cartAmt = grandTotal.replaceAll("\\D", "");
		System.out.println(cartAmt);
		System.out.println(finPrice);
		if (cartAmt.equals(finPrice)) {
			System.out.println("Price value matches");
		} else {
			System.out.println("Price value does not match");
		}

//			12) Click on Place Order

		driver.findElementByXPath("//button[@title='Proceed to Checkout'][1]").click();
		Thread.sleep(5000);
		driver.findElementByXPath("(//span[text()='Place Order'])[4]").click();

//			13) Capture the Error message and Print

		String errMsg = driver.findElementByXPath("//div[@class='message notice']").getText();
		System.out.println("Error Message is : " + errMsg);

//			14) Close Browser

		driver.quit();
		System.out.println("Online Order of HP Product TC Completed Successfully");

	}

}
