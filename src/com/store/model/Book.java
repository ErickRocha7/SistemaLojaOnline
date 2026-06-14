package com.store.model;

/**
 * <h1>Capítulo 9 – Herança | Capítulo 10 – Polimorfismo e interfaces</h1>
 * Representa um livro, que é um tipo específico de produto.
 * Estende {@code Product} e implementa a interface {@code Discountable}.
 *
 * <h2>Capítulos abordados:</h2>
 * <ul>
 * <li><b>9 – Herança:</b> {@code Book} herda de {@code Product} (classe
 * abstrata).
 * Usa {@code super()} para invocar o construtor da superclasse e
 * {@code super.toString()} para reaproveitar a representação textual.</li>
 * <li><b>10 – Polimorfismo:</b> implementa {@code Discountable}, permitindo que
 * livros
 * sejam tratados como descontáveis (referência polimórfica).</li>
 * <li><b>8 – Classes e objetos:</b> atributos privados {@code author} e
 * {@code isbn},
 * encapsulamento com getters, construtor com parâmetros e uso de
 * {@code this}.</li>
 * <li><b>6 – Métodos:</b> sobrescrita de {@code toString()} e implementação de
 * {@code applyDiscount}.</li>
 * <li><b>14 – Strings:</b> formatação com {@code String.format} e concatenação
 * de strings.</li>
 * <li><b>4 – Operadores:</b> operadores aritméticos e parênteses na fórmula do
 * desconto.</li>
 * <li><b>15 – Arquivos, fluxos e serialização:</b> herda a capacidade de
 * serialização de
 * {@code Product}, permitindo que objetos {@code Book} sejam persistidos em
 * arquivos
 * binários via {@code ObjectOutputStream} e {@code ObjectInputStream}.</li>
 * </ul>
 */
public class Book extends Product implements Discountable {
    // Capítulo 15: identificador de versão de serialização
    private static final long serialVersionUID = 1L;

    // Capítulo 8: atributos privados – encapsulamento
    private String author;
    private String isbn;

    /**
     * Construtor de {@code Book}.
     * <b>Capítulo 9:</b> chamada a {@code super(name, price, Category.BOOK)} para
     * inicializar a parte herdada de {@code Product}.
     * <b>Capítulo 8:</b> uso de {@code this.author} e {@code this.isbn} para
     * distinguir
     * atributos dos parâmetros.
     *
     * @param name   nome do livro
     * @param price  preço unitário
     * @param author nome do autor
     * @param isbn   código ISBN
     */
    public Book(String name, double price, String author, String isbn) {
        super(name, price, Category.BOOK); // Capítulo 9: super()
        this.author = author; // Capítulo 8: this
        this.isbn = isbn;
    }

    /**
     * @return o autor do livro.
     *         <b>Capítulo 6:</b> método getter.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @return o ISBN do livro.
     *         <b>Capítulo 6:</b> método getter.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Aplica desconto percentual ao preço do livro.
     * <b>Capítulo 10:</b> implementação do método da interface
     * {@code Discountable}.
     * <b>Capítulo 4:</b> operadores aritméticos: multiplicação, divisão, subtração.
     * Fórmula: novo preço = preço * (1 - percentage / 100).
     * <b>Capítulo 6:</b> chamada aos métodos herdados {@code getPrice()} e
     * {@code setPrice()}.
     */
    @Override
    public void applyDiscount(double percentage) {
        // Capítulo 4: expressão aritmética; parênteses controlam precedência
        double discounted = getPrice() * (1 - percentage / 100.0);
        setPrice(discounted);
    }

    /**
     * Representação textual do livro, acrescentando autor e ISBN à representação
     * padrão de {@code Product}.
     * <b>Capítulo 9:</b> {@code super.toString()} invoca o método da superclasse.
     * <b>Capítulo 14:</b> uso de {@code String.format} para formatar os campos.
     * <b>Capítulo 3:</b> concatenação de strings com o operador {@code +}.
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" | Autor: %s, ISBN: %s", author, isbn);
    }
}