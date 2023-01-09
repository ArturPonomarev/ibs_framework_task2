package rgs.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import rgs.framework.forms.CompaniesPage;
import rgs.framework.forms.HealthInsurancePage;
import rgs.framework.forms.MainPage;
import rgs.framework.models.UserData;
import rgs.framework.test.BaseTest;
import rgs.framework.utils.JsonDataProvider;
import rgs.framework.utils.TestDataUtils;

import java.util.stream.Stream;

public class HealthInsurancePrivateTest extends BaseTest {

    private final String FOR_COMPANIES_BUTTON_NAME = "Компаниям";
    private final String INCORRECT_EMAIL_MESSAGE = "Введите корректный адрес электронной почты";

    private MainPage mainPage;
    private CompaniesPage companiesPage;
    private HealthInsurancePage healthInsurancePage;

    public static Stream<UserData> parametersProvider() {
        return JsonDataProvider.testData.users.stream();
    }

    @ParameterizedTest
    @MethodSource("parametersProvider")
    public void Test(UserData userData) {
        driver.navigate().to(JsonDataProvider.testData.baseUrl);
        mainPage = new MainPage();
        Assertions.assertTrue(mainPage.isActive(),"Главная страница не открыта");
        if (mainPage.IsSubscribeFrameExist())
            mainPage.CloseSubscribeFrame();

        //2 - Переход на страницу добровольного медицинского страхования
        mainPage.ClickInsuranceTargetButton(FOR_COMPANIES_BUTTON_NAME);
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
        Assertions.assertTrue(healthInsurancePage.isRequestFormVisible(),"Форма заполнения заявки не видна");

        //4 - Заполнение формы и проверка на корректность заполнения, согласие с условиями обработки персональных данных
        healthInsurancePage.clickAcceptRulesCheckbox();
        healthInsurancePage.inputUserData(userData);
        userData.phoneNumber = TestDataUtils.TransformPhoneNumberToCorrect(userData.phoneNumber);
        Assertions.assertAll(
                () -> Assertions.assertEquals(userData.username,healthInsurancePage.getInputedUserData().username,"Имя пользователя не совпадает с введённым"),
                () -> Assertions.assertEquals(userData.phoneNumber,healthInsurancePage.getInputedUserData().phoneNumber,"Имя пользователя не совпадает с введённым"),
                () -> Assertions.assertEquals(userData.email,healthInsurancePage.getInputedUserData().email,"Имя пользователя не совпадает с введённым"),
                () -> Assertions.assertEquals(userData.address,healthInsurancePage.getInputedUserData().address,"Имя пользователя не совпадает с введённым")
        );
        Assertions.assertTrue(healthInsurancePage.isRulesCheckBoxChecked(),"Чекбокс на согласие с правилами обработки данных не активен");

        //5 - Нажатие кнопки "Свяжитесь со мной", проверка на ошибку в email
        healthInsurancePage.clickCallMeButton();
        Assertions.assertTrue(healthInsurancePage.isIncorrectEmailErrorExist(),"Надпись об ошибке в email не появилась");
        Assertions.assertEquals(healthInsurancePage.getIncorrectEmailErrorText(),INCORRECT_EMAIL_MESSAGE,"Надпись об ошибке в email не соотвествует требуемой");
    }
}
