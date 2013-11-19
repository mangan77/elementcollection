package com.elementcollection.executors.supplier;

import com.google.common.collect.Lists;
import org.mockito.Mockito;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.testng.Assert.assertEquals;

/**
 * <br> User: Mangan <br> Date: 19/11/13
 */
@Test(groups = "unit")
public class MultipleTest {

    public void Get_On_Empty_List_Should_Return_Empty_List() throws Exception {
        assertEquals(new Multiple(Collections.<WebElement>emptyList()).get().size(), 0);
    }

    public void Get_On_Non_Empty_List_Should_Return_List() throws Exception {
        final WebElement a = Mockito.mock(WebElement.class);
        final WebElement b = Mockito.mock(WebElement.class);
        final WebElement c = Mockito.mock(WebElement.class);
        final ArrayList<WebElement> webElements = Lists.newArrayList(a, b, c);

        assertEquals(new Multiple(webElements).get(), webElements);
    }
}
