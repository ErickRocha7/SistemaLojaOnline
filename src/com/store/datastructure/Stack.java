package com.store.datastructure;

import java.util.EmptyStackException;

/**
 * <h1>Capítulo 21 – Estruturas de dados genéricas personalizadas</h1>
 * Implementação de uma pilha (LIFO) genérica usando a classe {@code Node<T>}.
 *
 * <h2>Capítulos abordados:</h2>
 * <ul>
 * <li><b>20 – Genéricos:</b> {@code Stack<T>} permite armazenar qualquer
 * tipo.</li>
 * <li><b>21 – Estruturas personalizadas:</b> lógica de push/pop sobre lista
 * ligada.</li>
 * <li><b>6 – Métodos:</b> definição de métodos públicos {@code push},
 * {@code pop}, etc.</li>
 * <li><b>8 – Classes e objetos:</b> atributos privados, construtor,
 * encapsulamento.</li>
 * <li><b>11 – Exceções:</b> lançamento de {@code EmptyStackException} em
 * {@code pop()} e {@code peek()}.</li>
 * <li><b>4 / 5 – Instruções de controle:</b> uso de {@code if} e operador
 * lógico {@code ==} para verificar lista vazia.</li>
 * </ul>
 */
public class Stack<T> {
    // Capítulo 8: atributos privados para encapsulamento
    private Node<T> top; // topo da pilha
    private int size; // contador de elementos (Capítulo 4: operador ++ e --)

    /**
     * Construtor padrão – inicializa pilha vazia.
     * <b>Capítulo 3:</b> criação de objetos; <b>Capítulo 8:</b> construtor sem
     * argumentos.
     */
    public Stack() {
        top = null; // Capítulo 2: operador de atribuição
        size = 0;
    }

    /**
     * Insere um item no topo da pilha.
     * <b>Capítulo 21:</b> manipulação de nós.
     * <b>Capítulo 4:</b> operador de pré-incremento {@code ++}.
     */
    public void push(T item) {
        Node<T> newNode = new Node<>(item); // Capítulo 20: inferência de tipo genérico
        newNode.next = top; // Capítulo 8: acesso a atributo de outro objeto
        top = newNode;
        size++; // Capítulo 4: operador de incremento pós-fixado
    }

    /**
     * Remove e retorna o elemento do topo.
     * <b>Capítulo 11:</b> lança exceção se a pilha estiver vazia.
     * <b>Capítulo 4:</b> operador de decremento {@code --}.
     */
    public T pop() {
        // Capítulo 5: operador lógico de negação '!' + chamada a método isEmpty()
        if (isEmpty()) {
            throw new EmptyStackException(); // Capítulo 11: throw de exceção padrão do Java
        }
        T data = top.data; // Capítulo 20: tipo T inferido
        top = top.next; // remove o primeiro nó
        size--; // Capítulo 4: operador de decremento
        return data;
    }

    /**
     * Retorna o elemento do topo sem removê-lo.
     * <b>Capítulo 6:</b> método de acesso (consulta).
     */
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.data;
    }

    /**
     * Verifica se a pilha está vazia.
     * <b>Capítulo 5:</b> operador de igualdade {@code ==}.
     */
    public boolean isEmpty() {
        return top == null; // Capítulo 5: operador relacional
    }

    /**
     * Retorna a quantidade de elementos.
     * <b>Capítulo 6:</b> método getter.
     */
    public int size() {
        return size;
    }
}