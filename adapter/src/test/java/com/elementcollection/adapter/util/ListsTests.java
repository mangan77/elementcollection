package com.elementcollection.adapter.util;

import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static com.elementcollection.adapter.util.Lists.applyFunction;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * <br> User: Mangan <br> Date: 11/11/14
 */
@Test
public class ListsTests {

    public void When_Applying_Uppercase_Function_To_List_Then_All_Elements_In_List_Should_Be_Uppercase() {
        Function<String, String> function = uppercaseFunction();

        List<String> appliedList = applyFunction(simpleStringList(), function);
        assertThat(appliedList).hasSize(3);
        assertThat(appliedList.get(0)).isEqualTo("STRING1");
        assertThat(appliedList.get(1)).isEqualTo("STRING2");
        assertThat(appliedList.get(2)).isEqualTo("STRING3");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void When_Function_Is_Null_Applying_Function_Should_Throw_NPE() {
        try {
            applyFunction(simpleStringList(), null);
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).isEqualTo("Function can not be null");
            throw e;
        }
    }

    public void When_List_Is_Null_Applying_Function_Should_Return_Empty_List() {
        assertThat(applyFunction(null, uppercaseFunction())).hasSize(0);
    }

    public void When_List_Is_Empty_Applying_Function_Should_Return_Empty_List() {
        assertThat(applyFunction(new ArrayList<String>(), uppercaseFunction())).hasSize(0);
    }

    private static List<String> simpleStringList() {
        List<String> list = new ArrayList<String>();
        list.add("string1");
        list.add("string2");
        list.add("string3");
        return list;
    }

    private static Function<String, String> uppercaseFunction() {
        return new Function<String, String>() {

            @Nullable
            @Override
            public String apply(@Nullable String input) {
                return input.toUpperCase();
            }
        };
    }

}
