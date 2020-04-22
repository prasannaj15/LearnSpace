package Exercise1;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Program6 {
		
		public static void main(String[] args) throws InterruptedException
		{

			//1) Go to https://www.bigbasket.com/
			//--------------------------------
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			ChromeOptions options  = new ChromeOptions();
		    //options.addArguments("incognito");
		    options.addArguments("--disable-popup-blocking");
		    DesiredCapabilities capabilities = new DesiredCapabilities();
		    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		    ChromeDriver driver = new ChromeDriver(capabilities);
			
			//ChromeDriver driver = new ChromeDriver();
			
			
			driver.get("https://www.bigbasket.com/");
			
			driver.manage().window().maximize();
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			//2) mouse over on  Shop by Category 
			//-----------------------------------
			
			Actions action = new Actions(driver);
			
			action.moveToElement(driver.findElementByXPath("//a[contains(text(),'Shop by Category')]")).build().perform();
			
			//3)Go to FOODGRAINS, OIL & MASALA --> RICE & RICE PRODUCTS
			//-------------------------------------------------------
			
			//ul[@id='navBarMegaNav']//a[contains(text(),'Foodgrains, Oil & Masala')]
			
			action.moveToElement(driver.findElementByXPath("//ul[@id='navBarMegaNav']//a[contains(text(),'Foodgrains,')]")).build().perform();
			
			Thread.sleep(3000);
			
			WebDriverWait wait = new WebDriverWait(driver,30);
			
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//li[@class='ng-scope']//a[text()='Rice & Rice Products'])[2]")));
			
			action.moveToElement(driver.findElementByXPath("(//li[@class='ng-scope']//a[text()='Rice & Rice Products'])[2]")).click().build().perform();
			
			//wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//a[contains(text(),'Rice Products')]")));
			
			//driver.findElementByXPath("//a[contains(text(),'Rice & Rice')]").click();
			
			//action.moveToElement(driver.findElementByXPath("//a[contains(text(),'Rice Products')]")).click().build().perform();
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			//4) Click on Boiled & Steam Rice
			//-------------------------------
			Thread.sleep(1000);
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//div[@class='col-xs-12 checkbox subcat ng-scope']//span[text()='Boiled & Steam Rice'])[1]/parent::div")));
			
			driver.findElementByXPath("(//div[@class='col-xs-12 checkbox subcat ng-scope']//span[text()='Boiled & Steam Rice'])[1]/parent::div").click();
			
			//action.moveToElement(driver.findElementByXPath("(//div[@class='col-xs-12 checkbox subcat ng-scope']//span[text()='Boiled & Steam Rice'])[1]/parent::div")).click().build().perform();
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			//5) Choose the Brand as bb Royal
			//--------------------------------
			
			driver.findElementByXPath("(//div[@class='col-xs-12 ng-scope']//span[text()='bb Royal'])[1]//preceding-sibling::span").click();
			
			Thread.sleep(2000);
			
			
			
			wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[text()='bb Royal']")));
			
			if(driver.findElementByXPath("//div[text()='bb Royal']").isDisplayed())
			{
				
				System.out.println("bb Royal is clicked");
			}
			
			
			
			//6) Go to Ponni Boiled Rice - Super Premium and select 5kg bag from Dropdown
			//---------------------------------------------------------------------------
			
			//a[text()='Ponni Boiled Rice - Super Premium']/ancestor::div[@class='item prod-deck row ng-scope']//ul[@class='dropdown-menu drop-select']//span[text()='5 kg']
			
			wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//a[text()='Ponni Boiled Rice - Super Premium']/ancestor::div[@class='item prod-deck row ng-scope']//button[@class='btn btn-default dropdown-toggle form-control']")));
			
			driver.findElementByXPath("//a[text()='Ponni Boiled Rice - Super Premium']/ancestor::div[@class='item prod-deck row ng-scope']//button[@class='btn btn-default dropdown-toggle form-control']").click();
			
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//a[text()='Ponni Boiled Rice - Super Premium']/ancestor::div[@class='item prod-deck row ng-scope']//ul[@class='dropdown-menu drop-select']//span[text()='5 kg']")));
			
			driver.findElementByXPath("//a[text()='Ponni Boiled Rice - Super Premium']/ancestor::div[@class='item prod-deck row ng-scope']//ul[@class='dropdown-menu drop-select']//span[text()='5 kg']").click();
			
			
			//7) print the price of Rice
			//-------------------------
			
			String strPriceOfRice = driver.findElementByXPath("//a[text()='Ponni Boiled Rice - Super Premium']/ancestor::div[@class='item prod-deck row ng-scope']//span[@class='discnt-price']").getText();
			strPriceOfRice = strPriceOfRice.replaceAll("\\D", "");
			double dblPriceOfRice = Double.parseDouble(strPriceOfRice);
			
			System.out.println("Price of rice - "+dblPriceOfRice);
			
			//8) Click Add button
			//-----------------
			
			driver.findElementByXPath("//a[text()='Ponni Boiled Rice - Super Premium']/ancestor::div[@class='item prod-deck row ng-scope']//button[contains(text(),'Add')]").click();
			
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@class='dropdown-menu latest-at-bb']//p[text()='You are viewing our product catalogue in']")));
				
				if(driver.findElementByXPath("//div[@class='dropdown-menu latest-at-bb']//p[text()='You are viewing our product catalogue in']").isDisplayed())
				{
					//div[@class='dropdown-menu latest-at-bb']//a[text()='Continue']
					Thread.sleep(2000);
					action.moveToElement(driver.findElementByXPath("//div[@class='dropdown-menu latest-at-bb']//a[text()='Continue']")).click().build().perform();
					
				}
			} catch (NoSuchElementException e) {
				
			}
			
			
			//9) Verify the success message displayed
			//-------------------------------------------
			
			//wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[contains(text(),'Successfully added')]")));
			
			//String strSuccess = driver.findElementByXPath("//div[contains(text(),'Successfully added')]").getText();
			
			//System.out.println("Successfully added message - "+strSuccess);
			
			
			
			//10) Type Dal in Search field and enter
			//------------------------------------
			Thread.sleep(2000);
			driver.findElementById("input").clear();
			driver.findElementById("input").sendKeys("dal");
			
			driver.findElementByXPath("//button[@qa='searchBtn']").click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			//12) Go to Toor/Arhar Dal and select 2kg & set Qty 2 
			//-----------------------------------------------------
			
			driver.findElementByXPath("//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/ancestor::div[@class='item prod-deck row ng-scope']//button[@class='btn btn-default dropdown-toggle form-control']").click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/ancestor::div[@class='item prod-deck row ng-scope']//ul//span[text()='2 kg']")));
			
			driver.findElementByXPath("//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/ancestor::div[@class='item prod-deck row ng-scope']//ul//span[text()='2 kg']").click();
			
			WebElement ele = driver.findElementByXPath("//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/ancestor::div[@class='item prod-deck row ng-scope']//input[@class='form-control ng-pristine ng-untouched ng-valid ng-not-empty ng-valid-maxlength']");
			
			ele.clear();
			
			ele.sendKeys("2");
			
			//13) Print the price of Dal
			//---------------------------
			
			String strPriceOfDal = driver.findElementByXPath("//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/ancestor::div[@class='item prod-deck row ng-scope']//span[@class='discnt-price']/span").getText();
			strPriceOfDal = strPriceOfDal.replaceAll("\\D", "");
			double dblPriceOfDal = Double.parseDouble(strPriceOfDal);
			
			
			System.out.println("Price of Dal = "+dblPriceOfDal);
			
			//14) Click Add button
			//-------------------
			
			driver.findElementByXPath("//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/ancestor::div[@class='item prod-deck row ng-scope']//button[contains(text(),'Add')]").click();
			
			wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//button[@role='button']/following-sibling::div[1]")));
			
			driver.findElementByXPath("//button[@role='button']/following-sibling::div[1]").click();
			
			
			//15) Mouse hover on My Basket 
			//----------------------------
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='My Basket']")));
			
			action.moveToElement(driver.findElementByXPath("//span[text()='My Basket']")).build().perform();
			
			//16) Validate the Sub Total displayed for the selected items
			//----------------------------------------------------------
			
			double dblFrstRowPrice = calculatePrice(driver, 1);
			
			double dblSecondRowPrice = calculatePrice(driver, 2);

			double dblSubtotal = dblFrstRowPrice+dblSecondRowPrice;
			
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[@ng-bind='vm.cart.cart_total']")));
			
			String strSubTotalNow = driver.findElementByXPath("//span[@ng-bind='vm.cart.cart_total']").getText();
			//strSubTotalNow = strSubTotalNow.replaceAll("\\D", "");
			
			double dblSubTotalNow = Double.parseDouble(strSubTotalNow);
			
			if(dblSubtotal==dblSubTotalNow)
			{
				System.out.println("Correctly validated.  Subtotal = "+dblSubTotalNow);
			}
			
			
			
			//17) Reduce the Quantity of Dal as 1 
			//------------------------------------
			
			driver.findElementByXPath("//a[text()='bb Popular Toor/Arhar Dal 2 kg Pouch']/ancestor::div[@class='container-fluid item-wrap']//button[@qa='decQtyMB']").click();
			
			
			//18) Validate the Sub Total for the current items
			//---------------------------------------------------
			
			//input[@class='text-change-qty-search-popup ng-pristine ng-untouched ng-valid ng-not-empty ng-valid-maxlength']
			
			Thread.sleep(2000);
			dblFrstRowPrice = calculatePrice(driver, 1);
			
			dblSecondRowPrice = calculatePrice(driver, 2);
			
			dblSubtotal = dblFrstRowPrice+dblSecondRowPrice;
			
			strSubTotalNow = driver.findElementByXPath("//span[@ng-bind='vm.cart.cart_total']").getText();
			//strSubTotalNow = strSubTotalNow.replaceAll("\\D", "");
			
			dblSubTotalNow = Double.parseDouble(strSubTotalNow);
			
			if(dblSubtotal==dblSubTotalNow)
			{
				System.out.println("Correctly validated.  Subtotal = "+dblSubTotalNow);
			}
			
			driver.close();
			
		}
		
		
		
		public static double calculatePrice(ChromeDriver driver, int rowNum) throws InterruptedException
		{
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//li[@qa='itemsListMB']//div[@class='product-qty ng-binding'])["+rowNum+"]")));
			String strQtyIntoPrice1 = driver.findElementByXPath("(//li[@qa='itemsListMB']//div[@class='product-qty ng-binding'])["+rowNum+"]").getText();
			
			String[] splitArr1 = strQtyIntoPrice1.split("x");
					
			String strQty1 = splitArr1[0];
			strQty1 = strQty1.replaceAll("\\D", "");
			double dblQty1 = Double.parseDouble(strQty1);
			
			String strPrice1 = splitArr1[1];
			//strPrice1 = strPrice1.replaceAll("\\D", "");
			double dblPrice1 = Double.parseDouble(strPrice1);
			
			Double total1 = dblQty1*dblPrice1;
			
			
			return total1;
		}

	}