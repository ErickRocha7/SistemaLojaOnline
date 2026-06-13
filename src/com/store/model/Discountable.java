package com.store.model;

/**
 * <h1>Capítulo 10 – Programação orientada a objetos: polimorfismo e
 * interfaces</h1>
 * Esta interface declara um contrato para aplicação de desconto em produtos.
 * Classes que a implementam se comprometem a fornecer o método
 * {@code applyDiscount}.
 *
 * <p>
 * <b>Capítulo 10:</b> definição de interface Java, que estabelece um tipo
 * abstrato
 * que será usado polimorficamente (ex.: {@code Discountable} referencia objetos
 * de
 * diferentes classes).
 * </p>
 * <p>
 * <b>Capítulo 6:</b> declaração de método público abstrato (implicitamente).
 * </p>
 */
public interface Discountable {
    /**
     * Aplica um desconto percentual ao produto.
     * 
     * @param percentage percentual de desconto (ex.: 10.0 para 10%).
     *
     *                   <b>Capítulo 6:</b> assinatura de método sem corpo
     *                   (abstrato).
     *                   <b>Capítulo 4:</b> o parâmetro {@code double} será
     *                   utilizado em operações aritméticas.
     */
    void applyDiscount(double percentage);
}