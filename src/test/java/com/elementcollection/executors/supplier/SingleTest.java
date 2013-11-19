package com.elementcollection.executors.supplier;

import com.google.common.collect.Lists;
import org.mockito.Mockito;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * <br> User: Mangan <br> Date: 19/11/13
 */
@Test(groups = "unit")
public class SingleTest {

    public void Get_On_Non_Empty_List_Should_Return_First_Element() throws Exception {
        final WebElement a = Mockito.mock(WebElement.class);
        final WebElement b = Mockito.mock(WebElement.class);
        final WebElement c = Mockito.mock(WebElement.class);

        assertEquals(new Single(Lists.newArrayList(a, b, c)).get(), a);
    }

    public void Get_On_Empty_List_Should_Return_Null() throws Exception {
        assertNull(new Single(Lists.<WebElement>newArrayList()).get());
    }
}
