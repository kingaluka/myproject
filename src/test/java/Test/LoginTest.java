package Test;

import Help.BaseTest;
import Help.Helpmethods;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.accessibility.AccessibleStateSet;
import java.util.List;

public class LoginTest extends BaseTest
{
    public Helpmethods functions = new Helpmethods(driver);

    @Test
    public void logintest ()
    {
//      validare homepage
        String expectedhomepagetitle = BaseTest.getvalue("homepagetitle");
        functions.validatetitle(expectedhomepagetitle, driver);

//      click on login button
        WebElement loginbutton = driver.findElement(By.xpath("//span[@class='visible-lg-inline-block']"));
        functions.clickelement(loginbutton);

//      validare login page
        String expectedloginpagetitle = BaseTest.getvalue("loginpagetitle");
        functions.validatetitle(expectedloginpagetitle,driver);

//      fill the username and password field with valid values
        WebElement usernamefield = driver.findElement(By.xpath("//input[@id='username2']"));
        String usenamevalue = BaseTest.getvalue("userforlogin");
        functions.completefield(usernamefield, usenamevalue);

        WebElement passwordfield = driver.findElement(By.xpath("//input[@id='password2']"));
        String passwordvalue = BaseTest.getvalue("passwordforlogin");
        functions.completefield(passwordfield,passwordvalue);

//      check the keep me logged in box
        WebElement keepmeloggedin = driver.findElement(By.xpath("//input[@id='rememberMe2']"));
        functions.clickelement(keepmeloggedin);

//      click o loggin button
        WebElement Loginbutton = driver.findElement(By.xpath("//input[@id='submit']"));
        functions.clickelement(Loginbutton);

//      validate the successfull login message
        String expectedsuccesfullloginmessage = BaseTest.getvalue("successfulloginmessage");
        List<WebElement> loginmessagelist=driver.findElements(By.xpath("//div[@class='column large-8']//li"));
        for(int index=0;index<loginmessagelist.size();index++)
        {
            new WebDriverWait(driver,4500).until(ExpectedConditions.visibilityOf(loginmessagelist.get(index)));
            String actualsuccessfulllogginmessage=loginmessagelist.get(index).getText();
            System.out.println(actualsuccessfulllogginmessage);

            Assert.assertEquals(expectedsuccesfullloginmessage,actualsuccessfulllogginmessage);
        }

//      validate the homepage after login
        new WebDriverWait(driver,4500).until(ExpectedConditions.titleIs(BaseTest.getvalue("homepagetitle")));
        functions.validatetitle(expectedhomepagetitle, driver);







    }
}
