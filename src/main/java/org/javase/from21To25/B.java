package org.javase.from21To25;

import java.util.Objects;

public class B extends A {
    private final String a;

    public B(String a) {
        this.a = Objects.requireNonNull(a);
        super();
    }

    @Override
    String a() {
        return a;
    }

    static void main() {
        new B("b");
    }
}
