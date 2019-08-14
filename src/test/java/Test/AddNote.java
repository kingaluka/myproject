package Test;

import Help.BaseTest;
import Help.Helpmethods;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AddNote extends BaseTest {

    public Helpmethods functions = new Helpmethods(driver);
    public String notevalue;

    @Test
    public void proba () {
        //  click on login button
        WebElement loginbutton = driver.findElement(By.xpath("//span[@class='visible-lg-inline-block']"));
        functions.clickelement(loginbutton);
//  complete all fields for login
        WebElement usernamefield = driver.findElement(By.xpath("//input[@id='username2']"));
        String usenamevalue = BaseTest.getvalue("userforlogin");
        functions.completefield(usernamefield, usenamevalue);
//  complete password field
        WebElement passwordfield = driver.findElement(By.xpath("//input[@id='password2']"));
        String passwordvalue = BaseTest.getvalue("passwordforlogin");
        functions.completefield(passwordfield, passwordvalue);
//  click on login button
        WebElement Loginbutton = driver.findElement(By.xpath("//input[@id='submit']"));
        functions.clickelement(Loginbutton);
//  click on your travel button
        functions.waitwithtry();
        List<WebElement> menubarlist = driver.findElements(By.xpath("//nav/ul/li"));
        functions.clickelement(menubarlist.get(2));



//   validarea paginii


        String expectedyourtrippagetitle = BaseTest.getvalue("yourtripspagetitle");
        functions.validatetitle(expectedyourtrippagetitle, driver);

        functions.waitwithtry();
        WebElement tripnameweb = driver.findElement(By.xpath("//ul/li[@data-v-19daf797]"));
        String tripname =BaseTest.getvalue("tripnamevalues");
        new WebDriverWait(driver,8500).until(ExpectedConditions.visibilityOf(tripnameweb));
//        functions.waitexplicit(tripnameweb);
        functions.validatetext(tripnameweb, tripname);
        functions.clickelement(tripnameweb);

        functions.validatetitle(expectedyourtrippagetitle,driver);

        functions.waitwithtry();
        WebElement triptitle = driver.findElement(By.xpath("//span[@class='trip-name']"));
        new WebDriverWait(driver, 5500).until(ExpectedConditions.visibilityOf(triptitle));
        functions.validatetext(triptitle,tripname);

        functions.waitwithtry();


        List<WebElement> locationlist2 = driver.findElements(By.xpath("//ul[@data-v-a115fc5c]/li"));
        String actuallocationname =locationlist2.get(0).getText();
        functions.clickelement(locationlist2.get(0));

        functions.waitwithtry();
        WebElement locationnameweb = driver.findElement(By.xpath("//span[@class='stop-name']"));
        new WebDriverWait(driver, 7500).until(ExpectedConditions.visibilityOf(locationnameweb));
        String locationname = "1 "+locationnameweb.getText();
        Assert.assertEquals(actuallocationname,locationname);




//   validarea paginii

        functions.validatetitle(expectedyourtrippagetitle,driver);

        notevalue = "" +BaseTest.getvalue("notevalue");
        String [] parsenote = notevalue.split(",");

        for (int index=0; index<3; index++) {
            functions.waitwithtry();
            WebElement elipsabutton = driver.findElement(By.xpath("//i[@class='icon icon-ellipsis']"));
            functions.clickelement(elipsabutton);
            WebElement addnotebutton = driver.findElement(By.xpath("//a[contains(text(),'Add a note')]"));
            functions.hovermethod(addnotebutton, driver);
            functions.clickelement(addnotebutton);

            if(index==0) {

                WebElement notefield = driver.findElement(By.xpath("//textarea[@data-v-6db1aff9]"));
                new WebDriverWait(driver, 8500).until(ExpectedConditions.visibilityOf(notefield));
                String privatenotevalue = parsenote[0];
                functions.completefield(notefield,privatenotevalue);
                WebElement savenotebutton = driver.findElement(By.xpath("//button[@class='tp-button green secondary']"));
                functions.clickelement(savenotebutton);

                functions.waitwithtry();
                WebElement privatemessage= driver.findElement(By.xpath("//b[contains(text(),'private')]"));
                String expectedsuccessfullmsg = BaseTest.getvalue("privatenotemessage");
                functions.validatetext(privatemessage,expectedsuccessfullmsg);
                Assert.assertEquals(actuallocationname,locationname);

//                driver.navigate().refresh();

            }

            if(index==1) {
                functions.waitwithtry();

                WebElement notefield = driver.findElement(By.xpath("//textarea[@data-v-6db1aff9]"));
                String publicnotevalue = parsenote[1];
                functions.completefield(notefield,publicnotevalue);
                WebElement privatecheckbox = driver.findElement(By.xpath("//input[@id='is_private']"));
                functions.clickelement(privatecheckbox);
                WebElement savenotebutton = driver.findElement(By.xpath("//button[@class='tp-button green secondary']"));
                functions.clickelement(savenotebutton);

                functions.waitwithtry();
                WebElement publicmessage= driver.findElement(By.xpath("//b[contains(text(),'public')]"));
                String expectedsuccessfullmsg2 = BaseTest.getvalue("publicmessage");
                functions.validatetext(publicmessage,expectedsuccessfullmsg2);
                Assert.assertEquals(actuallocationname,locationname);
            }

            if (index==2) {
                WebElement savenotebutton = driver.findElement(By.xpath("//button[@class='tp-button green secondary']"));
                functions.clickelement(savenotebutton);

            }
        }
    }
}
