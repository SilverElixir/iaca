import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by lucky on 12/16/18.
 */
public class SeleniumWebdriverInterviewTest extends BasicTest {

/*
*  потрібно зробити:
+ заавтоматизувати на Java з використанням TestNG;
- додати report в кінці (логгер не потрібен, репорт повинен бути прийнятий для клієнта);
+ проект повинен бути написаний в стилі https://www.tutorialspoint.com/design_pattern/factory_pattern.htm
+ проект повинен бути побудований з Maven або Gradle
+ все повинно бути зроблено в ООП;
+ надсилати код в будь-якій version control system (наприклад в Git)
+ ну і бути готовим пояснити все, що зробили.

Великим плюсом буде, якщо справитесь з наступним:
1. +/- Розподілити пакети згідно паттернам тестування.
2. - Зробити графічний репорт зі степами або описом дій.
3. - Ассерти в тестовому класі, не в об’єктивному.
4. +/- Зробити драйвер статичним, але так щоб він ранився в декілька потоках з ізоляцією.
* */

    @Test
    public void validateMortgageCalculatorTest(){
        openMPC()
                .validatePurchasePriceSliderMovementWorks()
                .validateDownPaymentSliderMovementWorks()
                .checkAmortizationDropdownWorks()
                .checkPaymentFrequencyDropdownWorks()
                .validateInterestRateInputFieldWorks()
                .calculate()
                .makeSureMonthlyPaymentsAreAcurate();
    }
}
