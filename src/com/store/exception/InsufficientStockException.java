package com.store.exception;

/**
 * <h1>Capítulo 11 – Tratamento de exceção</h1>
 * Exceção lançada quando uma operação de venda não pode ser concluída
 * por falta de estoque. Assim como as demais, estende {@code Exception}
 * (<b>Capítulo 9 – Herança</b>).
 */
public class InsufficientStockException extends Exception {
    public InsufficientStockException(String message) {
        super(message);
    }
}