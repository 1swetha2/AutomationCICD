package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{

	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css="[class='hero-primary']")
	WebElement ThankYouText;

	
	public String getConfirmationMessage() throws InterruptedException {
		
		waitForElementToAppear(ThankYouText);
		String ordermessage = ThankYouText.getText();
		return ordermessage;
	}
}
