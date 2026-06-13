package com.store.exception;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String id) {
        super("Produto com ID " + id + " não encontrado.");
    }
}