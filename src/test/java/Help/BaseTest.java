package Help;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;
    public static Properties property;
    public FileInputStream file;

    @Before
    public void baza () throws FileNotFoundException {

        System.setProperty("webdriver.chrome.driver","C:\\automation\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.travellerspoint.com/");
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loadproperty ();
    }

    public void loadproperty () throws FileNotFoundException {
        property = new Properties();
        file = new FileInputStream("C:\\Users\\Bogar\\IdeaProjects\\MyPojectAutomation\\src\\test\\resources\\InputDate.properties");
        try {
            property.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getvalue (String key) {
        return property.getProperty(key);
    }




}
