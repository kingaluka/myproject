package Test;

import Help.BaseTest;
import Help.Helpmethods;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LoginTest extends BaseTest
{
    public Helpmethods functions = new Helpmethods(driver);

    @Test
    public void logintest ()
    {
        String expectedhomepagetitle = BaseTest.getvalue("homepagetitle");
        functions.validatetitle(expectedhomepagetitle, driver);

        WebElement loginbutton = driver.findElement(By.xpath("//span[@class='visible-lg-inline-block']"));
        functions.clickelement(loginbutton);

        String expectedloginpagetitle = BaseTest.getvalue("loginpagetitle");
        functions.validatetitle(expectedloginpagetitle,driver);

        WebElement usernamefield = driver.findElement(By.xpath("//input[@id='username2']"));
        String usenamevalue = BaseTest.getvalue("userforlogin");
        functions.completefield(usernamefield, usenamevalue);

        WebElement passwordfield = driver.findElement(By.xpath("//input[@id='password2']"));
        String passwordvalue = BaseTest.getvalue("passwordforlogin");
        functions.completefield(passwordfield,passwordvalue);

        WebElement keepmeloggedin = driver.findElement(By.xpath("//input[@id='rememberMe2']"));
        functions.clickelement(keepmeloggedin);

        WebElement Loginbutton = driver.findElement(By.xpath("//input[@id='submit']"));
        functions.clickelement(Loginbutton);

        List<WebElement> proba=driver.findElements(By.xpath("//div[@class='column large-8']//li"));
        for(int index=0;index<proba.size();index++)
        {
            new WebDriverWait(driver,4500).until(ExpectedConditions.visibilityOf(proba.get(index)));
            String text=proba.get(index).getText();
            System.out.println(text);

        }

//      validarea homepage
        functions.validatetitle(expectedhomepagetitle,driver);





    }
}
