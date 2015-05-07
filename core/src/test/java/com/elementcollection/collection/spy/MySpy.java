package com.elementcollection.collection.spy;

import com.beust.jcommander.internal.Maps;
import com.elementcollection.collection.ElementCollection;
import com.elementcollection.finder.ElementCollectionFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySpy {
    private final Map<String, List<MethodSpy>> methodSpies;

    public MySpy() {
        methodSpies = Maps.newHashMap();
    }

    public void put(String method, MethodSpy spy) {
        if (!methodSpies.containsKey(method)) {
            methodSpies.put(method, new ArrayList<MethodSpy>());
        }
        methodSpies.get(method).add(spy);
    }

    public List<MethodSpy> get(String method) {
        return methodSpies.get(method);
    }

    public ElementCollectionWithSpy collectionSpy(MethodExecutor<ElementCollection> methodExecutor) {
        MethodSpy methodSpy = new MethodSpy();
        methodSpy.start();
        ElementCollection elementCollection = methodExecutor.execute();
        methodSpy.end();
        put(methodExecutor.getMethodName(), methodSpy);
        return new ElementCollectionWithSpy(elementCollection, this);
    }

    public ElementCollectionFinderWithSpy finderSpy(MethodExecutor<ElementCollectionFinder> methodExecutor) {
        MethodSpy methodSpy = new MethodSpy();
        methodSpy.start();
        ElementCollectionFinder elementCollection = methodExecutor.execute();
        methodSpy.end();
        put(methodExecutor.getMethodName(), methodSpy);
        return new ElementCollectionFinderWithSpy(elementCollection, this);
    }
}
