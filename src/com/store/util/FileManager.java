package com.store.util;

import com.store.model.*;
import com.store.repository.GenericRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <h1>Capítulo 15 – Arquivos, fluxos e serialização</h1>
 * Classe utilitária para persistir e recuperar dados da loja em arquivos.
 *
 * <h2>Funcionalidades abordadas:</h2>
 * <ul>
 * <li><b>Serialização binária:</b> salva e carrega objetos como {@code Product}
 * e
 * {@code OrderComponent} usando {@link ObjectOutputStream} e
 * {@link ObjectInputStream} (fluxos de objetos).</li>
 * <li><b>Arquivos de texto:</b> importa produtos de um arquivo CSV usando
 * {@link Scanner} com {@link File}.</li>
 * <li><b>Tratamento de exceções:</b> utiliza blocos {@code try}-with-resources
 * para garantir o fechamento automático dos fluxos.</li>
 * </ul>
 */
public class FileManager {

    /**
     * Salva o repositório de produtos em um arquivo binário.
     * <b>Capítulo 15:</b> {@link ObjectOutputStream} grava objetos serializados.
     *
     * @param repo     repositório contendo os produtos
     * @param filename nome do arquivo (ex: "produtos.dat")
     * @throws IOException se ocorrer erro de E/S
     */
    public static void saveProducts(GenericRepository<Product> repo, String filename) throws IOException {
        // try-with-resources fecha automaticamente o stream (Capítulo 11/15)
        try (ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(filename)))) {
            out.writeObject(new ArrayList<>(repo.getAll())); // grava uma cópia da lista
        }
    }

    /**
     * Carrega os produtos de um arquivo binário e os insere em um repositório.
     * <b>Capítulo 15:</b> {@link ObjectInputStream} lê objetos serializados.
     *
     * @param filename arquivo de origem
     * @return um repositório populado com os produtos lidos
     * @throws IOException            se o arquivo não existir ou houver erro de E/S
     * @throws ClassNotFoundException se a classe do objeto não for encontrada
     */
    public static GenericRepository<Product> loadProducts(String filename) throws IOException, ClassNotFoundException {
        File file = new File(filename);
        if (!file.exists()) {
            // Se o arquivo não existe, retorna repositório vazio (primeira execução)
            return new GenericRepository<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(file)))) {
            @SuppressWarnings("unchecked")
            List<Product> list = (List<Product>) in.readObject();
            GenericRepository<Product> repo = new GenericRepository<>();
            for (Product p : list) {
                repo.add(p);
            }
            return repo;
        }
    }

    /**
     * Salva a lista de pedidos em um arquivo binário.
     */
    public static void saveOrders(List<OrderComponent> orders, String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(filename)))) {
            out.writeObject(new ArrayList<>(orders));
        }
    }

    /**
     * Carrega os pedidos de um arquivo binário.
     */
    @SuppressWarnings("unchecked")
    public static List<OrderComponent> loadOrders(String filename) throws IOException, ClassNotFoundException {
        File file = new File(filename);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(file)))) {
            return (List<OrderComponent>) in.readObject();
        }
    }

    /**
     * Importa produtos a partir de um arquivo CSV (texto).
     * <b>Capítulo 15:</b> {@link Scanner} com {@link File} para leitura de texto.
     *
     * Formato esperado (uma linha por produto):
     * 
     * <pre>
     * tipo,nome,preço,atributo1,atributo2
     * </pre>
     * 
     * Exemplo:
     * 
     * <pre>
     * 1,Effective Java,45.0,Joshua Bloch,978-0134685991
     * 2,Notebook,3500.0,Dell,12
     * </pre>
     * 
     * O primeiro campo indica o tipo: 1 para Book, 2 para Electronics.
     *
     * @param filename caminho do arquivo CSV
     * @param repo     repositório onde os produtos serão adicionados
     * @return número de produtos importados
     * @throws IOException se houver erro ao abrir ou ler o arquivo
     */
    public static int importFromCSV(String filename, GenericRepository<Product> repo) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            throw new IOException("Arquivo não encontrado: " + filename);
        }
        int count = 0;
        try (Scanner scanner = new Scanner(file)) { // Capítulo 15: Scanner + File
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty())
                    continue;
                String[] parts = line.split(",");
                if (parts.length < 4) {
                    System.out.println("Linha inválida (ignorada): " + line);
                    continue;
                }
                int type;
                try {
                    type = Integer.parseInt(parts[0].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Tipo inválido na linha: " + line);
                    continue;
                }
                String name = parts[1].trim();
                double price;
                try {
                    price = Double.parseDouble(parts[2].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Preço inválido na linha: " + line);
                    continue;
                }
                Product product;
                if (type == 1) { // Book: autor, isbn
                    if (parts.length < 5) {
                        System.out.println("Dados insuficientes para Livro: " + line);
                        continue;
                    }
                    String author = parts[3].trim();
                    String isbn = parts[4].trim();
                    product = new Book(name, price, author, isbn);
                } else if (type == 2) { // Electronics: marca, garantia (meses)
                    if (parts.length < 5) {
                        System.out.println("Dados insuficientes para Eletrônico: " + line);
                        continue;
                    }
                    String brand = parts[3].trim();
                    int warranty;
                    try {
                        warranty = Integer.parseInt(parts[4].trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Garantia inválida: " + line);
                        continue;
                    }
                    product = new Electronics(name, price, brand, warranty);
                } else {
                    System.out.println("Tipo de produto desconhecido: " + type);
                    continue;
                }
                repo.add(product);
                count++;
            }
        }
        return count;
    }
}