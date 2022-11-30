package com.cleancode.domain.dto.card;

public class ClassB {
    @Override
    public String toString() {
        return "ClassB{" +
                "classA=" + classA +
                '}';
    }

    private final ClassA classA;

    public ClassA getClassA() {
        return classA;
    }

    public ClassB(ClassA classA) {
        this.classA = classA;
    }
}
