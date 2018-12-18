import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Looks like https://www.tutorialspoint.com/design_pattern/factory_pattern.htm :) Or not?
 */
public class DriverFactory{

    private DriverFactory(){}

    protected static WebDriver webDriver;

    public static WebDriver getWebDriverInstance(String browser) {
        if(webDriver == null) {
            switch (browser) {
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/mac/geckodriver");
//             uncomment, please, if running tests on Windows
//                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/mac/geckodriver.exe");
                    return new FirefoxDriver();
                case "chrome":
                default:
                    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/mac/chromedriver");
//                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/win/chromedriver.exe");
                    return new ChromeDriver();
            }
        }
        return webDriver;
    }
}

