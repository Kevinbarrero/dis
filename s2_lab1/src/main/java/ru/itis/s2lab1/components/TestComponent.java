package ru.itis.s2lab1.components;

import ru.itis.s2lab1.annotations.Component;

@Component(name= "Test")
public class TestComponent {
    public String getComponentInfo(){
        return "Hello from TestComponent";
    }
}
