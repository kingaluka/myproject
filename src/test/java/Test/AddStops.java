package Test;

import Help.BaseTest;
import Help.HelperMethods;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class AddStops  extends BaseTest {

    public HelperMethods functions = new HelperMethods(driver);
    private String today;

    @Test
    public void addastops (){
        JavascriptExecutor scroll = (JavascriptExecutor)driver;
        String expectedhomepagetitle = BaseTest.getvalue("homepagetitle");
        functions.validatetitle(expectedhomepagetitle, driver);
//  click on login button
        WebElement loginbutton = driver.findElement(By.xpath("//span[@class='visible-lg-inline-block']"));
        functions.clickelement(loginbutton);
//  complete all fields for login
        WebElement usernamefield = driver.findElement(By.xpath("//input[@id='username2']"));
        String usenamevalue = BaseTest.getvalue("userforlogin");
        functions.sendkeys(usernamefield, usenamevalue);
        WebElement passwordfield = driver.findElement(By.xpath("//input[@id='password2']"));
        String passwordvalue = BaseTest.getvalue("passwordforlogin");
        functions.sendkeys(passwordfield, passwordvalue);
//  click on login button
        WebElement log_inbutton = driver.findElement(By.xpath("//input[@id='submit']"));
        functions.clickelement(log_inbutton);
//  click on your travel button
        functions.waitwithtry();
        List<WebElement> menubarlist = driver.findElements(By.xpath("//nav/ul/li"));
        functions.clickelement(menubarlist.get(2));
//  click on add a new trip button
        WebElement addanewtripbutton = driver.findElement(By.xpath("//i[@class='icon icon-plus-circled']"));
        functions.clickelement(addanewtripbutton);

//   VALIDARE PAGINA: YOUR TRIP
        String expectedyourtriptitle = BaseTest.getvalue("yourtripspagetitle");
        functions.validatetitle(expectedyourtriptitle,driver);

//  complete the title field for add a trip
        WebElement tripnamefield = driver.findElement(By.xpath("//input[@class='trip-name']"));
        String tripnamevalue = BaseTest.getvalue("tripnamevalues");
        functions.sendkeys(tripnamefield,tripnamevalue);
//   click on add trip button
        functions.waitwithtry();
        WebElement addtripbutton = driver.findElement(By.xpath("//button[@class='tp-button has-icon green']"));
        functions.clickelement(addtripbutton);

//    VALIDARE TRIPUL CREAT
        functions.waitwithtry();
        WebElement triptitle = driver.findElement(By.xpath("//span[@class='trip-name']"));
        functions.waitexplicit(triptitle,driver,7500);
//        new WebDriverWait(driver,7500).until(ExpectedConditions.visibilityOf(triptitle));
        functions.validatetext(triptitle,tripnamevalue);


        // ADD A STOP

//  fill add a stop field
        List<String> stopValues=new ArrayList<String>();
        WebElement addastopfield = driver.findElement(By.xpath("//div[@id='geocoder-typeahead']/input"));
        String firststopname = BaseTest.getvalue("locationname");
        stopValues.add(firststopname);
        functions.sendkeys(addastopfield,firststopname);

//select the stopname
        WebElement stopname =driver.findElement(By.xpath("//li[@class='highlighted']"));
        functions.clickelement(stopname);

//        VALIDARE PAGINA DE OPRIRE
        WebElement firststop = driver.findElement(By.xpath("//h3/b"));
        functions.validatetext(firststop,firststopname);
////        2. validarea pagina de trip
//        functions.validatetext(triptitle,tripnamevalue);
////        3. validarea pagina de web
//        String expectedyourtriptitle = BaseTest.getvalue("yourtripspagetitle");
//        functions.validatetitle(expectedyourtriptitle,driver);

//click on save stop button
        WebElement savestopbutton = driver.findElement(By.xpath("//button[@id]"));
        functions.clickelement(savestopbutton);

//VALIDARE
////        1.titlul paginii
//
//        functions.validatetitle(expectedyourtriptitle,driver);
//
////        2. pagina de trip
//
//        functions.validatetext(triptitle,tripnamevalue);

//        3. VALIDARE PRIMUL OPRIRE
        functions.waitwithtry();
        WebElement locationweb = driver.findElement(By.xpath("//ul[@data-v-a115fc5c]/li"));
        String actuallocation = locationweb.getText();
        String expectedlocation = "1 "+ firststopname;
        Assert.assertEquals(expectedlocation, actuallocation);

//        4. VALIDAREA LOCATIEI PE HARTA
        functions.waitwithtry();
        WebElement firststoplocation = driver.findElement(By.xpath("//div[@class='place_label']"));
        functions.validatetext(firststoplocation,firststopname);





        //ADD A SECOND STOP

//   fill the add a stop field
        functions.waitwithtry();

        WebElement secondstopfield = driver.findElement(By.xpath("//input[@data-v-6c7ee37b]"));
        String secondstopvalue = BaseTest.getvalue("secondstopname");
        functions.sendkeys(secondstopfield,secondstopvalue);

        List<WebElement> secondstoplist = driver.findElements(By.xpath("//div[@data-v-6c7ee37b]/ul/li"));
        String secondstopname = secondstoplist.get(0).getText();
        stopValues.add(secondstopname);
        functions.clickelement(secondstoplist.get(0));

//  validarea paginii
//        1. VALIDARE PAGINA DE OPRIRE
        WebElement secondstop = driver.findElement(By.xpath("//h3/b"));
        functions.validatetext(secondstop,secondstopname);
////        2. validarea pagina de trip
//        functions.validatetext(triptitle,tripnamevalue);
////        3. validarea pagina de web
//        functions.validatetitle(expectedyourtriptitle,driver);


//  click random for a arrival transport mode

        List<WebElement> arrivaltransportmodelist = driver.findElements(By.xpath("//div[@class='transport-mode-selector']/div"));
        Random r = new Random();
        int randomvalue =r.nextInt(arrivaltransportmodelist.size()); //Getting a random value that is between 0 and (list's size)-1
        functions.clickelement(arrivaltransportmodelist.get(randomvalue));

//   select arrival and departure date

        WebElement arrivaldatebutton = driver.findElement(By.xpath("//div[@id='arrival-date-container']"));
        functions.clickelement(arrivaldatebutton);

//  scroll pana cand apare elementul

        scroll.executeScript("arguments[0].scrollIntoView();", arrivaldatebutton);

//   select arrival date
        today = getCurrentDay();
        WebElement arrivaldatetable = driver.findElement(By.xpath("//div[@id='arrival-date-container']//div[@class='asd__inner-wrapper']/div/div[2]/table/tbody"));

        List<WebElement> columns = arrivaldatetable.findElements(By.tagName("td"));
        for (WebElement cell: columns) {
            if (cell.getText().equals(today)){
                cell.click();
                break;
            }
        }

        functions.waitwithtry();
        scroll.executeScript("window.scrollBy(0,-250)");

//    select departure date
        WebElement departuredatebutton = driver.findElement(By.xpath("//div[@id='departure-date-container']"));
        functions.clickelement(departuredatebutton);


//  scroll pana cand apare elementul
        scroll.executeScript("arguments[0].scrollIntoView();", departuredatebutton);

        WebElement nextbutton = driver.findElement(By.xpath("//div[@id='departure-date-container']//button[@aria-label='Move forward to switch to the next month.']"));
        functions.clickelement(nextbutton);


        functions.waitwithtry();
        WebElement departuredatetable = driver.findElement(By.xpath("//div[@id='departure-date-container']//div[@class='asd__inner-wrapper']/div/div[2]/table/tbody"));
        List<WebElement> columns2 = departuredatetable.findElements(By.tagName("td"));
        for (WebElement cell: columns2) {

            if (cell.getText().equals("3")){
                cell.click();
                break;
            }
        }

        functions.waitwithtry();
        WebElement save2stopbutton = driver.findElement(By.xpath("//button[@id]"));
        functions.clickelement(save2stopbutton);

//   validarea
////        1. validarea paginii
//
//        functions.validatetitle(expectedyourtriptitle,driver);
//
////        2. validarea tripname
//
//        functions.waitwithtry();
//        functions.validatetext(triptitle,tripnamevalue);

//        3. VALIDRAEA OPRIRIILE IN LISTA

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
//         4. VALIDAREA OPRIRII PE HARTA

        for(int index=1;index<3;index++)
        {
            functions.waitwithtry();
            WebElement stopNameList = driver.findElement(By.xpath("//div[@data-v-7ffac3aa]/span[contains(text(),'"+index+"')]"));
            functions.hovermethod(stopNameList,driver);
            WebElement stopNameMap = driver.findElement(By.xpath("//div[@class='place_label']"));
            functions.validatetext(stopNameMap,stopValues.get(index-1));

        }


//  EDIT STOP

//        click pe oprire

        functions.clickelement(locationlist2.get(1));
        functions.waitwithtry();

////        validarea your trip page
//        functions.validatetitle(expectedyourtriptitle, driver);
//
//
////        validarea trip page
//        WebElement tripnameweb =driver.findElement(By.xpath("//h4/span[2][@data-v-393cebf4]"));
//        functions.validatetext(tripnameweb,tripnamevalue);

//        VALIDARE PAGINA DE OPRIRE

        WebElement locationnameweb = driver.findElement(By.xpath("//span[@class='stop-name']"));
//        new WebDriverWait(driver, 9500).until(ExpectedConditions.visibilityOf(locationnameweb));
        functions.waitexplicit(locationnameweb, driver,7500);
        String actualstopname = locationnameweb.getText();
        Assert.assertEquals(secondstopname,actualstopname);


//        click on edit stop button

        functions.waitwithtry();
        WebElement elipsabutton = driver.findElement(By.xpath("//i[@class='icon icon-ellipsis']"));
        functions.clickelement(elipsabutton);
        WebElement editbutton= driver.findElement(By.xpath("//ul/li/a[@data-v-1492b61e]/i[@class='icon icon-pencil']"));
        functions.hovermethod(editbutton, driver);
        functions.clickelement(editbutton);

//       VALIDARE PAGINA DE EDITARE
        functions.waitwithtry();
        WebElement editingweb = driver.findElement(By.xpath("//span[@class='stop-name']"));
        String expectededitpagename = "Editing " + secondstopname;
        functions.validatetext(editingweb,expectededitpagename);

//      select "At Start" from dropdown list

        functions.waitwithtry();
        WebElement dropdown = driver.findElement(By.xpath("//span[@class='selected-option-label']"));
        functions.clickelement(dropdown);
        List<WebElement> list=driver.findElements(By.xpath("//ul[@class='select']/li"));
        for(int i=0;i<list.size();i++)
        {
            System.out.println(list.get(i).getText());
            if(list.get(i).getText().equalsIgnoreCase("At Start"))
            {functions.hovermethod(list.get(i),driver);
//                list.get(i).click();
                functions.clickelement(list.get(i));
                break;
            }
        }

//        click on save button

        functions.waitwithtry();
        WebElement save =driver.findElement(By.xpath("//button[@class='tp-button green']"));
        functions.clickelement(save);

//       click on back button

        WebElement backbutton = driver.findElement(By.xpath("//h4/span[2][@data-v-393cebf4]"));
//        new WebDriverWait(driver,8000).until(ExpectedConditions.visibilityOf(backbutton));
        functions.waitexplicit(backbutton,driver,7000);
        functions.clickelement(backbutton);

//        VALIDARE
//        1. oprirea care a fost adaugat a doua oare acuma ii prima
        functions.waitwithtry();
        List<WebElement> locationlist1 = driver.findElements(By.xpath("//ul[@data-v-a115fc5c]/li"));
        for (int index = 0; index < locationlist2.size(); index++) {
            if(index==0) {
                new WebDriverWait(driver, 7500).until(ExpectedConditions.visibilityOf(locationlist1.get(index)));
                String stopname1 = locationlist1.get(index).getText();
                System.out.println(stopname1);

                Assert.assertEquals("1 "+secondstopname,stopname1);
            }
            if(index==1) {
                new WebDriverWait(driver, 7500).until(ExpectedConditions.visibilityOf(locationlist1.get(index)));
                String stopname2 = locationlist1.get(index).getText();
                System.out.println(stopname2);
                Assert.assertEquals("2 "+firststopname,stopname2);

            }
        }

//        driver.navigate().refresh();
        functions.waitwithtry();
//        2. validarea paginii

        functions.validatetitle(expectedyourtriptitle,driver);

//        3. validarea tripname
        WebElement triptitleweb = driver.findElement(By.xpath("//span[@class='trip-name']"));
        functions.validatetext(triptitleweb,tripnamevalue);


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
