package pages.mortgages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.IndividualsBasicPage;

import java.util.List;

/**
 * Created by lucky on 12/16/18.
 */
public class MortgagePaymentCalculator extends IndividualsBasicPage {

    public MortgagePaymentCalculator(WebDriver driver) {
        super(driver);
    }

//    final Logger logger = LoggerFactory.getLogger(MortgagePaymentCalculator.class);

    JavascriptExecutor js = (JavascriptExecutor) driver;

    @FindBy(id = "PrixPropriete")
    WebElement purchasePriceTextField;

    @FindBy(id = "sliderPrixPropriete")
    WebElement purchasePriceSlider;

    @FindBy(id = "PrixProprietePlus")
    WebElement increasePurchasePriceBtn;

    @FindBy(name = "MiseDeFond")
    WebElement downPaymentInputField;

    @FindBy(id = "sliderMiseDeFond")
    WebElement downPaymentSlider;

    @FindBy(className = "selectric")
    List<WebElement> dropdowns;

    @FindBy(className = "selectric-scroll")
    List<WebElement> dropdownsFake;

    @FindBy(id = "TauxInteret")
    WebElement interestRateInputField;

    @FindBy(id = "btn_calculer")
    WebElement calculateBtn;

    @FindBy(className = "calculateur-resultats-total")
    WebElement monthlyPaymentsCalculated;

    /**
     * Changes the Purchase Price to 500 000 using the + button of the slider
     * @return current page
     */
    @Step("Change the Purchase Price to 500 000 using the + button of the slider")
    public MortgagePaymentCalculator validatePurchasePriceSliderMovementWorks(){
        increasePurchasePriceBtn.click();
        increasePurchasePriceBtn.click();
        Assert.assertEquals(purchasePriceSlider.getAttribute("value"), "500000", "OOPS! TBD");
        Assert.assertEquals(purchasePriceTextField.getAttribute("value"), "500000");
        return this;
    }

    /**
     * Change the Down Payment to 50 000 using the + button of the slider
     * @return current page
     */
    @Step("Change the Down Payment to 50 000 using slider (or, in this case, input field directly")
    public MortgagePaymentCalculator validateDownPaymentSliderMovementWorks(){
//        Actions builder = new Actions(driver);
//        builder
//                .clickAndHold(downPaymentSlider)
////               .moveToElement()
//                .build().perform();

//        WebElement slider = driver.findElements(By.className("slider-track-high")).get(1);
//        js.executeScript("arguments[0].setAttribute('style', 'right: 0px; width: 95%;')", slider);

        // Ok, I gave up on trying to do that :( Here's a workaround
        downPaymentInputField.clear();
        downPaymentInputField.sendKeys("50000");
        js.executeScript("document.getElementById('MiseDeFond').blur();"); // needed somehow to unfocus current element... urgh
        Assert.assertEquals(downPaymentSlider.getAttribute("data-value"), "50000");
        return this;
    }

    /**
     * Selects 15 years for Amortization
     * @return current page
     */
    @Step("Select 15 years for Amortization")
    public MortgagePaymentCalculator checkAmortizationDropdownWorks(){
        Actions actions = new Actions(driver);
        actions
                .click(dropdowns.get(0))
                .contextClick()
                .moveToElement(dropdownsFake.get(0).findElement(By.cssSelector("ul > li[data-index='0']")))
                .click()
                .build().perform();
        return this;
    }

    /**
     * Selects "Weekly" for Payment Frequency
     * @return current page
     */
    @Step("Select 'Weekly' for Payment Frequency")
    public MortgagePaymentCalculator checkPaymentFrequencyDropdownWorks(){
        Actions actions = new Actions(driver);
        actions
                .click(dropdowns.get(1))
                .moveToElement(dropdowns.get(1))
                .moveToElement(dropdownsFake.get(1).findElement(By.cssSelector("ul > li[data-index='3']")))
                .click()
                .build().perform();
        return this;
    }

    /**
     * Changes the Interest Rate to 5%
     * @return current page
     */
    @Step("Change the Interest Rate to 5%")
    public MortgagePaymentCalculator validateInterestRateInputFieldWorks(){
        interestRateInputField.clear();
        interestRateInputField.sendKeys("5");
        return this;
    }

    /**
     * Clicks "Calculate" button
     * @return current page
     */
    @Step("Click 'Calculate' button")
    public MortgagePaymentCalculator calculate(){
        calculateBtn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("info-calculateur")));
        return this;
    }

    /**
     * Verifies if Monthly Payments are calculated correctly
     * @return current page
     */
    @Step("Verify that the weekly payments value is 842.47")
    public MortgagePaymentCalculator makeSureMonthlyPaymentsAreAcurate(){
        String expectedPayment = "$842.47";

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("calculateur-resultats-total")));
        String actualPayment = monthlyPaymentsCalculated.getText();

        Assert.assertEquals(actualPayment, expectedPayment);
        return this;
    }
}
