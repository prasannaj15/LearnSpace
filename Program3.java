package Exercise1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class Program3 {

	public static void main(String[] args) throws InterruptedException {

		// Variable Declaration

		String url = "https://www.makemytrip.com/";
		String city = "Goa";
		int adult = 2;
		int child = 1;
		String childAge = "10";
		String checkinDate = "May 20 2020";
		String checkoutDate = "May 22 2020";
		String myDestination = "Baga";
		String category = "5 Star";

		// Initialization

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;

//		1) Go to https://www.makemytrip.com/

		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//			2) Click Hotels

		driver.findElementByXPath("//span[text()='Hotels']").click();

//			3) Enter city as Goa, and choose Goa, India

		driver.findElementByXPath("//input[@id='city']").click();
		driver.findElementByXPath("//input[@placeholder='Enter city/ Hotel/ Area/ Building']").sendKeys(city, Keys.TAB);

//			4) Enter Check in date as Next month 15th (May 15) and Check out as start date+5

		driver.findElementByXPath("//input[@id='checkin']").click();

		driver.findElementByXPath("//div[contains(@aria-label,'" + checkinDate + "')]").click();
		driver.findElementByXPath("//div[contains(@aria-label,'" + checkoutDate + "')]").click();

//			5) Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click Apply Button.

		driver.findElementByXPath("//input[@id='guest']").click();
		Thread.sleep(5000);

		driver.findElementByXPath("//ul[@data-cy='adultCount']/li[text()='" + adult + "']").click();

//		driver.findElementByXPath("(//li[contains(@data-cy,'children')])[1]").getText();

		driver.findElementByXPath("//li[contains(@data-cy,'children-" + child + "')]").click();

		WebElement selectAge = driver.findElementByClassName("ageSelectBox");
		Select dropdown = new Select(selectAge);
		dropdown.selectByVisibleText(childAge);
		driver.findElementByXPath("//button[text()='APPLY']").click();

//			6) Click Search button

		driver.findElementByXPath("//button[text()='Search']").click();

//			7) Select locality as Baga
		Thread.sleep(4000);
		driver.get(driver.getCurrentUrl());
		driver.findElementByXPath("//label[text()='" + myDestination + "']").click();

//			8) Select 5 start in Star Category under Select Filters

		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,1000)");

		driver.findElementByXPath("//label[text()='" + category + "']").click();

//			9) Click on the first resulting hotel and go to the new window

		String hotelName = driver.findElementByXPath("//p[@itemprop='name']/span[1]").getText();
		driver.findElementByXPath("//p[@itemprop='name']/span[1]").click();

//			10) Print the Hotel Name

		System.out.println("Our Hotel Name is: " + hotelName);
		Set<String> myWindow = driver.getWindowHandles();
		List<String> win1 = new ArrayList<String>(myWindow);

		String win2 = win1.get(1);
		driver.switchTo().window(win2);

//			11) Click MORE OPTIONS link and Select 3Months plan and close

		driver.findElementByXPath("//span[text()='MORE OPTIONS']").click();
		WebElement eleTable = driver.findElementByXPath("//table[@class='tblEmiOption']/tbody");
		List<WebElement> rowCount = eleTable.findElements(By.tagName("tr"));
		int rowSize = rowCount.size();
		
		for (int i = 1; i < rowSize; i++) {
			if (rowCount.get(i).getText().contains("3 months")) {
				driver.findElementByXPath("//table[@class='tblEmiOption']/tbody/tr["+(i+1)+"]/td[6]/span").click();
				driver.findElementByClassName("close").click();
				break;
			} 			
		}
		

//			12) Click on BOOK THIS NOW
		
		driver.findElementByLinkText("BOOK THIS NOW").click();
		
//			13) Print the Total Payable amount
		
		 String totalAmt = driver.findElementByXPath("//span[@id='revpg_total_payable_amt']").getText();
		
		 System.out.println(totalAmt);
		 
//			14) Close the browser  

		 Set<String> windows2 = driver.getWindowHandles();
			List<String> winList2 = new ArrayList<String>(windows2);

			for (int i = 0; i < winList2.size(); i++) {
				String finWindow = winList2.get(i);
				driver.switchTo().window(finWindow).close();

			}
			System.out.println("Execution Completed Successfully");
		 
	}
}
