package rahulshettyacademy.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {
	
	@Test (groups= {"ErrorHandling"}) // retryAnalyzer=Retry.class
	public void LoginErrorValidation() throws IOException, InterruptedException {
		String productName = "ADIDAS ORIGINAL";
		landingPage.loginApplication("swethas@gmail", "S@7358");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	@Test 
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productName = "ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue  = landingPage.loginApplication("praveen@gamil.com", "Iampraveen@1");
		
		List <WebElement> products = productCatalogue.getProductlist();
		productCatalogue.addProductToCart(productName);		
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ADIDAS ORIGINAL");
		Assert.assertTrue(match);
	}
}
