package com.elementcollection.collection.spy;

import com.beust.jcommander.internal.Maps;
import com.elementcollection.collection.ElementCollection;
import com.elementcollection.finder.ElementCollectionFinder;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

public class MySpy {
    private final Map<String, List<MethodSpy>> methodSpies;

    public MySpy() {
        methodSpies = Maps.newHashMap();
    }

    public void put(String method, MethodSpy spy) {
        if (!methodSpies.containsKey(method)) {
            methodSpies.put(method, Lists.<MethodSpy>newArrayList());
        }
        methodSpies.get(method).add(spy);
    }

    public List<MethodSpy> get(String method) {
        return methodSpies.get(method);
    }

    public ElementCollectionWithSpy spy(MethodExecutor<ElementCollection> methodExecutor) {
        MethodSpy methodSpy = new MethodSpy();
        methodSpy.start();
        ElementCollection elementCollection = methodExecutor.execute();
        methodSpy.end();
        put(methodExecutor.getMethodName(), methodSpy);
        return new ElementCollectionWithSpy(elementCollection, this);
    }

    public ElementCollectionFinderWithSpy spy(MethodExecutor<ElementCollectionFinder> methodExecutor) {
        MethodSpy methodSpy = new MethodSpy();
        methodSpy.start();
        ElementCollectionFinder elementCollection = methodExecutor.execute();
        methodSpy.end();
        put(methodExecutor.getMethodName(), methodSpy);
        return new ElementCollectionFinderWithSpy(elementCollection, this);
    }
}
