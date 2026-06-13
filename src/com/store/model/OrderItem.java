package com.store.model;

/**
 * <h1>Capítulo 10 – Polimorfismo (padrão Composite, folha)</h1>
 * Representa um item individual de pedido (folha na árvore Composite).
 * Contém um produto e uma quantidade.
 *
 * <h2>Capítulos abordados:</h2>
 * <ul>
 * <li><b>10 – Polimorfismo:</b> estende {@code OrderComponent} e implementa os
 * métodos
 * abstratos como folha da árvore Composite.</li>
 * <li><b>8 – Classes e objetos:</b> atributos {@code final} e não-final,
 * construtor
 * parametrizado com uso de {@code this}.</li>
 * <li><b>6 – Métodos:</b> getters, cálculo de preço e impressão formatada.</li>
 * <li><b>4 – Operadores:</b> multiplicação em {@code getPrice()}.</li>
 * <li><b>2 – Entrada/saída:</b> {@code System.out.printf} para saída
 * formatada.</li>
 * <li><b>14 – Strings:</b> {@code " ".repeat(indentation)} e formatação com
 * {@code printf}.</li>
 * </ul>
 */
public class OrderItem extends OrderComponent {
    // Capítulo 8: atributo final (referência imutável ao produto)
    private final Product product;
    // Capítulo 4: atributo de quantidade (pode variar)
    private int quantity;

    /**
     * Construtor que inicializa o item com um produto e uma quantidade.
     * <b>Capítulo 8:</b> uso de {@code this.product} e {@code this.quantity}.
     *
     * @param product  produto associado ao item
     * @param quantity quantidade desejada
     */
    public OrderItem(Product product, int quantity) {
        this.product = product; // Capítulo 8: this
        this.quantity = quantity; // Capítulo 2: atribuição de inteiro
    }

    /**
     * @return o produto deste item.
     *         <b>Capítulo 6:</b> método getter.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @return a quantidade.
     *         <b>Capítulo 6:</b> método getter.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Calcula o preço total do item (preço unitário × quantidade).
     * <b>Capítulo 10:</b> implementação polimórfica de
     * {@link OrderComponent#getPrice()}.
     * <b>Capítulo 4:</b> operador de multiplicação {@code *}.
     * <b>Capítulo 6:</b> chamada ao método {@code getPrice()} do produto.
     */
    @Override
    public double getPrice() {
        return product.getPrice() * quantity; // Capítulo 4: operador aritmético
    }

    /**
     * Imprime o item com indentação, exibindo nome, quantidade e preço total.
     * <b>Capítulo 10:</b> implementação polimórfica de
     * {@link OrderComponent#print(int)}.
     * <b>Capítulo 2:</b> {@code System.out.printf} com especificadores de formato
     * {@code %s}, {@code %d}, {@code %.2f}, {@code %n}.
     * <b>Capítulo 14:</b> {@code " ".repeat(indentation)} gera a indentação.
     * <b>Capítulo 6:</b> chamadas a {@code getName()}, {@code getPrice()}.
     */
    @Override
    public void print(int indentation) {
        System.out.printf("%s- %s x%d = R$ %.2f%n",
                " ".repeat(indentation), // Capítulo 14: repeat()
                product.getName(), // Capítulo 3: String
                quantity, // Capítulo 2: saída de inteiro
                getPrice()); // Capítulo 6: invocação de método
    }
}