package com.store.model;

/**
 * <h1>Capítulo 9 – Herança | Capítulo 10 – Polimorfismo e interfaces</h1>
 * Representa um produto eletrônico, herdando de {@code Product} e implementando
 * a interface {@code Discountable}.
 *
 * <h2>Capítulos abordados:</h2>
 * <ul>
 * <li><b>9 – Herança:</b> estende a classe abstrata {@code Product}, herdando
 * atributos
 * e métodos, e invoca o construtor da superclasse com {@code super()}.</li>
 * <li><b>10 – Polimorfismo:</b> implementa {@code Discountable}; objetos
 * {@code Electronics}
 * podem ser referenciados como {@code Product} ou {@code Discountable}.</li>
 * <li><b>8 – Classes e objetos:</b> atributos privados {@code brand} e
 * {@code warrantyMonths},
 * encapsulamento com getters, construtor parametrizado com uso de
 * {@code this}.</li>
 * <li><b>6 – Métodos:</b> sobrescrita de {@code applyDiscount} e
 * {@code toString()}.</li>
 * <li><b>4 – Operadores:</b> expressão aritmética no cálculo do desconto.</li>
 * <li><b>14 – Strings:</b> formatação de string com {@code String.format} e
 * concatenação.</li>
 * <li><b>15 – Arquivos, fluxos e serialização:</b> herda a capacidade de
 * serialização de
 * {@code Product}, permitindo que objetos {@code Electronics} sejam persistidos
 * em
 * arquivos binários via {@code ObjectOutputStream} e
 * {@code ObjectInputStream}.</li>
 * </ul>
 */
public class Electronics extends Product implements Discountable {
    // Capítulo 15: identificador de versão de serialização
    private static final long serialVersionUID = 1L;

    // Capítulo 8: encapsulamento – atributos privados
    private String brand; // marca do eletrônico
    private int warrantyMonths; // meses de garantia (Capítulo 2: tipo primitivo int)

    /**
     * Construtor de {@code Electronics}.
     * <b>Capítulo 9:</b> {@code super(name, price, Category.ELECTRONICS)}
     * inicializa a
     * porção herdada de {@code Product}.
     * <b>Capítulo 8:</b> {@code this.brand = brand;} diferencia atributo de
     * parâmetro.
     *
     * @param name           nome do produto
     * @param price          preço unitário
     * @param brand          marca do eletrônico
     * @param warrantyMonths meses de garantia
     */
    public Electronics(String name, double price, String brand, int warrantyMonths) {
        super(name, price, Category.ELECTRONICS); // Capítulo 9: chamada ao construtor base
        this.brand = brand; // Capítulo 8: this
        this.warrantyMonths = warrantyMonths;
    }

    /**
     * @return a marca do eletrônico.
     *         <b>Capítulo 6:</b> método getter.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @return os meses de garantia.
     *         <b>Capítulo 6:</b> método getter.
     */
    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    /**
     * Aplica um desconto percentual ao preço do eletrônico.
     * <b>Capítulo 10:</b> implementação concreta do método da interface
     * {@code Discountable}.
     * <b>Capítulo 4:</b> operadores aritméticos: subtração, divisão, multiplicação.
     * <b>Capítulo 6:</b> invocação de métodos herdados {@code getPrice()} e
     * {@code setPrice()}.
     */
    @Override
    public void applyDiscount(double percentage) {
        // Capítulo 4: expressão aritmética
        double discounted = getPrice() * (1 - percentage / 100.0);
        setPrice(discounted); // Capítulo 6: chamada a setter herdado
    }

    /**
     * Retorna a representação textual do eletrônico, acrescentando marca e
     * garantia.
     * <b>Capítulo 9:</b> {@code super.toString()} reutiliza a formatação da
     * superclasse.
     * <b>Capítulo 14:</b> {@code String.format} para formatar strings com
     * especificadores
     * {@code %s} (string) e {@code %d} (inteiro).
     * <b>Capítulo 3:</b> concatenação de strings com {@code +}.
     */
    @Override
    public String toString() {
        return super.toString() +
                String.format(" | Marca: %s, Garantia: %d meses", brand, warrantyMonths);
    }
}