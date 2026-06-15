package com.store.controller;

import com.store.datastructure.Stack;
import com.store.exception.ProductNotFoundException;
import com.store.model.*;
import com.store.repository.GenericRepository;
import com.store.util.FileManager;
import com.store.util.MathUtil;
import com.store.util.StringUtil;
import com.store.view.StoreView;

import java.io.IOException;
import java.util.*;

/**
 * <h1>Controlador principal do sistema de loja online</h1>
 * Esta classe orquestra as operações do sistema, conectando a camada de
 * visualização ({@code StoreView}) com os modelos e repositórios. Ela contém
 * exemplos práticos de quase todos os capítulos do livro.
 *
 * <h2>Capítulos abordados neste arquivo:</h2>
 * <ul>
 * <li><b>Capítulo 2:</b> Entrada/saída via métodos da view, operadores
 * aritméticos.</li>
 * <li><b>Capítulo 3:</b> Criação de objetos, chamadas de métodos, manipulação
 * de strings.</li>
 * <li><b>Capítulo 4:</b> Estruturas de repetição (while, for-each) e seleção
 * (if, if-else, switch).</li>
 * <li><b>Capítulo 5:</b> Operadores lógicos (&&, ||, !) em expressões
 * condicionais.</li>
 * <li><b>Capítulo 6:</b> Declaração e invocação de métodos
 * (privados/públicos).</li>
 * <li><b>Capítulo 7:</b> Uso de ArrayList e conversão para array.</li>
 * <li><b>Capítulo 8:</b> Atributos final, construtor, referência this
 * implícita.</li>
 * <li><b>Capítulo 9:</b> Herança (Product -> Book/Electronics).</li>
 * <li><b>Capítulo 10:</b> Polimorfismo (OrderComponent, Discountable,
 * UndoAction).</li>
 * <li><b>Capítulo 11:</b> Tratamento de exceções com try-catch, throw e
 * exceções customizadas.</li>
 * <li><b>Capítulo 14:</b> Busca com regex via método matchesKeyword.</li>
 * <li><b>Capítulo 15:</b> Persistência de dados em arquivos via FileManager
 * (serialização binária) e importação de CSV com Scanner + File.</li>
 * <li><b>Capítulo 16:</b> Coleções genéricas (ArrayList,
 * Comparator.comparing).</li>
 * <li><b>Capítulo 18:</b> Recursão (fatorial e busca binária recursiva).</li>
 * <li><b>Capítulo 20:</b> Uso de métodos genéricos
 * (MathUtil.recursiveBinarySearch).</li>
 * <li><b>Capítulo 21:</b> Uso da pilha genérica personalizada Stack.</li>
 * </ul>
 */
public class StoreController {
    // Capítulo 8: atributos final (constantes de referência)
    private final StoreView view;
    private GenericRepository<Product> productRepo;
    private final Stack<UndoAction> undoStack; // Capítulo 21: estrutura genérica personalizada
    private List<OrderComponent> orders; // Capítulo 7 e 16: ArrayList (listas genéricas)

    // Capítulo 15: nomes dos arquivos de persistência (constantes)
    private static final String PRODUCTS_FILE = "produtos.dat";
    private static final String ORDERS_FILE = "pedidos.dat";

    /**
     * Construtor padrão.
     * <b>Capítulo 3:</b> criação de objetos com new.
     * <b>Capítulo 7:</b> inicialização de ArrayList.
     */
    public StoreController() {
        view = new StoreView();
        productRepo = new GenericRepository<>();
        undoStack = new Stack<>();
        orders = new ArrayList<>(); // Capítulo 7/16: ArrayList
    }

