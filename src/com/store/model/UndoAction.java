package com.store.model;

import java.io.Serializable;

/**
 * <h1>Capítulo 10 – Polimorfismo (classe abstrata) | Capítulo 9 – Herança</h1>
 * Classe abstrata que representa uma ação que pode ser desfeita (padrão Command
 * simplificado). Subclasses concretas definem como desfazer a operação e
 * fornecem
 * uma descrição.
 *
 * <h2>Capítulos abordados:</h2>
 * <ul>
 * <li><b>10 – Polimorfismo:</b> define a interface comum para todas as ações
 * desfazíveis;
 * a pilha de desfazer trabalha com referências {@code UndoAction}.</li>
 * <li><b>8 – Classes e objetos:</b> declaração de classe abstrata com métodos
 * abstratos.</li>
 * <li><b>9 – Herança:</b> será a superclasse de {@code AddProductAction} e
 * {@code RemoveProductAction}.</li>
 * <li><b>15 – Arquivos, fluxos e serialização:</b> implementa
 * {@code Serializable}
 * para que as ações armazenadas na pilha de desfazer possam, futuramente, ser
 * persistidas junto com os demais dados do sistema.</li>
 * </ul>
 */
public abstract class UndoAction implements Serializable {
    // Capítulo 15: identificador de versão de serialização
    private static final long serialVersionUID = 1L;

    /**
     * Executa a operação inversa, desfazendo a ação.
     * <b>Capítulo 10:</b> método abstrato que será implementado polimorficamente.
     */
    public abstract void undo();

    /**
     * Retorna uma descrição legível da ação (usada na interface com o usuário).
     * <b>Capítulo 10:</b> método abstrato.
     * 
     * @return descrição da ação
     */
    public abstract String getDescription();
}