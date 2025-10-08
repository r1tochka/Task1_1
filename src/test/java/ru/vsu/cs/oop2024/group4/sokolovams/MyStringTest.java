package ru.vsu.cs.oop2024.group4.sokolovams;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyStringTest {

    @Test
    void split() {
        MyString str = new MyString("a,b,c");
        MyString[] result = str.split(',', false);

        assertEquals(3, result.length);
        assertEquals("a", result[0].toString());
        assertEquals("b", result[1].toString());
        assertEquals("c", result[2].toString());
    }

    @Test
    void join() {
        MyString[] array = {
                new MyString("a"),
                new MyString("b"),
                new MyString("c")
        };
        MyString result = MyString.join(array, '-');
        assertEquals("a-b-c", result.toString());
    }

    @Test
    void replace() {
        MyString str = new MyString("hello");
        MyString result = str.replace('l', 'x');
        assertEquals("hexxo", result.toString());
    }

    @Test
    void equalsignoreCase() {
        MyString str1 = new MyString("Hello");
        MyString str2 = new MyString("HELLO");
        MyString str3 = new MyString("World");

        assertTrue(str1.equalsignoreCase(str1, str2));
        assertFalse(str1.equalsignoreCase(str1, str3));
    }

    @Test
    void indexOf() {
        MyString str = new MyString("Hello");
        assertEquals(1, str.indexOf('e'));
        assertEquals(-1, str.indexOf('x'));
    }

    @Test
    void charAt() {
        MyString str = new MyString("Hello");
        assertEquals('H', str.charAt(0));
        assertEquals('o', str.charAt(4));
    }

    @Test
    void toLowerCase() {
        MyString str = new MyString("JAVA");
        MyString result = str.toLowerCase();
        assertEquals("java", result.toString());
    }

    @Test
    void toUpperCase() {
        MyString str = new MyString("java");
        MyString result = str.toUpperCase();
        assertEquals("JAVA", result.toString());
    }

    @Test
    void concat() {
        MyString str1 = new MyString("Hello");
        MyString str2 = new MyString("World");
        MyString result = MyString.concat(str1, str2);
        assertEquals("HelloWorld", result.toString());
    }

    @Test
    void contains() {
        MyString str = new MyString("Hello World");
        MyString substring = new MyString("World");
        MyString notSubstring = new MyString("Java");

        assertTrue(str.contains(substring));
        assertFalse(str.contains(notSubstring));
    }

    @Test
    void strip() {
        MyString str = new MyString("  hello  ");
        MyString result = str.strip();
        assertEquals("hello", result.toString());
    }

    @Test
    void isEmpty() {
        MyString empty = new MyString("");
        MyString notEmpty = new MyString("text");

        assertTrue(empty.isEmpty());
        assertFalse(notEmpty.isEmpty());
    }

    @Test
    void substring() {
        MyString str = new MyString("Hello World");
        MyString result = str.substring(6);
        assertEquals("World", result.toString());
    }

    @Test
    void startsWith() {
        MyString str = new MyString("Hello World");
        MyString prefix = new MyString("Hello");
        MyString notPrefix = new MyString("World");

        assertTrue(str.startsWith(prefix));
        assertFalse(str.startsWith(notPrefix));
    }

    @Test
    void endsWith() {
        MyString str = new MyString("Hello World");
        MyString suffix = new MyString("World");
        MyString notSuffix = new MyString("Hello");

        assertTrue(str.endsWith(suffix));
        assertFalse(str.endsWith(notSuffix));
    }

    @Test
    void length() {
        assertEquals(0, new MyString().length());
        assertEquals(5, new MyString("Hello").length());
    }

    @Test
    void getData() {
        MyString str = new MyString("test");
        char[] data = str.getData();
        assertArrayEquals(new char[]{'t', 'e', 's', 't'}, data);
    }
}