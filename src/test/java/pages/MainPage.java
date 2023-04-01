package pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
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

    public WebElement getAddCustomer() {
        checkOpen(addCustomer);
        return addCustomer;
    }
    public WebElement getCustomers() {
        checkOpen(customers);
        return customers;
    }

    @Step("Нажатие кнопки Add Customer")
    public Customers clickAddCustomer(){
        getAddCustomer().click();
        return new Customers(driver);
    }

    @Step("Нажатие кнопки Customers")
    public Customers clickCustomers(){
        getCustomers().click();
        return new Customers(driver);
    }

    @Step("Проверка присутствия элемента на странице")
    public void checkOpen(WebElement element){
        Assertions.assertTrue(element.isDisplayed());
    }
}
