//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


import br.com.mangarosa.model.Pedido;
import br.com.mangarosa.model.CountingSort;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Pedido> pedidos = Arrays.asList(// Cria a lista de pedidos para teste
                new Pedido("X4T9J2B8K1M6Z".toCharArray()),
                new Pedido("G7D5N3V0L8Q4H".toCharArray()),
                new Pedido("P9R2F6W3X1J8K".toCharArray()),
                new Pedido("B8M7T4Z1C5Q9V".toCharArray()),
                new Pedido("H2L3K7R0X6J8N".toCharArray()),
                new Pedido("W5Q8M1T3P9R2F".toCharArray()),
                new Pedido("C4L9V2J7K1X8B".toCharArray()),
                new Pedido("N6T3P0M9R5Q2H".toCharArray()),
                new Pedido("J1K8X4V7B2M9L".toCharArray()),
                new Pedido("R3X6T8P1Q9J4M".toCharArray())
        );

        System.out.println("\nAntes de ordenar:\n");
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }

        CountingSort sorter = new CountingSort();
        List<Pedido> sortedPedidos = sorter.sort(pedidos);

        System.out.println("\nApós ordenar:\n");
        for (Pedido pedido : sortedPedidos) {
            System.out.println(pedido);
        }

        System.out.println("\nNúmero de comparações: " + sorter.numberComparisons());
    }
}
