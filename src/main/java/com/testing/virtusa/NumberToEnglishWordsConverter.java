package com.testing.virtusa;

import java.text.DecimalFormat;

public class NumberToEnglishWordsConverter {
    private static final String[] DIGITS_NAMES = {"", " one", " two", " three", " four", " five", " six", " seven", " eight", " nine", " ten", " eleven", " twelve", " thirteen", " fourteen", " fifteen", " sixteen", " seventeen", " eighteen", " nineteen"};
    private static final String[] TENS_NAMES = {"", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};


    public static String convert(long number) {
        if (number == 0) {
            return "zero";
        }

        if (number < 1000) {
            return trimAndReplaceMulitipleWhiteSpacesIntoOne(convertLessThanOneThousand((int) number));
        }

        // pad with "0"
        String mask = "000000000000";
        DecimalFormat df = new DecimalFormat(mask);
        String numberString = df.format(number);

        // (BBB) MMM TTT H T D
        int billions = Integer.parseInt(numberString.substring(0, 3));
        // BBB (MMM) TTT H T D
        int millions = Integer.parseInt(numberString.substring(3, 6));
        // BBB MMM (TTT) H T D
        int hundredThousands = Integer.parseInt(numberString.substring(6, 9));
        // BBB MMM TTT (H T D)
        int thousands = Integer.parseInt(numberString.substring(9, 12));


        String result = billions == 0 ? "" : convertLessThanOneThousand(billions) + " billion ";
        result += millions == 0 ? "" : convertLessThanOneThousand(millions) + " million ";
        result += hundredThousands == 0 ? "" : convertLessThanOneThousand(hundredThousands) + " thousand";
        result += convertLessThanOneThousand(thousands);

        return trimAndReplaceMulitipleWhiteSpacesIntoOne(result);
    }

    private static String trimAndReplaceMulitipleWhiteSpacesIntoOne(String s) {
        return s.trim().replaceAll("\\s{2,}", " ");
    }

    private static String convertLessThanOneThousand(int number) {
        StringBuilder numberString = new StringBuilder();

        if (number / 100 > 0) {
            numberString.append(DIGITS_NAMES[number / 100]).append(" hundred");
            number = number % 100;
            if (number > 0) {
                numberString.append(" and ");
            }
        }
        if (number < 20) {
            numberString.append(DIGITS_NAMES[number]);
        } else {
            numberString.append(TENS_NAMES[number / 10]).append(DIGITS_NAMES[number % 10]);
        }
        return numberString.toString();
    }
}
