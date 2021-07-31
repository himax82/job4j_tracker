package ru.job4j.ex;

import java.util.SplittableRandom;

public class FindEl {

    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        int rsl = -1;
        for (int i = 0; i < value.length; i++) {
            if (value[i].equals(key)) {
                rsl = i;
            }
        }
        if (rsl == -1) {
            throw new ElementNotFoundException("Элемент не найден!");
        }
        return rsl;
    }

    public static void main(String[] args) {
        String[] str = {"I", "love", "java"};
        String key = "C++";
        try {
            FindEl.indexOf(str, key);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }
}
