package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    @Test
    public void testGetName() {
        Person person = new Person("Me", 25);
        assertEquals("Me", person.getName());
    }

    @Test
    public void testSetAge() {
        Person person = new Person("You", 22);
        assertEquals(22, person.getAge());
    }

    @Test
    public void testIsAdult() {
        Person person = new Person("Stranger", 270000000);
        assertTrue(person.isAdult());
    }

    @Test
    public void testIsNotAdult() {
        Person person = new Person("Youngling", 1);
        assertFalse(person.isAdult());
    }

    @Test
    public void testSetName() {
        Person person = new Person("Me", 25);
        person.setName("Not me");
        assertEquals("Not me", person.getName());
    }

    @Test
    public void testIncrementAge() {
        Person person = new Person("He", 17);
        person.incrementAge();
        assertEquals(18, person.getAge());
    }
}
