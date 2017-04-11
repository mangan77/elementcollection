package com.elementcollection.util;

import javax.annotation.Nullable;

public interface Function<F, T> {

  @Nullable
  T apply(@Nullable F input);
}
