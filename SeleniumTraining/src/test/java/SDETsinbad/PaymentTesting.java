package SDETsinbad;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PaymentTesting {
	
	public static String browser = "chrome";
	public static WebDriver driver;
	public static void main (String[] args) throws InterruptedException {
		
		if(browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		driver.get("https://demo.midtrans.com/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@id=\"container\"]/div/div/div[1]/div[2]/div/div/a")).click();
		WebDriverWait wait = new WebDriverWait(driver,2);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart-checkout"))).click();
		driver.switchTo().frame("snap-midtrans");
		WebDriverWait wait2 = new WebDriverWait(driver,2);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"application\"]/div[1]/a"))).click();
		WebDriverWait wait3 = new WebDriverWait(driver,2);
		wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"payment-list\"]/div[1]/a"))).click();
		driver.findElement(By.name("cardnumber")).sendKeys("4811 1111 1111 1114");
		driver.findElement(By.xpath("//*[@id=\"application\"]/div[3]/div/div/div/form/div[2]/div[2]/input")).sendKeys("03/24");
		driver.findElement(By.xpath("//*[@id=\"application\"]/div[3]/div/div/div/form/div[2]/div[3]/input")).sendKeys("123");
		driver.findElement(By.className("button-main-content")).click();
		
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"application\"]/div[3]/div/div/div/iframe")));
		WebDriverWait wait4 = new WebDriverWait(driver,2);
		wait4.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#PaRes"))).sendKeys("112233");
		driver.findElement(By.name("ok")).click();
		
		Thread.sleep(5000);
		driver.close();
	}

}
