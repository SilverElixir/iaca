import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.mortgages.MortgagePaymentCalculator;

/**
 * Created by lucky on 12/16/18.
 */
public class BasicTest {

    protected static WebDriver driver;

    @Parameters("browser")
    @BeforeTest
    public void setUp(@Optional("chrome") String browser){
        driver = DriverFactory.getWebDriverInstance(browser);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    protected MortgagePaymentCalculator openMPC(){
        driver.get("https://ia.ca/mortgage-payment-calculator");
        MortgagePaymentCalculator mpc = PageFactory.initElements(driver, MortgagePaymentCalculator.class);
        return mpc;
    }
}
