package miniproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MiniProject {
	String url ="https://petstore.octoperf.com/actions/Catalog.action";
	String Dogs_img_xpath ="//area[contains(@alt,'Dogs')]";
	String product_id_xpath ="//table/tbody/tr[2]/td[1]";
	String add_to_cart_xpath ="//table/tbody/tr[3]/td[5]";
	String qty_css = "input[name='EST-7']";
	String update_css = "input[value='Update Cart']";
	String proceed_xpath = "//a[contains(text(),'Proceed')]";

   WebDriver driver;
   @BeforeTest
   public void setUp() {
       driver = new ChromeDriver();
       driver.get(url);

   }
   @Test(priority = 1)
   public void testCategory() {
       driver.findElement(By.xpath(Dogs_img_xpath)).click();

       String actURL = driver.getCurrentUrl();

       Assert.assertTrue(actURL.contains("categoryId=DOGS"));
   }

   @Test(priority = 2, dependsOnMethods = "testCategory")
   public void testProductID() {
       driver.findElement(By.xpath(product_id_xpath)).click();

       String actURL = driver.getCurrentUrl();

       Assert.assertTrue(actURL.contains("productId=K9-BD-01"));
   }

   @Test(priority = 3, dependsOnMethods = "testProductID")
   public void testAddtoCart() {
       driver.findElement(By.xpath(add_to_cart_xpath)).click();

       String actURL = driver.getCurrentUrl();

       Assert.assertTrue(actURL.contains("workingItemId=EST-7"));
   }

   @Test(priority = 4, dependsOnMethods = "testAddtoCart")
   public void testCheckout() {

       driver.findElement(By.cssSelector(qty_css)).sendKeys("3");

       WebElement up = driver.findElement(By.cssSelector(update_css));
       up.click();

       // System.out.println(up.getAttribute("value"));

       driver.findElement(By.xpath(proceed_xpath)).click();

       String actURL = driver.getCurrentUrl();

       Assert.assertTrue(actURL.contains("newOrderForm"));
   }

   @AfterTest
   public void testDown() {
       driver.close();
   }
}



