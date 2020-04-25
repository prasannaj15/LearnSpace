package Exercise1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Program9 {

	public static void main(String[] args) throws InterruptedException {

//		setTimeout(function() {debugger;},5000)

		String tomoDate;

		String url = "https://demo.1crmcloud.com/";

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

//			1) Go to https://demo.1crmcloud.com/

		driver.get(url);

//			2) Give username as admin and password as admin

		driver.findElementByXPath("//input[@id='login_user']").click();
		driver.findElementByXPath("//input[@id='login_user']").clear();
		driver.findElementByXPath("//input[@id='login_user']").sendKeys("admin", Keys.TAB);

		driver.findElementByXPath("//input[@id='login_pass']").click();
		driver.findElementByXPath("//input[@id='login_pass']").clear();
		driver.findElementByXPath("//input[@id='login_pass']").sendKeys("admin", Keys.TAB);

//			3) Choose theme as Claro Theme
		Thread.sleep(3000);
		WebElement eleTheme = driver.findElementByXPath("//select[@id='login_theme']");
		Select drpDwn = new Select(eleTheme);
		drpDwn.selectByValue("Claro");

		boolean chkSign = driver.findElementByXPath("(//input[@name='user_remember'])[2]").isSelected();

		if (chkSign == true) {
			driver.findElementByXPath("(//input[@name='user_remember'])[2]").click();
		}

		driver.findElementByXPath("//span[@class='uii uii-arrow-right']").click();

//			4) Click on Sales and Marketting 

		driver.findElementByXPath("//div[text()='Sales & Marketing']").click();

//			5) Click Create contact

		driver.findElementByXPath("//div[text()='Create Contact']").click();

//			6) Select Title and type First name, Last Name, Email and Phone Numbers

		Thread.sleep(5000);
		driver.findElementByXPath("//div[@id='DetailFormsalutation-input']").click();
		driver.findElementByXPath("//div[text()='Mr.']").click();

		driver.findElementByXPath("//input[@name='first_name']").sendKeys("Prasanna");
		driver.findElementByXPath("//input[@name='last_name']").sendKeys("Narayanan");
		driver.findElementByXPath("//input[@name='email1']").sendKeys("prasannaj15@gmail.com");
		driver.findElementByXPath("//input[@name='phone_work']").sendKeys("9876543210");

//			7) Select Lead Source as "Public Relations"

		Thread.sleep(2000);
		driver.findElementByXPath("//div[@id='DetailFormlead_source-input']").click();
		driver.findElementByXPath("//div[text()='Public Relations']").click();

//			8) Select Business Roles as "Sales"

		driver.findElementByXPath("//div[@id='DetailFormbusiness_role-input']").click();
		driver.findElementByXPath("//div[text()='Sales']").click();

//			9) Fill the Primary Address, City, State, Country and Postal Code and click Save

		driver.findElementByXPath("//textarea[@id='DetailFormprimary_address_street-input']").sendKeys("Test1");
		driver.findElementByXPath("//input[@id='DetailFormprimary_address_city-input']").sendKeys("Chennai");
		driver.findElementByXPath("//input[@id='DetailFormprimary_address_state-input']").sendKeys("Tamil Nadu");
		driver.findElementByXPath("//input[@id='DetailFormprimary_address_country-input']").sendKeys("India");
		driver.findElementByXPath("//input[@id='DetailFormprimary_address_postalcode-input']").sendKeys("600063");

		driver.findElementByXPath("(//span[text()='Save'])[2]").click();

//			10) Mouse over on Today's Activities and click Meetings

		WebElement eleLink = driver.findElementByXPath("//li[@data-tab-id='LBL_TABGROUP_TODAY_ACTIVITIES']/a/div");
		Actions action = new Actions(driver);
		action.moveToElement(eleLink).build().perform();

		driver.findElementByXPath("//div[text()='Meetings']").click();

//			11) Click Create 

		Thread.sleep(5000);
		driver.findElementByXPath("(//span[text()='Create'])[1]").click();

//			12) Type Subject as "Project Status" , Status as "Planned" 
		Thread.sleep(2000);
		driver.findElementByXPath("//input[@id='DetailFormname-input']").sendKeys("Project Status");

//			13) Start Date & Time as tomorrow 3 pm and Duration as 1hr

		Thread.sleep(5000);
		driver.findElementByXPath("//div[contains(@class,'datetime-input')]").click();

		driver.findElementByXPath("//div[contains(@class,'input-search')]/div/following-sibling::input").click();
		Thread.sleep(2000);

		tomoDate = getDate();
		driver.findElementByXPath("//div[contains(@class,'input-search')]/div/following-sibling::input").clear();
		driver.findElementByXPath("//div[contains(@class,'input-search')]/div/following-sibling::input")
				.sendKeys(tomoDate, Keys.ENTER);

		Thread.sleep(3000);

		driver.findElementByXPath("//div[contains(@class,'input-search')]/div/following-sibling::input").clear();
		driver.findElementByXPath("//div[contains(@class,'input-search')]/div/following-sibling::input")
				.sendKeys("15:00", Keys.ENTER);

		driver.findElementByXPath("//input[@id='DetailFormduration-time']").click();
		driver.findElementByXPath("//input[@id='DetailFormduration-time']").clear();
		driver.findElementByXPath("//input[@id='DetailFormduration-time']").sendKeys("1hr", Keys.TAB);

//			14) Click Add paricipants, add your created Contact name and click Save

		driver.findElementByXPath("//span[text()=' Add Participants']").click();

		driver.findElementByXPath("(//input[@class='input-text'])[4]").clear();
		driver.findElementByXPath("(//input[@class='input-text'])[4]").sendKeys("Prasanna");
		Thread.sleep(2000);

		driver.findElementByXPath("//div[text()='Prasanna Narayanan']").click();

		driver.findElementByXPath("(//span[text()='Save'])[2]").click();

//			15) Go to Sales and Marketting-->Contacts

		Thread.sleep(5000);

		WebElement salesMarketing = driver.findElementByXPath("//div[text()='Sales & Marketing']");
		action.moveToElement(salesMarketing).build().perform();

		driver.findElementByXPath("(//div[text()='Contacts'])[1]").click();

//			16) search the lead Name and click the name from the result

		driver.findElementByXPath("//input[@id='filter_text']").sendKeys("Prasanna");

		driver.findElementByXPath("//div[contains(text(),'Prasanna Narayanan')]").click();

		Thread.sleep(8000);

//			17) Check weather the Meeting is assigned to the contact under Activities Section.

		String meetingValue = driver.findElementByXPath("//table[@class='listView']//a[@class='listViewNameLink']")
				.getText();

		if (meetingValue.contains("Project Status")) {
			System.out.println("Meeting Added");
		} else {
			System.out.println("Meeting Not Added");
		}

		System.out.println("Execution Completed");
	}

	public static String getDate() {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, 1);
		String myDate = dateFormat.format(c.getTime());

		return myDate;
	}

}
