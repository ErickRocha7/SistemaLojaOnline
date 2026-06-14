package com.store.view;

import com.store.model.OrderComponent;
import com.store.model.Product;
import java.util.List;
import java.util.Scanner;

/**
 * <h1>Camada de visualização (View) – Entrada e saída</h1>
 * Responsável por toda a interação com o usuário via console.
 * Utiliza extensivamente os conceitos dos primeiros capítulos.
 *
 * <h2>Capítulos abordados:</h2>
 * <ul>
 * <li><b>2 – Introdução a aplicativos Java, entrada/saída e operadores:</b> uso
 * de
 * {@code Scanner} para leitura do teclado, métodos {@code System.out.print},
 * {@code println} e {@code printf} para saída formatada.</li>
 * <li><b>3 – Classes, objetos, métodos e strings:</b> criação de classe,
 * instanciação
 * de {@code Scanner}, definição de métodos que retornam valores e recebem
 * parâmetros, manipulação de strings.</li>
 * <li><b>4 – Instruções de controle:</b> loops {@code while} para validação de
 * entrada,
 * laço {@code for} tradicional em {@code displayProducts}.</li>
 * <li><b>5 – Operadores lógicos:</b> uso de {@code !} (negação) nas validações
 * de
 * entrada ({@code !scanner.hasNextInt()}).</li>
 * <li><b>6 – Métodos:</b> sobrecarga implícita nos diferentes métodos de
 * leitura
 * ({@code readInt}, {@code readDouble}, {@code readString}).</li>
 * <li><b>7 – Arrays e ArrayLists:</b> recebe {@code List<Product>} e itera com
 * for
 * tradicional e {@code size()}/{@code get()}.</li>
 * <li><b>10 – Polimorfismo:</b> método {@code displayOrder} recebe
 * {@code OrderComponent}
 * e invoca {@code print(0)} polimorficamente.</li>
 * <li><b>15 – Arquivos, fluxos e serialização:</b> menus atualizados incluem
 * opções
 * para salvar dados e importar arquivos CSV.</li>
 * </ul>
 */
public class StoreView {
    // Capítulo 2: Scanner para entrada de dados (System.in)
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Exibe uma mensagem simples no console.
     * <b>Capítulo 2:</b> {@code System.out.println}.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Exibe o menu principal e retorna a opção escolhida.
     * <b>Capítulo 2:</b> {@code System.out.println} para montar o menu.
     * <b>Capítulo 3:</b> strings com quebras de linha (\n).
     * <b>Capítulo 6:</b> chamada ao método {@code readInt}.
     * <b>Capítulo 15:</b> inclui opções para salvar dados manualmente e sair
     * salvando.
     */
    public int showMainMenu() {
        System.out.println("\n==== MENU PRINCIPAL ====");
        System.out.println("1. Gerenciar Produtos");
        System.out.println("2. Criar Pedido");
        System.out.println("3. Desfazer Última Ação");
        System.out.println("4. Exibir Fatorial (recursão)");
        System.out.println("5. Buscar produto (busca binária recursiva)");
        System.out.println("6. Salvar dados em arquivo");
        System.out.println("7. Salvar e Sair");
        return readInt("Escolha uma opção: ");
    }

    /**
     * Exibe o submenu de produtos e retorna a opção.
     * <b>Capítulo 15:</b> inclui opção para importar CSV.
     */
    public int showProductMenu() {
        System.out.println("\n--- Produtos ---");
        System.out.println("1. Adicionar Produto");
        System.out.println("2. Listar Todos");
        System.out.println("3. Buscar por ID");
        System.out.println("4. Buscar por Palavra-chave");
        System.out.println("5. Aplicar Desconto");
        System.out.println("6. Remover Produto");
        System.out.println("7. Importar produtos de CSV");
        System.out.println("8. Voltar");
        return readInt("Opção: ");
    }

    /**
     * Lê um inteiro do teclado com validação de entrada.
     *
     * <b>Capítulo 2:</b> {@code scanner.nextInt()} para leitura.
     * <b>Capítulo 4:</b> loop {@code while} que se repete enquanto a entrada não
     * for um inteiro.
     * <b>Capítulo 5:</b> operador lógico {@code !} em
     * {@code !scanner.hasNextInt()}.
     * <b>Capítulo 3:</b> chamada a {@code scanner.next()} para descartar a entrada
     * inválida.
     * <b>Capítulo 2:</b> {@code scanner.nextLine()} para limpar o buffer do
     * teclado.
     */
    public int readInt(String prompt) {
        System.out.print(prompt); // Capítulo 2: saída sem quebra de linha
        while (!scanner.hasNextInt()) { // Capítulo 4: while; Capítulo 5: !
            System.out.print("Valor inválido. " + prompt); // Capítulo 3: concatenação
            scanner.next(); // Capítulo 2: descarta token inválido
        }
        int value = scanner.nextInt(); // Capítulo 2: leitura de inteiro
        scanner.nextLine(); // limpa o newline
        return value;
    }

    /**
     * Lê um double do teclado com validação.
     * Mesmos conceitos de {@code readInt}, adaptado para {@code double}.
     */
    public double readDouble(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) { // Capítulo 5: !
            System.out.print("Valor inválido. " + prompt);
            scanner.next();
        }
        double value = scanner.nextDouble(); // Capítulo 2: leitura de double
        scanner.nextLine();
        return value;
    }

    /**
     * Lê uma linha de texto do teclado.
     * <b>Capítulo 2:</b> {@code scanner.nextLine()}.
     */
    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Exibe todos os produtos de uma lista.
     * <b>Capítulo 7:</b> uso de {@code List} com {@code isEmpty()}, {@code size()},
     * {@code get()}.
     * <b>Capítulo 4:</b> laço {@code for} tradicional com inicialização, condição e
     * incremento ({@code i++}).
     * <b>Capítulo 10:</b> polimorfismo: cada objeto {@code Product} imprime sua
     * própria
     * descrição via {@code toString()}.
     */
    public void displayProducts(List<Product> products) {
        if (products.isEmpty()) { // Capítulo 7: método isEmpty
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        for (int i = 0; i < products.size(); i++) { // Capítulo 4: for; Capítulo 7: size()
            System.out.println(products.get(i)); // Capítulo 7: get()
        }
    }

    /**
     * Exibe um único produto.
     * <b>Capítulo 10:</b> {@code println(p)} invoca implicitamente
     * {@code toString()}
     * polimórfico.
     */
    public void displayProduct(Product p) {
        System.out.println(p);
    }

    /**
     * Exibe a estrutura completa de um pedido (composto ou item).
     * <b>Capítulo 10 – Polimorfismo:</b> recebe {@code OrderComponent} e chama
     * {@code print}, que se comporta diferentemente para folhas e compostos.
     */
    public void displayOrder(OrderComponent order) {
        order.print(0); // Capítulo 10: polimorfismo
    }
}