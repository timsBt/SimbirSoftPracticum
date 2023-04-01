package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;
import pages.AddCustPage;
import pages.Customers;
import pages.MainPage;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.Properties;

public class TestCases {

    WebDriver driver = utils.WebDriver.getChromeDriver();
    MainPage mainPage = new MainPage(driver);
    AddCustPage addCustPage = new AddCustPage(driver);
    Customers customers = new Customers(driver);

    public static String valueProperties (String param) throws Exception {
        Properties props = new Properties();
        props.load(new InputStreamReader(new FileInputStream("src/main/resources/aplication.properties"), "UTF-8"));
        return props.getProperty(param);
    }

    @BeforeEach
    public void SetUp() {
        driver.get(mainPage.pageUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }


    @Test
    @Description("Создание клиента с заполненными полями")
    @Execution(ExecutionMode.CONCURRENT)
    public void createCustomerTest() throws Exception {
        mainPage.clickAddCustomer();                              // Клик по кнопке AddCustomers
        addCustPage.login(valueProperties("firstName"),
                valueProperties("lastName"),
                valueProperties("postCode"));               // Создание пользователя,данные из файла Properties
        Assertions.assertEquals(addCustPage.getTextAlert(),
                valueProperties("actualAllert") ,
                valueProperties("message"));
        addCustPage.getCloseAlert();                              // Закрытие алерта
        mainPage.clickCustomers();                                // Клик по кнопке Customers
        customers.writeSearchCustomer(valueProperties("firstName"))
                 .clickDeleteButton();                            // Ввод в поле "Severus" и Удаление пользователя "Severus"
    }

    @Test
    @Description("Проверка Всплывающей ошибки при Создании клиента с пустыми полями")
    @Execution(ExecutionMode.CONCURRENT)
    public void createCustomerEmptyFieldsTest() throws Exception {
        mainPage.clickAddCustomer();
        addCustPage.emptyField();
        Assertions.assertEquals(addCustPage.missingMessage(addCustPage.getFirstName()),
                valueProperties("actualMessage"),valueProperties("message"));
    }


    @Test
    @Description("Проверка Всплывающей ошибки при Создании клиента с пустым полем First Name")
    @Execution(ExecutionMode.CONCURRENT)
    public void createCustomerEmptyFirstNameTest() throws Exception {
        mainPage.clickAddCustomer();
        addCustPage.emptyFirstName(valueProperties("firstName"), valueProperties("postCode"));
        Assertions.assertEquals(addCustPage.missingMessage(addCustPage.getFirstName()),
                valueProperties("actualMessage"),valueProperties("message"));
    }


    @Test
    @Description("Проверка Всплывающей ошибки при Создании клиента с пустым полем Last Name")
    @Execution(ExecutionMode.CONCURRENT)
    public void createCustomerEmptyLastNameTest() throws Exception {
        mainPage.clickAddCustomer();
        addCustPage.emptyLastName(valueProperties("firstName"), valueProperties("postCode"));
        Assertions.assertEquals(addCustPage.missingMessage(addCustPage.getLastName()),
                valueProperties("actualMessage"),valueProperties("message"));
    }

    @Test
    @Description("Проверка Всплывающей ошибки при Создании клиента с пустым полем Post Code")
    @Execution(ExecutionMode.CONCURRENT)
    public void createCustomerEmptyPostCodeTest() throws Exception {
        mainPage.clickAddCustomer();
        addCustPage.emptyPostCode(valueProperties("firstName"),valueProperties("lastName"));
        Assertions.assertEquals(addCustPage.missingMessage(addCustPage.getPostCode()),
                valueProperties("actualMessage"),valueProperties("message"));
    }


    @Test
    @Description("Сортировка в алфавитном порядке")
    @Execution(ExecutionMode.CONCURRENT)
    public void clickFirstNameTest() throws Exception {
        mainPage.clickAddCustomer();                                   // Клик по кнопке AddCustomers
        addCustPage.login(valueProperties("firstName"),valueProperties("lastName"), valueProperties("postCode"))                 // Создание пользователя 1
                   .getCloseAlert()                                    // Закрытие алерта
                   .login(valueProperties("firstName2"),valueProperties("lastName2"), valueProperties("postCode2"))              // Создание пользователя 2
                   .getCloseAlert();                                   // Закрытие алерта
        mainPage.clickCustomers();                                     // Клик по кнопке Customers
        customers.clickFirstNameButton()                               // Клик по кнопке First Name
                 .clickFirstNameButton();                              // Клик по кнопке First Name
        String sortedText = customers.resultFirstName();               // Текст под кнопкой First Name
        String elementText = String.valueOf((sortedText.charAt(0)));   // Получение первого элемента из текста
        Assertions.assertEquals(elementText,"A",valueProperties("messageNoSort"));
        customers.writeSearchCustomer(valueProperties("firstName"))                       // Ввод в поле "Severus"
                 .clickDeleteButton()                                                           // Удаление пользователя "Severus"
                 .writeSearchCustomer(valueProperties("firstName2"))                      // Ввод в поле "Altron"
                 .clickDeleteButton();                                                          // Удаление пользователя "Altron"

    }

    @Test
    @Description ("Поиск клиента по First Name")
    @Execution(ExecutionMode.CONCURRENT)
    public void clickSearchFirstNameTest() throws Exception {
        mainPage.clickAddCustomer();                                                        // Клик по кнопке AddCustomers
        addCustPage.login(valueProperties("firstName"),
                valueProperties("lastName"),
                valueProperties("postCode"))                                         // Создание пользователя
                   .getCloseAlert();                                                       // Закрытие алерта
        mainPage.clickCustomers();                                                         // Клик по кнопке Customers
        customers.writeSearchCustomer(valueProperties("firstName"));                 // Ввод в поле "Severus"
        String firstNameResult = customers.resultFirstName();                              // Текст под кнопкой First Name
        Assertions.assertEquals(firstNameResult,valueProperties("firstName"),valueProperties("message"));
        customers.clickDeleteButton();                                                     // Удаление пользователя после проверки

    }

    @Test
    @Description ("Поиск клиента по Last Name")
    @Execution(ExecutionMode.CONCURRENT)
    public void clickSearchLastNameTest() throws Exception {
        mainPage.clickAddCustomer();                                                        // Клик по кнопке AddCustomers
        addCustPage.login(valueProperties("firstName"),
                valueProperties("lastName"),
                valueProperties("postCode"))                                          // Создание пользователя
                   .getCloseAlert();                                                        // Закрытие алерта
        mainPage.clickCustomers();                                                         // Клик по кнопке Customers
        customers.writeSearchCustomer(valueProperties("lastName"));                 // Ввод в поле "Snape"
        String lastNameResult = customers.resultLastName();                               // Текст под кнопкой Last Name
        Assertions.assertEquals(lastNameResult,valueProperties("lastName"),valueProperties("message"));
        customers.clickDeleteButton();                                                    // Удаление пользователя после проверки

    }


    @Test
    @Description ("Поиск клиента по Post Code")
    @Execution(ExecutionMode.CONCURRENT)
    public void clickSearchPostCodeTest() throws Exception {
        mainPage.clickAddCustomer();                                                         // Клик по кнопке AddCustomers
        addCustPage.login(valueProperties("firstName"),
                valueProperties("lastName"),
                valueProperties("postCode"))                                          // Создание пользователя
                   .getCloseAlert();                                                        // Закрытие алерта
        mainPage.clickCustomers();                                                          // Клик по кнопке Customers
        customers.writeSearchCustomer( valueProperties("postCode"));                 // Ввод в поле "E725JB"
        String postCodeResult = customers.resultPostCode();                                // Текст под кнопкой Post Code
        Assertions.assertEquals(postCodeResult,  valueProperties("postCode"), valueProperties("message"));
        customers.clickDeleteButton();                                                     // Удаление пользователя после проверки
    }


    @AfterEach
    public void tearDown(){
        driver.quit();
    }

}