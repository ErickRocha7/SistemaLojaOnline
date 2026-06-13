package com.store.repository;

import com.store.model.Identifiable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>Capítulo 20 – Classes e métodos genéricos</h1>
 * Repositório genérico que armazena objetos que implementam
 * {@code Identifiable}.
 * Utiliza um {@code HashMap} para acesso rápido por ID e um {@code ArrayList}
 * para
 * iteração ordenada.
 *
 * <h2>Capítulos abordados:</h2>
 * <ul>
 * <li><b>20 – Genéricos:</b> classe genérica
 * {@code GenericRepository<T extends Identifiable>}
 * que pode operar sobre qualquer tipo que forneça um ID.</li>
 * <li><b>16 – Coleções genéricas:</b> uso de {@code HashMap<String, T>} e
 * {@code ArrayList<T>} para armazenamento e manipulação.</li>
 * <li><b>8 – Classes e objetos:</b> atributos {@code final} (referências
 * imutáveis),
 * inicialização inline, encapsulamento.</li>
 * <li><b>6 – Métodos:</b> implementação de CRUD básico com getters, add,
 * remove, etc.</li>
 * <li><b>7 – ArrayList:</b> utilização de {@code ArrayList} para manter a lista
 * de itens.</li>
 * <li><b>3 – Strings:</b> o ID é tratado como String.</li>
 * </ul>
 */
public class GenericRepository<T extends Identifiable> { // Capítulo 20: classe genérica com restrição
    // Capítulo 16: HashMap para mapeamento rápido ID -> objeto
    private final Map<String, T> map = new HashMap<>();
    // Capítulo 7/16: ArrayList para preservar ordem de inserção
    private final List<T> list = new ArrayList<>();

    /**
     * Adiciona um item ao repositório.
     * <b>Capítulo 16:</b> métodos {@code put} e {@code add} das coleções.
     * <b>Capítulo 6:</b> chamada a {@code getId()} do item (interface
     * Identifiable).
     */
    public void add(T item) {
        map.put(item.getId(), item); // Capítulo 16: HashMap.put
        list.add(item); // Capítulo 7: ArrayList.add
    }

    /**
     * Recupera um item pelo ID.
     * 
     * @return o item ou null se não encontrado.
     *         <b>Capítulo 16:</b> HashMap.get.
     */
    public T get(String id) {
        return map.get(id);
    }

    /**
     * Remove um item pelo ID.
     * 
     * @return o item removido ou null se não existir.
     *         <b>Capítulo 16:</b> HashMap.remove, ArrayList.remove.
     *         <b>Capítulo 4:</b> uso de if para verificar se o item foi encontrado.
     */
    public T remove(String id) {
        T removed = map.remove(id); // Capítulo 16: HashMap.remove
        if (removed != null) { // Capítulo 4: instrução if
            list.remove(removed); // Capítulo 7: ArrayList.remove(Object)
        }
        return removed;
    }

    /**
     * Retorna uma cópia da lista de todos os itens.
     * <b>Capítulo 7:</b> criação de novo ArrayList a partir de list (defensivo).
     * <b>Capítulo 16:</b> retorno de lista genérica.
     */
    public List<T> getAll() {
        return new ArrayList<>(list); // Capítulo 7: construtor de cópia
    }

    /**
     * Verifica se existe um item com o ID fornecido.
     * <b>Capítulo 16:</b> HashMap.containsKey.
     */
    public boolean contains(String id) {
        return map.containsKey(id);
    }
}