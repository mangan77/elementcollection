package com.elementcollection.type;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Test
public class TimeUnitsTest {

    public void testTwoSecsShouldReturn2000fMilliseconds() throws Exception {
        assertThat(TimeUnits.secs(2).inMilliseconds(), is(2000));
    }

    public void testTwoMillisShouldReturn2Milliseconds() throws Exception {
        assertThat(TimeUnits.millis(2).inMilliseconds(), is(2));
    }
}