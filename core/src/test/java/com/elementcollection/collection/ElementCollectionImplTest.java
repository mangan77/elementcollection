package com.elementcollection.collection;

import com.elementcollection.ElementMockBuilder;
import com.elementcollection.collection.spy.ElementCollectionWithSpy;
import com.elementcollection.collection.spy.MySpy;
import com.elementcollection.context.FindContexts;
import com.elementcollection.element.Element;
import com.google.common.collect.Lists;
import org.testng.annotations.Test;

import static com.elementcollection.type.TimeUnit.secs;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 * <br> User: Mangan <br> Date: 31/10/13
 */
@Test(groups = "unit")
public class ElementCollectionImplTest {


    public void Get_Elements_On_Empty_Collection_Should_Return_Empty_Collection() {
        assertEquals(emptyElementCollection().getElements().size(), 0);
    }

    public void Get_Elements_On_Non_Empty_Collection_Should_Return_The_Correct_Number_Of_Elements() {
        final ElementCollection elementCollection = elementCollection(
                new ElementMockBuilder().build(),
                new ElementMockBuilder().build(),
                new ElementMockBuilder().build()
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
        final Element one = new ElementMockBuilder().build();
        final Element two = new ElementMockBuilder().build();

        elementCollection(one, two).click();
        verify(one).click();
        verify(two).click();
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void Submitting_An_Empty_Collection_Should_Throw_Illegal_State_Exception() {
        emptyElementCollection().submit();
    }

    public void When_More_Then_One_Element_In_Collection_All_Elements_Should_Get_Submitted() {
        final Element one = new ElementMockBuilder().build();
        final Element two = new ElementMockBuilder().build();

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
        elementCollection(new ElementMockBuilder().build()).get(1);
    }

    public void Getting_An_Element_In_The_Collection_Should_Return_The_Correct_Element() {
        final Element one = new ElementMockBuilder().build();
        final Element two = new ElementMockBuilder().withAttribute("id", "two").build();
        final Element three = new ElementMockBuilder().build();

        assertEquals(elementCollection(one, two, three).get(1).attr("id"), "two");
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void Getting_The_First_Element_From_An_Empty_Collection_Should_Throw_Illegal_State_Exception() {
        emptyElementCollection().first();
    }

    public void Getting_The_First_Element_From_A_Collection_Should_Return_The_First_Element() {
        final Element one = new ElementMockBuilder().withAttribute("id", "one").build();
        final Element two = new ElementMockBuilder().withAttribute("id", "two").build();

        assertEquals(elementCollection(one, two).first().attr("id"), "one");
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void Getting_The_Last_Element_From_An_Empty_Collection_Should_Throw_Illegal_State_Exception() {
        emptyElementCollection().last();
    }

    public void Getting_The_Last_Element_From_A_Collection_Should_Return_The_Last_Element() {
        final Element one = new ElementMockBuilder().withAttribute("id", "one").build();
        final Element two = new ElementMockBuilder().withAttribute("id", "two").build();

        assertEquals(elementCollection(one, two).last().attr("id"), "two");
    }

    public void Getting_Value_From_Empty_Collection_Should_Return_Null() {
        final ElementCollection elementCollection = emptyElementCollection();

        assertNull(elementCollection.val());
    }

    public void Getting_Value_From_A_Collection_Should_Return_The_Value_Of_The_First_Element() {
        final Element one = new ElementMockBuilder().withText("one").build();
        final Element two = new ElementMockBuilder().withText("two").build();

        assertEquals(elementCollection(one, two).val(), "one");
    }

    public void Setting_Value_True_To_An_Unchecked_Checkable_Should_Make_It_Checked() {
        final Element element = new ElementMockBuilder().withTagName("input").withAttribute("type", "checkbox").build();
        final ElementCollection elementCollection = elementCollection(element);

        elementCollection.val(true);
        verify(element).click();
    }

    public void Setting_Value_True_To_An_Already_Checked_Checkable_Should_Leave_It_Checked() {
        final Element element = new ElementMockBuilder()
                .withTagName("input")
                .withAttribute("type", "radio")
                .setSelected(true)
                .build();

        final ElementCollection elementCollection = elementCollection(element);

        elementCollection.val(true);
        verify(element, never()).click();
    }

    public void Setting_Value_False_To_A_Checked_Checkbox_Should_Make_It_Unchecked() {
        final Element element = new ElementMockBuilder().withTagName("option").setSelected(true).build();

        elementCollection(element).val(false);
        verify(element).click();
    }

    public void Setting_Value_False_To_An_Already_Unchecked_Checkbox_Should_Leave_It_Unchecked() {
        final Element element = new ElementMockBuilder()
                .withTagName("input")
                .withAttribute("type", "checkbox")
                .setSelected(false)
                .build();

        elementCollection(element).val(false);
        verify(element, never()).click();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void Checking_Non_Checkable_Element_Should_Throw_Illegal_Argument_Exception() {
        final Element element = new ElementMockBuilder().withTagName("div").build();
        elementCollection(element).val(true);
    }

    public void When_At_Least_One_Element_In_Collection_Is_Not_Displayed_Should_Give_False() throws Exception {
        final Element elementOne = new ElementMockBuilder().isDisplayed(true).build();
        final Element elementTwo = new ElementMockBuilder().build();

        assertFalse(elementCollection(elementOne, elementTwo).isDisplayed());
    }

    public void No_Element_In_Collection_Should_Give_False() {
        assertFalse(emptyElementCollection().isDisplayed());
    }

    public void Find_On_Empty_Collection_Should_Return_Empty_Collection() {
        assertEquals(emptyElementCollection().find(".class").length(), 0);
    }

    public void Find_When_No_Elements_Can_Be_Found_Should_Return_Empty_Collection() {
        final Element one = new ElementMockBuilder().finds(Lists.<Element>newArrayList(), "something").build();
        final Element two = new ElementMockBuilder().finds(Lists.<Element>newArrayList(), "something").build();
        final Element three = new ElementMockBuilder().finds(Lists.<Element>newArrayList(), "something").build();

        assertEquals(elementCollection(one, two, three).find("something").length(), 0);
    }

    public void Find_Elements_Should_Return_All_Found_Elements() {
        final Element one_a = new ElementMockBuilder().finds(Lists.<Element>newArrayList(), "something").build();
        final Element one = new ElementMockBuilder().finds(Lists.newArrayList(one_a), "something").build();
        final Element two = new ElementMockBuilder().finds(Lists.<Element>newArrayList(), "something").build();
        final Element three = new ElementMockBuilder().finds(Lists.<Element>newArrayList(), "something").build();

        assertEquals(elementCollection(one, two, three).find("something").length(), 1);
    }

    public void Within_Should_Return_Elements_When_They_Get_Found() {
        final Element child = new ElementMockBuilder().finds(Lists.<Element>newArrayList(), "something").build();
        final Element parent = new ElementMockBuilder().finds(Lists.<Element>newArrayList(child), "something", 2).build();

        assertEquals(elementCollection(parent).within(secs(3)).find("something").length(), 1);
    }

    public void Within_Should_Return_No_Elements_When_They_Do_Not_Appear_Before_Time_Out() {
        final Element child = new ElementMockBuilder().finds(Lists.<Element>newArrayList(), "something").build();
        final Element parent = new ElementMockBuilder().finds(Lists.<Element>newArrayList(child), "something", 3).build();

        ElementCollectionWithSpy elementCollectionWithSpy = elementCollection(parent);
        assertEquals(elementCollectionWithSpy.within(secs(2)).find("something").length(), 0);
    }

    public void Waiting_Before_Find_Should_Call_Find_Method_After_Wait_Time_And_Not_Before() {
        ElementCollectionWithSpy elementCollection = elementCollection(mock(Element.class));
        elementCollection.wait(secs(2)).find("something");

        assertEquals(elementCollection.getSpy().get("find").size(), 1);
        assertTrue(elementCollection.getSpy().get("find").get(0).getDuration() > 1999l, "Duration : " + elementCollection.getSpy().get("find").get(0).getDuration() + " ms");
    }

    public void Setting_Integer_Value_To_A_Non_Select_Element_Should_Set_Value_To_Integer_Value() {
        final Element input = new ElementMockBuilder().withTagName("input").isDisplayed(true).build();
        elementCollection(input).val(1);
        verify(input).sendKeys("1");
    }

    public void Setting_Value_To_A_Non_Select_Element_Should_Set_Value_To_Value() {
        final Element input = new ElementMockBuilder().withTagName("input").isDisplayed(true).build();
        elementCollection(input).val("valluee");
        verify(input).sendKeys("valluee");
    }

    public void Setting_Value_On_A_Single_Select_Should_Select_The_Correct_Option() {
        final Element one = optionWithValue("text").build();
        final Element two = optionWithValue("text").build();

        final Element select = singleSelect(one, two);

        elementCollection(select).val("text");
        verify(one).click();
        verifyZeroInteractions(two);
    }

    public void Setting_Value_On_A_Multi_Select_Should_Select_All_The_Correct_Options() {
        final Element one = optionWithValue("text").build();
        final Element two = optionWithValue("text").build();

        final Element select = multiSelect(one, two);

        elementCollection(select).val("text");
        verify(one).click();
        verify(two).click();
    }

    public void Setting_Value_By_Index_On_A_Select_Should_Select_The_Correct_Option() {
        final Element zero = optionWithIndex("0").build();
        final Element one = optionWithIndex("1").build();
        final Element two = optionWithIndex("2").build();

        final Element select = singleSelect(zero, one, two);

        elementCollection(select).valByIndex(1);
        verify(one).click();
    }


    public void Setting_Value_By_Index_On_Non_Select_Element_Should_Set_Value_To_Value_Of_Index() {
        final Element input = new ElementMockBuilder().withTagName("input").isDisplayed(true).build();
        elementCollection(input).valByIndex(2);
        verify(input).sendKeys("2");
    }

    public void Setting_Value_By_Visible_Text_On_Non_Select_Element_Should_Set_Value_Correctly() {
        final Element input = new ElementMockBuilder().withTagName("input").isDisplayed(true).build();
        elementCollection(input).valByVisibleText("text");
        verify(input).sendKeys("text");
    }

    public void Setting_Value_By_Visible_Text_On_A_Single_Select_Should_Select_The_Correct_Option() {
        final Element one = optionWithText("text").build();
        final Element two = optionWithText("text").build();

        final Element select = singleSelect(one, two);

        elementCollection(select).valByVisibleText("text");
        verify(one).click();
        verifyZeroInteractions(two);
    }

    public void Setting_Value_By_Visible_Text_On_A_Multi_Select_Should_Select_All_The_Correct_Options() {
        final Element one = optionWithText("text").build();
        final Element two = optionWithText("text").build();

        final Element select = multiSelect(one, two);

        elementCollection(select).valByVisibleText("text");
        verify(one).click();
        verify(two).click();
    }

    private ElementMockBuilder optionWithIndex(String index) {
        return new ElementMockBuilder().withTagName("option").withAttribute("index", index);
    }

    private ElementMockBuilder optionWithText(String text) {
        return new ElementMockBuilder().withTagName("option").withText(text);
    }

    private ElementMockBuilder optionWithValue(String value) {
        return new ElementMockBuilder().withTagName("option").withAttribute("value", value);
    }

    private Element singleSelect(Element... elements) {
        return new ElementMockBuilder()
                .withTagName("select")
                .finds(Lists.newArrayList(elements), "option")
                .build();
    }

    private Element multiSelect(Element... elements) {
        return new ElementMockBuilder()
                .withTagName("select")
                .finds(Lists.newArrayList(elements), "option")
                .withAttribute("multiple", "true")
                .build();
    }

    public void When_All_Elements_Are_Displayed_In_Collection_Should_Give_True() throws Exception {
        final Element one = new ElementMockBuilder().isDisplayed(true).build();
        final Element two = new ElementMockBuilder().isDisplayed(true).build();

        assertTrue(elementCollection(one, two).isDisplayed());
    }

    public void When_No_Elements_In_Collection_Is_Empty_Should_Return_True() throws Exception {
        assertTrue(emptyElementCollection().isEmpty());
    }

    public void When_Elements_In_Collection_Is_Empty_Should_Return_False() throws Exception {
        final Element one = new ElementMockBuilder().build();

        assertFalse(elementCollection(one).isEmpty());
    }

    public void When_No_Elements_In_Collection_Has_Elements_Should_Return_False() throws Exception {
        assertFalse(emptyElementCollection().hasElements());
    }

    public void When_Elements_In_Collection_Has_Elements_Should_Return_True() throws Exception {
        final Element one = new ElementMockBuilder().build();
        assertTrue(elementCollection(one).hasElements());
    }

    public void Length_Is_Zero_When_No_Elements_In_Collection() throws Exception {
        assertEquals(emptyElementCollection().length(), 0);
    }

    public void Length_Is_Same_As_Number_Of_Elements_In_Collection() throws Exception {
        final Element one = new ElementMockBuilder().build();
        final Element two = new ElementMockBuilder().build();

        assertEquals(elementCollection(one, two).length(), 2);
    }

    private ElementCollection emptyElementCollection() {
        return elementCollection();
    }

    private ElementCollectionWithSpy elementCollection(Element... elements) {
        return new ElementCollectionWithSpy(new ElementCollectionImpl(FindContexts.immediate(), null, Lists.newArrayList(elements)), new MySpy());
    }

}
