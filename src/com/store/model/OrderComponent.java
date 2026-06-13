package com.store.model;

/**
 * <h1>Capítulo 10 – Polimorfismo (padrão Composite)</h1>
 * Classe abstrata que define a interface comum para todos os componentes de um
 * pedido
 * (folhas e compostos). Representa o papel de {@code Component} no padrão
 * Composite.
 *
 * <h2>Capítulos abordados:</h2>
 * <ul>
 * <li><b>10 – Polimorfismo:</b> classe abstrata com métodos abstratos que são
 * sobrescritos
 * polimorficamente por {@code OrderItem} (folha) e {@code CompositeOrder}
 * (composto).</li>
 * <li><b>8 – Classes e objetos:</b> declaração de classe abstrata; método
 * {@code add} com
 * implementação padrão que lança exceção (comportamento esperado para
 * folhas).</li>
 * <li><b>9 – Herança:</b> será superclasse de {@code OrderItem} e
 * {@code CompositeOrder}.</li>
 * <li><b>11 – Tratamento de exceção:</b> lançamento de
 * {@code UnsupportedOperationException}
 * no método {@code add} para classes que não suportam a operação.</li>
 * <li><b>6 – Métodos:</b> definição de métodos abstratos e concretos.</li>
 * </ul>
 */
public abstract class OrderComponent {
    /**
     * Retorna o preço total deste componente.
     * <b>Capítulo 10:</b> método abstrato que será implementado polimorficamente.
     * 
     * @return preço calculado (em folhas: preço × quantidade; em compostos: soma
     *         dos filhos).
     */
    public abstract double getPrice();

    /**
     * Imprime este componente com uma indentação visual.
     * <b>Capítulo 10:</b> método abstrato implementado nas subclasses.
     * 
     * @param indentation número de espaços para indentação.
     */
    public abstract void print(int indentation);

    /**
     * Adiciona um componente filho. A implementação padrão lança exceção,
     * pois apenas os compostos ({@code CompositeOrder}) devem aceitar filhos.
     *
     * <b>Capítulo 10:</b> operação padrão do Composite: folhas não suportam
     * {@code add}.
     * <b>Capítulo 11:</b> lançamento de {@code UnsupportedOperationException}, uma
     * exceção unchecked do pacote {@code java.lang}.
     *
     * @param component componente a ser adicionado
     * @throws UnsupportedOperationException se chamado em uma folha
     */
    public void add(OrderComponent component) {
        // Capítulo 11: throw de exceção
        throw new UnsupportedOperationException("Operação não suportada em folha");
    }
}