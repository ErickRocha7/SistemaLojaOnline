package com.store.exception;

/**
 * <h1>Capítulo 11 – Tratamento de exceção: um exame mais profundo</h1>
 * Esta classe representa uma exceção personalizada (checked) lançada quando
 * o usuário fornece uma entrada inválida. Ela herda de {@code Exception},
 * característica do <b>Capítulo 9 – Herança</b>.
 */
public class InvalidInputException extends Exception {
    /**
     * Construtor que recebe a mensagem de erro e a repassa à superclasse.
     * <b>Capítulo 9:</b> uso de {@code super} para invocar o construtor da classe base.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}