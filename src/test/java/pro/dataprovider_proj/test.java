package pro.dataprovider_proj;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class test {

	
	@Test(dataProvider = "LoginData")
	public void testWithMultiData(String user, String pass, String condition) {

	    WebDriver driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    driver.manage().window().maximize();

	    driver.get("https://tutorialsninja.com/demo/");
	    driver.findElement(By.xpath("//span[text()='My Account']")).click();
	    driver.findElement(By.xpath("//a[text()='Login']")).click();

	    driver.findElement(By.id("input-email")).sendKeys(user);
	    driver.findElement(By.id("input-password")).sendKeys(pass);
	    driver.findElement(By.xpath("//input[@value='Login']")).click();

	    boolean isLoginSuccessful;

	    try {
	    	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    	    WebElement myAccountHeading = wait.until(
	    	        ExpectedConditions.visibilityOfElementLocated(
	    	            By.xpath("//h2[text()='My Account']")
	    	        )
	    	    );

	    	    isLoginSuccessful = myAccountHeading.isDisplayed();
	    } catch (Exception e) {
	        isLoginSuccessful = false;
	    }

	    // ASSERTION (Expected vs Actual)
	    if (condition.equalsIgnoreCase("Valid")) {
	        Assert.assertTrue(isLoginSuccessful,
	                "Valid credentials ke bawajood login fail ho gaya");
	    } else {
	        Assert.assertFalse(isLoginSuccessful,
	                "Invalid credentials ke bawajood login ho gaya");
	    }

	    // CLEANUP (Actual result pe depend karta hai, NOT on condition)
	    if (isLoginSuccessful) {
	        driver.findElement(By.xpath("(//a[text()='Logout'])[2]")).click();
	    }

	    driver.quit();
	}

	
	@DataProvider(name = "LoginData")
	public String[][] datatable() {

	    return new String[][] {
	        {"srinivastrainer4@gmail.com", "Welcome@123", "Valid"},
	        {"lakshsdami@yahoo.com", "Lasadxmi", "Invalid"},
	        {"laksdasdadh@yahoo.com", "Laasdkshmi", "Invalid"},
	        {"abc123@gmail.com", "test@123", "Valid"}
	    };
	}

}
