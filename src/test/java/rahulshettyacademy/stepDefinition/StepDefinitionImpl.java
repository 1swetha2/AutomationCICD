package rahulshettyacademy.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {
	
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		
		productCatalogue  = landingPage.loginApplication(username,password);

	}
	// When I add product <productName> to cart
	 @When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		 
		 List <WebElement> products = productCatalogue.getProductlist();
			productCatalogue.addProductToCart(productName);		
	 }
	@When("^Checkout (.+) and submit the order$")
	 public void checkout_Practise_Page_Object(String productName) throws InterruptedException {
		 
		 	CartPage cartPage = productCatalogue.goToCartPage();
			
			Boolean match = cartPage.VerifyProductDisplay(productName);
			Assert.assertTrue(match);
			CheckoutPage checkoutPage = cartPage.CheckoutButton();
			checkoutPage.countryInput("india");
			confirmationPage = checkoutPage.submitOrder();
	 }
	 
	 @Then ("{string} message is displayed on ConfirmationPage")
	 public void message_displayed_confirmationPage(String string) throws InterruptedException {
		 String confirmMsg = confirmationPage.getConfirmationMessage();
			Assert.assertTrue(confirmMsg.equalsIgnoreCase(string));	
			driver.close();
	 }
	 
	 @Then("^\"([^\"]*)\" message is displayed$")
	 public void something_message_is_displayed(String strarg1) throws Throwable {
		 
		 Assert.assertEquals(strarg1, landingPage.getErrorMessage());
		 driver.close();
		 }
	 
	 
	 
	 
	 
	
}
