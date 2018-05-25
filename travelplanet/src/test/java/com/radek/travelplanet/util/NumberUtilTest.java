package com.radek.travelplanet.util;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class NumberUtilTest {

    @Test
    public void shouldReturnEmptyTextForNoTextNumber() throws Exception {
        assertThat(NumberUtil.findTextNumber(""), Is.is(""));
        assertThat(NumberUtil.findTextNumber("str"), Is.is(""));
    }

    @Test
    public void shouldFindFirstNumberInGivenString() throws Exception {
        String text = " zzz 1 239 zł 55 gr ";

        assertThat(NumberUtil.findTextNumber(text), Is.is("123955"));
        assertThat(NumberUtil.findNumber(text), Is.is(123955));
    }

    @Test
    public void shouldFindFirstNumberInGivenString2() throws Exception {
        String text = " 1Â 198 zĹ‚ ";

        assertThat(NumberUtil.findTextNumber(text), Is.is("1198"));
        assertThat(NumberUtil.findNumber(text), Is.is(1198));
    }

    @Test
    public void shouldFindFirstNumberInGivenString3() throws Exception {
        String text = "1 899 zł";

        assertThat(NumberUtil.findTextNumber(text), Is.is("1899"));
        assertThat(NumberUtil.findNumber(text), Is.is(1899));
    }

}