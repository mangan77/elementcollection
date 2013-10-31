package com.webelementcollection;

import com.google.common.collect.Maps;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 * <br> User: Mangan <br> Date: 31/10/13
 */
@Test(groups = "unit")
public class ElementCollectionImplTest {
    //    public void testFind() throws Exception {
//
//    }
//
//    public void testClick() throws Exception {
//
//    }
//
    @Test(expectedExceptions = IllegalStateException.class)
    public void Clicking_An_Empty_Collection_Should_Throw_Illegal_Argument_Exception() {
        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver());
        elementCollection.click();
        fail();
    }

    public void When_More_Then_One_Element_In_Collection_All_Elements_Should_Get_Clicked() {
        final WebElement webElementOne = new WebElementMockBuilder().build();
        final WebElement webElementTwo = new WebElementMockBuilder().build();
        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver(), webElementOne, webElementTwo);

        elementCollection.click();
        verify(webElementOne).click();
        verify(webElementTwo).click();
    }

//    public void testSubmit() throws Exception {
//
//    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void Submitting_An_Empty_Collection_Should_Throw_Illegal_State_Exception() {
        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver());
        elementCollection.submit();
        fail();
    }

    public void When_More_Then_One_Element_In_Collection_All_Elements_Should_Get_Submitted() {
        final WebElement webElementOne = new WebElementMockBuilder().build();
        final WebElement webElementTwo = new WebElementMockBuilder().build();
        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver(), webElementOne, webElementTwo);

        elementCollection.submit();
        verify(webElementOne).submit();
        verify(webElementTwo).submit();
    }
//
//    public void testGet() throws Exception {
//
//    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void Getting_An_Element_From_An_Empty_Collection_Should_Throw_Illegal_State_Exception() {
        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver());

        elementCollection.get(0);
        fail();
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void Specifying_An_Index_Outside_The_Collection_Should_Throw_Illegal_State_Exception() {
        final WebElement webElementOne = new WebElementMockBuilder().build();
        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver(), webElementOne);

        elementCollection.get(1);
        fail();
    }

    public void Getting_An_Element_In_The_Collection_Should_Return_The_Correct_Element() {
        final WebElement webElementOne = new WebElementMockBuilder().build();
        final WebElement webElementTwo = new WebElementMockBuilder().withId("two").build();
        final WebElement webElementThree = new WebElementMockBuilder().build();
        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver(),
                webElementOne, webElementTwo, webElementThree);

        assertEquals(elementCollection.get(1).attr("id"), "two");
    }

//
//    public void testFirst() throws Exception {
//
//    }
//

    @Test(expectedExceptions = IllegalStateException.class)
    public void Getting_The_First_Element_From_An_Empty_Collection_Should_Throw_Illegal_State_Exception() {
        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver());

        elementCollection.first();
        fail();
    }

    public void Getting_The_First_Element_From_A_Collection_Should_Return_The_First_Element() {
        final WebElement webElementOne = new WebElementMockBuilder().withId("one").build();
        final WebElement webElementTwo = new WebElementMockBuilder().withId("two").build();
        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver(), webElementOne, webElementTwo);

        assertEquals(elementCollection.first().attr("id"), "one");
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void Getting_The_Last_Element_From_An_Empty_Collection_Should_Throw_Illegal_State_Exception() {
        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver());

        elementCollection.last();
        fail();
    }

    public void Getting_The_Last_Element_From_A_Collection_Should_Return_The_Last_Element() {
        final WebElement webElementOne = new WebElementMockBuilder().withId("one").build();
        final WebElement webElementTwo = new WebElementMockBuilder().withId("two").build();
        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver(), webElementOne, webElementTwo);

        assertEquals(elementCollection.last().attr("id"), "two");
    }


//    public void testLast() throws Exception {
//
//    }
//
//    public void testVal() throws Exception {
//
//    }


    public void Getting_Value_From_Empty_Collection_Should_Return_Null() {
        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver());

        assertNull(elementCollection.val());
    }

    public void Getting_Value_From_A_Collection_Should_Return_The_Value_Of_The_First_Element() {
        final WebElement webElementOne = new WebElementMockBuilder().withValue("one").build();
        final WebElement webElementTwo = new WebElementMockBuilder().withValue("two").build();
        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver(), webElementOne, webElementTwo);

        assertEquals(elementCollection.val(), "one");
    }

    public void Setting_Value_True_To_An_Unchecked_Checkable_Should_Make_It_Checked() {
        final WebElement webElementOne = new WebElementMockBuilder().build();
        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver(), webElementOne);

        elementCollection.val(true);
        verify(webElementOne).click();
    }

    public void Setting_Value_True_To_An_Already_Checked_Checkable_Should_Leave_It_Checked() {
        final WebElement webElement = new WebElementMockBuilder().isSelected().build();

        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver(), webElement);

        elementCollection.val(true);
        verify(webElement, times(2)).isSelected(); // Once when creating the mock and once when checking it
    }

    public void testSettingValueFalseToACheckedCheckboxShouldMakeItUnchecked() {
        Assert.assertTrue(false);
    }

    public void testSettingValueFalseToAnAlreadyUncheckedCheckboxShouldLeaveItUnchecked() {
        Assert.assertTrue(false);
    }

    @Test
    public void testCheckingNonCheckableElementShouldThrowIllegalStateException() {
        Assert.assertTrue(false);
    }
