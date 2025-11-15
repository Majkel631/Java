package org.stockmarket;


import org.stockmarket.stockmarket.Portfolio;
import org.stockmarket.stockmarket.Stock;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Tworzymy portfel z określoną gotówką
        System.out.print("Podaj początkową gotówkę: ");
        double initialCash = scanner.nextDouble();
        scanner.nextLine(); // czyszczenie bufora
        Portfolio portfolio = new Portfolio(initialCash);

        System.out.println("Twój portfel został utworzony z gotówką: " + portfolio.getCash());

        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Dodaj akcję do portfela");
            System.out.println("2. Pokaż stan portfela");
            System.out.println("3. Wyjście");
            System.out.print("Wybierz opcję: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // czyszczenie bufora

            switch (choice) {
                case 1:
                    System.out.print("Podaj symbol akcji: ");
                    String symbol = scanner.nextLine().trim();

                    System.out.print("Podaj nazwę firmy: ");
                    String name = scanner.nextLine().trim();

                    System.out.print("Podaj cenę początkową: ");
                    double price = scanner.nextDouble();

                    System.out.print("Podaj ilość akcji: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        Stock stock = new Stock(symbol, name, price);
                        portfolio.addStock(stock, quantity);
                        System.out.println("Dodano " + quantity + " akcji " + symbol + " do portfela.");
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        System.out.println("Błąd: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("Stan portfela:");
                    System.out.println("Gotówka: " + portfolio.getCash());
                    System.out.println("Liczba różnych akcji: " + portfolio.getHoldingsCount());

                    Stock[] stocks = portfolio.getStocks();
                    for (Stock s : stocks) {
                        System.out.println("Akcja: " + s.getName() + " (" + s.getSymbol() + "), ilość: " + portfolio.getStockQuantity(s));
                    }

                    System.out.println("Wartość akcji: " + portfolio.calculateStockValue());
                    System.out.println("Całkowita wartość portfela: " + portfolio.calculateTotalValue());
                    break;

                case 3:
                    running = false;
                    System.out.println("Koniec programu.");
                    break;

                default:
                    System.out.println("Niepoprawna opcja.");
            }
        }

        scanner.close();
    }
        }

