import br.com.mangarosa.model.Pedido;
import br.com.mangarosa.model.RadixSort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

        public static void main(String[] args) {
            // Gerar uma lista de pedidos com IDs alfanuméricos aleatórios
            List<Pedido> pedidos = generateRandomPedidos(10);  // Teste com 10 pedidos

            // Exibir os pedidos antes da ordenação
            System.out.println("Pedidos antes da ordenação:");
            for (Pedido pedido : pedidos) {
                System.out.println(pedido);
            }

            // Criar uma instância do algoritmo de ordenação
            RadixSort sorter = new RadixSort();

            // Ordenar a lista de pedidos
            List<Pedido> sortedPedidos = sorter.sort(pedidos);

            // Exibir os pedidos após a ordenação
            System.out.println("\nPedidos após a ordenação:");
            for (Pedido pedido : sortedPedidos) {
                System.out.println(pedido);
            }

            // Exibir o número de comparações realizadas
            System.out.println("\nNúmero de comparações realizadas: " + sorter.numberComparisons());
        }

        // Método para gerar pedidos aleatórios com IDs alfanuméricos (letras e números)
        private static List<Pedido> generateRandomPedidos(int size) {
            List<Pedido> pedidos = new ArrayList<>(size);
            Random random = new Random();
            String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // Conjunto de caracteres alfanuméricos

            for (int i = 0; i < size; i++) {
                char[] id = new char[13];
                for (int j = 0; j < 13; j++) {
                    id[j] = alphanumeric.charAt(random.nextInt(alphanumeric.length())); // Gera caracteres alfanuméricos aleatórios
                }
                pedidos.add(new Pedido(id));
            }

            return pedidos;
        }
    }

