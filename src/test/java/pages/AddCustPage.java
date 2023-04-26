package pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustPage {

    private WebDriver driver;
    private Alert alert;

    public JavascriptExecutor getJavascriptExecutor() {
        return (JavascriptExecutor) driver ;
    }

    public AddCustPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@ng-model =  'fName']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@ng-model =  'lName']")
    private WebElement lastName;

    @FindBy(xpath = "//input[@ng-model =  'postCd']")
    private WebElement postCode;

    @FindBy(xpath = "//button[@type =  'submit']")
    private WebElement addCustomerButton;

    public WebElement getFirstName() {
        checkOpen(firstName);
        return firstName;
    }
    public WebElement getLastName() {
        checkOpen(lastName);
        return lastName;
    }
    public WebElement getPostCode() {
        checkOpen(postCode);
        return postCode;
    }
    public WebElement getAddCustomerButton(){
        checkOpen(addCustomerButton);
        return addCustomerButton;
    }

   public AddCustPage getCloseAlert() {
        alert = driver.switchTo().alert();
        alert.accept();
        return new AddCustPage(driver);
    }

   public String getTextAlert() {
        alert = driver.switchTo().alert();
        String alertText = alert.getText();
        return alertText;
    }

    @Step("Ввод в поля данных и нажатие кнопки Add Customer")
    public AddCustPage login(String name, String lastname, String postcode){
        getFirstName().sendKeys(name);
        getLastName().sendKeys(lastname);
        getPostCode().sendKeys(postcode);
        getAddCustomerButton().click();
        return this;
    }

    @Step("Нажатие кнопки Add Customer с пустыми полями")
    public void emptyField(){
        getAddCustomerButton().click();
    }

    @Step("Нажатие кнопки Add Customer с пустым полем First Name")
    public void emptyFirstName(String lastname, String postcode){
        getLastName().sendKeys(lastname);
        getPostCode().sendKeys(postcode);
        getAddCustomerButton().click();
    }

    @Step("Нажатие кнопки Add Customer с пустым полем Last Name")
    public void emptyLastName(String firstname, String postcode){
        getFirstName().sendKeys(firstname);
        getPostCode().sendKeys(postcode);
        getAddCustomerButton().click();
    }

    @Step("Нажатие кнопки Add Customer с пустым полем Post Code")
    public void emptyPostCode(String firstname, String lastname){
        getFirstName().sendKeys(firstname);
        getLastName().sendKeys(lastname);
        getAddCustomerButton().click();
    }

    @Step("Проверка присутствия элемента на странице")
    public void checkOpen(WebElement element){
        Assertions.assertTrue(element.isDisplayed());
    }

    @Step("Получаем всплывающее сообщение")
    public Object missingMessage (WebElement element) {
        return getJavascriptExecutor().executeScript("return arguments[0].validationMessage",element);
    }
}

