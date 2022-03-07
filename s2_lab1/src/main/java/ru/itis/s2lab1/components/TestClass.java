package ru.itis.s2lab1.components;

import ru.itis.s2lab1.annotations.Inject;

public class TestClass {
    @Inject
    public TestComponent testComponent;
    public void print(){
        System.out.println(testComponent.getComponentInfo());
    }
}
