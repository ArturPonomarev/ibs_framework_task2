package rgs.framework.utils;

public class TestDataUtils {

    public static String TransformPhoneNumberToCorrect(String rawNumber)
    {
        char[] number = rawNumber.toCharArray();

        String newNumber = "+7 (";
        newNumber = newNumber + number[0] + number[1] + number[2] + ") "
                + number[3] + number[4] + number[5] + '-'
                + number[6] + number[7] + number[8] + number[9];

        return newNumber;
    }
}
