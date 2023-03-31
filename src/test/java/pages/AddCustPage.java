package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustPage {

    private WebDriver driver;

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
        return firstName;
    }
    public WebElement getLastName() {
        return lastName;
    }
    public WebElement getPostCode() {
        return postCode;
    }
    public WebElement getAddCustomerButton(){
        return addCustomerButton;
    }

    @Step("Ввод в поля данных и нажатие кнопки Add Customer")
    public void login(String name, String lastname, String postcode){
        firstName.sendKeys(name);
        lastName.sendKeys(lastname);
        postCode.sendKeys(postcode);
        addCustomerButton.click();
    }

    @Step("Нажатие кнопки Add Customer с пустыми полями")
    public void emptyField(){
        addCustomerButton.click();
    }

    @Step("Нажатие кнопки Add Customer с пустым полем First Name")
    public void emptyFirstName(String lastname, String postcode){
        lastName.sendKeys(lastname);
        postCode.sendKeys(postcode);
        addCustomerButton.click();
    }

    @Step("Нажатие кнопки Add Customer с пустым полем Last Name")
    public void emptyLastName(String name, String postcode){
        firstName.sendKeys(name);
        postCode.sendKeys(postcode);
        addCustomerButton.click();
    }

    @Step("Нажатие кнопки Add Customer с пустым полем Post Code")
    public void emptyPostCode(String name, String lastname){
        firstName.sendKeys(name);
        lastName.sendKeys(lastname);
        addCustomerButton.click();
    }
}
