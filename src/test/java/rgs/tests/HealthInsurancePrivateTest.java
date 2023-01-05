package rgs.tests;

import org.junit.Test;
import rgs.framework.test.BaseTest;
import rgs.framework.utils.JsonDataProvider;

public class HealthInsurancePrivateTest extends BaseTest {
    @Test
    public void Test()
    {
        driver.navigate().to(JsonDataProvider.testData.baseUrl);
    }
}
