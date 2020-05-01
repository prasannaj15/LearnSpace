package Exercise1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Program12 {

	public static void main(String[] args) throws InterruptedException {
		
		String url = "https://www.carwale.com/";
		
		// Initialization

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		
//			1) Go to https://www.carwale.com/
		

		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		
//			2) Click on Used
		
		Actions action = new Actions(driver);
		WebElement usedCars = driver.findElementByXPath("(//span[text()='Used Cars'])[2]");
		action.moveToElement(usedCars).build().perform();
		
		driver.findElementByXPath("(//a[text()='All Used Cars'])[2]").click();
		
//			3) Select the City as Chennai
		
		driver.findElementByXPath("//input[@searchtype='citySearch']").sendKeys("Chennai");
		Thread.sleep(4000);
		driver.findElementByXPath("//input[@searchtype='citySearch']").sendKeys(Keys.ENTER);
		
//			4) Select budget min (8L) and max(12L) and Click Search
		
		Boolean chklink = driver.findElementByXPath("//p[@class='margin-top5']/a[contains(text(),'show anymore tips')]").isDisplayed();
		if (chklink==true) {
			driver.findElementByXPath("//p[@class='margin-top5']/a[contains(text(),'show anymore tips')]").click();
		}
		
		
		Thread.sleep(4000);
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;

		jse.executeScript("scroll(0, 250);");
		
		driver.findElementByXPath("//div[@id='minMaxContainer']").click();
		
		driver.findElementByXPath("//ul[@id='minPriceList']/li[text()='8 Lakh']").click();
		
		Thread.sleep(1000);
		driver.findElementByXPath("//ul[@id='maxPriceList']/li[text()='12 Lakh']").click();
		
		
//			5) Select Cars with Photos under Only Show Cars With
		
		driver.findElementByXPath("//span[text()='Cars with Photos']").click();
		
//			6) Select Manufacturer as "Hyundai" --> Creta
		
		jse.executeScript("scroll(0, 500);");
		Thread.sleep(2000);
		driver.findElementByXPath("//input[@placeholder='Select Manufacturer']").sendKeys("Hyundai");
		jse.executeScript("scroll(0, 250);");
		driver.findElementByXPath("//span[text()=' Hyundai ']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//span[text()='Creta']").click();
		
		
//			7) Select Fuel Type as Petrol
		jse.executeScript("scroll(0, 1000);");
		driver.findElementByXPath("//div[@name='fuel']").click();
		driver.findElementByXPath("//li[@name='Petrol']/span[text()='Petrol']").click();
		
		
		
//			8) Select Best Match as "KM: Low to High"
		
		WebElement sort = driver.findElementByXPath("//select[@id='sort']");
		jse.executeScript("arguments[0].scrollTop = arguments[1];",sort, 250); 
		Thread.sleep(500); 
		sort.click();
		
		Select drpDwn = new Select(sort);
		drpDwn.selectByValue("2");
				
//			9) Validate the Cars are listed with KMs Low to High
//			10) Add the least KM ran car to Wishlist
		
		List<WebElement> eleItems = driver.findElementsByXPath("//span[@class='slkms vehicle-data__item']");
		List<WebElement> carNames = driver.findElementsByXPath("//span[@data-carname-id='carname']");
		List<Integer> items = new ArrayList<Integer>();
		Map<String,Integer> myMap = new LinkedHashMap<String,Integer>();
		for (int i = 0; i < eleItems.size(); i++) {
			
			int val = Integer.parseInt(eleItems.get(i).getText().replaceAll("\\D", ""));
			String name = carNames.get(i).getText();
			myMap.put(name, val);
			items.add(val);
			
		}
		String myKey = " ";
//		System.out.println(myMap);
		
		Collections.sort(items);
		
		for (Entry<String, Integer> entry : myMap.entrySet()) {
            if (entry.getValue().equals(items.get(0))) {
                 myKey = entry.getKey();
            }
        }
		
//			11) Go to Wishlist and Click on More Details
		
		jse.executeScript("scroll(0, 1000);");

		driver.findElementByXPath("//span[text()='"+myKey+"']/ancestor::div[@class='stock-detail']//span[@class='shortlist-icon--inactive shortlist']").click();

		driver.findElementByXPath("//li[@data-role='click-tracking']").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//span[text()='Hyundai Creta E 1.6 Petrol ']/parent::a/parent::p/following-sibling::p[contains(@class,'slpid')]/a").click();
				
//			12) Print all the details under Overview 
		
		Set<String> myWindow = driver.getWindowHandles();
		List<String> myWin = new ArrayList<String>(myWindow);
		
		driver.switchTo().window(myWin.get(1));
		
		List<WebElement> eleOverview = driver.findElementsByXPath("//div[@id='overview']/div/ul/li");
		Map<String,String> ovrviewList = new LinkedHashMap<String,String>();
		System.out.println(eleOverview.size());
		
		jse.executeScript("scroll(0, 1000);");
		for (int k = 1; k <= eleOverview.size(); k++) {
			String key1 = driver.findElementByXPath("//div[@id='overview']/div/ul/li["+k+"]/div[1]").getText();
			String value1 = driver.findElementByXPath("//div[@id='overview']/div/ul/li["+k+"]/div[2]").getText();
			ovrviewList.put(key1, value1);
		}
		
		System.out.println(ovrviewList);
		

		for (Entry<String, String> entry : ovrviewList.entrySet()) {
			System.out.println(entry.getKey()+'\t'+'\t'+entry.getValue());
        }
		
		
//			13) Close the browser.
		
		driver.quit();
	}

}
