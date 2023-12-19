package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy(id = "input-firstname")
	private WebElement firstNameFields;

	@FindBy(id = "input-lastname")
	private WebElement lastNameFields;
	
	@FindBy(id = "input-email")
	private WebElement emailFields;
	
	@FindBy(id = "input-telephone")
	private WebElement telephoneFields;
	
	@FindBy(id = "input-password")
	private WebElement passwordFields;
	
	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordFields;
	
	@FindBy(name ="agree")
	private WebElement privacyPolicy;
	
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement submitButton;
	
	@FindBy(xpath = "(//input[@name='newsletter'])[1]")
	private WebElement newsLetterRadioButton;
	
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement warningMessageDuplicateEmailId;
	
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement privacyPolicyWarningMessage;
	
	@FindBy(xpath = "//div[text()='First Name must be between 1 and 32 characters!']")
	private WebElement firstNameWarning;
	
	@FindBy(xpath = "//div[text()='Last Name must be between 1 and 32 characters!']")
	private WebElement lastNameWarning;
	
	@FindBy(xpath = "//div[text()='E-Mail Address does not appear to be valid!']")
	private WebElement emailWarning;
	
	@FindBy(xpath = "//div[text()='Telephone must be between 3 and 32 characters!']")
	private WebElement telephoneWarning;
	
	@FindBy(xpath = "//div[text()='Password must be between 4 and 20 characters!']")
	private WebElement passwordWarning;
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public void enterFirstName(String firstName) {
		firstNameFields.sendKeys(firstName);
	}
	
	public void enterLastName(String lastName) {
		lastNameFields.sendKeys(lastName);
	}
	
	public void enterEmail(String email) {
		emailFields.sendKeys(email);
	}
	
	public void enterTelephone(String telephone) {
		telephoneFields.sendKeys(telephone);
	}
	
	public void enterPassword(String password) {
		passwordFields.sendKeys(password);
	}
	
	public void enterConfirmPassword(String confirmPassword) {
		confirmPasswordFields.sendKeys(confirmPassword);
	}
	
	public void clickOnPrivacyPolicy() {
		privacyPolicy.click();
	}
	
	public AccountSuccessPage clickOnSubmitButton() {
		submitButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public void clickOnNewsLetterRadioButton() {
		newsLetterRadioButton.click();
	}
	
	public String retriveWarningMessageDuplicateEmailIdMessage() {
		String warningMessage = warningMessageDuplicateEmailId.getText();
		return warningMessage;
	}
	
	public String retrivePrivacyPolicyWarningMessage() {
		String privacyMessage = privacyPolicyWarningMessage.getText();
		return privacyMessage;
	}
	
	public String retriveFirstNameWarningMessage() {
		String firstName = firstNameWarning.getText();
		return firstName;
	}
	
	public String retriveLastNameWarningMessage() {
		String lastName = lastNameWarning.getText();
		return lastName;
	}
	
	public String retriveEmailWarningMessage() {
		String email = emailWarning.getText();
		return email;
	}
	
	public String retriveTelephoneWarningMessage() {
		String telephone = telephoneWarning.getText();
		return telephone;
	}
	
	public String retrivePasswordWarningMessage() {
		String password = passwordWarning.getText();
		return password;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
