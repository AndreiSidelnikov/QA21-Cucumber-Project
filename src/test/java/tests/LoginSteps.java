package tests;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;

public class LoginSteps {

    WebDriver driver;

    @Given("Navigate to Page PhoneBook")
    public void navigateToLoginPage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://contacts-app.tobbymarshall815.vercel.app");
    }

    @When("Click on Login tab")
    public void clickOnLoginTab() {
        click(By.xpath("//a[contains(.,'LOGIN')]"));
    }

    @Then("Appear LoginRegistration form")
    public void isLoginRegistrationFormPresent() {
        Assert.assertTrue(isElementPresent(By.cssSelector(".login_login__3EHKB")));
        // driver.quit();
    }

    @And("Enter a valid data")
    public void enterValidData() {
        type(By.cssSelector("[placeholder='Email']"), "kroozs@gm.com");
        type(By.cssSelector("[placeholder='Password']"), "Kroozzzzs12345~");
    }

    @And("Click on Login button")
    public void clickOnLoginButton() {
        click(By.xpath("//button[contains(.,'Login')]"));
    }

    @Then("SignOut tab displayed")
    public void isSignOutTabDisplayed() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(isElementPresent(By.xpath("//button[contains(.,'Sign Out')]")));
        driver.quit();
    }

    @And("Enter a valid email and an invalid password")
    public void enterInvalidPassword(DataTable table) {
        List<Map<String, String>> dataTable = table.asMaps();
        String email = dataTable.get(0).get("email");
        String password = dataTable.get(0).get("password");

        type(By.cssSelector("[placeholder='Email']"), email);
        type(By.cssSelector("[placeholder='Password']"), password);
    }

    @Then("Alert appeared")
    public void isAlertAppeared() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(isAlertPresent());
        driver.quit();
    }


    @And("click On Add Tab")
    public void clickOnAddTab() throws InterruptedException {
        Thread.sleep(5000);
        click(By.cssSelector("a:nth-child(5)"));
    }

    @And("enter a valid data of a contact")
    public void enterAValidDataOfANewContact() throws InterruptedException {
        click(By.cssSelector("a:nth-child(5)"));
        type(By.cssSelector("[placeholder='Name']"), "Karl");
        type(By.cssSelector("input:nth-child(2)"), "Adamskys");
        type(By.cssSelector("input:nth-child(3)"), "22341777");
        type(By.cssSelector("input:nth-child(4)"), "karladamskys@gresz.com");
        type(By.cssSelector("input:nth-child(5)"), "Berlin");
        type(By.cssSelector("input:nth-child(6)"), "teamplayer");
        Thread.sleep(3000);
    }

    @And("Click on Save button")
    public void clickOnSaveButton() {
        clickWithActions(By.cssSelector(".add_form__2rsm2 button"));
    }

    @Then("is Contact Created")
    public void isNewContactCreated() throws InterruptedException {
        Thread.sleep(3000);
        Assert.assertTrue(isContactCreated("22345777"));

    }

    public boolean isContactCreated(String text) {
        List<WebElement> contacts = driver.findElements(By.xpath("//h3"));
        for (WebElement el : contacts) {
            if (el.getText().contains(text))
                return true;
        }
        return false;
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public void type(By locator, String text) {
        if (text != null) {
            click(locator);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }
    }

    public boolean isAlertPresent() {
        Alert alert = new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
        if (alert == null) {
            return false;
        } else {
            driver.switchTo().alert();
            alert.accept();
            return true;
        }
    }

    public void clickWithActions(By save) {
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(save);
        action.moveToElement(element).build().perform();
        element.click();
    }
}






