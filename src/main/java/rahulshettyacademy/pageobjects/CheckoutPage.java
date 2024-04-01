package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "((//button[contains(@class,'ta-item')]))[2]" )
	WebElement SelectCountry ;
	
	@FindBy (css = ".action__submit")
	WebElement Submit;
	
	@FindBy (css = "[placeholder='Select Country']")
	WebElement Input;
	
	
	By countryList = By.cssSelector(".ta-results");
			
	public void countryInput(String wantedCountry) {
		
		Actions a = new Actions(driver);
		a.sendKeys(Input,wantedCountry).build().perform();
		//Input.sendKeys(wantedCountry);
		waitForElementToAppear(countryList);
		SelectCountry.click();
	}
	
	public ConfirmationPage submitOrder() {
		waitForElementToAppear(Submit);
		Submit.click();
		return new ConfirmationPage(driver);
	}
	
	
	
}