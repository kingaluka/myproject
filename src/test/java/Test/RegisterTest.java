package Test;

import Help.BaseTest;
import Help.Helpmethods;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class RegisterTest extends BaseTest {

    public Helpmethods functions = new Helpmethods(driver);

    @Test
    public void register () {

        String expectedhomepagetitle = BaseTest.getvalue("homepagetitle");
        functions.validatetitle(expectedhomepagetitle,driver);

        WebElement joinbutton = driver.findElement(By.xpath("//a[contains(text(),'Join')]"));
        functions.clickelement(joinbutton);

        String expectedregisterpagetitle = BaseTest.getvalue("registerpagetitle");
        functions.validatetitle(expectedregisterpagetitle,driver);

        WebElement usernamefield = driver.findElement(By.xpath("//input[@id='username_new']"));
        String usernamevalue = BaseTest.getvalue("usernamevalid");
        functions.completefield(usernamefield,usernamevalue);

        WebElement usernamechecked = driver.findElement(By.xpath("//input[@class='full valid']"));
        functions.validatemessagewithisdisplay(usernamechecked);
//        if (usernamechecked.isDisplayed())
//        {
//          Assert.assertTrue(usernamechecked.isDisplayed());
//        }

        WebElement emailfield = driver.findElement(By.xpath("//input[@id='email']"));
        String emailvalue = BaseTest.getvalue("emailvalid");
        functions.completefield(emailfield,emailvalue);

        WebElement passwordfield = driver.findElement(By.xpath("//input[@id='password_new']"));
        String passwordvalue = BaseTest.getvalue("passwordvalid");
        functions.completefield(passwordfield,passwordvalue);

        WebElement confirmpasswordfield = driver.findElement(By.xpath("//input[@id='password_confirm']"));
        functions.completefield(confirmpasswordfield,passwordvalue);
        confirmpasswordfield.sendKeys(Keys.TAB);

//        webElement.sendKeys(Keys.TAB);
//        webElement.sendKeys(Keys.ENTER);

//        WebElement Imnotarobot = driver.findElement(By.xpath("//span[@class='recaptcha-checkbox goog-inline-block recaptcha-checkbox-unchecked rc-anchor-checkbox']"));
//        functions.clickelement(Imnotarobot);

    }
}
