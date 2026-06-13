package com.store.datastructure;

/**
 * <h1>Capítulo 21 – Estruturas de dados genéricas personalizadas</h1>
 * Representa um nó de uma lista ligada simples com tipo genérico {@code <T>}.
 * <br>
 * <b>Capítulo 20 – Classes e métodos genéricos:</b> a declaração
 * {@code Node<T>}
 * torna a classe reutilizável para qualquer tipo de objeto.
 * <br>
 * <b>Capítulo 8 – Classes e objetos: um exame mais profundo:</b>
 * uso do operador {@code this} para distinguir o atributo do parâmetro.
 */
public class Node<T> {
    /**
     * Dado armazenado no nó (genérico).
     */
    T data;
    /**
     * Referência para o próximo nó na sequência.
     */
    Node<T> next;

    /**
     * Construtor que inicializa o nó com o dado fornecido.
     * <b>Capítulo 3:</b> criação de objetos e inicialização de atributos.
     * <b>Capítulo 8:</b> uso de {@code this.data} para resolver conflito de nomes.
     */
    public Node(T data) {
        this.data = data;
        this.next = null; // Capítulo 2: operador de atribuição '='
    }
}