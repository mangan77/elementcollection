package com.elementcollection.executors.supplier;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * <br> User: Mangan <br> Date: 19/11/13
 */
@Test(groups = "unit")
public class ElementSupplierTest {
    public void Creating_Single_Should_Return_Instance_Of_Single() throws Exception {
        assertThat(ElementSupplier.single(Collections.<WebElement>emptyList()), instanceOf(Single.class));
    }

    public void Creating_Multiple_Should_Return_Instance_Of_Multiple() throws Exception {
        assertThat(ElementSupplier.multiple(Collections.<WebElement>emptyList()), instanceOf(Multiple.class));
    }
}
