package DataProvider_HRM;

import java.time.Duration;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

public class Test 
{
	
	@org.testng.annotations.Test(dataProvider = "LoginData")
	public void test(String username,String password,String Condition)
	{
		WebDriver driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    driver.manage().window().maximize();

	    driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	    driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
	    driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    
	    boolean headline=false;
	    boolean url = false ;
	  
		  headline = driver.findElements(By.xpath("//h6[text()='Dashboard']")).size()>0;
		  url = driver.getCurrentUrl().contains("dashboard");
	   
	
	   if(Condition.equalsIgnoreCase("Valid"))
	   {
		   Assert.assertTrue(headline,"Dashboard Not found");
		   Assert.assertTrue(url,"Url does not contains Dashboard");
	       
	   }
	   else
	   {
		   Assert.assertFalse(url," User still login with invalid credential");
		   Assert.assertFalse(headline,"User still login with invalid Credential");
	   }
	   
	  
		if(headline)
		{
			 WebElement menuButton = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));
		     menuButton.click();
			 WebElement logout = driver.findElement(By.xpath("//a[text()='Logout']"));
			 logout.click();
		}
	    
	driver.quit();
	}
	
	@DataProvider(name = "LoginData")
	public String[][] datatable() {

	    return new String[][] {
	        {"Admin", "admin123", "Valid"},
	        {"lakshsdami@yahoo.com", "Lasadxmi", "Invalid"},
	        {"laksdasdadh@yahoo.com", "Laasdkshmi", "Invalid"},
	        {"Admin", "admin123", "Valid"}
	    };
	}

}
