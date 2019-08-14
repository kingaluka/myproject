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
import java.util.Random;

public class AddaTrip extends BaseTest {
    public Helpmethods functions= new Helpmethods(driver);

    @Test
    public void addatrip (){

//  validare homepage

        String expectedhomepagetitle = BaseTest.getvalue("homepagetitle");
        functions.validatetitle(expectedhomepagetitle, driver);

//   click on login button

        WebElement loginbutton = driver.findElement(By.xpath("//span[@class='visible-lg-inline-block']"));
        functions.clickelement(loginbutton);


//   validare login page
        String expectedloginpagetitle = BaseTest.getvalue("loginpagetitle");
        functions.validatetitle(expectedloginpagetitle,driver);

//   fill login fields with valid values

        WebElement usernamefield = driver.findElement(By.xpath("//input[@id='username2']"));
        String usenamevalue = BaseTest.getvalue("userforlogin");
        functions.completefield(usernamefield, usenamevalue);

        WebElement passwordfield = driver.findElement(By.xpath("//input[@id='password2']"));
        String passwordvalue = BaseTest.getvalue("passwordforlogin");
        functions.completefield(passwordfield, passwordvalue);

//  click on login button

        WebElement log_inbutton = driver.findElement(By.xpath("//input[@id='submit']"));
        functions.clickelement(log_inbutton);

//  validate the successfull login message

        String expectedsuccesfullloginmessage = BaseTest.getvalue("successfulloginmessage");
        List<WebElement> loginmessagelist = driver.findElements(By.xpath("//div[@class='column large-8']//li"));
        for (int index = 0; index < loginmessagelist.size(); index++) {
            new WebDriverWait(driver, 4500).until(ExpectedConditions.visibilityOf(loginmessagelist.get(index)));
            String actualsuccessfulllogginmessage = loginmessagelist.get(index).getText();
//            System.out.println(actualsuccessfulllogginmessage);

            Assert.assertEquals(expectedsuccesfullloginmessage, actualsuccessfulllogginmessage);
        }

//  validate the homepage after login

        new WebDriverWait(driver, 4500).until(ExpectedConditions.titleIs(BaseTest.getvalue("homepagetitle")));
        functions.validatetitle(expectedhomepagetitle, driver);

//  click on your travel button

        functions.waitwithtry();
        List<WebElement> menubarlist = driver.findElements(By.xpath("//nav/ul/li"));
        functions.clickelement(menubarlist.get(2));

//  validate new your trips page

        String expectedyourtripspagetitle = BaseTest.getvalue("yourtripspagetitle");
        functions.validatetitle(expectedyourtripspagetitle,driver);


//   click on trip color button

//        WebElement tribbutton = driver.findElement(By.xpath("//span[@class='trip-color']"));
//        functions.clickelement(tribbutton);

//   select a random color

//        List<WebElement> colorlist = driver.findElements(By.xpath("//div[@class='color-options']/div"));
//        colorlist.get(getRandomNumberInBetween(1, colorlist.size() - 1)).click();
//        functions.clickelement(colorlist.get(getRandomNumberInBetween(1,colorlist.size()-1)));


        for (int i=0; i<2; i++) {
            WebElement addanewtripbutton1 = driver.findElement(By.xpath("//i[@class='icon icon-plus-circled']"));
            new WebDriverWait(driver,8500).until(ExpectedConditions.visibilityOf(addanewtripbutton1));
            functions.hovermethod(addanewtripbutton1, driver);

            functions.clickelement(addanewtripbutton1);

            functions.waitwithtry();
            WebElement newtripweb = driver.findElement(By.xpath("//h3[contains(text(),'Add a New Trip')]"));
            String expectednewripmessagevalue = BaseTest.getvalue("expectedaddatriptitle");
            functions.validatetext(newtripweb,expectednewripmessagevalue);
            functions.validatetitle(expectedyourtripspagetitle,driver);

            WebElement tripnamefield = driver.findElement(By.xpath("//input[@class='trip-name']"));
            WebElement describefield = driver.findElement(By.xpath("//textarea[@class='trip-description']"));
            WebElement tribbutton = driver.findElement(By.xpath("//span[@class='trip-color']"));
//            List<WebElement> colorlist = driver.findElements(By.xpath("//div[@class='color-options']/div"));
            WebElement addtripbutton = driver.findElement(By.xpath("//button[@class='tp-button has-icon green']"));

            if(i==0){
                String tripnamevalue = System.currentTimeMillis()+"";
                functions.completefield(tripnamefield,tripnamevalue);

                String describevalue = BaseTest.getvalue("describetripvalues");
                functions.completefield(describefield,describevalue);

                functions.clickelement(tribbutton);
                List<WebElement> colorlist = driver.findElements(By.xpath("//div[@class='color-options']/div"));
                Random r = new Random();
                int randomvalue =r.nextInt(colorlist.size()); //Getting a random value that is between 0 and (list's size)-1
                functions.clickelement(colorlist.get(randomvalue));
//                functions.clickelement(colorlist.get(getRandomNumberInBetween(1,colorlist.size()-1)));

                functions.clickelement(addtripbutton);


                //  validarea paginii
                functions.validatetitle(expectedyourtripspagetitle,driver);

//  validarea titlului de trip
                functions.waitwithtry();
                WebElement triptitleweb = driver.findElement(By.xpath("//span[@class='trip-name']"));
                new WebDriverWait(driver,7500).until(ExpectedConditions.visibilityOf(triptitleweb));
                functions.validatetext(triptitleweb,tripnamevalue);

// validarea textul describe
                WebElement describesection = driver.findElement(By.xpath("//div[@class='trip-description']"));
                functions.validatetext(describesection,describevalue);

                WebElement backbutton = driver.findElement(By.xpath("//div[@data-v-a115fc5c]/h3/i[@class='icon icon-left-open-big']"));
                functions.clickelement(backbutton);
                functions.waitwithtry();
                functions.validatetitle(expectedyourtripspagetitle,driver);

            }

            if (i==1) {
                functions.clickelement(addtripbutton);
                WebElement addtriperrormsgweb = driver.findElement(By.xpath("//div[@class='tp-error-message']/span"));
                String expectedaddtriperrormsg = BaseTest.getvalue("addtriperrormsg");
                functions.validatetext(addtriperrormsgweb,expectedaddtriperrormsg);

                WebElement backbutton = driver.findElement(By.xpath("//h3/a/i"));
                functions.clickelement(backbutton);
                functions.waitwithtry();
                functions.validatetitle(expectedyourtripspagetitle,driver);
            }

        }

        WebElement nameoftrip = driver.findElement(By.xpath("//ul/li[@data-v-19daf797]"));
        functions.clickelement(nameoftrip);
        //  delete the trip
        WebElement elipsabutton = driver.findElement(By.xpath("//i[@class='icon icon-ellipsis']"));
        functions.clickelement(elipsabutton);

        WebElement deletetrip = driver.findElement(By.xpath("//i[@class='icon icon-trash']"));
        functions.clickelement(deletetrip);

        WebElement yesdelete = driver.findElement(By.xpath("//button[@class='tp-button has-icon secondary danger']"));
        functions.clickelement(yesdelete);

    }

//    private static int getRandomNumberInBetween(int lowerBound, int upperBound)
//    {
//        Random r = new Random();
//        return (r.nextInt(upperBound) + lowerBound);
//
//    }
}