//
//    public void testValByIndex() throws Exception {
//
//    }
//
//    public void testValByVisibleText() throws Exception {
//
//    }
//
//    public void testVal() throws Exception {
//
//    }
//
//    public void testVal() throws Exception {
//
//    }
//
//    public void testAttr() throws Exception {
//
//    }
//
//    public void testVal() throws Exception {
//
//    }
//
//    public void testFindByName() throws Exception {
//
//    }
//
//    public void testFindByXpath() throws Exception {
//
//    }
//
//    public void testGetElements() throws Exception {
//
//    }
//

    public void No_Element_In_Collection_Should_Give_False() {
        final ElementCollection elementCollection = new ElementCollectionImpl(mockWebDriver(), null, Collections.EMPTY_LIST);
        assertFalse(elementCollection.isDisplayed());
    }

    public void When_At_Least_One_Element_In_Collection_Is_Not_Displayed_Should_Give_False() throws Exception {
        final WebElement webElementOne = new WebElementMockBuilder().isDisplayed().build();
        final WebElement webElementTwo = new WebElementMockBuilder().build();
        final ElementCollection elementCollection = new ElementCollectionImpl(mockWebDriver(), webElementOne, webElementTwo);
        assertFalse(elementCollection.isDisplayed());
    }

    public void When_All_Elements_Are_Displayed_In_Collection_Should_Give_True() throws Exception {
        final WebElement webElementOne = new WebElementMockBuilder().isDisplayed().build();
        final WebElement webElementTwo = new WebElementMockBuilder().isDisplayed().build();
        final ElementCollection elementCollection = new ElementCollectionImpl(mockWebDriver(), webElementOne, webElementTwo);
        assertTrue(elementCollection.isDisplayed());
    }

    public void Length_Is_Zero_When_No_Elements_In_Collection() throws Exception {
        final ElementCollection elementCollection = new ElementCollectionImpl(mockWebDriver());

        assertEquals(elementCollection.length(), 0);
    }

    public void Length_Is_Same_As_Number_Of_Elements_In_Collection() throws Exception {
        final WebElement webElementOne = new WebElementMockBuilder().build();
        final WebElement webElementTwo = new WebElementMockBuilder().build();
        final ElementCollection elementCollection = new ElementCollectionImpl(mockWebDriver(), webElementOne, webElementTwo);

        assertEquals(elementCollection.length(), 2);
    }

    private static WebDriver mockWebDriver() {
        return mock(WebDriver.class);
    }

    private class WebElementMockBuilder {
        private boolean isDisplayed;
        private String id;
        private final Map<String, String> attributes;
        private String text;
        private boolean isSelected;

        private WebElementMockBuilder() {
            attributes = Maps.newHashMap();
        }

        public WebElementMockBuilder isDisplayed() {
            isDisplayed = true;
            return this;
        }

        public WebElement build() {
            final WebElement webElement = mock(WebElement.class);
            // Is displayed
            if (isDisplayed) {
                when(webElement.isDisplayed()).thenReturn(true);
            }
            // Adding attributes
            for (Map.Entry<String, String> attribute : attributes.entrySet()) {
                when(webElement.getAttribute(attribute.getKey())).thenReturn(attribute.getValue());
            }
            // Set text
            if (text != null) {
                when(webElement.getText()).thenReturn(text);
            }

            when(webElement.isSelected()).thenReturn(isSelected);

            return webElement;
        }

        public WebElementMockBuilder withId(String id) {
            attributes.put("id", id);
            return this;
        }

        public WebElementMockBuilder withValue(String value) {
            text = value;
            return this;
        }

        public WebElementMockBuilder isSelected() {
            isSelected = true;
            return this;
        }
    }

    private class WebbElement implements WebElement {
        @Override
        public void click() {
        }

        @Override
        public void submit() {
        }

        @Override
        public void sendKeys(CharSequence... keysToSend) {
        }

        @Override
        public void clear() {
        }

        @Override
        public String getTagName() {
            throw new RuntimeException("Not impl");
        }

        @Override
        public String getAttribute(String name) {
            throw new RuntimeException("Not impl");
        }

        @Override
        public boolean isSelected() {
            throw new RuntimeException("Not impl");
        }

        @Override
        public boolean isEnabled() {
            throw new RuntimeException("Not impl");
        }

        @Override
        public String getText() {
            throw new RuntimeException("Not impl");
        }

        @Override
        public List<WebElement> findElements(By by) {
            throw new RuntimeException("Not impl");
        }

        @Override
        public WebElement findElement(By by) {
            throw new RuntimeException("Not impl");
        }

        @Override
        public boolean isDisplayed() {
            throw new RuntimeException("Not impl");
        }

        @Override
        public Point getLocation() {
            throw new RuntimeException("Not impl");
        }

        @Override
        public Dimension getSize() {
            throw new RuntimeException("Not impl");
        }

        @Override
        public String getCssValue(String propertyName) {
            throw new RuntimeException("Not impl");
        }
    }
}
