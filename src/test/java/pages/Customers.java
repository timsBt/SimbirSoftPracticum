package pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Customers {

    private WebDriver driver;

    public Customers(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[@ng-click=\"sortType = 'fName'; sortReverse = !sortReverse\"]")
    private WebElement firstNameButton;

    @FindBy(xpath = "//input[@ng-model=\"searchCustomer\"]")
    private WebElement searchCustomer;

    @FindBy(xpath = "//td[@class=\"ng-binding\"][1]")
    private WebElement firstNameResult;

    @FindBy(xpath = "//td[@class=\"ng-binding\"][2]")
    private WebElement lastNameResult;

    @FindBy(xpath = "//td[@class=\"ng-binding\"][3]")
    private WebElement postCodeResult;

    @FindBy(xpath = "//button[@ng-click=\"deleteCust(cust)\"]")
    private WebElement deleteButton;

    public WebElement getFirstNameButton(){
        checkOpen(firstNameButton);
        return firstNameButton;
    }
    public WebElement getSearchCustomer(){
        checkOpen(searchCustomer);
        return searchCustomer;
    }
    public WebElement getClickDeleleButton(){
        checkOpen(deleteButton);
        return deleteButton;
    }

    @Step("Нажатие на кнопку First Name")
    public Customers clickFirstNameButton(){
        getFirstNameButton().click();
        return this;
    }

    @Step("Нажатие на кнопку Delete")
    public Customers clickDeleteButton(){
        getClickDeleleButton().click();
        return this;
    }

    @Step ("Ввод в поле Search Customer данных")
    public Customers writeSearchCustomer(String name){
        getSearchCustomer().clear();
        getSearchCustomer().sendKeys(name);
        return this;
    }

    @Step ("Получаем текст под кнопкой First Name")
    public String resultFirstName(){
        return firstNameResult.getText();
    }

    @Step ("Получаем текст под кнопкой Last Name")
    public String resultLastName(){
        return lastNameResult.getText();
    }

    @Step ("Получаем текст под кнопкой Post Code")
    public String resultPostCode(){
        return postCodeResult.getText();
    }

    @Step("Проверка присутствия элемента на странице")
    public void checkOpen(WebElement element){
        Assertions.assertTrue(element.isDisplayed());
    }

}
