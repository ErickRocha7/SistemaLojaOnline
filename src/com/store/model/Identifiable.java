package com.store.model;

/**
 * <h1>Capítulo 10 – Programação orientada a objetos: polimorfismo e
 * interfaces</h1>
 * Interface que define o contrato para objetos que possuem um identificador
 * único.
 *
 * <p>
 * <b>Capítulo 10:</b> declaração de interface como tipo abstrato que será
 * implementado
 * por várias classes (ex.: {@code Product}) e usado polimorficamente por
 * coleções genéricas
 * como {@code GenericRepository<T extends Identifiable>}.
 * </p>
 * <p>
 * <b>Capítulo 6:</b> declaração de método público abstrato (implicitamente).
 * </p>
 */
public interface Identifiable {
    /**
     * Retorna o identificador único do objeto.
     * 
     * @return ID como String.
     *
     *         <b>Capítulo 6:</b> método sem implementação (abstrato).
     *         <b>Capítulo 14:</b> retorna uma {@code String}, tipo amplamente usado
     *         em todo o sistema.
     */
    String getId();
}