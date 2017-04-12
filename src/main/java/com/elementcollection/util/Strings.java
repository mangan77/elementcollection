package com.elementcollection.util;

import java.util.List;

public class Strings {

  private static final String EMPTY = "";
  private static final char WHITESPACE = ' ';

  public static String trimToEmpty(String str) {
    return str == null ? EMPTY : str.trim();
  }

  public static String[] split(String str) {
    if (str == null) return null;
    if (str.length() == 0) return new String[0];

    List<String> parts = Lists.newList();
    String part = "";
    for (char c : str.toCharArray()) {
      if (WHITESPACE == c) {
        if (part.length() > 0) {
          parts.add(part);
          part = "";
        }
      } else {
        part += c;
      }
    }

    if (part.length() > 0) {
      parts.add(part);
    }

    return parts.toArray(new String[parts.size()]);
  }
}
