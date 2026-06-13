package com.store.model;

import com.store.repository.GenericRepository;

/**
 * <h1>Capítulo 10 – Polimorfismo | Capítulo 9 – Herança</h1>
 * Representa uma ação de adicionar um produto ao repositório, que pode ser
 * desfeita.
 * Estende {@code UndoAction} (classe abstrata), implementando o comportamento
 * específico
 * de desfazer a adição (remover o produto).
 *
 * <h2>Capítulos abordados:</h2>
 * <ul>
 * <li><b>9 – Herança:</b> herda de {@code UndoAction} e invoca o construtor
 * padrão
 * da superclasse implicitamente (ou explicitamente se necessário).</li>
 * <li><b>10 – Polimorfismo:</b> sobrescreve {@code undo()} e
 * {@code getDescription()}
 * da classe abstrata; a pilha de desfazer trata todas as ações como
 * {@code UndoAction}.</li>
 * <li><b>8 – Classes e objetos:</b> atributos {@code final}, construtor
 * parametrizado,
 * uso de {@code this} para referenciar os atributos.</li>
 * <li><b>6 – Métodos:</b> implementação dos métodos abstratos {@code undo} e
 * {@code getDescription}.</li>
 * <li><b>16 – Coleções genéricas:</b> utiliza
 * {@code GenericRepository<Product>},
 * que internamente usa coleções genéricas.</li>
 * <li><b>14 – Strings:</b> concatenação de string na descrição.</li>
 * </ul>
 */
public class AddProductAction extends UndoAction {
    // Capítulo 8: atributos final (imutáveis após construção)
    private final Product product; // Capítulo 9/10: referência à superclasse Product
    private final GenericRepository<Product> repository; // Capítulo 16: repositório genérico

    /**
     * Construtor que recebe o produto adicionado e o repositório onde ele foi
     * inserido.
     * <b>Capítulo 8:</b> {@code this.product = product;} resolve conflito de nomes.
     * <b>Capítulo 9:</b> {@code super()} é chamado implicitamente para a classe
     * base {@code UndoAction}.
     *
     * @param product    produto que foi adicionado
     * @param repository repositório onde a operação ocorreu
     */
    public AddProductAction(Product product, GenericRepository<Product> repository) {
        this.product = product; // Capítulo 8: uso de this
        this.repository = repository;
    }

    /**
     * Desfaz a ação de adicionar, removendo o produto do repositório.
     * <b>Capítulo 10:</b> implementação polimórfica do método abstrato
     * {@code undo()}.
     * <b>Capítulo 6:</b> chamada ao método {@code remove} do repositório.
     * <b>Capítulo 3:</b> obtenção do ID do produto com {@code getId()}.
     */
    @Override
    public void undo() {
        repository.remove(product.getId()); // Capítulo 6: invocação de método; Capítulo 3: String
    }

    /**
     * Fornece uma descrição legível da ação realizada.
     * <b>Capítulo 10:</b> sobrescrita de método abstrato.
     * <b>Capítulo 14/Strings:</b> concatenação de string com operador {@code +}.
     * <b>Capítulo 6:</b> retorno de String.
     */
    @Override
    public String getDescription() {
        return "Adicionar " + product.getName(); // Capítulo 3: concatenação
    }
}