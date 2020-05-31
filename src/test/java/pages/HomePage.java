package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage {
    private WebDriver driver;
    protected WebDriverWait wait;

    private String existingTagXpath = "//div[@class='tag-list']/a[contains(text(),'%s')]";

    //PageUrl
    private static String PAGE_URL = "https://demo.realworld.io";

    //Constructor
    public HomePage(WebDriver driver){
        this.driver=driver;
        driver.get(PAGE_URL);
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    @Step
    public void clickOnExistingTag(String existingTag) {
        WebElement xpath = driver.findElement(By.xpath(String.format(existingTagXpath, existingTag)));
        xpath.click();
    }

    // there are bunch of methods to check that list is filtered.
    // in current I'll check that all articles on the page are containing the selected tag
    // in a simple way. that the size of the list of articles is equal the size of the
    // list of articles with tag
    @Step
    public boolean checkTagsPresenceInAllArticlesOnPage(String existingTag) {
        List<WebElement> articles = driver.findElements(By.xpath("//div[@class='article-preview']"));
        List<WebElement> articlesWithTag = driver.findElements(By.xpath(String.format("//div[@class='article-preview']//li[contains(text(),'%s')]",existingTag)));
        return articles.size() == articlesWithTag.size();
    }
}