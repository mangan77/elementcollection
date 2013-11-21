package com.elementcollection;

import com.elementcollection.impl.ElementCollectionImpl;
import com.google.common.collect.Lists;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 * <br> User: Mangan <br> Date: 31/10/13
 */
@Test(groups = "unit")
public class ElementCollectionImplTest {


    public void testGetElementsOnEmptyCollectionShouldReturnEmptyCollection() {
        assertEquals(emptyElementCollection().getElements().size(), 0);
    }

    public void testGetElementsOnNonEmptyCollectionShouldReturnTheCorrectNumberOfElements() {
        final ElementCollection elementCollection = elementCollection(
                new WebElementMockBuilder().build(),
                new WebElementMockBuilder().build(),
                new WebElementMockBuilder().build()
        );
        assertEquals(elementCollection.getElements().size(), 3);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void Clicking_An_Empty_Collection_Should_Throw_Illegal_Argument_Exception() {
        final ElementCollection elementCollection = emptyElementCollection();
        elementCollection.click();
        fail();
    }

    public void When_More_Then_One_Element_In_Collection_All_Elements_Should_Get_Clicked() {
        final WebElement one = new WebElementMockBuilder().build();
        final WebElement two = new WebElementMockBuilder().build();

        elementCollection(one, two).click();
        verify(one).click();
        verify(two).click();
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void Submitting_An_Empty_Collection_Should_Throw_Illegal_State_Exception() {
        emptyElementCollection().submit();
    }

    public void When_More_Then_One_Element_In_Collection_All_Elements_Should_Get_Submitted() {
        final WebElement one = new WebElementMockBuilder().build();
        final WebElement two = new WebElementMockBuilder().build();

        elementCollection(one, two).submit();
        verify(one).submit();
        verify(two).submit();
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void Getting_An_Element_From_An_Empty_Collection_Should_Throw_Illegal_State_Exception() {
        emptyElementCollection().get(0);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void Specifying_An_Index_Outside_The_Collection_Should_Throw_Illegal_State_Exception() {
        elementCollection(new WebElementMockBuilder().build()).get(1);
    }

    public void Getting_An_Element_In_The_Collection_Should_Return_The_Correct_Element() {
        final WebElement one = new WebElementMockBuilder().build();
        final WebElement two = new WebElementMockBuilder().withAttribute("id", "two").build();
        final WebElement three = new WebElementMockBuilder().build();

        assertEquals(elementCollection(one, two, three).get(1).attr("id"), "two");
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void Getting_The_First_Element_From_An_Empty_Collection_Should_Throw_Illegal_State_Exception() {
        emptyElementCollection().first();
    }

    public void Getting_The_First_Element_From_A_Collection_Should_Return_The_First_Element() {
        final WebElement one = new WebElementMockBuilder().withAttribute("id", "one").build();
        final WebElement two = new WebElementMockBuilder().withAttribute("id", "two").build();

        assertEquals(elementCollection(one, two).first().attr("id"), "one");
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void Getting_The_Last_Element_From_An_Empty_Collection_Should_Throw_Illegal_State_Exception() {
        emptyElementCollection().last();
    }

    public void Getting_The_Last_Element_From_A_Collection_Should_Return_The_Last_Element() {
        final WebElement one = new WebElementMockBuilder().withAttribute("id", "one").build();
        final WebElement two = new WebElementMockBuilder().withAttribute("id", "two").build();

        assertEquals(elementCollection(one, two).last().attr("id"), "two");
    }

    public void Getting_Value_From_Empty_Collection_Should_Return_Null() {
        final ElementCollection elementCollection = emptyElementCollection();

        assertNull(elementCollection.val());
    }

    public void Getting_Value_From_A_Collection_Should_Return_The_Value_Of_The_First_Element() {
        final WebElement one = new WebElementMockBuilder().withText("one").build();
        final WebElement two = new WebElementMockBuilder().withText("two").build();

        assertEquals(elementCollection(one, two).val(), "one");
    }

    public void Setting_Value_True_To_An_Unchecked_Checkable_Should_Make_It_Checked() {
        final WebElement webElement = new WebElementMockBuilder().withTagName("input").withAttribute("type", "checkbox").build();
        final ElementCollection elementCollection = elementCollection(webElement);

        elementCollection.val(true);
        verify(webElement).click();
    }

    public void Setting_Value_True_To_An_Already_Checked_Checkable_Should_Leave_It_Checked() {
        final WebElement webElement = new WebElementMockBuilder()
                .withTagName("input")
                .withAttribute("type", "radio")
                .setSelected(true)
                .build();

        final ElementCollection elementCollection = elementCollection(webElement);

        elementCollection.val(true);
        verify(webElement, never()).click();
    }

    public void Setting_Value_False_To_A_Checked_Checkbox_Should_Make_It_Unchecked() {
        final WebElement webElement = new WebElementMockBuilder().withTagName("option").setSelected(true).build();

        elementCollection(webElement).val(false);
        verify(webElement).click();
    }

    public void Setting_Value_False_To_An_Already_Unchecked_Checkbox_Should_Leave_It_Unchecked() {
        final WebElement webElement = new WebElementMockBuilder()
                .withTagName("input")
                .withAttribute("type", "checkbox")
                .setSelected(false)
                .build();

        elementCollection(webElement).val(false);
        verify(webElement, never()).click();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void Checking_Non_Checkable_Element_Should_Throw_Illegal_Argument_Exception() {
        final WebElement webElement = new WebElementMockBuilder().withTagName("div").build();
        elementCollection(webElement).val(true);
    }

    public void When_At_Least_One_Element_In_Collection_Is_Not_Displayed_Should_Give_False() throws Exception {
        final WebElement webElementOne = new WebElementMockBuilder().isDisplayed(true).build();
        final WebElement webElementTwo = new WebElementMockBuilder().build();

        assertFalse(elementCollection(webElementOne, webElementTwo).isDisplayed());
    }

    public void No_Element_In_Collection_Should_Give_False() {
        assertFalse(emptyElementCollection().isDisplayed());
    }

    public void Find_On_Empty_Collection_Should_Return_Empty_Collection() {
        assertEquals(emptyElementCollection().find(".class").length(), 0);
    }

    public void Find_When_No_Elements_Can_Be_Found_Should_Return_Empty_Collection() {
        final WebElement one = new WebElementMockBuilder().finds(Lists.<WebElement>newArrayList(), By.cssSelector("something")).build();
        final WebElement two = new WebElementMockBuilder().finds(Lists.<WebElement>newArrayList(), By.cssSelector("something")).build();
        final WebElement three = new WebElementMockBuilder().finds(Lists.<WebElement>newArrayList(), By.cssSelector("something")).build();

        assertEquals(elementCollection(one, two, three).find("something").length(), 0);
    }

    public void Find_Elements_Should_Return_All_Found_Elements() {
        final WebElement one_a = new WebElementMockBuilder().finds(Lists.<WebElement>newArrayList(), By.cssSelector("something")).build();
        final WebElement one = new WebElementMockBuilder().finds(Lists.<WebElement>newArrayList(one_a), By.cssSelector("something")).build();
        final WebElement two = new WebElementMockBuilder().finds(Lists.<WebElement>newArrayList(), By.cssSelector("something")).build();
        final WebElement three = new WebElementMockBuilder().finds(Lists.<WebElement>newArrayList(), By.cssSelector("something")).build();

        assertEquals(elementCollection(one, two, three).find("something").length(), 1);
    }

    public void testWithinShouldReturnElementsWhenTheyGetFound() {
        final WebElement child = new WebElementMockBuilder().finds(Lists.<WebElement>newArrayList(), By.cssSelector("something")).build();
        final WebElement parent = new WebElementMockBuilder().finds(Lists.<WebElement>newArrayList(child), By.cssSelector("something"), 2).build();

        assertEquals(elementCollection(parent).within(3).find("something").length(), 1);
    }

    public void testWithinShouldReturnNoElementsWhenTheyDoNotAppearBeforeTimeOut() {
        final WebElement child = new WebElementMockBuilder().finds(Lists.<WebElement>newArrayList(), By.cssSelector("something")).build();
        final WebElement parent = new WebElementMockBuilder().finds(Lists.<WebElement>newArrayList(child), By.cssSelector("something"), 3).build();

        assertEquals(elementCollection(parent).within(2).find("something").length(), 0);
    }

    public void Setting_Integer_Value_To_A_Non_Select_Element_Should_Set_Value_To_Integer_Value() {
        final WebElement input = new WebElementMockBuilder().withTagName("input").isDisplayed(true).build();
        elementCollection(input).val(1);
        verify(input).sendKeys("1");
    }

    public void Setting_Value_To_A_Non_Select_Element_Should_Set_Value_To_Value() {
        final WebElement input = new WebElementMockBuilder().withTagName("input").isDisplayed(true).build();
        elementCollection(input).val("valluee");
        verify(input).sendKeys("valluee");
    }

    public void Setting_Value_On_A_Single_Select_Should_Select_The_Correct_Option() {
        final WebElement one = optionWithText("text").build();
        final WebElement two = optionWithText("text").build();

        final WebElement select = new WebElementMockBuilder()
                .withTagName("select")
                .finds(Lists.newArrayList(one, two), By.xpath(".//option[@value = \"text\"]"))
                .build();

        elementCollection(select).val("text");
        verify(one).click();
        verifyZeroInteractions(two);
    }

    public void Setting_Value_On_A_Multi_Select_Should_Select_All_The_Correct_Options() {
        final WebElement one = optionWithText("text").build();
        final WebElement two = optionWithText("text").build();

        final WebElement select = new WebElementMockBuilder()
                .withTagName("select")
                .finds(Lists.newArrayList(one, two), By.xpath(".//option[@value = \"text\"]"))
                .withAttribute("multiple", "true")
                .build();

        elementCollection(select).val("text");
        verify(one).click();
        verify(two).click();
    }

    public void Setting_Value_By_Index_On_A_Select_Should_Select_The_Correct_Option() {
        final WebElement zero = optionWithIndex("0").build();
        final WebElement one = optionWithIndex("1").build();
        final WebElement two = optionWithIndex("2").build();

        final WebElement select = new WebElementMockBuilder()
                .withTagName("select")
                .finds(Lists.newArrayList(zero, one, two), By.tagName("option"))
                .build();

        elementCollection(select).valByIndex(1);
        verify(one).click();
    }

    public void Setting_Value_By_Index_On_Non_Select_Element_Should_Set_Value_To_Value_Of_Index() {
        final WebElement input = new WebElementMockBuilder().withTagName("input").isDisplayed(true).build();
        elementCollection(input).valByIndex(2);
        verify(input).sendKeys("2");
    }

    public void Setting_Value_By_Visible_Text_On_Non_Select_Element_Should_Set_Value_Correctly() {
        final WebElement input = new WebElementMockBuilder().withTagName("input").isDisplayed(true).build();
        elementCollection(input).valByVisibleText("text");
        verify(input).sendKeys("text");
    }

    public void Setting_Value_By_Visible_Text_On_A_Single_Select_Should_Select_The_Correct_Option() {
        final WebElement one = optionWithText("text").build();
        final WebElement two = optionWithText("text").build();

        final WebElement select = new WebElementMockBuilder()
                .withTagName("select")
                .finds(Lists.newArrayList(one, two), By.xpath(".//option[normalize-space(.) = \"text\"]"))
                .build();

        elementCollection(select).valByVisibleText("text");
        verify(one).click();
        verifyZeroInteractions(two);
    }

    public void Setting_Value_By_Visible_Text_On_A_Multi_Select_Should_Select_All_The_Correct_Options() {
        final WebElement one = optionWithText("text").build();
        final WebElement two = optionWithText("text").build();

        final WebElement select = new WebElementMockBuilder()
                .withTagName("select")
                .finds(Lists.newArrayList(one, two), By.xpath(".//option[normalize-space(.) = \"text\"]"))
                .withAttribute("multiple", "true")
                .build();

        elementCollection(select).valByVisibleText("text");
        verify(one).click();
        verify(two).click();
    }

    private WebElementMockBuilder optionWithIndex(String index) {
        return new WebElementMockBuilder().withTagName("option").withAttribute("index", index);
    }

    private WebElementMockBuilder optionWithText(String text) {
        return new WebElementMockBuilder().withTagName("option").withText(text);
    }

    public void When_All_Elements_Are_Displayed_In_Collection_Should_Give_True() throws Exception {
        final WebElement one = new WebElementMockBuilder().isDisplayed(true).build();
        final WebElement two = new WebElementMockBuilder().isDisplayed(true).build();

        assertTrue(elementCollection(one, two).isDisplayed());
    }

    public void Length_Is_Zero_When_No_Elements_In_Collection() throws Exception {
        assertEquals(emptyElementCollection().length(), 0);
    }

    public void Length_Is_Same_As_Number_Of_Elements_In_Collection() throws Exception {
        final WebElement one = new WebElementMockBuilder().build();
        final WebElement two = new WebElementMockBuilder().build();

        assertEquals(elementCollection(one, two).length(), 2);
    }

    private ElementCollection emptyElementCollection() {
        return elementCollection();
    }

    private ElementCollection elementCollection(WebElement... webElement) {
        return new ElementCollectionImpl(webElement);
    }

}
