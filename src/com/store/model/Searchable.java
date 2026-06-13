package com.store.model;

/**
 * <h1>Capítulo 10 – Programação orientada a objetos: polimorfismo e
 * interfaces</h1>
 * Interface que define a capacidade de um objeto ser pesquisado por
 * palavra-chave.
 *
 * <p>
 * <b>Capítulo 10:</b> declaração de interface como tipo abstrato. Classes como
 * {@code Product} implementam {@code Searchable}, permitindo que coleções de
 * produtos
 * possam ser pesquisadas polimorficamente.
 * </p>
 * <p>
 * <b>Capítulo 6:</b> declaração de método público abstrato (implicitamente).
 * </p>
 * <p>
 * <b>Capítulo 14 – Expressões regulares:</b> a implementação em {@code Product}
 * utiliza regex para realizar a busca.
 * </p>
 */
public interface Searchable {
    /**
     * Verifica se o objeto corresponde à palavra-chave fornecida.
     * 
     * @param keyword termo de busca (pode ser uma expressão regular)
     * @return true se houver correspondência
     */
    boolean matchesKeyword(String keyword);
}