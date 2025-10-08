package ru.vsu.cs.oop2024.group4.sokolovams;

/**
 * Для всех задача необходимо реализовать библиотеку классов. Для демонстрации
 * работы импортировать реализованную библиотеку, используя maven, в тестовый проект.
 * 1. Библиотека для работы со строками. Реализация операций split, join, replace,
 * equalsignoreCase, indexOf, format и т.д.
 */

public class MyString {

    private char[] data;

    public MyString() {
        this.data = new char[0];
    }

    public MyString(char[] value) {
        if (value == null) {
            this.data = new char[0];
        } else {
            this.data = value.clone();
        }
    }

    public MyString(String str) {
        if (str == null) {
            this.data = new char[0];
        } else {
            this.data = stringToCharArray(str);
        }
    }

    //Alt + Ins (или тапнуть на поле класса + Generate)
    public char[] getData() {
        return data.clone();
    }

    public int length() {
        return data.length;
    }

    public MyString[] split(char separator, boolean skipEmpty) {
        //return split(new MyString(separator))

        int count = 1;

        for (int i = 0; i < data.length; i++) {
            if (data[i] == separator) {
                count++;
            }
        }
        MyString[] result = new MyString[count];
        int start = 0;
        int index = 0;

        for (int i = 0; i <= data.length; i++) {
            if (i == data.length || data[i] == separator) {
                int partLength = i - start;
                char[] part = new char[partLength];
                for (int j = 0; j < partLength; j++) {
                    part[j] = data[start + j];
                }

                result[index] = new MyString(part);
                index++;
                start = i + 1;
            }
        }
        return result;
    }

    public static MyString join(MyString[] array, char separator) {
        if (array == null || array.length == 0) {
            return new MyString();
        }

        int totalLength = 0;
        for (int i = 0; i < array.length; i++) {
            totalLength += array[i].length(); //длина каждой строки
        }
        totalLength += array.length - 1;
        char[] result = new char[totalLength];

        int pos = 0;
        for (int i = 0; i < array.length; i++) {
            char[] part = array[i].getData();
            for (int j = 0; j < part.length; j++) {
                result[pos] = part[j];
                pos++;
            }
            if (i < array.length - 1) { // если не последняя
                result[pos] = separator;
                pos++;
            }
        }

        return new MyString(result);
    }

    public MyString replace(char oldChar, char newChar) {
        char[] newData = new char[data.length];
        for (int i = 0; i < data.length; i++) {
            if (data[i] == oldChar) {
                newData[i] = newChar;
            } else {
                newData[i] = data[i];
            }
        }
        return new MyString(newData);
    }

    public boolean equalsignoreCase(MyString str1, MyString str2) {
        if (str1 == null && str2 == null) {
            return true;
        }
        if (str1 == null || str2 == null) {
            return false;
        }
        for (int i = 0; i < str1.data.length; i++) {
            char c1 = toLowerCase(str1.data[i]);
            char c2 = toLowerCase(str2.data[i]);
            if (c1 != c2) {
                return false;
            }
        }
        return true;

    }

    public int indexOf(char ch) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    public char charAt(int index) {
        if (index < 0 || index >= data.length) {
            throw new IndexOutOfBoundsException(
                    "Index " + index + " out of bounds for length " + data.length
            );
        }
        return data[index];
    }

    private static char toLowerCase(char c) {
        if (c >= 'A' && c <= 'Z') {
            return (char) (c + 32);
        }

        if (c >= 'А' && c <= 'Я') {
            return (char) (c + 32);
        }

        if (c == 'Ё') {
            return 'ё';
        }

        return c;
    }

    private static char toUpperCase(char c) {
        if (c >= 'a' && c <= 'z') {
            return (char) (c - 32);
        }

        if (c >= 'а' && c <= 'я') {
            return (char) (c - 32);
        }

        if (c == 'ё') {
            return 'Ё';
        }

        return c;
    }

    public MyString toLowerCase() {
        char[] result = new char[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = toLowerCase(data[i]);
        }
        return new MyString(result);
    }

    public MyString toUpperCase() {
        char[] result = new char[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = toUpperCase(data[i]);
        }
        return new MyString(result);
    }

    public static MyString concat(MyString first, MyString second) {
        if (first == null) {
            return second == null ? new MyString() : new MyString(second.data);
        }
        if (second == null) {
            return new MyString(first.data);
        }

        int len1 = first.data.length;
        int len2 = second.data.length;
        char[] result = new char[len1 + len2];

        for (int i = 0; i < len1; i++) {
            result[i] = first.data[i];
        }
        for (int i = 0; i < len2; i++) {
            result[len1 + i] = second.data[i];
        }

        return new MyString(result);
    }

    public boolean contains(MyString substring) {
        if (substring == null) {
            return false;
        }
        if (substring.data.length == 0) {
            return true; // пустая строка содержится в любой
        }
        if (substring.data.length > this.data.length) {
            return false;
        }


        for (int i = 0; i <= this.data.length - substring.data.length; i++) {
            boolean found = true;
            for (int j = 0; j < substring.data.length; j++) {
                if (this.data[i + j] != substring.data[j]) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return true;
            }
        }
        return false;
    }

    public MyString strip() {
        int start = 0;
        int end = data.length - 1;

        while (start <= end && isWhitespace(data[start])) {
            start++;
        }

        while (end >= start && isWhitespace(data[end])) {
            end--;
        }

        if (start > end) {
            return new MyString();
        }

        int length = end - start + 1;
        char[] stripped = new char[length];
        for (int i = 0; i < length; i++) {
            stripped[i] = data[start + i];
        }

        return new MyString(stripped);
    }

    private static boolean isWhitespace(char c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\r' || c == '\f';
    }

    public boolean isEmpty() {
        return data.length == 0;
    }

    public MyString substring(int beginIndex, int endIndex) {
        if (beginIndex < 0 || endIndex > data.length || beginIndex > endIndex) {
            throw new IndexOutOfBoundsException("Invalid indices");
        }
        char[] result = new char[endIndex - beginIndex];
        for (int i = 0; i < result.length; i++) {
            result[i] = data[beginIndex + i];
        }
        return new MyString(result);
    }

    public MyString substring(int beginIndex) {
        return substring(beginIndex, data.length);
    }

    public boolean startsWith(MyString prefix) {
        if (prefix == null) return false;
        if (prefix.data.length == 0) return true;
        if (prefix.data.length > this.data.length) return false;
        for (int i = 0; i < prefix.data.length; i++) {
            if (this.data[i] != prefix.data[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean endsWith(MyString suffix) {
        if (suffix == null) return false;
        if (suffix.data.length == 0) return true;
        if (suffix.data.length > this.data.length) return false;
        int offset = this.data.length - suffix.data.length;
        for (int i = 0; i < suffix.data.length; i++) {
            if (this.data[offset + i] != suffix.data[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return new String(data);
    }

    //@NotNull
    private static char[] stringToCharArray(String str) {
        if (str == null) {
            return new char[0];
        }
        char[] result = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            result[i] = str.charAt(i);
        }

        return result;

    }
}