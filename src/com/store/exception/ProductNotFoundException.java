package com.store.exception;

/**
 * <h1>Capítulo 11 – Tratamento de exceção</h1>
 * Exceção disparada quando um produto não é localizado pelo ID informado.
 * A mensagem é construída utilizando concatenação de {@code String}
 * (<b>Capítulo 3 – Strings</b>) e passada ao construtor pai com
 * {@code super} (<b>Capítulo 9 – Herança</b>).
 */
public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String id) {
        // Capítulo 3: concatenação de strings com operador '+'
        super("Produto com ID " + id + " não encontrado.");
    }
}