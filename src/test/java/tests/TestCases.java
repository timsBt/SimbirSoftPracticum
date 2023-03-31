package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.*;
import pages.AddCustPage;
import pages.Customers;
import pages.MainPage;
import java.time.Duration;

public class TestCases {

    WebDriver driver = utils.WebDriver.getChromeDriver();
    MainPage mainPage = new MainPage(driver);
    AddCustPage addCustPage = new AddCustPage(driver);
    Customers customers = new Customers(driver);
    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;

    String firstName = "Severus";
    String lastName = "Snape";
    String postCode = "AE9999";

    String actualMessage = "Заполните это поле.";
    String message = "Всплывающее сообщение Не соответсвует ожидаемому";
    String executorString = "return arguments[0].validationMessage";


    @BeforeEach
    public void SetUp() {
        driver.get(mainPage.pageUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    @Description("Открытие главной страницы")
    @Execution(ExecutionMode.CONCURRENT)
    public void openMainPageTest(){
        Assertions.assertTrue(mainPage.getAddCustomer().isDisplayed());
        Assertions.assertTrue(mainPage.getCustomers().isDisplayed());
    }

    @Test
    @Description("Открытие страницы Add Customer")
    @Execution(ExecutionMode.CONCURRENT)
    public void clickCustomerTest(){
        mainPage.clickAddCustomer();
        Assertions.assertTrue(addCustPage.getFirstName().isDisplayed());
        Assertions.assertTrue(addCustPage.getLastName().isDisplayed());
        Assertions.assertTrue(addCustPage.getPostCode().isDisplayed());
        Assertions.assertTrue(addCustPage.getAddCustomerButton().isDisplayed());
    }


    @Test
    @Description("Создание клиента с заполненными полями")
    @Execution(ExecutionMode.CONCURRENT)
    public void createCustomerTest(){
        mainPage.clickAddCustomer();
        addCustPage.login(firstName,lastName,postCode);
        Alert alert = driver.switchTo().alert();
        String aler = alert.getText();
        Assertions.assertEquals(aler,"Customer added successfully with customer id :6" , "Cообщение не соответствует ожидаемому");
        alert.accept();
    }


    @Test
    @Description("Проверка Всплывающей ошибки при Создании клиента с пустыми полями")
    @Execution(ExecutionMode.CONCURRENT)
    public void createCustomerEmptyFieldsTest(){
        mainPage.clickAddCustomer();
        addCustPage.emptyField();
        Assertions.assertEquals(javascriptExecutor.executeScript(executorString, addCustPage.getFirstName()), actualMessage, message);
    }


    @Test
    @Description("Проверка Всплывающей ошибки при Создании клиента с пустым полем First Name")
    @Execution(ExecutionMode.CONCURRENT)
    public void createCustomerEmptyFirstNameTest(){
        mainPage.clickAddCustomer();
        addCustPage.emptyFirstName(lastName,postCode);
        Assertions.assertEquals(javascriptExecutor.executeScript(executorString, addCustPage.getFirstName()), actualMessage, message);
    }


    @Test
    @Description("Проверка Всплывающей ошибки при Создании клиента с пустым полем Last Name")
    @Execution(ExecutionMode.CONCURRENT)
    public void createCustomerEmptyLastNameTest(){
        mainPage.clickAddCustomer();
        addCustPage.emptyLastName(firstName,postCode);
        Assertions.assertEquals(javascriptExecutor.executeScript(executorString, addCustPage.getLastName()), actualMessage, message);
    }

    @Test
    @Description("Проверка Всплывающей ошибки при Создании клиента с пустым полем Post Code")
    @Execution(ExecutionMode.CONCURRENT)
    public void createCustomerEmptyPostCodeTest(){
        mainPage.clickAddCustomer();
        addCustPage.emptyPostCode(firstName,lastName);
        Assertions.assertEquals(javascriptExecutor.executeScript(executorString, addCustPage.getPostCode()), actualMessage, message);
    }

    @Test
    @Description("Открытие страницы Customers")
    @Execution(ExecutionMode.CONCURRENT)
    public void clickCustomersTest(){
        mainPage.clickCustomers();
        Assertions.assertTrue(customers.getFirstNameButton().isDisplayed());
        Assertions.assertTrue(customers.getSearchCustomer().isDisplayed());
    }


    @Test
    @Description("Сортировка в алфавитном порядке")
    @Execution(ExecutionMode.CONCURRENT)
    public void clickFirstNameTest(){
        mainPage.clickCustomers();
        customers.clickFirstNameButton();                              // Клик по кнопке First Name
        customers.clickFirstNameButton();                              // Клик по кнопке First Name
        String sortedText = customers.resultFirstName();               // Текст под кнопкой First Name
        String elementText = String.valueOf((sortedText.charAt(0)));   // Получение первого элемента из текста
        Assertions.assertEquals(elementText,"A","Сортировка НЕ в алфавитном порядке");
    }

    @Test
    @Description ("Поиск клиента по First Name")
    @Execution(ExecutionMode.CONCURRENT)
    public void clickSearchFirstNameTest(){
        String firstName = "Harry";
        mainPage.clickCustomers();
        customers.writeSearchCustomer(firstName);                // Ввод в поле "Harry"
        String firstNameResult = customers.resultFirstName();    // Текст под кнопкой First Name
        Assertions.assertEquals(firstNameResult,firstName,"First Name НЕ соответсвует ожидаемому");
    }

    @Test
    @Description ("Поиск клиента по Last Name")
    @Execution(ExecutionMode.CONCURRENT)
    public void clickSearchLastNameTest(){
        String lastName = "Potter";
        mainPage.clickCustomers();
        customers.writeSearchCustomer(lastName);                 // Ввод в поле "Potter"
        String lastNameResult = customers.resultLasttName();     // Текст под кнопкой Last Name
        Assertions.assertEquals(lastNameResult,lastName,"Last Name НЕ соответсвует ожидаемому");
    }


    @Test
    @Description ("Поиск клиента по Post Code")
    @Execution(ExecutionMode.CONCURRENT)
    public void clickSearchPostCodeTest(){
        String postCode = "E725JB";
        mainPage.clickCustomers();
        customers.writeSearchCustomer(postCode);                 // Ввод в поле "E725JB"
        String postCodeResult = customers.resultPostCode();      // Текст под кнопкой Post Code
        Assertions.assertEquals(postCodeResult, postCode, "Post Code НЕ соответсвует ожидаемому");
    }


    @AfterEach
    public void tearDown(){
        driver.quit();
    }

}