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

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class AddFirstStop extends BaseTest {

    public Helpmethods functions = new Helpmethods(driver);
    private String today;

    @Test

    public void addafirststop () {
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
        String tripnamevalue = System.currentTimeMillis()+"";
        functions.completefield(tripnamefield,tripnamevalue);

        WebElement describefield = driver.findElement(By.xpath("//textarea[@class='trip-description']"));
        String describevalue = BaseTest.getvalue("describetripvalues");
        functions.completefield(describefield,describevalue);
//   click on add trip button
        WebElement addtripbutton = driver.findElement(By.xpath("//button[@class='tp-button has-icon green']"));
        functions.clickelement(addtripbutton);

        String expectedyourtriptitle = BaseTest.getvalue("yourtripspagetitle");
        functions.validatetitle(expectedyourtriptitle,driver);

        functions.waitwithtry();
        WebElement triptitle = driver.findElement(By.xpath("//span[@class='trip-name']"));
        new WebDriverWait(driver, 6500).until(ExpectedConditions.visibilityOf(triptitle));
        functions.validatetext(triptitle,tripnamevalue);

        // ADD THE FIRST STOP STOP

//   fill add a stop field

        WebElement addastopfield = driver.findElement(By.xpath("//div[@id='geocoder-typeahead']/input"));
        String firststopfield = BaseTest.getvalue("firststopname");
        functions.completefield(addastopfield,firststopfield);

//  click on first location from the list

        List<WebElement> stoplist = driver.findElements(By.xpath("//div[@id='geocoder-typeahead']/div/ul/li"));
        String expectedfirststop = stoplist.get(0).getText();
        functions.clickelement(stoplist.get(0));

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

            if (cell.getText().equals("25")){
                cell.click();
                break;
            }
        }

//  click on save stop button
        WebElement savestopbutton = driver.findElement(By.xpath("//button[@id]"));
        functions.clickelement(savestopbutton);



//   validarea locatiei in lista
        functions.waitwithtry();
        List<WebElement> locationlist = driver.findElements(By.xpath("//ul[@data-v-a115fc5c]/li"));
        String actuallocation = locationlist.get(0).getText();
        Assert.assertEquals("1 "+expectedfirststop, actuallocation);
        functions.hovermethod(locationlist.get(0),driver);

        //   validarea locatiei pe harta
//        driver.navigate().refresh();
        functions.waitwithtry();
        WebElement firststoplocation = driver.findElement(By.xpath("//div[@class='place_label']"));

        functions.validatetext(firststoplocation,expectedfirststop);

//  validarea paginii

        functions.validatetitle(expectedyourtriptitle,driver);

//  validarea pagina de trip
        functions.validatetext(triptitle,tripnamevalue);
//
//  delete the trip
        jse.executeScript("window.scrollBy(0,-250)");

        WebElement elipsabutton = driver.findElement(By.xpath("//i[@class='icon icon-ellipsis']"));
        functions.clickelement(elipsabutton);

        WebElement deletetrip = driver.findElement(By.xpath("//i[@class='icon icon-trash']"));
        functions.clickelement(deletetrip);

        WebElement yesdelete = driver.findElement(By.xpath("//button[@class='tp-button has-icon secondary danger']"));
        functions.clickelement(yesdelete);




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
