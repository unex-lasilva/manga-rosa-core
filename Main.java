package com.example;

public class Main {
    public static void main(String[] args) {
        Pedido[] pedidos = {
            new Pedido("A123456789012"),
            new Pedido("B987654321098"),
            new Pedido("C111111111111"),
            new Pedido("D222222222222")
        };

        RadixSort radixSort = new RadixSort();

        System.out.println("Antes da ordenação:");
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }

        radixSort.sort(pedidos);

        System.out.println("\nDepois da ordenação:");
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }
    }
}
