package com.store.controller;

import com.store.datastructure.Stack;
import com.store.exception.ProductNotFoundException;
import com.store.exception.InsufficientStockException;
import com.store.model.*;
import com.store.repository.GenericRepository;
import com.store.util.MathUtil;
import com.store.util.StringUtil;
import com.store.view.StoreView;

import java.util.*;

public class StoreController {
    private final StoreView view;
    private final GenericRepository<Product> productRepo;
    private final Stack<UndoAction> undoStack;
    private final List<OrderComponent> orders;

    public StoreController() {
        view = new StoreView();
        productRepo = new GenericRepository<>();
        undoStack = new Stack<>();
        orders = new ArrayList<>();
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            try {
                int option = view.showMainMenu();
                switch (option) {
                    case 1:
                        manageProducts();
                        break;
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
                        exit = true;
                        break;
                    default:
                        view.showMessage("Opção inválida!");
                }
            } catch (Exception e) {
                view.showMessage("Erro: " + e.getMessage());
            }
        }
    }

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
                    back = true;
                    break;
                default:
                    view.showMessage("Opção inválida!");
            }
        }
    }

    private void addProduct() {
        view.showMessage("\n1. Livro  2. Eletrônico");
        int type = view.readInt("Tipo: ");
        String name = view.readString("Nome: ");
        double price = view.readDouble("Preço: ");
        Product product;
        if (type == 1) {
            String author = view.readString("Autor: ");
            String isbn = view.readString("ISBN: ");
            product = new Book(name, price, author, isbn);
        } else {
            String brand = view.readString("Marca: ");
            int warranty = view.readInt("Garantia (meses): ");
            product = new Electronics(name, price, brand, warranty);
        }
        productRepo.add(product);
        UndoAction action = new AddProductAction(product, productRepo);
        undoStack.push(action);
        view.showMessage("Produto adicionado: " + product.getId());
    }

    private void listProducts() {
        view.displayProducts(productRepo.getAll());
    }

    private void searchById() {
        String id = view.readString("ID do produto: ");
        Product p = productRepo.get(id);
        if (p != null) {
            view.displayProduct(p);
        } else {
            try {
                throw new ProductNotFoundException(id);
            } catch (ProductNotFoundException e) {
                view.showMessage(e.getMessage());
            }
        }
    }

    private void searchByKeyword() {
        String keyword = view.readString("Palavra-chave (regex permitida): ");
        List<Product> all = productRepo.getAll();
        boolean found = false;
        for (Product p : all) {
            if (p.matchesKeyword(keyword)) {
                view.displayProduct(p);
                found = true;
            }
        }
        if (!found)
            view.showMessage("Nenhum produto corresponde.");
    }

    private void applyDiscount() {
        String id = view.readString("ID do produto: ");
        Product p = productRepo.get(id);
        if (p == null) {
            view.showMessage("Produto não encontrado.");
            return;
        }
        if (p instanceof Discountable) {
            double perc = view.readDouble("Percentual de desconto: ");
            ((Discountable) p).applyDiscount(perc);
            view.showMessage("Desconto aplicado. Novo preço: R$ " + String.format("%.2f", p.getPrice()));
        } else {
            view.showMessage("Este produto não aceita desconto.");
        }
    }

    private void removeProduct() {
        String id = view.readString("ID do produto: ");
        Product p = productRepo.get(id);
        if (p == null) {
            view.showMessage("Produto não encontrado.");
            return;
        }
        productRepo.remove(id);
        UndoAction action = new RemoveProductAction(p, productRepo);
        undoStack.push(action);
        view.showMessage("Produto removido.");
    }

    private void createOrder() {
        CompositeOrder order = new CompositeOrder();
        boolean addMore = true;
        while (addMore) {
            view.showMessage("\n1. Adicionar item  2. Adicionar sub-pedido  3. Finalizar");
            int choice = view.readInt("Escolha: ");
            if (choice == 1) {
                String id = view.readString("ID do produto: ");
                Product p = productRepo.get(id);
                if (p == null) {
                    view.showMessage("Produto não encontrado.");
                    continue;
                }
                int qty = view.readInt("Quantidade: ");
                if (qty <= 0) {
                    view.showMessage("Quantidade inválida.");
                    continue;
                }
                order.add(new OrderItem(p, qty));
                view.showMessage("Item adicionado.");
            } else if (choice == 2) {
                view.showMessage("Criando sub-pedido...");
                // cria um sub-pedido recursivamente
                CompositeOrder subOrder = new CompositeOrder();
                fillOrder(subOrder);
                order.add(subOrder);
                view.showMessage("Sub-pedido adicionado.");
            } else if (choice == 3) {
                addMore = false;
            } else {
                view.showMessage("Opção inválida.");
            }
        }
        orders.add(order);
        view.showMessage("Pedido finalizado. Resumo:");
        view.displayOrder(order);
    }

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

    private void undoLastAction() {
        if (undoStack.isEmpty()) {
            view.showMessage("Nada a desfazer.");
            return;
        }
        UndoAction action = undoStack.pop();
        action.undo();
        view.showMessage("Ação desfeita: " + action.getDescription());
    }

    private void factorialDemo() {
        int n = view.readInt("Valor para fatorial (0-20): ");
        if (n < 0 || n > 20) {
            view.showMessage("Intervalo inválido.");
            return;
        }
        long result = MathUtil.factorial(n);
        view.showMessage(n + "! = " + result);
    }

    private void recursiveBinarySearchDemo() {
        List<Product> list = productRepo.getAll();
        if (list.isEmpty()) {
            view.showMessage("Nenhum produto para buscar.");
            return;
        }
        Product[] array = list.toArray(new Product[0]);
        Arrays.sort(array, Comparator.comparing(Product::getId));
        view.showMessage("Produtos ordenados por ID:");
        for (Product p : array) {
            System.out.println(p.getId() + " - " + p.getName());
        }
        String keyId = view.readString("ID para buscar: ");
        Product key = productRepo.get(keyId);
        if (key == null) {
            view.showMessage("ID não existe.");
            return;
        }
        int index = MathUtil.recursiveBinarySearch(array, key, 0, array.length - 1);
        if (index >= 0) {
            view.showMessage("Encontrado na posição " + index + ": " + array[index]);
        } else {
            view.showMessage("Não encontrado (inesperado).");
        }
    }
}