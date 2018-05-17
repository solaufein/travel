package com.radek.travelplanet.util;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class NumberUtilTest {

    @Test
    public void shouldFindFirstNumberInGivenString() throws Exception {
        int price = NumberUtil.findFirstNumber(" zzz 1 239 zł 55 gr ");

        assertThat(price, Is.is(1239));
    }

}