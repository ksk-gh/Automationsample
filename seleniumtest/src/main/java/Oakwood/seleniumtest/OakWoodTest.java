package Oakwood.seleniumtest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



//import com.beust.jcommander.Parameter;

public class OakWoodTest {
	public static RemoteWebDriver driver;
	
	
	
public static void main (String [] args) throws InterruptedException
{
	try{
	
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
	
	
Actions action = new Actions(driver);
Actions act = new Actions(driver);
driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
WebDriverWait wait =new WebDriverWait(driver,20);
driver.get("http://www.oakwood.com");
driver.manage().window().maximize();
Thread.sleep(5000);
WebElement location = driver.findElementById("location");
location.sendKeys("Los Angeles");//,Keys.ENTER);
Thread.sleep(1000);


action.sendKeys(Keys.ARROW_DOWN).click().build().perform();

//action.sendKeys(Keys.ARROW_DOWN).build().perform();
driver.findElementById("searchBtn").click();

Thread.sleep(5000);

WebElement untilFind = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oak-searchToolbar_content']/div[2]/div/div[1]/div")));
action.moveToElement(untilFind);
action.click(untilFind).build().perform();
Thread.sleep(1000);
//WebElement available = driver.findElementByXPath("//div[@data-value='standardAvailability']");
//act.moveToElement(available);
//WebElement distanceFinder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-value='distance']")));
WebElement distanceFinder =driver.findElementByXPath("(//div[@data-value='distance'])[1]");
action.click(distanceFinder)
.build()
.perform();
Thread.sleep(2000);
List<WebElement> findElementsByXPath = driver.findElementsByXPath("//span[text()='Distance']/following-sibling::span" );
// List<String> arrayList = new ArrayList <String> ();
List<Float> arrayList = new ArrayList <Float> ();
for(WebElement add: findElementsByXPath)
{
	String[] splitString = add.getText().split("Kilometers");
	
	//System.out.println("split :" +splitString);
	//System.out.println("ss "+splitString[1]);
	float var = Float.valueOf(splitString[0]);
	arrayList.add(var);
}
//List<Float> floatType =new ArrayList<Float>();
List<Float> sorted= new ArrayList<Float>();
sorted.addAll(arrayList);
Collections.sort(sorted);
System.out.println("Actual : "+arrayList);
System.out.println("Sorted : "+sorted);
//Assert.assertTrue(arrayList.equals(sorted));
Assert.assertEquals(arrayList,sorted);
	}
	
finally{

driver.quit();
System.out.println("Finished");

}
	
}
}