package Help;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

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

    public void completefield (WebElement element, String value)
    {
        element.sendKeys(value);
    }

//    hover method

    public void hovermethod (WebElement element, WebDriver driver)
    {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

//    validarea unui mesaj

    public void validatemessagewithgettext (WebElement element, String value)
    { String actualmessage = element.getText();
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
}
