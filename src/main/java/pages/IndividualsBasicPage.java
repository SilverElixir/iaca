package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by lucky on 12/16/18.
 */
public class IndividualsBasicPage extends AbstractPage {

    public IndividualsBasicPage(WebDriver driver) {
        super(driver);
    }

    public void init(final WebDriver driver) {
        PageFactory.initElements(driver, this);
//     vs   PageFactory.initElements(driver, IndividualsBasicPage.class); ??
    }



}
