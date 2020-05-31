package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class TagFilterTest {
    WebDriver driver;

    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void close() {
        driver.close();
    }

    @Test
    public void successfulTagFilter() throws InterruptedException {
        String existingTag = "flowers";

        HomePage home = new HomePage(driver);

        //TODO redo sleep for more appropriate method for page load waiting
        Thread.sleep(3000);

        home.clickOnExistingTag(existingTag);

        //TODO think about adding soft assert of the active tag presence on the page

        //Wait until page is refreshed, filtered and ready
        Thread.sleep(3000);
        //assert that tag is present in all elements in the filtered list
        assertTrue(home.checkTagsPresenceInAllArticlesOnPage(existingTag));

        //TODO add allure reports to the FW
    }
}
