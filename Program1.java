package Exercise1;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Program1 {

	@Test
	public void myntra() throws InterruptedException
	{
	System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	
	//Disable notification
	ChromeOptions options=new ChromeOptions();
	options.addArguments("--disable-notifications");
	
	//launch browser,load url and maximize
	ChromeDriver driver =new ChromeDriver(options);
	driver.get("https://www.myntra.com/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	WebDriverWait wait=new WebDriverWait(driver,10);
	
	//Mouse hover on women
	WebElement womenLink = driver.findElementByXPath("(//div[@class='desktop-navLink'])[2]//a");
	Actions obj=new Actions(driver);
	obj.moveToElement(womenLink).perform();
	driver.findElementByLinkText("Jackets & Coats").click();
	
	//6985 items to get count alone
	String totalcount=driver.findElementByClassName("title-count").getText();
	String count=totalcount.replaceAll("\\D", "");
	int exactCount=Integer.parseInt(count);
	System.out.println("The count of total items is" +count);
	
	//Validate the sum of categories count matches
	String rawJacketCount=driver.findElementByXPath("//label[text()='Jackets']//span").getText();
	int jacketsCount=Integer.parseInt(rawJacketCount.replaceAll("\\D", ""));
	
	String rawCoatCount=driver.findElementByXPath("//label[text()='Coats']//span").getText();
	int coatsCount=Integer.parseInt(rawCoatCount.replaceAll("\\D", ""));
	
	System.out.println(+jacketsCount +coatsCount);
	if (exactCount==jacketsCount+coatsCount)
	{
		System.out.println("The count matched");
	}
	
	//Check Coats.Click + More option under BRAND.Type MANGO and click checkbox.Close the pop-up x
	driver.findElementByXPath("//label[text()='Coats']").click();
	wait.until(ExpectedConditions.textToBe(By.className("filter-summary-filter"), "Coats"));
	
	driver.findElementByClassName("brand-more").click();
	driver.findElementByClassName("FilterDirectory-searchInput").sendKeys("MANGO");
	driver.findElementByXPath("(//label[text()='MANGO'])[2]").click();
	driver.findElementByXPath("//div[@class='FilterDirectory-titleBar']//span").click();
	wait.until(ExpectedConditions.textToBe(By.xpath("(//div[@class='filter-summary-filter'])[2]"), "MANGO"));
	
	//Confirm all the Coats are of brand MANGO
	List<WebElement> products = driver.findElementsByXPath("//h3[@class='product-brand']");
	int brandCount=0;
	for (WebElement product : products) {
		if(product.getText().equals("MANGO"))
		{
			brandCount=brandCount+1;
		}}
	
	if(brandCount==products.size()) {
	System.out.println("All products are Mango brands");}
	
	//Sort by Better Discount
	obj.moveToElement(driver.findElementByClassName("sort-sortBy")).perform();
	driver.findElementByXPath("//label[text()='Better Discount']").click();
	Thread.sleep(3000);
	
	// Find the price of first displayed item
	List<WebElement> price = driver.findElementsByClassName("product-discountedPrice");
	System.out.println("The price of first item is " +price.get(0).getText());

	//Mouse over on size of the first item
    obj.moveToElement(driver.findElementByXPath("(//h3[@class='product-brand'])[1]")).perform();
	
	//Click on WishList Now
     driver.findElementByXPath("(//span[text()='wishlist now'])[1]").click();
	 driver.close();
	
	}
}

