package com.testing.virtusa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class NumberToEnglishWordsConverterTest {

    private long inputNumber;
    private String expectedOutput;

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{
            {0, "zero"},
            {1, "one"},
            {9, "nine"},
            {19, "nineteen"},
            {30, "thirty"},
            {37, "thirty seven"},
            {600, "six hundred"},
            {105, "one hundred and five"},
            {690, "six hundred and ninety"},
            {654, "six hundred and fifty four"},
            {9000, "nine thousand"},
            {7990, "seven thousand nine hundred and ninety"},
            {7997, "seven thousand nine hundred and ninety seven"},
            {56945781, "fifty six million nine hundred and forty five thousand seven hundred and eighty one"},
        });
    }

    public NumberToEnglishWordsConverterTest(int inputNumber, String expectedOutput) {
        super();
        this.inputNumber = inputNumber;
        this.expectedOutput = expectedOutput;
    }

    @Test
    public void convert() {
        assertEquals(expectedOutput, NumberToEnglishWordsConverter.convert(inputNumber));
    }
}