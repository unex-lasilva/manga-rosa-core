package br.com.mangarosa;

import br.com.mangarosa.model.Pedido;
import br.com.mangarosa.model.Radix;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Pedidos para o teste
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido("A1B2C3D4E5F6G".toCharArray()));
        pedidos.add(new Pedido("H7J8K9L0M1N2O".toCharArray()));
        pedidos.add(new Pedido("P3Q4R5S6T7U8V".toCharArray()));
        pedidos.add(new Pedido("W9X0Y1Z2A3B4C".toCharArray()));
        pedidos.add(new Pedido("D5E6F7G8H9I0J".toCharArray()));
      
        


        // Lista de pedidos antes da ordenação
        System.out.println("Antes da ordenação:");
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }

        // Instanciando o Radix e ordenando a lista
        Radix radix = new Radix();
        List<Pedido> sortedPedidos = radix.sort(pedidos);

        // Exibindo a lista de pedidos ordenada
        System.out.println("\nApós a ordenação:");
        for (Pedido pedido : sortedPedidos) {
            System.out.println(pedido);
        }

        // Exibindo o número de comparações realizadas
        System.out.println("\nNúmero de comparações realizadas: " + radix.numberComparisons());
    }
}
