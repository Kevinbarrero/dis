package homework.components;

import homework.annotations.Component;

@Component(name = "test2")
public class TestComponent2 {

    public void driver(String name) {
        System.out.println("hi " + name + "! from TestComponent2");
    }

}
