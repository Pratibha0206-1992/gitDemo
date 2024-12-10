package frame.pageObjects;

import java.time.Duration;
import java.util.List;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class StandAlone {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String productName="ZARA COAT 3";
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\pratibha.pandey\\OneDrive\\Documents\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("pratibhapandey0206@gmail.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Pratibha@21");
		driver.findElement(By.xpath("//input[@id='login']")).click();
		WebDriverWait wait  = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'mb-3 ng-star-inserted')]")));
		List<WebElement> products =driver.findElements(By.xpath("//div[contains(@class,'mb-3 ng-star-inserted')]"));
		WebElement prod= products.stream().filter(product->product.findElement(By.xpath("//b[contains(text(),'ZARA COAT 3')]")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.xpath("//div[contains(@class,'card-body')]//button[2]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toast-container']")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		Thread.sleep(1000L);
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		List<WebElement> cartProducts= driver.findElements(By.xpath("//div[@Class='cartSection']/h3"));
		Boolean match= cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.xpath("//li[@Class='totalRow']/button")).click();
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[contains(@class,'ta-results')]")));
		driver.findElement(By.xpath("//button[contains(@class,'ta-item ')][2]")).click();
		driver.findElement(By.xpath("//a[contains(@class,'action__submit')]")).click();
		String confirmMessage= driver.findElement(By.xpath("//h1[contains(@class,'hero-primary')]")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
		
		
		
	}

}
