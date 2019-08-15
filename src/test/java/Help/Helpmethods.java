package Help;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.KeyInput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Helpmethods {

    WebDriver driver;


    public Helpmethods (WebDriver driver){
        this.driver = driver;

    }

//    click method
    public void clickelement (WebElement element)
    {

        element.click();
    }

//    select by value from a dopdownlist
    public void selectbyvaluedropdown (WebElement element, String value)
    {
        Select elementselectbyvalue = new Select(element);
        elementselectbyvalue.selectByValue(value);
    }

//    select by text from a dropdownlist

    public void selectbytextdropdown (WebElement element, String value)
    {
        Select elementselectbytext = new Select(element);
        elementselectbytext.selectByVisibleText(value);
    }

//    validarea paginii

    public void validatetitle (String expectedresult, WebDriver driver)
    {
        if (expectedresult.length()>0) {
            String actualresult = driver.getTitle();
            Assert.assertEquals(expectedresult,actualresult);
        }
    }

//    complete a field

    public void sendkeys (WebElement element, String value)
    {
        element.sendKeys(value);
    }

//    hover method

    public void hovermethod (WebElement element, WebDriver driver)
    {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }
//   validaretext
    public void validatetext(WebElement element, String value)
    {
        String actualmessage=element.getText();
        Assert.assertTrue(value.equals(actualmessage));
     }
//    validare mesaj cu is.Display
    public void validatemessagewithisdisplay (WebElement element)
    {
        if (element.isDisplayed())
        {
            Assert.assertTrue(element.isDisplayed());
        }
    }

//    wait explicit

    public void waitexplicit (WebElement element)
    {
        new WebDriverWait(driver,4500).until(ExpectedConditions.visibilityOf(element));
    }

//    wait explicit cu try
    public void waitwithtry ()
    {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


//     press TAB key
    public void pressTABkey (WebElement element)
    {
        element.sendKeys(Keys.TAB);

    }

//   metoda pentru validarea unei liste de webelement

    public void validateListWebelement (List<WebElement> elements, int value)
    {
        Assert.assertTrue(elements.size()==value);
    }
}
