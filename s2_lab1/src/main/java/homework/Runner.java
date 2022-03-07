package homework;


import homework.components.TestComponent;
import homework.test.Test;

public class Runner {

    public static void main(String[] args) {
        Context c = new Context(Runner.class);

        Test test = c.get(Test.class);

        test.print();
        test.hi("Kevin");
        TestComponent A = c.get(TestComponent.class);
        TestComponent B = test.testComponent;
        if(A.equals(B)){
            System.out.println(A + " is equals to " + B);
        }else
            System.out.println(A + "is not equals to " + B);

    }
}