    /**
     * Método principal de execução do programa.
     * <b>Capítulo 4:</b> loop while, switch-case.
     * <b>Capítulo 5:</b> operador lógico ! (NOT) em !exit.
     * <b>Capítulo 11:</b> bloco try-catch genérico para capturar exceções.
     * <b>Capítulo 15:</b> carrega dados salvos no início e salva ao sair.
     * <b>Refatoração:</b> utiliza try-finally para garantir que o Scanner
     * seja encerrado via {@code view.shutdown()}. O tratamento de exceções
     * agora exibe o stack trace, auxiliando na depuração sem mascarar erros.
     */
    public void run() {
        // Capítulo 15: carrega dados persistentes (produtos e pedidos)
        loadData();

        boolean exit = false; // Capítulo 2: declaração e inicialização de variável
        try {
            while (!exit) { // Capítulo 4: while; Capítulo 5: !
                try {
                    int option = view.showMainMenu(); // Capítulo 2: entrada de dados via teclado (Scanner interno)
                    switch (option) { // Capítulo 4: switch
                        case 1:
                            manageProducts();
                            break; // Capítulo 4: break
                        case 2:
                            createOrder();
                            break;
                        case 3:
                            undoLastAction();
                            break;
                        case 4:
                            factorialDemo();
                            break;
                        case 5:
                            recursiveBinarySearchDemo();
                            break;
                        case 6:
                            saveDataManually(); // Capítulo 15: salvamento manual
                            break;
                        case 7:
                            saveDataOnExit(); // Capítulo 15: salva e sai
                            exit = true; // Capítulo 2: atribuição
                            break;
                        default: // Capítulo 4: cláusula default
                            view.showMessage("Opção inválida!");
                    }
                } catch (Exception e) { // Capítulo 11: captura de exceção
                    view.showMessage("Erro: " + e.getMessage()); // Capítulo 3: concatenação de strings
                    e.printStackTrace(); // Loga o stack trace para diagnóstico
                }
            }
        } finally {
            // Fecha o Scanner para liberar os recursos do System.in
            view.shutdown();
        }
    }

    /**
     * Carrega os dados persistentes (produtos e pedidos) dos arquivos.
     * <b>Capítulo 15:</b> utiliza FileManager para desserializar objetos.
     * <b>Capítulo 11:</b> tratamento de exceções com multi-catch.
     */
    private void loadData() {
        try {
            // Capítulo 15: carrega produtos do arquivo binário
            productRepo = FileManager.loadProducts(PRODUCTS_FILE);
            // Capítulo 15: carrega pedidos do arquivo binário
            orders = FileManager.loadOrders(ORDERS_FILE);
            view.showMessage("Dados carregados com sucesso. " +
                    productRepo.getAll().size() + " produto(s) e " +
                    orders.size() + " pedido(s) encontrado(s).");
        } catch (IOException | ClassNotFoundException e) { // Capítulo 11: multi-catch
            view.showMessage("Não foi possível carregar dados anteriores. " +
                    "Iniciando com dados vazios.");
            // Capítulo 3: criação de novos objetos
            productRepo = new GenericRepository<>();
            orders = new ArrayList<>();
        }
    }

    /**
     * Salva os dados atuais nos arquivos (opção explícita do menu).
     * <b>Capítulo 15:</b> utiliza FileManager para serializar objetos.
     */
    private void saveDataManually() {
        try {
            // Capítulo 15: salva produtos
            FileManager.saveProducts(productRepo, PRODUCTS_FILE);
            // Capítulo 15: salva pedidos
            FileManager.saveOrders(orders, ORDERS_FILE);
            view.showMessage("Dados salvos com sucesso em '" + PRODUCTS_FILE +
                    "' e '" + ORDERS_FILE + "'.");
        } catch (IOException e) { // Capítulo 11: captura de exceção de E/S
            view.showMessage("Erro ao salvar: " + e.getMessage());
        }
    }

    /**
     * Salva os dados antes de sair da aplicação.
     * <b>Capítulo 6:</b> método privado auxiliar.
     */
    private void saveDataOnExit() {
        saveDataManually();
        view.showMessage("Encerrando o sistema...");
    }

