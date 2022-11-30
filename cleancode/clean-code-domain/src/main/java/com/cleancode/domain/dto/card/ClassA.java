package com.cleancode.domain.dto.card;

public class ClassA {
    private final ClassB classB;

    public ClassA(ClassB classB) {
        this.classB = classB;
    }

    public ClassB getClassB() {
        return classB;
    }


    @Override
    public String toString() {
        return "ClassA{" +
                "classB=" + classB +
                '}';
    }
}
