package com.store.model;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * <h1>Capítulo 9 – Herança (superclasse abstrata) | Capítulo 20 –
 * Genéricos</h1>
 * Classe abstrata que representa um produto genérico na loja.
 * Implementa as interfaces {@code Identifiable}, {@code Searchable} e agora
 * também {@code Comparable<Product>}, permitindo que produtos sejam comparados
 * naturalmente pelo ID.
 *
 * <h2>Capítulos abordados:</h2>
 * <ul>
 * <li><b>9 – Herança:</b> é a superclasse abstrata da hierarquia de
 * produtos.</li>
 * <li><b>10 – Polimorfismo:</b> implementa {@code Identifiable} e
 * {@code Searchable}.</li>
 * <li><b>20 – Genéricos:</b> implementa {@code Comparable<Product>},
 * possibilitando que
 * produtos sejam usados em métodos genéricos restritos a
 * {@code <T extends Comparable<T>>}, como
 * {@code MathUtil.recursiveBinarySearch}.</li>
 * <li><b>16 – Coleções genéricas:</b> a ordenação natural via
 * {@code Comparable} é usada por
 * métodos como {@code Collections.sort} (sem {@code Comparator}).</li>
 * <li><b>8 – Classes e objetos:</b> atributos estáticos, finais,
 * encapsulamento, {@code this}.</li>
 * <li><b>4 – Operadores:</b> pós-incremento ({@code nextId++}).</li>
 * <li><b>6 – Métodos:</b> getters, setters, {@code compareTo}.</li>
 * <li><b>14 – Strings e expressões regulares:</b> {@code Pattern},
 * {@code Matcher}, {@code String.format}.</li>
 * <li><b>15 – Arquivos, fluxos e serialização:</b> agora implementa
 * {@code Serializable}
 * para permitir a persistência de objetos em arquivos binários.</li>
 * </ul>
 */
public abstract class Product implements Identifiable, Searchable, Comparable<Product>, Serializable {
    // Capítulo 15: identificador de versão de serialização (boa prática)
    private static final long serialVersionUID = 1L;

    // Capítulo 8: membro static para IDs sequenciais
    private static int nextId = 1;

    private final String id;
    private String name;
    private double price;
    private final Category category;
    private int stock; // Novo atributo para controle de estoque

    /**
     * Construtor da classe abstrata Product.
     * 
     * @param name     nome do produto
     * @param price    preço unitário
     * @param category categoria (enum)
     */
    public Product(String name, double price, Category category) {
        this.id = String.format("PROD-%04d", nextId++); // Capítulo 14: formatação; Capítulo 4: ++
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = 0; // Inicialmente sem estoque
    }

    // ---------- Getters e Setters (Capítulo 6) ----------
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Implementação de {@code Comparable<Product>} – Capítulo 20.
     * Compara produtos pelo ID (ordem lexicográfica natural de String).
     * Essa ordenação permite que arrays de produtos sejam ordenados e usados
     * em algoritmos genéricos como busca binária recursiva.
     *
     * @param other outro produto a ser comparado
     * @return valor negativo, zero ou positivo conforme o ID deste produto seja
     *         menor, igual ou maior que o ID do outro produto
     */
    @Override
    public int compareTo(Product other) {
        return this.id.compareTo(other.id);
    }

    /**
     * Busca por palavra-chave (regex) no nome ou ID.
     * <b>Capítulo 10:</b> implementação de {@code Searchable}.
     * <b>Capítulo 14:</b> {@code Pattern} e {@code Matcher}.
     * <b>Capítulo 5:</b> operador lógico {@code ||}.
     */
    @Override
    public boolean matchesKeyword(String keyword) {
        Pattern pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(name).find() || pattern.matcher(id).find();
    }

    /**
     * Representação textual.
     * <b>Capítulo 14:</b> {@code String.format}.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s (%s) - R$ %.2f (Estoque: %d)", id, name, category, price, stock);
    }
}