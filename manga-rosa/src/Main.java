//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

package br.com.mangarosa.main;

import br.com.mangarosa.model.Pedido;
import br.com.mangarosa.sort.PedidoSort;
import br.com.mangarosa.interfaces.Sort;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Criando alguns pedidos com IDs de 13 caracteres
        List<Pedido> pedidos = Arrays.asList(
            new Pedido("B1234567890AB".toCharArray()),
            new Pedido("A1234567890BC".toCharArray()),
            new Pedido("C1234567890AB".toCharArray()),
            new Pedido("B9876543210DC".toCharArray())
        );

        // Criando uma instância de PedidoSort
        Sort<Pedido> sorter = new PedidoSort();

        // Ordenando os pedidos
        List<Pedido> sortedPedidos = sorter.sort(pedidos);

        // Exibindo os pedidos ordenados
        System.out.println("Pedidos ordenados:");
        for (Pedido pedido : sortedPedidos) {
            System.out.println(pedido);
        }

        // Exibindo o número de comparações feitas
        System.out.println("Número de comparações: " + sorter.numberComparisons());
    }
}
