package Test;

import Help.BaseTest;
import Help.Helpmethods;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class RegisterTest extends BaseTest {

    public Helpmethods functions = new Helpmethods(driver);
    public String usernamevalue;
    public String emailvalue;
    public String passwordvalue;
    public String confirmpasswordvalue;
    public String usernameerrormessage;

    @Test
    public void register () {

// validarea homepage
        String expectedhomepagetitle = BaseTest.getvalue("homepagetitle");
        functions.validatetitle(expectedhomepagetitle,driver);

// click on join button
        WebElement joinbutton = driver.findElement(By.xpath("//a[contains(text(),'Join')]"));
        functions.clickelement(joinbutton);

// validarea registerpage
        String expectedregisterpagetitle = BaseTest.getvalue("registerpagetitle");
        functions.validatetitle(expectedregisterpagetitle,driver);


        usernamevalue = "" + BaseTest.getvalue("usernamevalues");
        emailvalue = "" + BaseTest.getvalue("emailvalues");
        passwordvalue = "" + BaseTest.getvalue("passwordvalues");
        usernameerrormessage = "" + BaseTest.getvalue("usernamerrormessages");
        String[] parseUsername=usernamevalue.split(",");
        String[] parseEmail=emailvalue.split(",");
        String[] parsePassword=passwordvalue.split(",");
        String[] paresUsernameerrormsg = usernameerrormessage.split("/");


        for (int index = 0; index<8; index++) {
            WebElement usernamefield = driver.findElement(By.xpath("//input[@id='username_new']"));
            WebElement emailfield = driver.findElement(By.xpath("//input[@id='email']"));
            WebElement passwordfield = driver.findElement(By.xpath("//input[@id='password_new']"));
            WebElement confirmpasswordfield = driver.findElement(By.xpath("//input[@id='password_confirm']"));



//      1. valid value for all fields
            if (index==0) {
                usernamevalue = parseUsername[0];
                functions.completefield(usernamefield,usernamevalue);
                emailvalue = parseEmail[0];
                functions.completefield(emailfield,emailvalue);
                passwordvalue = parsePassword[0];
                functions.completefield(passwordfield, passwordvalue);
                functions.completefield(confirmpasswordfield,passwordvalue);
                confirmpasswordfield.sendKeys(Keys.TAB);

                functions.waitwithtry();

                List<WebElement> validfieldlist = driver.findElements(By.xpath("//input[@class='full valid']"));
                //Assert.assertTrue(validfieldlist.size()==4);
                functions.validateListWebelement(validfieldlist,4);

                functions.validatetitle(expectedregisterpagetitle,driver);

                driver.navigate().refresh();
            }

//      2. old value for username field

            if (index==1) {
                usernamevalue = parseUsername[1];
                functions.completefield(usernamefield, usernamevalue);
                emailvalue = parseEmail[0];
                functions.completefield(emailfield, emailvalue);
                passwordvalue = parsePassword[0];
                functions.completefield(passwordfield, passwordvalue);
                functions.completefield(confirmpasswordfield, passwordvalue);
                confirmpasswordfield.sendKeys(Keys.TAB);

                functions.waitwithtry();

                WebElement usernameerrormsg = driver.findElement(By.xpath("//span/p"));
                String expectedusernamerrormsg = paresUsernameerrormsg[0];
                functions.validatetext(usernameerrormsg,expectedusernamerrormsg);

                List<WebElement> validfieldlist = driver.findElements(By.xpath("//input[@class='full valid']"));
                functions.validateListWebelement(validfieldlist,3);

                functions.validatetitle(expectedregisterpagetitle,driver);

                driver.navigate().refresh();
            }

//       3. invalid value for username field
            if (index==2) {
                usernamevalue = parseUsername[2];
                functions.completefield(usernamefield, usernamevalue);
                emailvalue = parseEmail[0];
                functions.completefield(emailfield, emailvalue);
                passwordvalue = parsePassword[0];
                functions.completefield(passwordfield, passwordvalue);
                functions.completefield(confirmpasswordfield, passwordvalue);
                confirmpasswordfield.sendKeys(Keys.TAB);

                functions.waitwithtry();

                WebElement usernameerrormsg = driver.findElement(By.xpath("//span/p"));
                String expectedusernamerrormsg = paresUsernameerrormsg[1];
                functions.validatetext(usernameerrormsg,expectedusernamerrormsg);

                List<WebElement> validfieldlist = driver.findElements(By.xpath("//input[@class='full valid']"));
                functions.validateListWebelement(validfieldlist,3);

                functions.validatetitle(expectedregisterpagetitle,driver);

                driver.navigate().refresh();

            }

//       4. too many characters for username field
            if (index==3) {
                usernamevalue = parseUsername[3];
                functions.completefield(usernamefield, usernamevalue);
                emailvalue = parseEmail[0];
                functions.completefield(emailfield, emailvalue);
                passwordvalue = parsePassword[0];
                functions.completefield(passwordfield, passwordvalue);
                functions.completefield(confirmpasswordfield, passwordvalue);
                confirmpasswordfield.sendKeys(Keys.TAB);

                functions.waitwithtry();

                WebElement usernameerrormsg = driver.findElement(By.xpath("//span/p"));
                String expectedusernamerrormsg = paresUsernameerrormsg[2];
                functions.validatetext(usernameerrormsg,expectedusernamerrormsg);

                List<WebElement> validfieldlist = driver.findElements(By.xpath("//input[@class='full valid']"));
                functions.validateListWebelement(validfieldlist,3);

                functions.validatetitle(expectedregisterpagetitle,driver);

                driver.navigate().refresh();
            }

//       5. invalid value for email field

            if(index==4) {
                usernamevalue = parseUsername[0];
                functions.completefield(usernamefield,usernamevalue);
                emailvalue = parseEmail[2];
                functions.completefield(emailfield,emailvalue);
                passwordvalue = parsePassword[0];
                functions.completefield(passwordfield,passwordvalue);
                functions.completefield(confirmpasswordfield,passwordvalue);
                functions.pressTABkey(confirmpasswordfield);

                //functions.waitwithtry();

                WebElement emailerrormsg = driver.findElement(By.xpath("//span/p"));
                String expectedemailerrormsg = BaseTest.getvalue("emailerrormsg");
                try {
                    functions.validatetext(emailerrormsg, expectedemailerrormsg);
                }
                catch (NoSuchElementException e)
                {
                    System.out.println(e);
                }

                functions.waitwithtry();

                List<WebElement> validfieldlist = driver.findElements(By.xpath("//input[@class='full valid']"));
                functions.validateListWebelement(validfieldlist,3);

                functions.validatetitle(expectedregisterpagetitle,driver);

                driver.navigate().refresh();
            }

//      6. too less value for password field

            if(index==5) {
                usernamevalue = parseUsername[0];
                functions.completefield(usernamefield,usernamevalue);
                emailvalue = parseEmail[0];
                functions.completefield(emailfield,emailvalue);
                passwordvalue = parsePassword[2];
                functions.completefield(passwordfield,passwordvalue);
                functions.completefield(confirmpasswordfield,passwordvalue);
                functions.pressTABkey(confirmpasswordfield);

             //functions.waitwithtry();

                WebElement passworderrormsg = driver.findElement(By.xpath("//span[@class='field_message']/p"));
                String expectedpassworderrormsg = BaseTest.getvalue("passworderrormsg");

                try {
                    functions.validatetext(passworderrormsg, expectedpassworderrormsg);
                }
                catch (NoSuchElementException e)
                {
                    System.out.println(e);
                }

                functions.waitwithtry();

                List<WebElement> validfieldlist = driver.findElements(By.xpath("//input[@class='full valid']"));
                functions.validateListWebelement(validfieldlist,3);

                functions.validatetitle(expectedregisterpagetitle,driver);

                driver.navigate().refresh();
            }

//      7. invalid value for confirm password field
            if(index==6) {
                usernamevalue = parseUsername[0];
                functions.completefield(usernamefield,usernamevalue);
                emailvalue = parseEmail[0];
                functions.completefield(emailfield,emailvalue);
                passwordvalue = parsePassword[0];
                functions.completefield(passwordfield,passwordvalue);
                confirmpasswordvalue = BaseTest.getvalue("confirmpasswordvalues");
                functions.completefield(confirmpasswordfield,confirmpasswordvalue);
                functions.pressTABkey(confirmpasswordfield);

             //functions.waitwithtry();

//                WebElement confirmpassworderrormsg = driver.findElement(By.xpath("//span[@class='field_message']/p"));
//                String expectedconfirmmsg = BaseTest.getvalue("confirmpassworderrormsg");


                try {
                    WebElement confirmpassworderrormsg = driver.findElement(By.xpath("//span[@class='field_message']/p"));
                    String expectedconfirmmsg = BaseTest.getvalue("confirmpassworderrormsg");
                    functions.validatetext(confirmpassworderrormsg, expectedconfirmmsg);
                }
                catch (NoSuchElementException e)
                {
                    System.out.println(e);
                }

                List<WebElement> validfieldlist = driver.findElements(By.xpath("//input[@class='full valid']"));
                functions.validateListWebelement(validfieldlist,3);

                functions.validatetitle(expectedregisterpagetitle,driver);

                driver.navigate().refresh();
            }

//      8. empty value for all fields

            if (index==7){
                WebElement jointravellerspointbutton = driver.findElement(By.xpath("//input[@value='Join Travellerspoint']"));
                functions.clickelement(jointravellerspointbutton);

                List<WebElement> errormsglist = driver.findElements(By.xpath("//ul[@class='error']/li"));
                functions.validateListWebelement(errormsglist,4);

                List<WebElement> errorlist = driver.findElements(By.xpath("//label[@class=' error']"));
                functions.validateListWebelement(errorlist,3);
                functions.validatetitle(expectedregisterpagetitle,driver);
            }
        }

    }
//    @After
//    public void inchide () {
//        driver.quit();

//    }
}
