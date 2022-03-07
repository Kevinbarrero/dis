package homework.components;


import homework.annotations.Component;

@Component(name="test1")
public class TestComponent {
    public String getComponentInfo() {
        return "Hi from TestComponent";
    }
}