    /**
     * Submenu de gerenciamento de produtos.
     * <b>Capítulo 4:</b> while, switch.
     * <b>Capítulo 15:</b> inclui opção para importar CSV.
     */
    private void manageProducts() {
        boolean back = false;
        while (!back) {
            int op = view.showProductMenu();
            switch (op) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    listProducts();
                    break;
                case 3:
                    searchById();
                    break;
                case 4:
                    searchByKeyword();
                    break;
                case 5:
                    applyDiscount();
                    break;
                case 6:
                    removeProduct();
                    break;
                case 7:
                    importProductsFromCSV(); // Capítulo 15: nova opção
                    break;
                case 8:
                    back = true;
                    break;
                default:
                    view.showMessage("Opção inválida!");
            }
        }
    }

    /**
     * Adiciona um novo produto (Book ou Electronics) ao repositório.
     *
     * <b>Capítulo 2:</b> leitura de inteiro, double e string.
     * <b>Capítulo 4:</b> if-else para decidir tipo.
     * <b>Capítulo 9:</b> herança – Product é superclasse abstrata.
     * <b>Capítulo 10:</b> polimorfismo – referência Product pode apontar para Book
     * ou Electronics.
     * <b>Capítulo 21:</b> push na pilha de desfazer (UndoAction).
     */
    private void addProduct() {
        view.showMessage("\n1. Livro  2. Eletrônico");
        int type = view.readInt("Tipo: "); // Capítulo 2: entrada de inteiro
        String name = view.readString("Nome: "); // Capítulo 2/3: leitura de string
        double price = view.readDouble("Preço: "); // Capítulo 2: leitura de double
        Product product; // Capítulo 10: referência polimórfica
        if (type == 1) { // Capítulo 4: if-else
            String author = view.readString("Autor: ");
            String isbn = view.readString("ISBN: ");
            product = new Book(name, price, author, isbn); // Capítulo 9: instanciação de subclasse
        } else {
            String brand = view.readString("Marca: ");
            int warranty = view.readInt("Garantia (meses): ");
            product = new Electronics(name, price, brand, warranty);
        }
        productRepo.add(product); // Capítulo 16: coleção genérica
        UndoAction action = new AddProductAction(product, productRepo);
        undoStack.push(action); // Capítulo 21: uso da pilha
        view.showMessage("Produto adicionado: " + product.getId());
    }

    /**
     * Lista todos os produtos cadastrados.
     * <b>Capítulo 7:</b> uso de ArrayList retornado por getAll().
     */
    private void listProducts() {
        view.displayProducts(productRepo.getAll());
    }

    /**
     * Busca produto por ID e exibe. Se não encontrado, lança exceção customizada.
     * <b>Capítulo 11:</b> throw e catch de ProductNotFoundException.
     * <b>Capítulo 4:</b> if-else.
     */
    private void searchById() {
        String id = view.readString("ID do produto: ");
        Product p = productRepo.get(id);
        if (p != null) { // Capítulo 5: operador !=
            view.displayProduct(p);
        } else {
            try {
                throw new ProductNotFoundException(id); // Capítulo 11: lançamento de exceção
            } catch (ProductNotFoundException e) { // Capítulo 11: captura
                view.showMessage(e.getMessage());
            }
        }
    }

    /**
     * Busca produtos cujo nome ou ID correspondam a uma palavra-chave (regex).
     * <b>Capítulo 14:</b> uso de expressões regulares via {@code matchesKeyword()}.
     * <b>Capítulo 7:</b> iteração sobre ArrayList com for-each.
     * <b>Capítulo 5:</b> operador lógico de curto-circuito {@code ||} dentro do
     * método matchesKeyword.
     */
    private void searchByKeyword() {
        String keyword = view.readString("Palavra-chave (regex permitida): "); // Capítulo 14
        List<Product> all = productRepo.getAll(); // Capítulo 16: retorna ArrayList<Product>
        boolean found = false; // Capítulo 2: inicialização
        for (Product p : all) { // Capítulo 7: enhanced for (for-each)
            if (p.matchesKeyword(keyword)) { // Capítulo 14: Pattern/Matcher internamente
                view.displayProduct(p);
                found = true; // Capítulo 2: atribuição
            }
        }
        if (!found) { // Capítulo 5: operador !
            view.showMessage("Nenhum produto corresponde.");
        }
    }

    /**
     * Aplica desconto a um produto, se ele implementar a interface
     * {@code Discountable}.
     * <b>Capítulo 10:</b> operador {@code instanceof} e casting para interface.
     * <b>Capítulo 3:</b> uso de {@code String.format} para formatar valor
     * monetário.
     */
    private void applyDiscount() {
        String id = view.readString("ID do produto: ");
        Product p = productRepo.get(id);
        if (p == null) { // Capítulo 5: comparação ==
            view.showMessage("Produto não encontrado.");
            return; // Capítulo 6: retorno antecipado
        }
        if (p instanceof Discountable) { // Capítulo 10: verificação de tipo em tempo de execução
            double perc = view.readDouble("Percentual de desconto: "); // Capítulo 2
            ((Discountable) p).applyDiscount(perc); // Capítulo 10: downcasting para interface
            view.showMessage("Desconto aplicado. Novo preço: R$ " +
                    String.format("%.2f", p.getPrice())); // Capítulo 3: String.format
        } else {
            view.showMessage("Este produto não aceita desconto.");
        }
    }

    /**
     * Remove um produto e registra ação para possível desfazer.
     * <b>Capítulo 21:</b> push na pilha.
     * <b>Capítulo 7:</b> remoção de elemento da lista interna do repositório.
     */
    private void removeProduct() {
        String id = view.readString("ID do produto: ");
        Product p = productRepo.get(id);
        if (p == null) {
            view.showMessage("Produto não encontrado.");
            return;
        }
        productRepo.remove(id);
        UndoAction action = new RemoveProductAction(p, productRepo); // Capítulo 10: polimorfismo
        undoStack.push(action);
        view.showMessage("Produto removido.");
    }

    /**
     * Importa produtos de um arquivo CSV usando Scanner + File.
     * <b>Capítulo 15:</b> leitura de arquivo texto com Scanner.
     * <b>Capítulo 11:</b> tratamento de exceção de E/S.
     */
    private void importProductsFromCSV() {
        String filename = view.readString("Caminho do arquivo CSV: "); // Capítulo 2: entrada de string
        try {
            // Capítulo 15: importação via FileManager
            int count = FileManager.importFromCSV(filename, productRepo);
            view.showMessage("Importação concluída. " + count + " produto(s) adicionado(s).");
        } catch (IOException e) { // Capítulo 11: captura de exceção
            view.showMessage("Erro na importação: " + e.getMessage());
        }
    }

    /**
     * Cria um pedido composto (CompositeOrder), podendo conter itens simples
     * ou sub-pedidos. Demonstra o padrão Composite (Capítulo 10 – Polimorfismo).
     * <b>Capítulo 4:</b> while, if-else encadeado, continue.
     */
    private void createOrder() {
        CompositeOrder order = new CompositeOrder(); // Capítulo 10: Composite
        boolean addMore = true;
        while (addMore) {
            view.showMessage("\n1. Adicionar item  2. Adicionar sub-pedido  3. Finalizar");
            int choice = view.readInt("Escolha: ");
            if (choice == 1) {
                String id = view.readString("ID do produto: ");
                Product p = productRepo.get(id);
                if (p == null) {
                    view.showMessage("Produto não encontrado.");
                    continue; // Capítulo 4/5: continue (pula iteração)
                }
                int qty = view.readInt("Quantidade: ");
                if (qty <= 0) { // Capítulo 4: condição if com operador relacional
                    view.showMessage("Quantidade inválida.");
                    continue;
                }
                order.add(new OrderItem(p, qty)); // Capítulo 10: OrderItem é folha do Composite
                view.showMessage("Item adicionado.");
            } else if (choice == 2) {
                view.showMessage("Criando sub-pedido...");
                // Cria um sub-pedido recursivamente (a estrutura é composta, mas a
                // construção é iterativa – a recursão ocorre no método de exibição)
                CompositeOrder subOrder = new CompositeOrder();
                fillOrder(subOrder); // Capítulo 6: chamada de método auxiliar
                order.add(subOrder);
                view.showMessage("Sub-pedido adicionado.");
            } else if (choice == 3) {
                addMore = false; // Capítulo 2: atribuição
            } else {
                view.showMessage("Opção inválida.");
            }
        }
        orders.add(order); // Capítulo 7: adiciona ao ArrayList
        view.showMessage("Pedido finalizado. Resumo:");
        view.displayOrder(order); // Capítulo 10: print recursivo da árvore
    }

    /**
     * Método auxiliar que preenche um sub-pedido com itens.
     * <b>Capítulo 4:</b> while, if-else.
     */
    private void fillOrder(CompositeOrder order) {
        boolean adding = true;
        while (adding) {
            int ch = view.readInt("1. Item  2. Finalizar sub-pedido: ");
            if (ch == 1) {
                String id = view.readString("ID do produto: ");
                Product p = productRepo.get(id);
                if (p == null) {
                    view.showMessage("Produto não encontrado.");
                    continue;
                }
                int qty = view.readInt("Quantidade: ");
                order.add(new OrderItem(p, qty));
            } else {
                adding = false;
            }
        }
    }

    /**
     * Desfaz a última ação registrada (adição ou remoção de produto).
     * <b>Capítulo 21:</b> pop da pilha personalizada.
     * <b>Capítulo 10:</b> polimorfismo – {@code UndoAction} é abstrata, executa
     * {@code undo()} de acordo com o tipo real.
     * <b>Capítulo 5:</b> operador ! e método isEmpty().
     */
    private void undoLastAction() {
        if (undoStack.isEmpty()) { // Capítulo 21: método isEmpty da pilha
            view.showMessage("Nada a desfazer.");
            return;
        }
        UndoAction action = undoStack.pop(); // Capítulo 21: pop
        action.undo(); // Capítulo 10: polimorfismo
        view.showMessage("Ação desfeita: " + action.getDescription());
    }

    /**
     * Demonstra o cálculo de fatorial usando método recursivo.
     * <b>Capítulo 18 – Recursão:</b> chamada ao método factorial.
     * <b>Capítulo 4:</b> validação com if e operadores relacionais (&&).
     */
    private void factorialDemo() {
        int n = view.readInt("Valor para fatorial (0-20): "); // Capítulo 2: entrada int
        if (n < 0 || n > 20) { // Capítulo 5: operadores lógicos || e &&
            view.showMessage("Intervalo inválido.");
            return;
        }
        long result = MathUtil.factorial(n); // Capítulo 18: recursão; Capítulo 6: método estático
        view.showMessage(n + "! = " + result); // Capítulo 3: concatenação de string
    }

    /**
     * Demonstra a busca binária recursiva sobre o array de produtos.
     * <b>Capítulo 18 – Recursão:</b> a busca é implementada recursivamente em
     * MathUtil.
     * <b>Capítulo 7:</b> conversão de List para array com toArray.
     * <b>Capítulo 16:</b> uso de Comparator.comparing para ordenação.
     * <b>Capítulo 20:</b> método genérico recursiveBinarySearch.
     */
    private void recursiveBinarySearchDemo() {
        List<Product> list = productRepo.getAll(); // Capítulo 16: ArrayList
        if (list.isEmpty()) { // Capítulo 5: método isEmpty
            view.showMessage("Nenhum produto para buscar.");
            return;
        }
        Product[] array = list.toArray(new Product[0]); // Capítulo 7: conversão para array
        Arrays.sort(array, Comparator.comparing(Product::getId)); // Capítulo 16: Comparator
        view.showMessage("Produtos ordenados por ID:");
        for (Product p : array) { // Capítulo 7: for-each
            System.out.println(p.getId() + " - " + p.getName()); // Capítulo 2: saída (println)
        }
        String keyId = view.readString("ID para buscar: ");
        Product key = productRepo.get(keyId);
        if (key == null) {
            view.showMessage("ID não existe.");
            return;
        }
        // Capítulo 18 e 20: método recursivo genérico
        int index = MathUtil.recursiveBinarySearch(array, key, 0, array.length - 1);
        if (index >= 0) { // Capítulo 4: if-else
            view.showMessage("Encontrado na posição " + index + ": " + array[index]);
        } else {
            view.showMessage("Não encontrado (inesperado).");
        }
    }
}