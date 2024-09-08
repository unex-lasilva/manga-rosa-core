import br.com.mangarosa.RadixSort;
import br.com.mangarosa.model.Pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe principal para executar a ordenação dos pedidos usando o algoritmo Radix Sort.
 */
public class Main {

    /**
     * Método principal que executa o programa.
     *
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Exibe o menu e obtém a escolha do usuário
            System.out.println("Escolha o número de pedidos para ordenar:");
            System.out.println("1. 10");
            System.out.println("2. 100");
            System.out.println("3. 1000");
            System.out.println("4. 10000");
            System.out.println("5. 100000");
            System.out.println("0. Sair");

            int choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("Saindo...");
                break;
            }

            // Define a quantidade de pedidos com base na escolha do usuário
            int numberOfPedidos;
            switch (choice) {
                case 1:
                    numberOfPedidos = 10;
                    break;
                case 2:
                    numberOfPedidos = 100;
                    break;
                case 3:
                    numberOfPedidos = 1000;
                    break;
                case 4:
                    numberOfPedidos = 10000;
                    break;
                case 5:
                    numberOfPedidos = 100000;
                    break;
                default:
                    System.out.println("Escolha inválida. Tente novamente.");
                    continue;
            }

            // Gera e ordena a lista de pedidos
            List<Pedido> pedidosList = generatePedidos(numberOfPedidos);
            RadixSort radixSort = new RadixSort();

            System.out.println("\nPedidos antes da ordenação:");
            pedidosList.forEach(System.out::println);

            List<Pedido> pedidosOrdenadosList = radixSort.sort(pedidosList);

            System.out.println("\nPedidos após a ordenação:");
            pedidosOrdenadosList.forEach(System.out::println);

            System.out.println("\nNúmero de comparações realizadas: " + radixSort.numberComparisons());
        }

        scanner.close();
    }

    /**
     * Gera uma lista de pedidos com identificadores aleatórios.
     *
     * @param numberOfPedidos Número de pedidos a serem gerados.
     * @return Lista de pedidos gerados.
     */
    private static List<Pedido> generatePedidos(int numberOfPedidos) {
        List<Pedido> pedidosList = new ArrayList<>();
        for (int i = 0; i < numberOfPedidos; i++) {
            String id = generateRandomId();
            pedidosList.add(new Pedido(id.toCharArray()));
        }
        return pedidosList;
    }

    /**
     * Gera um identificador de pedido aleatório.
     *
     * @return Identificador aleatório como uma String.
     */
    private static String generateRandomId() {
        // Gera um identificador aleatório de 13 caracteres
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(13);
        for (int i = 0; i < 13; i++) {
            int index = (int) (Math.random() * characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }
}
