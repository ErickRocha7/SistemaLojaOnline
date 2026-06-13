package com.store.util;

import java.util.List;

public class MathUtil {
    // Fatorial recursivo
    public static long factorial(int n) {
        if (n <= 1)
            return 1;
        return n * factorial(n - 1);
    }

    // Método genérico: maior elemento de uma lista
    public static <T extends Comparable<T>> T findMax(List<T> list) {
        if (list.isEmpty())
            return null;
        T max = list.get(0);
        for (T item : list) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }

    // Busca binária recursiva genérica
    public static <T extends Comparable<T>> int recursiveBinarySearch(T[] array, T key, int low, int high) {
        if (low > high)
            return -1;
        int mid = (low + high) / 2;
        int comp = key.compareTo(array[mid]);
        if (comp == 0)
            return mid;
        if (comp < 0)
            return recursiveBinarySearch(array, key, low, mid - 1);
        return recursiveBinarySearch(array, key, mid + 1, high);
    }
}