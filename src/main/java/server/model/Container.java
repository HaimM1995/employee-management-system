package server.model;

import java.util.ArrayList;

public class Container<T extends Number> {
    T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String show() {
        return this.value.getClass().getName();
    }

    public void demo(ArrayList<? extends T>obj){

    }

    public static void main(String[] args) {
        Container<Integer> e = new Container<>();
        e.setValue(1);
        System.out.println(e.show());

        e.demo(new ArrayList<Integer>());
    }
}
