package Exercise1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Program2 {

	public static void main(String[] args) throws InterruptedException {

//			1) Go to https://www.nykaa.com/

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		driver.navigate().to("https://www.nykaa.com/");

//			2) Mouseover on Brands and Mouseover on Popular
		Thread.sleep(4000);
		Actions action = new Actions(driver);
		WebElement eleBrand = driver.findElementByXPath("//a[text()='brands']");
		WebElement eleType = driver.findElementByXPath("//a[text()='Popular']");
		action.moveToElement(eleBrand).perform();
		eleType.click();

//			3) Click L'Oreal Paris

		driver.findElementByXPath("//img[contains(@src,'lorealparis.png')]").click();

//			4) Go to the newly opened window and check the title contains L'Oreal Paris

		Set<String> windows = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(windows);
		String secondWindow = winList.get(1);
		driver.switchTo().window(secondWindow);
		String title = driver.getTitle();
		if (title.contains("L'Oreal Paris")) {
			System.out.println("Launched the Window");
		} else {
			System.out.println("Issue Launching the Window");
		}

//			5) Click sort By and select customer top rated

		driver.findElementByXPath("//div[@id='sortComponent']").click();
		driver.findElementByXPath("//span[text()='customer top rated']").click();

		if (driver.findElementsByXPath("//button[text()='Maybe later']").size() > 0) {
			driver.findElementsByXPath("//button[text()='Maybe later']").get(0).click();
		}

//			6) Click Category and click Shampoo

		driver.findElementByXPath("//input[@id='SearchInputBox']").click();
		driver.findElementByXPath("//div[text()='Category']").click();
		driver.findElementByXPath("//span[contains(text(),'Shampoo')]").click();

//			7) check whether the Filter is applied with Shampoo

		String filter = driver.findElementByXPath("//div[@class='filter-applied']/following-sibling::ul/li").getText();
		if (filter.contains("Shampoo")) {
			System.out.println("Filter Applied");

		} else {
			System.out.println("Filter Not Applied");
		}

//			8) Click on L'Oreal Paris Colour Protect Shampoo

//		driver.findElementByXPath("//h2[@title='L'Oreal Paris Colour Protect Shampoo']").click();
		driver.findElementByXPath("//h2[contains(@title,'Paris Colour Protect Shampoo')]").click();
//			9) GO to the new window and select size as 175ml

		Set<String> windows1 = driver.getWindowHandles();
		List<String> winList1 = new ArrayList<String>(windows1);

		String thirdWindow = winList1.get(2);
		driver.switchTo().window(thirdWindow);

		driver.findElementByXPath("//span[text()='175ml']").click();

//			10) Print the MRP of the product

		String price = driver.findElementByXPath("//div[@class='price-info']/span[2]").getText();
		String priceval = price.replaceAll("^0-9", "");

		System.out.println(priceval);

//			11) Click on ADD to BAG

		driver.findElementByXPath("(//button[text()='ADD TO BAG'])[1]").click();

//			12) Go to Shopping Bag 

		driver.findElementByXPath("//div[@class='AddBagIcon']").click();

		// 13) Print the Grand Total amount

		String grandTotal = driver.findElementByXPath("//div[text()='Grand Total']//following-sibling::div").getText();
		String finTotal = grandTotal.replaceAll("^0-9", "");

		System.out.println("The Grand Total is: " + finTotal);

//			14) Click Proceed

		driver.findElementByXPath("//i[@class='proceed-arrow']/preceding-sibling::span").click();

//			15) Click on Continue as Guest

		driver.findElementByXPath("//button[contains(text(),'CONTINUE AS GUEST')]").click();

//			16) Print the warning message (delay in shipment)

		String errMsg = driver.findElementByXPath("//div[@class='message']").getText();
		System.out.println(errMsg);

//			17) Close all windows

		Set<String> windows2 = driver.getWindowHandles();
		List<String> winList2 = new ArrayList<String>(windows2);

		for (int i = 0; i < winList2.size(); i++) {
			String finWindow = winList1.get(i);
			driver.switchTo().window(finWindow).close();

		}
		System.out.println("Execution Completed Successfully");

	}

}
