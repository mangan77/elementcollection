package com.elementcollection.util;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Test(groups = "unit")
public class StringsTest {

  @Test
  public void WhenNullStringIsTrimmedThenEmptyStringShouldBeReturned() {
    String actual = Strings.trimToEmpty(null);

    assertThat(actual).isEmpty();
  }

  @Test
  public void WhenEmptyStringIsTrimmedThenEmptyStringShouldBeReturned() {
    String actual = Strings.trimToEmpty("");

    assertThat(actual).isEmpty();
  }

  @Test
  public void WhenWhiteSpaceStringIsTrimmedThenEmptyStringShouldBeReturned() {
    String actual = Strings.trimToEmpty("       ");

    assertThat(actual).isEmpty();
  }

  @Test
  public void WhenStringStartsWithWhiteSpaceIsTrimmedThenStringWithoutWhiteSpaceShouldBeReturned() {
    String actual = Strings.trimToEmpty("       bupp");

    assertThat(actual).isEqualTo("bupp");
  }

  @Test
  public void WhenEndsStartsWithWhiteSpaceIsTrimmedThenStringWithoutWhiteSpaceShouldBeReturned() {
    String actual = Strings.trimToEmpty("bupp       ");

    assertThat(actual).isEqualTo("bupp");
  }

  @Test
  public void WhenStringWithWhitespaceIsTrimmedThenStringShouldStillContainWhitespace() {
    String actual = Strings.trimToEmpty("bupp bipp");

    assertThat(actual).isEqualTo("bupp bipp");
  }

  @Test
  public void WhenSplittingNullStringThenNullShouldBeReturned() {
    String[] actual = Strings.split(null);

    assertThat(actual).isNull();
  }

  @Test
  public void WhenSplittingEmptyStringThenEmptyArrayShouldBeReturned() {
    String[] actual = Strings.split("");

    assertThat(actual).hasSize(0);
  }

  @Test
  public void WhenSplittingWhitespaceStringThenEmptyArrayShouldBeReturned() {
    String[] actual = Strings.split("        ");

    assertThat(actual).hasSize(0);
  }

  @Test
  public void WhenSplittingStringWithoutWhitespaceThenArrayWithTheSameStringShouldBeReturned() {
    String[] actual = Strings.split("buppbipp");

    assertThat(actual)
      .hasSize(1)
      .containsExactly("buppbipp");
  }

  @Test
  public void WhenSplittingStringThenArrayWithStringsShouldBeReturned() {
    String[] actual = Strings.split(" bupp bipp      ");

    assertThat(actual)
      .hasSize(2)
      .containsExactly("bupp", "bipp");
  }
}
