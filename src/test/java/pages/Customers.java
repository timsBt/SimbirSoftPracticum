package pages;

import io.qameta.allure.Step;
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

    public WebElement getFirstNameButton(){
        return firstNameButton;
    }
    public WebElement getSearchCustomer(){
        return searchCustomer;
    }

    @Step("Нажатие на кнопку First Name")
    public void clickFirstNameButton(){
        firstNameButton.click();
    }

    @Step ("Ввод в поле Search Customer данных")
    public void writeSearchCustomer(String name){
        searchCustomer.sendKeys(name);
    }

    @Step ("Получаем текст под кнопкой First Name")
    public String resultFirstName(){
        return firstNameResult.getText();
    }

    @Step ("Получаем текст под кнопкой Last Name")
    public String resultLasttName(){
        return lastNameResult.getText();
    }

    @Step ("Получаем текст под кнопкой Post Code")
    public String resultPostCode(){
        return postCodeResult.getText();
    }

}
