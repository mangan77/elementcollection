package com.elementcollection.adapter.util;

import java.util.ArrayList;
import java.util.List;

/**
 * <br> User: Mangan <br> Date: 11/11/14
 */
public class Lists {
    public static <F, T> List<T> applyFunction(List<F> fromList, Function<F, T> function) {
        if (function == null) throw new NullPointerException("Function can not be null");
        if (fromList == null || fromList.isEmpty()) return new ArrayList<>();

        List<T> toList = new ArrayList<>(fromList.size());
        for (F element : fromList) {
            toList.add((function.apply(element)));
        }
        return toList;
    }
}
