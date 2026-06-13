package com.store;

import com.store.controller.StoreController;

/**
 * <h1>Ponto de entrada da aplicação</h1>
 * Classe que contém o método {@code main}, dando início à execução do sistema.
 *
 * <h2>Capítulos abordados:</h2>
 * <ul>
 * <li><b>2 – Introdução a aplicativos Java:</b> método {@code main} padrão de
 * inicialização de um programa Java
 * ({@code public static void main(String[] args)}).</li>
 * <li><b>3 – Introdução a classes e objetos:</b> criação de um objeto
 * {@code StoreController} com o operador {@code new} e invocação de um método
 * ({@code controller.run()}).</li>
 * <li><b>7 – Arrays:</b> o parâmetro {@code args} é um array de {@code String}
 * que
 * permite receber argumentos da linha de comando (não utilizado, mas
 * presente).</li>
 * </ul>
 */
public class StoreApp {
    /**
     * Método principal – ponto de entrada do programa.
     * 
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        // Capítulo 3: instanciação de objeto
        StoreController controller = new StoreController();
        // Capítulo 3: chamada de método
        controller.run();
    }
}