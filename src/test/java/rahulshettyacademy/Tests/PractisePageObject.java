package rahulshettyacademy.Tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class PractisePageObject extends BaseTest {
	String productName = "ADIDAS ORIGINAL";
	
	@Test (dataProvider = "getData", groups = {"purchase"})
	public void PractisePageObject(HashMap<String,String> input) throws IOException, InterruptedException {
		
		ProductCatalogue productCatalogue  = landingPage.loginApplication(input.get("email"), input.get("password"));
		
		List <WebElement> products = productCatalogue.getProductlist();
		productCatalogue.addProductToCart(input.get("product"));		
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.CheckoutButton();
		checkoutPage.countryInput("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMsg = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));		
		}
	
	@Test(dependsOnMethods = {"PractisePageObject"})
	public void OrderHistoryTest() throws InterruptedException {
		
		ProductCatalogue productCatalogue  = landingPage.loginApplication("swethas@gmail.com", "Swetha@7358");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
		}
	
	@DataProvider
	public Object[][] getData() throws IOException {
	
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacadmey//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	

	/*	
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email","praveen@gamil.com" );
		map.put("password", "Iampraveen@1");
		map.put("product","ZARA COAT 3");

		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email","swethas@gmail.com" );
		map1.put("password","Swetha@7358");
		map1.put("product","ADIDAS ORIGINAL");
		
		return new Object[][] {{map},{map1}};
	*/	
		/*
		@DataProvider
		public Object[][] getData(){
			return new Object[][] {{"praveen@gamil.com","Iampraveen@1","ZARA COAT 3"},{"swethas@gmail.com","Swetha@7358","ADIDAS ORIGINAL"}
		}
		*/
	}
		
	

}
