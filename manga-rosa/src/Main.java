import br.com.mangarosa.model.Pedido;
import br.com.mangarosa.interfaces.Sort;

import br.com.mangarosa.model.RadixSortPedido;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        // Gera uma lista de pedidos com base na quantidade desejada
        List<Pedido> pedidos = gerarPedidosAleatorios(10);

        // Exibe a lista antes da ordenação
        System.out.println("Lista antes da ordenação:");
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }

        // Cria uma instância do RadixSortPedido e ordena a lista
        Sort<Pedido> sorter = new RadixSortPedido();
        List<Pedido> pedidosOrdenados = sorter.sort(pedidos);

        // Exibe a lista após a ordenação
        System.out.println("\nLista após a ordenação:");
        for (Pedido pedido : pedidosOrdenados) {
            System.out.println(pedido);
        }

        // Exibe o número de comparações realizadas
        System.out.println("\nNúmero de comparações: " + sorter.numberComparisons());
    }

    // Função que gera uma lista de pedidos aleatórios
    private static List<Pedido> gerarPedidosAleatorios(int quantidade) {
        List<Pedido> pedidos = new ArrayList<>(quantidade);
        Random random = new Random();
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        for (int i = 0; i < quantidade; i++) {
            // Gera um ID de exatamente 13 caracteres aleatórios
            char[] id = new char[13];
            for (int j = 0; j < 13; j++) {
                id[j] = caracteres.charAt(random.nextInt(caracteres.length()));
            }
            // Adiciona o novo pedido à lista
            pedidos.add(new Pedido(id));
        }

        return pedidos;
    }
}