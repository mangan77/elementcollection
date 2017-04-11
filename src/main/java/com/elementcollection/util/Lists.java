package com.elementcollection.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class Lists {

  public static <T> List<T> newList(T... elements) {
    List<T> list = new ArrayList<>(elements.length);
    for (T element : elements) {
      list.add(element);
    }
    return list;
  }

  @Nullable
  public static <T> T getFirst(List<T> list) {
    Checks.checkNotNull(list);
    return list.isEmpty() ? null : list.get(0);
  }

  @Nullable
  public static <T> T getLast(List<T> list) {
    Checks.checkNotNull(list);
    return list.isEmpty() ? null : list.get(list.size() - 1);
  }
}
