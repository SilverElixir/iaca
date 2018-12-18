package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by lucky on 12/16/18.
 */
public class AbstractPage {

    protected static WebDriver driver;
    protected WebDriverWait wait;

    public AbstractPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

}
