package com.store.model;

import java.util.regex.Pattern;

/**
 * <h1>Capítulo 9 – Herança (superclasse abstrata)</h1>
 * Classe abstrata que representa um produto genérico na loja.
 * Implementa as interfaces {@code Identifiable} e {@code Searchable},
 * fornecendo uma base comum para {@code Book} e {@code Electronics}.
 *
 * <h2>Capítulos abordados:</h2>
 * <ul>
 * <li><b>9 – Herança:</b> é a superclasse abstrata da hierarquia de produtos.
 * Subclasses usam {@code super()} no construtor e podem sobrescrever
 * métodos.</li>
 * <li><b>10 – Polimorfismo:</b> implementa {@code Identifiable} e
 * {@code Searchable},
 * permitindo tratamento polimórfico de diferentes tipos de produto.</li>
 * <li><b>8 – Classes e objetos:</b> atributos {@code private} e {@code final},
 * membro
 * {@code static} ({@code nextId}), construtor parametrizado, uso de
 * {@code this}.</li>
 * <li><b>4 – Operadores:</b> operador de pós-incremento ({@code nextId++}) no
 * construtor.</li>
 * <li><b>6 – Métodos:</b> getters, setters, implementação de métodos de
 * interface.</li>
 * <li><b>14 – Strings e expressões regulares:</b> uso de {@code Pattern} e
 * {@code Matcher}
 * para busca com regex em {@code matchesKeyword}; formatação com
 * {@code String.format}.</li>
 * <li><b>3 – Introdução a classes, objetos, métodos e strings:</b> criação de
 * classe,
 * definição de métodos, formatação de strings.</li>
 * </ul>
 */
public abstract class Product implements Identifiable, Searchable {
    // Capítulo 8: membro static para gerar IDs sequenciais
    private static int nextId = 1;

    // Capítulo 8: atributo final – ID não muda após construção
    private final String id;
    private String name; // Capítulo 2/3: String mutável (setter disponível)
    private double price; // Capítulo 2: tipo primitivo double
    private final Category category; // Capítulo 8: enum Category, atributo final

    /**
     * Construtor da classe abstrata Product.
     * Gera um ID automático e inicializa os atributos comuns a todos os produtos.
     *
     * <b>Capítulo 4:</b> operador de pós-incremento {@code nextId++} para gerar IDs
     * únicos.
     * <b>Capítulo 14:</b> {@code String.format("PROD-%04d", ...)} gera o ID no
     * formato "PROD-0001".
     * <b>Capítulo 8:</b> {@code this.name = name;} resolve conflito de nomes.
     *
     * @param name     nome do produto
     * @param price    preço unitário
     * @param category categoria (enum)
     */
    public Product(String name, double price, Category category) {
        // Capítulo 14: formatação de string com zero-padding
        // Capítulo 4: nextId++ incrementa após uso do valor atual
        this.id = String.format("PROD-%04d", nextId++);
        this.name = name; // Capítulo 2: atribuição de String
        this.price = price; // Capítulo 2: atribuição de double
        this.category = category; // Capítulo 8: atribuição de enum
    }

    /**
     * @return o ID do produto.
     *         <b>Capítulo 10:</b> implementação de {@code Identifiable.getId()}.
     */
    public String getId() {
        return id;
    }

    /**
     * @return o nome do produto.
     *         <b>Capítulo 6:</b> método getter.
     */
    public String getName() {
        return name;
    }

    /**
     * Altera o nome do produto.
     * <b>Capítulo 6:</b> método setter.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return o preço unitário.
     *         <b>Capítulo 6:</b> método getter.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Altera o preço unitário.
     * <b>Capítulo 6:</b> método setter.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return a categoria do produto.
     *         <b>Capítulo 6:</b> método getter; retorna um valor enum (Capítulo 8).
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Verifica se o nome ou o ID do produto correspondem a uma palavra-chave
     * (expressão regular).
     *
     * <b>Capítulo 10:</b> implementação de {@code Searchable.matchesKeyword()}.
     * <b>Capítulo 14 – Expressões regulares:</b> uso das classes {@code Pattern} e
     * {@code Matcher} para compilar a regex e buscar correspondências nos campos
     * {@code name} e {@code id} com {@code Pattern.CASE_INSENSITIVE}.
     * <b>Capítulo 5 – Operadores lógicos:</b> uso do operador {@code ||} (OU
     * lógico)
     * para combinar as duas condições de busca.
     *
     * @param keyword palavra-chave ou regex a ser procurada
     * @return {@code true} se o nome ou o ID corresponderem
     */
    @Override
    public boolean matchesKeyword(String keyword) {
        // Capítulo 14: compilação da regex com flag CASE_INSENSITIVE
        Pattern pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
        // Capítulo 5: operador lógico || (curto-circuito)
        return pattern.matcher(name).find() || pattern.matcher(id).find();
    }

    /**
     * Representação textual do produto.
     * <b>Capítulo 14/Strings:</b> {@code String.format} com especificadores
     * {@code %s},
     * {@code %.2f}.
     * <b>Capítulo 3:</b> formatação de strings; <b>Capítulo 9:</b> será sobrescrito
     * pelas
     * subclasses que adicionam mais informações.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s (%s) - R$ %.2f", id, name, category, price);
    }
}