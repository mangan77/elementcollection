package com.elementcollection.collection.spy;

import com.elementcollection.collection.ElementCollection;
import com.elementcollection.finder.ElementCollectionFinder;
import com.elementcollection.type.TimeUnit;

public class ElementCollectionFinderWithSpy implements ElementCollectionFinder {

    private final ElementCollectionFinder delegate;
    private final MySpy spy;

    public ElementCollectionFinderWithSpy(ElementCollectionFinder delegate, MySpy spy) {
        this.delegate = delegate;
        this.spy = spy;
    }

    public ElementCollectionFinder getDelegate() {
        return delegate;
    }

    public MySpy getSpy() {
        return spy;
    }

    @Override
    public ElementCollection find(final String cssSelector) {
        return spy.spy(new MethodExecutor<ElementCollection>("find") {
            @Override
            public ElementCollection execute() {
                return delegate.find(cssSelector);
            }
        });
    }

    @Override
    public ElementCollectionFinder within(final TimeUnit delay) {
        return spy.spy(new MethodExecutor<ElementCollectionFinder>("within") {
            @Override
            public ElementCollectionFinder execute() {
                return delegate.within(delay);
            }
        });
    }

    @Override
    public ElementCollectionFinder wait(final TimeUnit delay) {
        return spy.spy(new MethodExecutor<ElementCollectionFinder>("wait") {
            @Override
            public ElementCollectionFinder execute() {
                return delegate.wait(delay);
            }
        });
    }
}

