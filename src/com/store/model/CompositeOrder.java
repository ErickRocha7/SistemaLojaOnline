package com.store.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Capítulo 10 – Polimorfismo (padrão Composite)</h1>
 * Representa um pedido composto, que pode conter itens simples
 * ({@code OrderItem}) ou outros pedidos compostos, formando uma estrutura em
 * árvore.
 *
 * <h2>Capítulos abordados:</h2>
 * <ul>
 * <li><b>10 – Polimorfismo:</b> herda de {@code OrderComponent} (classe
 * abstrata) e sobrescreve métodos para tratar componentes uniformemente
 * (Composite Pattern).</li>
 * <li><b>7 – Arrays e ArrayLists:</b> utiliza {@code ArrayList<OrderComponent>}
 * para armazenar os componentes filhos.</li>
 * <li><b>16 – Coleções genéricas:</b> {@code ArrayList} é uma coleção
 * genérica.</li>
 * <li><b>8 – Classes e objetos:</b> atributo
 * {@code final List<OrderComponent> components}
 * (referência imutável), inicialização na declaração.</li>
 * <li><b>6 – Métodos:</b> implementação de {@code add}, {@code getPrice},
 * {@code print}, e agora {@code isEmpty}.</li>
 * <li><b>4/5 – Controle:</b> laço {@code for} (enhanced for) em
 * {@code print}.</li>
 * <li><b>2 – Entrada/saída:</b> {@code System.out.printf} para saída
 * formatada.</li>
 * <li><b>14 – Strings:</b> método {@code repeat} para indentação, formatação
 * com {@code printf}.</li>
 * <li><b>15 – Arquivos, fluxos e serialização:</b> herda a capacidade de
 * serialização de {@code OrderComponent}, permitindo que pedidos compostos
 * completos sejam persistidos.</li>
 * </ul>
 */
public class CompositeOrder extends OrderComponent {
    // Capítulo 15: identificador de versão de serialização
    private static final long serialVersionUID = 1L;

    // Capítulo 7: ArrayList para armazenar componentes
    // Capítulo 8: atributo final (referência não pode ser alterada)
    private final List<OrderComponent> components = new ArrayList<>();

    /**
     * Adiciona um componente (item ou subpedido) à lista de filhos.
     * <b>Capítulo 10:</b> sobrescrita do método {@code add} da classe base,
     * permitindo a construção da árvore.
     * <b>Capítulo 7/16:</b> uso do método {@code add} de ArrayList.
     */
    @Override
    public void add(OrderComponent component) {
        components.add(component);
    }

    /**
     * Calcula o preço total do pedido composto percorrendo todos os filhos
     * e somando seus preços.
     * <b>Capítulo 10:</b> polimorfismo – cada componente sabe calcular seu próprio
     * preço.
     * <b>Capítulo 16:</b> uso de stream e method reference.
     * <b>Capítulo 6:</b> invocação de método sobre cada objeto da coleção.
     */
    @Override
    public double getPrice() {
        return components.stream()
                .mapToDouble(OrderComponent::getPrice)
                .sum();
    }

    /**
     * Imprime recursivamente a estrutura do pedido composto, indentando conforme a
     * profundidade na árvore.
     * <b>Capítulo 10:</b> polimorfismo – cada componente imprime a si mesmo.
     * <b>Capítulo 4/5:</b> laço for-each.
     * <b>Capítulo 2:</b> {@code System.out.printf}.
     * <b>Capítulo 14:</b> {@code " ".repeat(indentation)}.
     */
    @Override
    public void print(int indentation) {
        System.out.printf("%sPedido Composto (Total: R$ %.2f):%n",
                " ".repeat(indentation), getPrice());
        for (OrderComponent comp : components) {
            comp.print(indentation + 2);
        }
    }

    /**
     * Verifica se o pedido composto não contém nenhum componente.
     * <b>Refatoração:</b> método adicionado para evitar a inclusão de
     * sub‑pedidos vazios.
     *
     * @return true se não houver itens ou sub‑pedidos.
     */
    public boolean isEmpty() {
        return components.isEmpty();
    }
}