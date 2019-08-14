package Test;

import Help.BaseTest;
import Help.Helpmethods;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;
import java.util.*;

public class AddaSecondStop extends BaseTest {

    public Helpmethods functions = new Helpmethods(driver);
    private String today;

    @Test
    public void addasecondstop (){
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
//  click on add a new trip button
        WebElement addanewtripbutton = driver.findElement(By.xpath("//i[@class='icon icon-plus-circled']"));
        functions.clickelement(addanewtripbutton);
//  complete the title and describe field for add a trip
        WebElement tripnamefield = driver.findElement(By.xpath("//input[@class='trip-name']"));
        String tripnamevalue = BaseTest.getvalue("tripnamevalues");
        functions.completefield(tripnamefield,tripnamevalue);

        WebElement describefield = driver.findElement(By.xpath("//textarea[@class='trip-description']"));
        String describevalue = BaseTest.getvalue("describetripvalues");
        functions.completefield(describefield,describevalue);
//   click on add trip button
        WebElement addtripbutton = driver.findElement(By.xpath("//button[@class='tp-button has-icon green']"));
        functions.clickelement(addtripbutton);
//   validarea titlului de trip

        functions.waitwithtry();
        WebElement triptitle = driver.findElement(By.xpath("//span[@class='trip-name']"));

        new WebDriverWait(driver,7500).until(ExpectedConditions.visibilityOf(triptitle));
        functions.validatetext(triptitle,tripnamevalue);



        // ADD A STOP

//  fill add a stop field
        List<String> stopValues=new ArrayList<String>();
        WebElement addastopfield = driver.findElement(By.xpath("//div[@id='geocoder-typeahead']/input"));
        String firststopfield = BaseTest.getvalue("firststopname");
        functions.completefield(addastopfield,firststopfield);
//  click on first location from the list
        List<WebElement> stoplist = driver.findElements(By.xpath("//div[@id='geocoder-typeahead']/div/ul/li"));
        String firststopname = stoplist.get(0).getText();
        stopValues.add(firststopname);
        functions.clickelement(stoplist.get(0));
//  click on save stop button
        WebElement savestopbutton = driver.findElement(By.xpath("//button[@id]"));
        functions.clickelement(savestopbutton);
//validarea
//        1.titlul paginii
        String expectedyourtriptitle = BaseTest.getvalue("yourtripspagetitle");
        functions.validatetitle(expectedyourtriptitle,driver);
//        2. pagina de trip
        functions.validatetext(triptitle,tripnamevalue);
//        3. vaidarea primul oprire
        functions.waitwithtry();
        List<WebElement> locationlist = driver.findElements(By.xpath("//ul[@data-v-a115fc5c]/li"));
        String actuallocation = locationlist.get(0).getText();
        Assert.assertEquals("1 "+firststopname, actuallocation);
        functions.hovermethod(locationlist.get(0),driver);





        //ADD A SECOND STOP

//   fill the add a stop field
        functions.waitwithtry();

        WebElement addastopfield2 = driver.findElement(By.xpath("//input[@data-v-6c7ee37b]"));
        String secondstopvalue = BaseTest.getvalue("secondstopname");
        functions.completefield(addastopfield2,secondstopvalue);

        List<WebElement> secondstoplist = driver.findElements(By.xpath("//div[@data-v-6c7ee37b]/ul/li"));
        String secondstopname = secondstoplist.get(0).getText();
        stopValues.add(secondstopname);
        functions.clickelement(secondstoplist.get(0));

//  validarea paginii
        WebElement secondstop = driver.findElement(By.xpath("//h3/b"));
        functions.validatetext(secondstop,secondstopname);

        functions.validatetitle(expectedyourtriptitle,driver);


//  click random for a arrival transport mode
        List<WebElement> arrivaltransportmodelist = driver.findElements(By.xpath("//div[@class='transport-mode-selector']/div"));
        Random r = new Random();
        int randomvalue =r.nextInt(arrivaltransportmodelist.size()); //Getting a random value that is between 0 and (list's size)-1
        functions.clickelement(arrivaltransportmodelist.get(randomvalue));
//        arrivaltransportmodelist.get(randomvalue).click(); //Clicking on the random item in the list.

//   select arrival and departure date

        today = getCurrentDay();
//        System.out.println("Today's number: " + today + "\n");

        WebElement arrivaldate = driver.findElement(By.xpath("//div[@id='arrival-date-container']"));
        functions.clickelement(arrivaldate);

//  scroll pana cand apare elementul
        JavascriptExecutor jse = (JavascriptExecutor)driver;
//        jse.executeScript("window.scrollBy(0,250)");
        WebElement scrollpage = driver.findElement(By.xpath("//div[@id='arrival-date-container']"));
        jse.executeScript("arguments[0].scrollIntoView();", scrollpage);

//   selectarea ziua curenta
        WebElement arrivaldatetable = driver.findElement(By.xpath("//div[@id='arrival-date-container']//div[@class='asd__inner-wrapper']/div/div[2]/table/tbody"));

//        List<WebElement> rows = arrivaldatetable.findElements(By.tagName("tr"));

        List<WebElement> columns = arrivaldatetable.findElements(By.tagName("td"));
        for (WebElement cell: columns) {
            if (cell.getText().equals(today)){
            cell.click();
            break;
        }
    }

        functions.waitwithtry();
        jse.executeScript("window.scrollBy(0,-250)");


        WebElement departuredatetable = driver.findElement(By.xpath("//div[@id='departure-date-container']"));
        functions.clickelement(departuredatetable);

//  scroll pana cand apare elementul
        WebElement scrollpage3 = driver.findElement(By.xpath("//div[@id='departure-date-container']"));
        jse.executeScript("arguments[0].scrollIntoView();", scrollpage3);

        WebElement departuredate = driver.findElement(By.xpath("//div[@id='departure-date-container']//div[@class='asd__inner-wrapper']/div/div[2]/table/tbody"));

//        List<WebElement> rows = arrivaldatetable.findElements(By.tagName("tr"));

        List<WebElement> columns2 = departuredate.findElements(By.tagName("td"));
        for (WebElement cell: columns2) {

            if (cell.getText().equals("18")){
                cell.click();
                break;
            }
        }

//        WebElement toppage = driver.findElement(By.xpath("//div[@id='pendingTripStop']/div/h3"));
//        jse.executeScript("arguments[0].scrollIntoView();", toppage);

        WebElement savestopbutton2 = driver.findElement(By.xpath("//button[@id]"));
        functions.clickelement(savestopbutton2);

//   validarea
//        1. validarea paginii
        functions.validatetitle(expectedyourtriptitle,driver);

//        2. validarea tripname
        functions.waitwithtry();
        functions.validatetext(triptitle,tripnamevalue);
//        driver.navigate().refresh();
//        3. validarea a doua opririi in lista
        functions.waitwithtry();
        List<WebElement> locationlist2 = driver.findElements(By.xpath("//ul[@data-v-a115fc5c]/li"));
        for (int index = 0; index < locationlist2.size(); index++) {
            if(index==0) {
                new WebDriverWait(driver, 7500).until(ExpectedConditions.visibilityOf(locationlist2.get(index)));
                String stopname1 = locationlist2.get(index).getText();
                System.out.println(stopname1);

                Assert.assertEquals("1 "+firststopname,stopname1);
            }
            if(index==1) {
                new WebDriverWait(driver, 7500).until(ExpectedConditions.visibilityOf(locationlist2.get(index)));
                String stopname2 = locationlist2.get(index).getText();
                System.out.println(stopname2);
                Assert.assertEquals("2 "+secondstopname,stopname2);

            }
        }
//         4. validarea opririi pe harta

        for(int index=1;index<3;index++)
        {
            functions.waitwithtry();
            WebElement stopNameList = driver.findElement(By.xpath("//div[@data-v-7ffac3aa]/span[contains(text(),'"+index+"')]"));
            functions.hovermethod(stopNameList,driver);
            WebElement stopNameMap = driver.findElement(By.xpath("//div[@class='place_label']"));
            functions.validatetext(stopNameMap,stopValues.get(index-1));

        }


//  delete the trip


        functions.waitwithtry();
        WebElement proba = driver.findElement(By.xpath("//div[@id='drawer']"));
        functions.hovermethod(proba,driver);
        jse.executeScript("window.scrollBy(0,-250)");


        functions.clickelement(locationlist2.get(1));
        functions.waitwithtry();
        WebElement locationnameweb = driver.findElement(By.xpath("//span[@class='stop-name']"));
        new WebDriverWait(driver, 9500).until(ExpectedConditions.visibilityOf(locationnameweb));
        String actualstopname = locationnameweb.getText();
        Assert.assertEquals(secondstopname,actualstopname);

        functions.waitwithtry();
        WebElement elipsabutton = driver.findElement(By.xpath("//i[@class='icon icon-ellipsis']"));
        functions.clickelement(elipsabutton);
        WebElement editbutton= driver.findElement(By.xpath("//ul/li/a[@data-v-1492b61e]/i[@class='icon icon-pencil']"));
        functions.hovermethod(editbutton, driver);
        functions.clickelement(editbutton);

        functions.waitwithtry();
        WebElement dropdown = driver.findElement(By.xpath("//span[@class='selected-option-label']"));
        functions.clickelement(dropdown);
//        driver.findElement(By.id("msdd")).click();
        List<WebElement> list=driver.findElements(By.xpath("//ul[@class='select']/li"));
        for(int i=0;i<list.size();i++)
        {
            System.out.println(list.get(i).getText());
            if(list.get(i).getText().equalsIgnoreCase("At Start"))
            {functions.hovermethod(list.get(i),driver);
                list.get(i).click();
//                functions.clickelement(list.get(i));
                break;
            }
        }


        WebElement save =driver.findElement(By.xpath("//button[@class='tp-button green']"));
        functions.clickelement(save);

        WebElement backbutton = driver.findElement(By.xpath("//h4/span[2][@data-v-393cebf4]"));
        new WebDriverWait(driver,8000).until(ExpectedConditions.visibilityOf(backbutton));
        functions.clickelement(backbutton);

        functions.waitwithtry();
//        List<WebElement> locationlist = driver.findElements(By.xpath("//ul[@data-v-a115fc5c]/li"));
        for (int index = 0; index < locationlist2.size(); index++) {
            if(index==0) {
                new WebDriverWait(driver, 7500).until(ExpectedConditions.visibilityOf(locationlist2.get(index)));
                String stopname1 = locationlist2.get(index).getText();
                System.out.println(stopname1);

                Assert.assertEquals("1 "+secondstopname,stopname1);
            }
            if(index==1) {
                new WebDriverWait(driver, 7500).until(ExpectedConditions.visibilityOf(locationlist2.get(index)));
                String stopname2 = locationlist2.get(index).getText();
                System.out.println(stopname2);
                Assert.assertEquals("2 "+firststopname,stopname2);

            }
        }



    }
    //Get The Current Day
    private String getCurrentDay () {
        //Create a Calendar Object
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        //Get Current Day as a number
        int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
//        System.out.println("Today Int: " + todayInt + "\n");

        //Integer to String Conversion
        String todayStr = Integer.toString(todayInt);
//        System.out.println("Today Str: " + todayStr + "\n");

        return todayStr;
    }
}
