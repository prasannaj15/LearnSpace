package Exercise1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class Program7 {

	public static void main(String[] args) throws InterruptedException {
		 
		String url = "https://www.honda2wheelersindia.com/";
		String val;
		

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
//		1) Go to https://www.honda2wheelersindia.com/r
		
		driver.get(url);
		driver.findElementByXPath("//button[@class='close']").click();
		
//			2) Click on scooters and click dio
		
		
		driver.findElementByXPath("//a[@id='link_Scooter']").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//img[contains(@src,'dio')]").click();
		
//			3) Click on Specifications and mouseover on ENGINE
		
		driver.findElementByXPath("//a[text()='Specifications']").click();
		driver.findElementByXPath("//a[text()='ENGINE']").click();
		
//			4) Get Displacement value
		
		Thread.sleep(3000);
		String displac = driver.findElementByXPath("//div[@class='engine part-2 axx']/ul[@class='tab_content']/li/span[text()='Displacement']/following-sibling::span").getText();
		
		String displac1 = displac.replaceAll("[^0-9.]","");
		
		double displacement = Double.parseDouble(displac1);
		
		
		
//			5) Go to Scooters and click Activa 125
		
		Thread.sleep(2000);
		driver.findElementByXPath("//a[@id='link_Scooter']").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//img[contains(@src,'activa-125')]").click();
		
//			6) Click on Specifications and mouseover on ENGINE
		
		Thread.sleep(2000);
		driver.findElementByXPath("//a[text()='Specifications']").click();
		driver.findElementByXPath("//a[text()='ENGINE']").click();
		
		
//			7) Get Displacement value
		
		String displacnew = driver.findElementByXPath("//div[@class='engine part-4 axx']/ul[@class='tab_content']/li/span[text()='Displacement']/following-sibling::span").getText();
		
		String displac2 = displacnew.replaceAll("[^0-9.]","");
		double displacementnew = Double.parseDouble(displac2);
		
		
//			8) Compare Displacement of Dio and Activa 125 and print the Scooter name having better Displacement.
		
		if (displacement>displacementnew) {
			System.out.println("Displacement of Dio is greater with value: "+displacement);
		
			val = "Dio BS-VI";
			
		} else {
			System.out.println("Displacement of Activa is greater with value: "+displacementnew);
			val = "Activa 125 BS-VI";
		}
		
//			9) Click FAQ from Menu 
		
		driver.findElementByXPath("(//a[text()='FAQ'])[1]").click();
		
//			10) Click Activa 125 BS-VI under Browse By Product
		
		driver.findElementByXPath("//a[text()='"+val+"']").click();
		
//			11) Click  Vehicle Price 
		
		driver.findElementByXPath("//a[text()=' Vehicle Price']").click();
		
//			12) Make sure Activa 125 BS-VI selected and click submit
		
		String text = driver.findElementByXPath("//select[@id='ModelID6']").getText();
		System.out.println(text);
		driver.findElementByXPath("(//button[text()='Submit'])[6]").click();
		
//			13) click the price link
		
		driver.findElementByXPath("//a[contains(text(),'Click here')]").click();
		
//			14)  Go to the new Window and select the state as Tamil Nadu and  city as Chennai
		
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandles1 = new ArrayList<String>(windowHandles);
		String newwin = windowHandles1.get(1);
		driver.switchTo().window(newwin);
		
		WebElement drpDwn1 = driver.findElementByXPath("//select[@id='StateID']");
		Select drpDwnval = new Select(drpDwn1);
		drpDwnval.selectByVisibleText("Tamil Nadu");
		
		WebElement drpDwn2 = driver.findElementByXPath("//select[@id='CityID']");
		Select drpDwnval2 = new Select(drpDwn2);
		drpDwnval2.selectByVisibleText("Chennai");
		
//			15) Click Search
		
		driver.findElementByXPath("//button[text()='Search']").click();
		Thread.sleep(3000);
		
//			16) Print all the 3 models and their prices
		
		WebElement table = driver.findElementByXPath("//table[@class='datashow']");
		List<WebElement> rowcells = table.findElements(By.tagName("tr"));
		int rows = rowcells.size();
		
		for (int i = 2; i < rows; i++) {
			List<WebElement> columns = rowcells.get(i).findElements(By.tagName("td"));
			int cols = columns.size();
				for (int j = 0; j < cols; j++) {
					String cellText = columns.get(j).getText();
					System.out.println(cellText);
					
					
				}
			System.out.println(" ");
		}
		
//			17) Close the Browser
		
		driver.quit();
		
	}

}
