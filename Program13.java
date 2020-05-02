package Exercise1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Program13 {

	public static void main(String[] args) throws InterruptedException {

		String url = "https://studyabroad.shiksha.com/";

		// Initialization

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, 30);

//		1) Go to https://studyabroad.shiksha.com/

		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//			2) Mouse over on Colleges and click MS in Computer Science &Engg under MS Colleges

		Actions action = new Actions(driver);
		WebElement collLink = driver.findElementByXPath("(//label[text()='Colleges '])[1]");
		action.moveToElement(collLink).build().perform();
		driver.findElementByXPath("//a[text()='MS in Computer Science &Engg']").click();

//			3) Select GRE under Exam Accepted and Score 300 & Below 

		driver.findElementByXPath("//p[text()='GRE']").click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElementByXPath("(//select[@name='examsScore[]'])[1]")));
		WebElement greScore = driver.findElementByXPath("(//select[@name='examsScore[]'])[1]");
		Select drpDwn = new Select(greScore);
		drpDwn.selectByValue("GRE--300--4");

//			4) Max 10 Lakhs under 1st year Total fees, USA under countries
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//p[text()='Max 10 Lakhs']")));
		driver.findElementByXPath("//p[text()='Max 10 Lakhs']").click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[text()='USA']")));
		WebElement location = driver.findElementByXPath("//a[text()='USA']");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
//		jse.executeScript("arguments[0].scrollTop = arguments[1];",location, 250); 

		jse.executeScript("arguments[0].scrollIntoView(true);", location);
		Thread.sleep(2000);
		driver.findElementByXPath("//a[text()='USA']/parent::p/parent::label/span").click();

//			5) Select Sort By: Low to high 1st year total fees

		Thread.sleep(2000);
		wait.until(
				ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//select[@id='categorySorter']")));
		WebElement sortby = driver.findElementByXPath("//select[@id='categorySorter']");
		Select sort = new Select(sortby);
		sort.selectByValue("fees_ASC");

//			6) Click Add to compare of the College having least fees with Public University, Scholarship and Accomadation facilities

		Thread.sleep(4000);
		WebElement myCollege = driver.findElementByXPath(
				"(//p[text()='Public university']/span[@class='tick-mark']/parent::p/following-sibling::p[text()='Scholarship']/span[@class='tick-mark']/parent::p/following-sibling::p[text()='Accommodation']/span[@class='tick-mark'])[1]//parent::p/parent::div/parent::div/ancestor::div[@class='clearwidth']/following-sibling::div[2]/label/span");
		jse.executeScript("arguments[0].scrollIntoView(true);", myCollege);
		jse.executeScript("scroll(0, 1000);");
		myCollege.click();

//			7) Select the first college under Compare with similar colleges 

		WebElement addTitle = driver.findElementByXPath("(//a[@class='add-tag-title'])[1]");
		jse.executeScript("arguments[0].scrollIntoView(true);", addTitle);
		driver.findElementByXPath("(//a[@class='add-tag-title'])[1]").click();

//			8) Click on Compare College>

		Thread.sleep(2000);
		driver.findElementByXPath("//strong[text()='Compare Colleges >']").click();

//			9) Select When to Study as 2021

		driver.findElementByXPath("//strong[text()='2021']").click();

//			10) Select Preferred Countries as USA

		Thread.sleep(3000);
		driver.findElementByXPath("//div[text()='Preferred Countries']/parent::div").click();
		driver.findElementByXPath("//label[contains(@for,'USA')]").click();
		driver.findElementByXPath("//a[text()='ok']").click();

//			11) Select Level of Study as Masters

		driver.findElementByXPath("//label[contains(@for,'Masters')]").click();

//			12) Select Preferred Course as MS

		WebElement selCourse = driver.findElementByXPath("//div[text()='Preferred Course']/parent::div");
		selCourse.click();
		Thread.sleep(1000);
		driver.findElementByXPath("//li[text()='MS']").click();

//			13) Select Specialization as "Computer Science & Engineering"

		WebElement specialisation = driver.findElementByXPath("//div[text()='Preferred Specialisations']/parent::div");
		specialisation.click();
		Thread.sleep(1000);
		driver.findElementByXPath("//li[text()='Computer Science & Engineering']").click();

//			14) Click on Sign Up

		WebElement signup = driver.findElementByXPath("//a[@id='signup']");
		jse.executeScript("arguments[0].scrollIntoView(true);", signup);
		signup.click();

//			15) Print all the warning messages displayed on the screen for missed mandatory fields

		List<WebElement> errMsg = driver.findElementsByXPath("//div[@class='helper-text']");
		errMsg.size();

		List<String> myValues = new ArrayList<String>();

		for (int i = 1; i <= errMsg.size(); i++) {

			String errValue = driver.findElementByXPath("(//div[@class='helper-text'])[" + i + "]").getText();
			if (errValue.isEmpty() != true) {
				myValues.add(errValue);
			}

		}

		for (String eacherror : myValues) {
			System.out.println(eacherror);
		}

	}

}
