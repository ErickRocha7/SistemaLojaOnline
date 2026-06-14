package com.store.model;

import com.store.repository.GenericRepository;

/**
 * <h1>Capítulo 10 – Polimorfismo | Capítulo 9 – Herança</h1>
 * Representa a ação de remover um produto do repositório, que pode ser
 * desfeita. Estende {@code UndoAction} e implementa o comportamento de
 * desfazer a remoção, ou seja, adicionar o produto de volta.
 *
 * <h2>Capítulos abordados:</h2>
 * <ul>
 * <li><b>9 – Herança:</b> herda da classe abstrata {@code UndoAction}.</li>
 * <li><b>10 – Polimorfismo:</b> sobrescrita dos métodos abstratos
 * {@code undo()} e
 * {@code getDescription()}; pode ser tratada polimorficamente como
 * {@code UndoAction}.</li>
 * <li><b>8 – Classes e objetos:</b> atributos {@code final}, construtor com uso
 * de
 * {@code this} para inicializar os campos.</li>
 * <li><b>16 – Coleções genéricas:</b> utiliza
 * {@code GenericRepository<Product>} que
 * internamente usa coleções genéricas.</li>
 * <li><b>14/Strings:</b> concatenação de strings na descrição.</li>
 * <li><b>15 – Arquivos, fluxos e serialização:</b> herda a capacidade de
 * serialização
 * de {@code UndoAction}, permitindo que ações pendentes na pilha de desfazer
 * sejam persistidas.</li>
 * </ul>
 */
public class RemoveProductAction extends UndoAction {
    // Capítulo 15: identificador de versão de serialização
    private static final long serialVersionUID = 1L;

    // Capítulo 8: atributos final – imutáveis após construção
    private final Product product;
    private final GenericRepository<Product> repository;

    /**
     * Construtor que guarda o produto removido e o repositório onde a operação
     * ocorreu.
     * 
     * @param product    produto que foi removido
     * @param repository repositório afetado
     */
    public RemoveProductAction(Product product, GenericRepository<Product> repository) {
        this.product = product; // Capítulo 8: this
        this.repository = repository;
    }

    /**
     * Desfaz a remoção adicionando o produto de volta ao repositório.
     * <b>Capítulo 10:</b> implementação polimórfica de {@code undo()}.
     * <b>Capítulo 6:</b> chamada ao método {@code add} do repositório.
     */
    @Override
    public void undo() {
        repository.add(product);
    }

    /**
     * Retorna uma descrição textual da ação.
     * <b>Capítulo 10:</b> sobrescrita de método abstrato.
     * <b>Capítulo 14:</b> concatenação de strings com {@code +}.
     */
    @Override
    public String getDescription() {
        return "Remover " + product.getName(); // Capítulo 3: concatenação de string
    }
}