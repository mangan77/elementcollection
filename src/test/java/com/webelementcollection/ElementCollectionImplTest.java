package com.webelementcollection;

import com.webelementcollection.mock.WebElementMock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.mockito.Mockito.mock;
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
    @Test(expectedExceptions = IllegalStateException.class)
    public void Clicking_An_Empty_Collection_Should_Throw_Illegal_Argument_Exception() {
        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver());
        elementCollection.click();
        fail();
    }

    public void When_More_Then_One_Element_In_Collection_All_Elements_Should_Get_Clicked() {
        final WebElementMock webElementOne = new WebElementMock();
        final WebElementMock webElementTwo = new WebElementMock();
        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver(), webElementOne, webElementTwo);

        elementCollection.click();
        assertTrue(webElementOne.wasClicked());
        assertTrue(webElementTwo.wasClicked());
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void Submitting_An_Empty_Collection_Should_Throw_Illegal_State_Exception() {
        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver());
        elementCollection.submit();
        fail();
    }

    public void When_More_Then_One_Element_In_Collection_All_Elements_Should_Get_Submitted() {
        final WebElementMock webElementOne = new WebElementMock();
        final WebElementMock webElementTwo = new WebElementMock();
        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver(), webElementOne, webElementTwo);

        elementCollection.submit();
        assertTrue(webElementOne.wasSubmitted());
        assertTrue(webElementTwo.wasSubmitted());
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void Getting_An_Element_From_An_Empty_Collection_Should_Throw_Illegal_State_Exception() {
        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver());

        elementCollection.get(0);
        fail();
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void Specifying_An_Index_Outside_The_Collection_Should_Throw_Illegal_State_Exception() {
        final WebElement webElementOne = new WebElementMock();
        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver(), webElementOne);

        elementCollection.get(1);
        fail();
    }

    public void Getting_An_Element_In_The_Collection_Should_Return_The_Correct_Element() {
        final WebElement webElementOne = new WebElementMock();
        final WebElement webElementTwo = new WebElementMock().withAttribute("id", "two");
        final WebElement webElementThree = new WebElementMock();
        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver(),
                webElementOne, webElementTwo, webElementThree);

        assertEquals(elementCollection.get(1).attr("id"), "two");
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void Getting_The_First_Element_From_An_Empty_Collection_Should_Throw_Illegal_State_Exception() {
        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver());

        elementCollection.first();
        fail();
    }

    public void Getting_The_First_Element_From_A_Collection_Should_Return_The_First_Element() {
        final WebElement webElementOne = new WebElementMock().withAttribute("id", "one");
        final WebElement webElementTwo = new WebElementMock().withAttribute("id", "two");
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
        final WebElement webElementOne = new WebElementMock().withAttribute("id", "one");
        final WebElement webElementTwo = new WebElementMock().withAttribute("id", "two");
        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver(), webElementOne, webElementTwo);

        assertEquals(elementCollection.last().attr("id"), "two");
    }

    public void Getting_Value_From_Empty_Collection_Should_Return_Null() {
        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver());

        assertNull(elementCollection.val());
    }

    public void Getting_Value_From_A_Collection_Should_Return_The_Value_Of_The_First_Element() {
        final WebElement webElementOne = new WebElementMock();
        webElementOne.sendKeys("one");
        final WebElement webElementTwo = new WebElementMock();
        webElementTwo.sendKeys("two");
        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver(), webElementOne, webElementTwo);

        assertEquals(elementCollection.val(), "one");
    }

    public void Setting_Value_True_To_An_Unchecked_Checkable_Should_Make_It_Checked() {
        final WebElement webElementOne = new WebElementMock().asCheckable().withTagName("input").withAttribute("type", "checkbox").asCheckable();
        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver(), webElementOne);

        elementCollection.val(true);
        assertTrue(webElementOne.isSelected());
    }

    public void Setting_Value_True_To_An_Already_Checked_Checkable_Should_Leave_It_Checked() {
        final WebElement webElement = new WebElementMock()
                .asCheckable()
                .withTagName("input")
                .withAttribute("type", "radio")
                .setSelected(true);

        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver(), webElement);

        elementCollection.val(true);
        assertTrue(webElement.isSelected());
    }

    public void Setting_Value_False_To_A_Checked_Checkbox_Should_Make_It_Unchecked() {
        final WebElement webElement = new WebElementMock().withTagName("option").setSelected(true).asCheckable();

        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver(), webElement);

        elementCollection.val(false);
        assertFalse(webElement.isSelected());
    }

    public void Setting_Value_False_To_An_Already_Unchecked_Checkbox_Should_Leave_It_Unchecked() {
        final WebElement webElement = new WebElementMock()
                .asCheckable()
                .withTagName("input")
                .withAttribute("type", "checkbox")
                .setSelected(false);

        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver(), webElement);

        elementCollection.val(false);
        assertFalse(webElement.isSelected());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void Checking_Non_Checkable_Element_Should_Throw_Illegal_Argument_Exception() {
        final WebElement webElement = new WebElementMock().withTagName("div");

        final ElementCollectionImpl elementCollection = new ElementCollectionImpl(mockWebDriver(), webElement);

        elementCollection.val(true);
        fail("A non clickable element should throw IllegalArgumentException");
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

    public void No_Element_In_Collection_Should_Give_False() {
        final ElementCollection elementCollection = new ElementCollectionImpl(mockWebDriver(), null, Collections.EMPTY_LIST);
        assertFalse(elementCollection.isDisplayed());
    }

    public void When_At_Least_One_Element_In_Collection_Is_Not_Displayed_Should_Give_False() throws Exception {
        final WebElement webElementOne = new WebElementMock().setDisplayed(true);
        final WebElement webElementTwo = new WebElementMock();
        final ElementCollection elementCollection = new ElementCollectionImpl(mockWebDriver(), webElementOne, webElementTwo);
        assertFalse(elementCollection.isDisplayed());
    }

    public void When_All_Elements_Are_Displayed_In_Collection_Should_Give_True() throws Exception {
        final WebElement webElementOne = new WebElementMock().setDisplayed(true);
        final WebElement webElementTwo = new WebElementMock().setDisplayed(true);
        final ElementCollection elementCollection = new ElementCollectionImpl(mockWebDriver(), webElementOne, webElementTwo);
        assertTrue(elementCollection.isDisplayed());
    }

    public void Length_Is_Zero_When_No_Elements_In_Collection() throws Exception {
        final ElementCollection elementCollection = new ElementCollectionImpl(mockWebDriver());

        assertEquals(elementCollection.length(), 0);
    }

    public void Length_Is_Same_As_Number_Of_Elements_In_Collection() throws Exception {
        final WebElement webElementOne = new WebElementMock();
        final WebElement webElementTwo = new WebElementMock();
        final ElementCollection elementCollection = new ElementCollectionImpl(mockWebDriver(), webElementOne, webElementTwo);

        assertEquals(elementCollection.length(), 2);
    }

    private static WebDriver mockWebDriver() {
        return mock(WebDriver.class);
    }
//*
}
