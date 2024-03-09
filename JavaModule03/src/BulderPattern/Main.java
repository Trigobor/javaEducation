package BulderPattern;

public class Main {
    public static void main(String[] args) {
        Pizza pizza = new Pizza.Backer()
                .sauce(Sauce.TOMATO)
                .pepperoni(true)
                .bacon(true)
                .jalapenoPepper(true)
                .bake();
    }
}
