package com.example.demo.decorator;

@TestClassDecorator(port = 9811)
public class TestClass extends ArcBaseServer {

    public static void main(String[] args) {
        TestClass c = new TestClass();
        c.boost(TestClass.class);
    }

}
