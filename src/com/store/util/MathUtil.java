package com.store.util;

import java.util.List;

/**
 * <h1>Capítulo 18 – Recursão | Capítulo 20 – Genéricos</h1>
 * Classe utilitária que contém métodos matemáticos genéricos e recursivos.
 *
 * <h2>Capítulos abordados:</h2>
 * <ul>
 * <li><b>18 – Recursão:</b> implementação recursiva do fatorial e da busca
 * binária.</li>
 * <li><b>20 – Classes e métodos genéricos:</b> {@code findMax} e
 * {@code recursiveBinarySearch} são métodos genéricos com restrição
 * {@code <T extends Comparable<T>>}.</li>
 * <li><b>7 – Arrays:</b> {@code recursiveBinarySearch} trabalha sobre um array
 * genérico.</li>
 * <li><b>16 – Coleções genéricas:</b> {@code findMax} opera sobre
 * {@code List<T>}.</li>
 * <li><b>4 – Operadores:</b> operadores aritméticos, relacionais e lógicos nos
 * métodos.</li>
 * <li><b>6 – Métodos:</b> métodos estáticos públicos.</li>
 * </ul>
 */
public class MathUtil {

    /**
     * Calcula o fatorial de um número inteiro de forma recursiva.
     * <b>Capítulo 18 – Recursão:</b> caso base (n <= 1) e chamada recursiva (n *
     * factorial(n-1)).
     * <b>Capítulo 4:</b> uso de if, operador <=.
     */
    public static long factorial(int n) {
        if (n <= 1) // Capítulo 4: if; Capítulo 5: operador relacional <=
            return 1; // caso base
        return n * factorial(n - 1); // Capítulo 18: chamada recursiva; Capítulo 4: operador -
    }

    /**
     * Encontra o maior elemento de uma lista genérica.
     * <b>Capítulo 20 – Genéricos:</b> método genérico
     * {@code <T extends Comparable<T>>}.
     * <b>Capítulo 16 – Coleções genéricas:</b> iteração sobre {@code List<T>} com
     * enhanced for.
     * <b>Capítulo 4:</b> if para comparar elementos.
     * <b>Capítulo 8:</b> retorna null se a lista estiver vazia.
     */
    public static <T extends Comparable<T>> T findMax(List<T> list) {
        if (list.isEmpty()) // Capítulo 5: método isEmpty()
            return null;
        T max = list.get(0); // Capítulo 7: acesso a elemento por índice
        for (T item : list) { // Capítulo 7: enhanced for
            // Capítulo 20: compareTo da interface Comparable
            if (item.compareTo(max) > 0) { // Capítulo 4: if; Capítulo 5: operador >
                max = item; // Capítulo 2: atribuição
            }
        }
        return max;
    }

    /**
     * Realiza uma busca binária recursiva em um array ordenado genérico.
     * <b>Capítulo 18 – Recursão:</b> a cada chamada o intervalo de busca é reduzido
     * pela metade.
     * <b>Capítulo 20 – Genéricos:</b> método genérico com restrição
     * {@code T extends Comparable<T>}.
     * <b>Capítulo 7 – Arrays:</b> parâmetro {@code T[] array}.
     * <b>Capítulo 4 – Operadores:</b> adição e divisão para calcular o meio,
     * subtração/adição nos limites.
     * <b>Capítulo 5 – Operadores lógicos e relacionais:</b> {@code >}, {@code <},
     * {@code ==}.
     */
    public static <T extends Comparable<T>> int recursiveBinarySearch(T[] array, T key, int low, int high) {
        if (low > high) // Capítulo 4/5: condição de parada (intervalo inválido)
            return -1;
        int mid = (low + high) / 2; // Capítulo 4: cálculo do índice médio
        int comp = key.compareTo(array[mid]); // Capítulo 20: compareTo genérico
        if (comp == 0) // Capítulo 4: if; Capítulo 5: operador ==
            return mid;
        if (comp < 0) // Capítulo 5: operador <
            return recursiveBinarySearch(array, key, low, mid - 1); // Capítulo 18: recursão
        return recursiveBinarySearch(array, key, mid + 1, high); // Capítulo 18: recursão
    }
}