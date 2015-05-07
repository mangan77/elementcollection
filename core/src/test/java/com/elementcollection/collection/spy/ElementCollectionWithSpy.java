package com.elementcollection.collection.spy;

import com.elementcollection.collection.ElementCollection;

import java.util.List;

public class ElementCollectionWithSpy extends ElementCollectionFinderWithSpy implements ElementCollection {

    public ElementCollectionWithSpy(ElementCollection delegate, MySpy spy) {
        super(delegate, spy);
    }

    @Override
    public ElementCollection click() {
        return getSpy().collectionSpy(new MethodExecutor<ElementCollection>("click") {
            @Override
            public ElementCollection execute() {
                return castDelegate().click();
            }
        });
    }

    private ElementCollection castDelegate() {
        return (ElementCollection) getDelegate();
    }

    @Override
    public ElementCollection submit() {
        return castDelegate().submit();
    }

    @Override
    public ElementCollection get(int index) {
        return castDelegate().get(index);
    }

    @Override
    public ElementCollection first() {
        return castDelegate().first();
    }

    @Override
    public ElementCollection last() {
        return castDelegate().last();
    }

    @Override
    public ElementCollection val(String value) {
        return castDelegate().val(value);
    }

    @Override
    public ElementCollection val(boolean value) {
        return castDelegate().val(value);
    }

    @Override
    public List<ElementCollection> getElements() {
        return castDelegate().getElements();
    }

    @Override
    public ElementCollection val(int value) {
        return castDelegate().val(value);
    }

    @Override
    public String val() {
        return castDelegate().val();
    }

    @Override
    public String attr(String name) {
        return castDelegate().attr(name);
    }

    @Override
    public int length() {
        return castDelegate().length();
    }

    @Override
    public boolean isEmpty() {
        return castDelegate().isEmpty();
    }

    @Override
    public boolean isNotEmpty() {
        return castDelegate().isNotEmpty();
    }

    @Override
    public boolean hasElements() {
        return castDelegate().hasElements();
    }

    @Override
    public ElementCollection valByIndex(int index) {
        return castDelegate().valByIndex(index);
    }

    @Override
    public boolean isDisplayed() {
        return castDelegate().isDisplayed();
    }

    @Override
    public ElementCollection valByVisibleText(String text) {
        return castDelegate().valByVisibleText(text);
    }

    @Override
    public boolean hasClass(String cssClass) {
        return castDelegate().hasClass(cssClass);
    }
}
