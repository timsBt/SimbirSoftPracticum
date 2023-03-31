package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String pageUrl = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager";

    @FindBy(css = (".btn.btn-lg.tab"))
    private WebElement addCustomer;

    @FindBy(xpath = "//button[@ng-click =  'showCust()']")
    private WebElement customers;

    public WebElement getAddCustomer(){
        return addCustomer;}

    public WebElement getCustomers(){
        return customers;}


    @Step("Нажатие кнопки Add Customer")
    public void clickAddCustomer(){
        addCustomer.click();}

    @Step("Нажатие кнопки Customers")
    public void clickCustomers(){
        customers.click();}
}
