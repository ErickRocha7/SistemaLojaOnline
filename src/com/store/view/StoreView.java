package com.store.view;

import com.store.model.Product;
import java.util.List;
import java.util.Scanner;

public class StoreView {
    private final Scanner scanner = new Scanner(System.in);

    public void showMessage(String message) {
        System.out.println(message);
    }

    public int showMainMenu() {
        System.out.println("\n==== MENU PRINCIPAL ====");
        System.out.println("1. Gerenciar Produtos");
        System.out.println("2. Criar Pedido");
        System.out.println("3. Desfazer Última Ação");
        System.out.println("4. Exibir Fatorial (recursão)");
        System.out.println("5. Buscar produto (busca binária recursiva)");
        System.out.println("6. Sair");
        return readInt("Escolha uma opção: ");
    }

    public int showProductMenu() {
        System.out.println("\n--- Produtos ---");
        System.out.println("1. Adicionar Produto");
        System.out.println("2. Listar Todos");
        System.out.println("3. Buscar por ID");
        System.out.println("4. Buscar por Palavra-chave");
        System.out.println("5. Aplicar Desconto");
        System.out.println("6. Remover Produto");
        System.out.println("7. Voltar");
        return readInt("Opção: ");
    }

    public int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Valor inválido. " + prompt);
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // limpar buffer
        return value;
    }

    public double readDouble(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Valor inválido. " + prompt);
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void displayProducts(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i));
        }
    }

    public void displayProduct(Product p) {
        System.out.println(p);
    }

    public void displayOrder(com.store.model.OrderComponent order) {
        order.print(0);
    }
}