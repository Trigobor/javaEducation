package org.example;

public class Person {

    private String name;
    private int age;

    // Конструктор
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Геттеры
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void incrementAge() {
        age++;
    }

    // Метод для проверки совершеннолетия
    public boolean isAdult() {
        return age >= 18;
    }
}