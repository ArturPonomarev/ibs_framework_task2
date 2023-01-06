package rgs.tests;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import rgs.framework.forms.CompaniesPage;
import rgs.framework.forms.HealthInsurancePage;
import rgs.framework.forms.MainPage;
import rgs.framework.models.UserData;
import rgs.framework.test.BaseTest;
import rgs.framework.utils.JsonDataProvider;

public class HealthInsurancePrivateTest extends BaseTest {

    private final String INCORRECT_EMAIL_MESSAGE = "Введите корректный адрес электронной почты";

    private MainPage mainPage;
    private CompaniesPage companiesPage;
    private HealthInsurancePage healthInsurancePage;

    @Test
    public void Test() {
        driver.navigate().to(JsonDataProvider.testData.baseUrl);
        mainPage = new MainPage();
        Assertions.assertTrue(mainPage.isActive(),"Главная страница не открыта");
        if (mainPage.IsSubscribeFrameExist())
            mainPage.CloseSubscribeFrame();

        //2 - Переход на страницу добровольного медицинского страхования
        mainPage.ClickCompaniesButton();
        companiesPage = new CompaniesPage();
        Assertions.assertTrue(companiesPage.isActive(),"Страница 'Компаниям' не открыта");
        companiesPage.ClickHealthListButton();
        Assertions.assertTrue(companiesPage.IsHealthInsuranceListOpened(),"Список видов страхования не появился");
        companiesPage.ClickHealthInsuranceButton();
        healthInsurancePage = new HealthInsurancePage();
        Assertions.assertTrue(healthInsurancePage.isActive(),"Страница добровольного медицинского страхования не открыта");
        Assertions.assertTrue(healthInsurancePage.isHealthInsuranceHeaderExist(),"Заголовок 'Добровольное медицинское страхование' не появился");

        //3 - Проверка наличия формы заявки
        healthInsurancePage.clickSendRequestButton();
        Assertions.assertTrue(, healthInsurancePage.isRequestFormVisible(),"Форма заполнения заявки не видна");

        //4 - Заполнение формы и проверка на корректность заполнения, согласие с условиями обработки персональных данных
        UserData data = JsonDataProvider.testData.users.get(0);
        healthInsurancePage.clickAcceptRulesCheckbox();
        healthInsurancePage.inputUserData(data);
        //TODO: добавить функцию конвертации
        data.phoneNumber = PHONE_NUMBER_AFTER_TRANSFORM;
        Assertions.assertEquals(data, healthInsurancePage.getInputedUserData(),"Значения в полях не соотвествует введённым");
        Assertions.assertTrue(healthInsurancePage.isRulesCheckBoxChecked(),"Чекбокс на согласие с правилами обработки данных не активен");

        //5 - Нажатие кнопки "Свяжитесь со мной", проверка на ошибку в email
        healthInsurancePage.clickCallMeButton();
        Assertions.assertTrue(healthInsurancePage.isIncorrectEmailErrorExist(),"Надпись об ошибке в email не появилась");
        Assertions.assertEquals(healthInsurancePage.getIncorrectEmailErrorText(),INCORRECT_EMAIL_MESSAGE,"Надпись об ошибке в email не соотвествует требуемой");
    }
}
