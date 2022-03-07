package homework.test;


import homework.annotations.Inject;
import homework.components.TestComponent;
import homework.components.TestComponent2;

public class Test{
    @Inject
    public TestComponent testComponent;
    @Inject
    public TestComponent2 testComponent2;

    public void print() {
        System.out.println(testComponent.getComponentInfo());
    }

    public void hi(String user) {
        testComponent2.driver(user);
    }
}
