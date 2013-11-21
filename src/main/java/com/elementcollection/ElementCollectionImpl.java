package com.elementcollection;

import com.elementcollection.executors.Executor;
import com.elementcollection.executors.ValidatableReturnValue;
import com.elementcollection.executors.WithinExecutor;
import com.elementcollection.executors.functions.*;
import com.elementcollection.executors.supplier.ElementSupplier;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@ParametersAreNonnullByDefault
public class ElementCollectionImpl implements ElementCollection {

    private final List<WebElement> webElements;
    private final String selectorString;

    private Executor executor;

    private ElementCollectionImpl(@Nullable String selectorString, Executor executor, List<WebElement> webElements) {
        this.webElements = checkNotNull(webElements, "webElements");
        this.selectorString = selectorString;
        this.executor = executor;
    }

    public ElementCollectionImpl(@Nullable final String selectorString, final List<WebElement> webElements) {
        this(selectorString, new Executor(), webElements);
    }

    public ElementCollectionImpl(@Nullable final String selectorString, final WebElement... webElements) {
        this(selectorString, Lists.newArrayList(checkNotNull(webElements)));
    }

    public ElementCollectionImpl(final WebElement... webElements) {
        this(null, Lists.newArrayList(checkNotNull(webElements)));
    }

    @Override
    public ElementCollection find(String cssSelector) {
        return new ElementCollectionImpl(cssSelector, new Executor(), executeWithMultiple(new Find(cssSelector)));
    }

    private List<WebElement> executeWithMultiple(Function<List<WebElement>, ValidatableReturnValue<List<WebElement>>> operation) {
        return executor.execute(operation, ElementSupplier.multiple(webElements));
    }

    @Override
    public ElementCollection click() {
        return new ElementCollectionImpl(selectorString, new Executor(), executeWithMultiple(new Click()));
    }

    @Override
    public ElementCollection submit() {
        return new ElementCollectionImpl(selectorString, new Executor(), executeWithMultiple(new Submit()));
    }

    @Override
    public ElementCollection get(final int index) {
        return new ElementCollectionImpl(selectorString, new Executor(), executeWithMultiple(new Get(index, selectorString)));
    }

    @Override
    public ElementCollection first() {
        return new ElementCollectionImpl(selectorString, new Executor(), executeWithMultiple(new First()));
    }

    @Override
    public ElementCollection last() {
        return new ElementCollectionImpl(selectorString, new Executor(), executeWithMultiple(new Last()));
    }

    @Override
    public ElementCollection val(final String value) {
        return new ElementCollectionImpl(selectorString, new Executor(), executeWithMultiple(SetVal.forValue(value)));
    }

    @Override
    public ElementCollection valByIndex(final int index) {
        return new ElementCollectionImpl(selectorString, new Executor(), executeWithMultiple(SetVal.forIndex(index)));
    }

    @Override
    public ElementCollection valByVisibleText(final String text) {
        return new ElementCollectionImpl(selectorString, new Executor(), executeWithMultiple(SetVal.forVisibleValue(text)));
    }

    @Override
    public String val() {
        return executor.execute(new GetVal(), ElementSupplier.multiple(webElements));
    }

    @Override
    public String attr(final String name) {
        return executor.execute(new Attr(name), ElementSupplier.multiple(webElements));
    }

    @Override
    public ElementCollection val(final boolean value) {
        return new ElementCollectionImpl(selectorString, new Executor(), executeWithMultiple(new SetBooleanVal(value)));
    }

    @Override
    public List<ElementCollection> getElements() {
        List<ElementCollection> elements = Lists.newArrayList();
        for (WebElement webElement : webElements) {
            elements.add(new ElementCollectionImpl(selectorString, new Executor(), Lists.newArrayList(webElement)));
        }
        return elements;
    }

    @Override
    public boolean isDisplayed() {
        if (webElements.isEmpty()) {
            return false;
        }
        for (WebElement webElement : webElements) {
            if (!webElement.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int length() {
        return webElements.size();
    }

    @Override
    public ElementCollection val(final int value) {
        return val(String.valueOf(value));
    }

    @Override
    public ElementCollection within(int secs) {
        return new ElementCollectionImpl(selectorString, new WithinExecutor(secs), webElements);
    }

}
