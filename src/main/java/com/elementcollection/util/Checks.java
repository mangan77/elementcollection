package com.elementcollection.util;

import javax.annotation.Nullable;

public class Checks {

  public static <T> T checkNotNull(T arg) {
    return checkNotNull(arg, null);
  }

  public static <T> T checkNotNull(T arg, @Nullable String msg) {
    if (arg != null) {
      return arg;
    }
    if (msg != null) {
      throw new NullPointerException(msg);
    } else {
      throw new NullPointerException();
    }
  }

  public static void checkState(boolean expression, String msg) {
    if (!expression) {
      throw new IllegalStateException(msg);
    }
  }

  public static void checkArgument(boolean expression) {
    checkArgument(expression, null);
  }

  public static void checkArgument(boolean expression, @Nullable String msg) {
    if (!expression) {
      if (msg != null) {
        throw new IllegalArgumentException(msg);
      } else {
        throw new IllegalArgumentException();
      }
    }
  }
}
